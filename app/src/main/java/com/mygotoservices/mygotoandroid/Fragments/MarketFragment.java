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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alium.nibo.autocompletesearchbar.NiboAutocompleteSVProvider;
import com.alium.nibo.autocompletesearchbar.NiboPlacesAutoCompleteSearchView;
import com.alium.nibo.autocompletesearchbar.NiboSearchSuggestionItem;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.mygotoservices.mygotoandroid.Adaptors.MultiCustomAdapter;
import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.Modules.ArtisanModule;
import com.mygotoservices.mygotoandroid.Modules.CategoryModule;
import com.mygotoservices.mygotoandroid.Modules.Notification;
import com.mygotoservices.mygotoandroid.Modules.NotificationModule;
import com.mygotoservices.mygotoandroid.Modules.PlaceModule;
import com.mygotoservices.mygotoandroid.Modules.RetailerModule;
import com.mygotoservices.mygotoandroid.Modules.SliderModule;
import com.mygotoservices.mygotoandroid.R;
import com.mygotoservices.mygotoandroid.RealmTables.CategoryTable;
import com.mygotoservices.mygotoandroid.RealmTables.PlaceTable;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;
import com.mygotoservices.mygotoandroid.Views.ViewData.ArtisanData;
import com.mygotoservices.mygotoandroid.Views.ViewData.NotificationData;
import com.mygotoservices.mygotoandroid.Views.ViewData.ProductData;
import com.mygotoservices.mygotoandroid.Views.ViewData.TopArtisanData;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static android.content.Context.LOCATION_SERVICE;
import static com.mygotoservices.mygotoandroid.Fragments.ServicesFragment.searchString;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MarketFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MarketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarketFragment extends Fragment {
    private RecyclerView recyclerView;
    static public MultiCustomAdapter adapter;
    public static List<Object> albumList;
    static SwipeRefreshLayout mSwipeRefreshLayout;
    public static double mLongitude,mLatitude;
    private NiboPlacesAutoCompleteSearchView mAutocompletesearchbar;
    Realm realm;
    private ShimmerFrameLayout mShimmerViewContainer;
    static int categoryId = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MarketFragment() {
        // Required empty public constructor

        mLatitude = 0.0000;
        mLongitude = 0.0000;
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MarketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MarketFragment newInstance(String param1, String param2) {
        MarketFragment fragment = new MarketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        View view = inflater.inflate(R.layout.fragment_market, container, false);




        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(getActivity(),permissions,200);
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

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

        albumList = new ArrayList<>();
        adapter = new MultiCustomAdapter(getActivity(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        loadServices();

        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onStart() {
        super.onStart();
    }



    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if(requestCode == 200)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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


    void loadServices()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
        realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();


        Call<NotificationModule> data = endpoints.getMyNotification(userTable.getUser_id());



        data.enqueue(new Callback<NotificationModule>() {
            @Override
            public void onResponse(Response<NotificationModule> response, Retrofit retrofit) {
                albumList.clear();

                NotificationModule artisanModuleList = response.body();
                if(response.code() == 200 && response.isSuccess())
                {

                    for (int i = 0; i < artisanModuleList.getData().size(); i++) {
                        Notification notification = artisanModuleList.getData().get(i);
                        NotificationData notificationData = new NotificationData();
                        notificationData.setBooking_id(notification.getBooking_id());
                        notificationData.setCreated_at(notification.getCreated_at());
                        notificationData.setId(notification.getId());
                        notificationData.setNotification(notification.getNotification());
                        notificationData.setType(notification.getType());
                        notificationData.setUpdated_at(notification.getUpdated_at());
                        notificationData.setUser_id(notification.getUser_id());
                        notificationData.setUuid(notification.getUuid());
                        notificationData.setCreated(notification.getCreated());
                        albumList.add(notificationData);
                    }
                    adapter.notifyDataSetChanged();

                }
                onItemsLoadComplete();

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(),"An Error Occurred",Toast.LENGTH_LONG).show();
                onItemsLoadComplete();

            }
        });

    }


    void refreshItems() {
        // Load items
        // ...
        loadServices();
        // Load complete
    }

    void onItemsLoadComplete() {

        // Update the adapter and notify data set changed
        // ...
        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);

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


}
