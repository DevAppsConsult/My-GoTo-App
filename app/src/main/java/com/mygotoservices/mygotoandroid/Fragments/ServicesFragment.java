package com.mygotoservices.mygotoandroid.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
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
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.mygotoservices.mygotoandroid.Adaptors.MultiCustomAdapter;
import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.BookServiceActivity;
import com.mygotoservices.mygotoandroid.Modules.ArtisanModule;
import com.mygotoservices.mygotoandroid.Modules.CategoryModule;
import com.mygotoservices.mygotoandroid.Modules.DashboardModule;
import com.mygotoservices.mygotoandroid.Modules.PlaceModule;
import com.mygotoservices.mygotoandroid.Modules.ProductModule;
import com.mygotoservices.mygotoandroid.Modules.SliderModule;
import com.mygotoservices.mygotoandroid.Modules.TopArtisanModule;
import com.mygotoservices.mygotoandroid.R;
import com.mygotoservices.mygotoandroid.RealmTables.CategoryTable;
import com.mygotoservices.mygotoandroid.RealmTables.PlaceTable;
import com.mygotoservices.mygotoandroid.Views.ViewData.ArtisanData;
import com.mygotoservices.mygotoandroid.Views.ViewData.MenuData;
import com.mygotoservices.mygotoandroid.Views.ViewData.SliderData;
import com.mygotoservices.mygotoandroid.Views.ViewData.TopArtisanData;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ServicesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesFragment extends Fragment {
    private RecyclerView recyclerView;
    static public MultiCustomAdapter adapter;
    public static List<Object> albumList;
    static SwipeRefreshLayout mSwipeRefreshLayout;
    static public List<SliderModule> sliderModules;
    public static String currentLocation ="";
    static boolean locationRequested = false;
    static String searchString;
    public static double mLongitude,mLatitude;
    ImageButton button,locationSpinner;
    ArrayList<PlaceModule> placeModules;
    ArrayList<CategoryModule> categoryModules;
    static int categoryId = 0;
    int PLACE_PICKER_REQUEST = 1;

    private NiboPlacesAutoCompleteSearchView mAutocompletesearchbar;
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

    public ServicesFragment() {
        currentLocation = "";
        locationRequested = false;
        mLatitude = 0.0000;
        mLongitude = 0.0000;
        // Required empty public constructor
    }
    LocationManager mLocationManager;


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String msg = "New Latitude: " + latitude + "New Longitude: " + longitude;
            if(!locationRequested)
            {
                mLatitude = latitude;
                mLongitude = longitude;
                //Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
                loadServices();
            }
            //getAddress(mLatitude, mLongitude);
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance(String param1, String param2) {
        ServicesFragment fragment = new ServicesFragment();
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
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        mLocationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        button =  view.findViewById(R.id.searchGeneral);
        locationSpinner = view.findViewById(R.id.locationSpinner);


        placeModules = new ArrayList<>();
        locationSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }


            }
        });
        Realm realm = Realm.getDefaultInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CategoryModule categoryModule = categoryModules.get(categorySpinner.getSelectedItemPosition());
                categoryId = categoryModule.getCategory_id();

                loadServices();

            }
        });
        RealmResults<PlaceTable> placeTableRealmResults = realm.where(PlaceTable.class).findAll();
        PlaceModule placeModule = new PlaceModule();
        placeModule.setCity("Current Location");
        placeModule.setCountry("");
        placeModule.setPlace_id(0);
        placeModule.setPlace_latitude(mLatitude+"");
        placeModule.setPlace_longitude(mLongitude+"");
        placeModule.setPlace_name("Current Location");
        placeModules.add(placeModule);

        for (int i = 0; i < placeTableRealmResults.size(); i++) {
            PlaceTable placeTable = placeTableRealmResults.get(i);
                placeModule = new PlaceModule();
                placeModule.setCity(placeTable.getCity());
                placeModule.setCountry(placeTable.getCountry());
                placeModule.setPlace_id(placeTable.getPlace_id());
                placeModule.setPlace_latitude(placeTable.getPlace_latitude());
                placeModule.setPlace_longitude(placeTable.getPlace_longitude());
                placeModule.setPlace_name(placeTable.getPlace_name());
            placeModules.add(placeModule);
        }

        ArrayAdapter<PlaceModule> myAdapter =
                new ArrayAdapter<PlaceModule>(getActivity(),  android.R.layout.simple_spinner_dropdown_item, placeModules);
        myAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);


        categorySpinner = (SearchableSpinner) view.findViewById(R.id.categorySpinner);
        categorySpinner.setTitle("Select a category");

        RealmResults<CategoryTable> categoryTableRealmResults = realm.where(CategoryTable.class).findAll();
        categoryModules = new ArrayList<>();

        CategoryModule categoryModule = new CategoryModule();
        categoryModule.setCategory_id(0);
        categoryModule.setCharge_type("");
        categoryModule.setDescription("");
        categoryModule.setFile("");
        categoryModule.setName("All Categories");
        categoryModule.setPrice(0.00);
        categoryModule.setUuid("");
        categoryModules.add(categoryModule);

        for (int i = 0; i < categoryTableRealmResults.size(); i++) {
            CategoryTable categoryTable = categoryTableRealmResults.get(i);
                categoryModule = new CategoryModule();
                categoryModule.setCategory_id(categoryTable.getCategory_id());
                categoryModule.setCharge_type(categoryTable.getCharge_type());
                categoryModule.setDescription(categoryTable.getDescription());
                categoryModule.setFile(categoryTable.getFile());
                categoryModule.setName(categoryTable.getName());
                categoryModule.setPrice(categoryTable.getPrice());
                categoryModule.setUuid(categoryTable.getUuid());
            categoryModules.add(categoryModule);
        }

        ArrayAdapter<CategoryModule> mAdapter =
                new ArrayAdapter<CategoryModule>(getActivity(),  android.R.layout.simple_spinner_dropdown_item, categoryModules);
        myAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(mAdapter);


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
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
        loadServices();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {

           // mListener.onFragmentInteraction(uri);
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

    @Override
    public void onStop() {
        super.onStop();

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
        searchString ="";

        if(searchString == null)
        {
            searchString = "";
        }
        Call<List<ArtisanModule>> data = endpoints.searchArtisan(searchString,mLongitude,mLatitude,categoryId);



        data.enqueue(new Callback<List<ArtisanModule>>() {
            @Override
            public void onResponse(Response<List<ArtisanModule>> response, Retrofit retrofit) {
                albumList.clear();
                sliderModules.clear();

                List<ArtisanModule> artisanModuleList = response.body();
                if(response.code() == 200 && response.isSuccess())
                {
                    for (int i = 0; i < artisanModuleList.size(); i++) {
                        ArtisanData artisanData = new ArtisanData();
                        ArtisanModule artisanModule = artisanModuleList.get(i);

                        artisanData.setImage(artisanModule.getImage());
                        artisanData.setId(artisanModule.getListing_id());
                        artisanData.setLocation(artisanModule.getProvider().getLocation()+", "+artisanModule.getProvider().getLandmark());
                        artisanData.setMainName(artisanModule.getName());
                        artisanData.setNumber_orders(artisanModule.getBookings()+" Orders");
                        artisanData.setRatings(artisanModule.getAvg_ratings());
                        artisanData.setType(artisanModule.getProvider().getVisible_name());
                        artisanData.setThumbnail(artisanModule.getProvider().getImage());
                        artisanData.setCategory(artisanModule.getCategory().getName());
                        System.out.println(artisanModule.getCategory().getName());
                        if(artisanModule.getImages().size() > 0)
                        {
                            artisanData.setImage(artisanModule.getImages().get(0).getImage());
                        }
                        albumList.add(artisanData);
                    }
                    //ProductModule productModule = new ProductModule();
                    //albumList.add(productModule);
                    adapter.notifyDataSetChanged();

                }
                onItemsLoadComplete();

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                onItemsLoadComplete();
            }
        });

    }
    SearchableSpinner categorySpinner;

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onActivityResult(int requestCode, final int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //getActivity();

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                mLongitude = place.getLatLng().longitude;
                mLatitude = place.getLatLng().latitude;

            }
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

}
