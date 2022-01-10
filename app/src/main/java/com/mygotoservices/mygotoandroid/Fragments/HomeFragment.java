package com.mygotoservices.mygotoandroid.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.mygotoservices.mygotoandroid.Adaptors.MultiCustomAdapter;
import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.Modules.DashboardModule;
import com.mygotoservices.mygotoandroid.Modules.ProductModule;
import com.mygotoservices.mygotoandroid.Modules.SliderModule;
import com.mygotoservices.mygotoandroid.Modules.TopArtisanModule;
import com.mygotoservices.mygotoandroid.R;
import com.mygotoservices.mygotoandroid.Views.ViewData.ArtisanData;
import com.mygotoservices.mygotoandroid.Views.ViewData.FooterData;
import com.mygotoservices.mygotoandroid.Views.ViewData.MenuData;
import com.mygotoservices.mygotoandroid.Views.ViewData.SliderData;
import com.mygotoservices.mygotoandroid.Views.ViewData.TopArtisanData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    static public MultiCustomAdapter adapter;
    public static List<Object> albumList;
    static SwipeRefreshLayout mSwipeRefreshLayout;
    static public List<SliderModule> sliderModules;
    public static double mLongitude,mLatitude;
    Realm realm;
    private ShimmerFrameLayout mShimmerViewContainer;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
        mLatitude = 0.00;
        mLongitude = 0.00;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    LocationManager mLocationManager;


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
            String msg = "New Latitude: " + mLatitude + "New Longitude: " + mLongitude;
            // Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mLocationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(getActivity(),permissions,200);
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        else
        {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
                    10, mLocationListener);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });

        sliderModules = new ArrayList<>();
        albumList = new ArrayList<>();
        adapter = new MultiCustomAdapter(getActivity(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        SliderData  sliderData =    new SliderData();
        albumList.add(sliderData);
        //MenuData menuData = new MenuData();
        //albumList.add(menuData);
        adapter.notifyDataSetChanged();
        loadDashboard();

        return view;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults)
    {
        if(requestCode == 200)
        {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                //mGoogleMap.setMyLocationEnabled(true);

            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(getContext(), "permission denied", Toast.LENGTH_LONG).show();
            }
            return;
        }


    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void prepareAlbums() {


        TopArtisanData a = new TopArtisanData();
        albumList.add(a);

        a = new TopArtisanData();
        albumList.add(a);

        a = new TopArtisanData();
        albumList.add(a);

        a = new TopArtisanData();
        albumList.add(a);

        a = new TopArtisanData();
        albumList.add(a);

        a = new TopArtisanData();
        albumList.add(a);

        a = new TopArtisanData();
        albumList.add(a);

        a = new TopArtisanData();
        albumList.add(a);

        a = new TopArtisanData();
        albumList.add(a);

        a = new TopArtisanData();
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    void loadDashboard()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
        Call<DashboardModule> data = endpoints.dashboard();
        data.enqueue(new Callback<DashboardModule>() {
            @Override
            public void onResponse(Response<DashboardModule> response, Retrofit retrofit) {
                albumList.clear();
                sliderModules.clear();

                DashboardModule dashboardModule = response.body();
                sliderModules.addAll(dashboardModule.getAdverts());
                SliderData  sliderData =    new SliderData();
                albumList.add(sliderData);
                //MenuData menuData = new MenuData();
                //albumList.add(menuData);
                adapter.notifyDataSetChanged();
                FooterData myFooter = new FooterData();
                albumList.add(myFooter);

                List<TopArtisanModule> topArtisanModules = dashboardModule.getArtisans();

                for(int i = 0; i<topArtisanModules.size();i++)
                {
                    TopArtisanModule  topArtisanModule = topArtisanModules.get(i);
                    //Toast.makeText()
                    //Toast.makeText(getActivity(),topArtisanModule.getListing_id()+"",Toast.LENGTH_LONG).show();
                    TopArtisanData topArtisanData = new TopArtisanData();
                    topArtisanData.setBookings(topArtisanModule.getBookings());
                    topArtisanData.setImage(topArtisanModule.getImage());
                    topArtisanData.setListing_id(topArtisanModule.getListing_id());
                    topArtisanData.setName(topArtisanModule.getName());
                    topArtisanData.setRating(topArtisanModule.getRating());
                    topArtisanData.setType(topArtisanModule.getType());
                    topArtisanData.setUser(topArtisanModule.getUser());

                    albumList.add(topArtisanData);
                    //
                }
                if(topArtisanModules.size() > 2)
                {
                    ProductModule productModule = new ProductModule();
                    albumList.add(productModule);
                }
                adapter.notifyDataSetChanged();
                onItemsLoadComplete();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                onItemsLoadComplete();

            }
        });

    }


    void refreshItems() {
        // Load items
        // ...
        loadDashboard();
        // Load complete
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
