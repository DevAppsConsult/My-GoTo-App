package com.mygotoservices.mygotoandroid;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.mygotoservices.mygotoandroid.Adaptors.MultiCustomAdapter;
import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.Modules.ArtisanModule;
import com.mygotoservices.mygotoandroid.Modules.CategoryModule;
import com.mygotoservices.mygotoandroid.Modules.DashboardModule;
import com.mygotoservices.mygotoandroid.Modules.ListingModule;
import com.mygotoservices.mygotoandroid.Modules.MyBooking;
import com.mygotoservices.mygotoandroid.Modules.ProductModule;
import com.mygotoservices.mygotoandroid.Modules.TopArtisanModule;
import com.mygotoservices.mygotoandroid.RealmTables.CartDetailTable;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;
import com.mygotoservices.mygotoandroid.Views.ViewData.ArtisanData;
import com.mygotoservices.mygotoandroid.Views.ViewData.BookingData;
import com.mygotoservices.mygotoandroid.Views.ViewData.CartItemData;
import com.mygotoservices.mygotoandroid.Views.ViewData.CartTotalData;
import com.mygotoservices.mygotoandroid.Views.ViewData.SliderData;
import com.mygotoservices.mygotoandroid.Views.ViewData.TopArtisanData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MyBookingsActivity extends AppCompatActivity {
    public static List<Object> objectList;

    public MultiCustomAdapter adapter;
    static SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        objectList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new MultiCustomAdapter(MyBookingsActivity.this, objectList,MyBookingsActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        Realm realm = Realm.getDefaultInstance();


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
        loadData();
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


    void refreshItems() {
        // Load items
        // ...
        loadData();
        // Load complete
        adapter.notifyDataSetChanged();
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);
    }


    void loadData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();
        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
        Call<List<MyBooking>> data = endpoints.myBookings(userTable.getUser_id());
        data.enqueue(new Callback<List<MyBooking>>() {
            @Override
            public void onResponse(Response<List<MyBooking>> response, Retrofit retrofit) {
                List<MyBooking> myBookingList = response.body();
                for(int i = 0; i <myBookingList.size(); i++ )
                {
                    MyBooking myBooking = myBookingList.get(i);
                    BookingData bookingData = new BookingData();
                    bookingData.setAmount(myBooking.getAmount());
                    bookingData.setOrder_code(myBooking.getOrder_code());
                    bookingData.setUser_id(myBooking.getUser_id());
                    bookingData.setDelivered_at(myBooking.getDelivered_at());
                    bookingData.setDelivery_charge(myBooking.getDelivery_charge());
                    bookingData.setDelivery_information(myBooking.getDelivery_information());
                    bookingData.setDelivery_method(myBooking.getDelivery_method());
                    bookingData.setNetwork(myBooking.getNetwork());
                    bookingData.setOrder_id(myBooking.getOrder_id());
                    bookingData.setPayment_means(myBooking.getPayment_means());
                    bookingData.setPayment_status(myBooking.getPayment_status());
                    bookingData.setPhone(myBooking.getPhone());
                    bookingData.setProcessing_fee(myBooking.getProcessing_fee());
                    bookingData.setStatus(myBooking.getStatus());
                    bookingData.setToken(myBooking.getToken());
                    bookingData.setTransaction_id(myBooking.getTransaction_id());
                    bookingData.setTransaction_reference(myBooking.getTransaction_reference());
                    bookingData.setCreated_at(myBooking.getCreated_at());
                    bookingData.setDescription(myBooking.getDescription());
                    bookingData.setId(myBooking.getId());
                    bookingData.setLandmark(myBooking.getLandmark());
                    bookingData.setLat(myBooking.getLat());
                    bookingData.setListing_id(myBooking.getListing_id());
                    bookingData.setLocation(myBooking.getLocation());
                    bookingData.setLon(myBooking.getLon());
                    bookingData.setNumber_hours(myBooking.getNumber_hours());
                    bookingData.setProvider_id(myBooking.getProvider_id());
                    bookingData.setRatings(myBooking.getRatings());
                    bookingData.setStart_time(myBooking.getStart_time());
                    bookingData.setTotal_charge(myBooking.getTotal_charge());
                    bookingData.setUuid(myBooking.getUuid());
                    bookingData.setCategory(myBooking.getListing().getCategory());
                    CategoryModule categoryModule = new CategoryModule();
                    categoryModule.setUuid(myBooking.getListing().getCategory().getUuid());
                    categoryModule.setPrice(myBooking.getListing().getCategory().getPrice());
                    categoryModule.setName(myBooking.getListing().getCategory().getName());
                    categoryModule.setFile(myBooking.getListing().getCategory().getFile());
                    categoryModule.setDescription(myBooking.getListing().getCategory().getDescription());
                    categoryModule.setCharge_type(myBooking.getListing().getCategory().getCharge_type());
                    categoryModule.setCategory_id(myBooking.getListing().getCategory().getCategory_id());
                    //bookingData.setCategory(categoryModule);
                    bookingData.setImages(myBooking.getImages());
                    bookingData.setRated(myBooking.getRated());
                    ArtisanModule listingModule = myBooking.getListing();
                    ArtisanData artisanData = new ArtisanData();
                    artisanData.setId(listingModule.getListing_id());
                    artisanData.setLocation(listingModule.getProvider().getLocation()+", "+listingModule.getProvider().getLandmark());
                    artisanData.setMainName(listingModule.getName());
                    artisanData.setNumber_orders(listingModule.getBookings()+" Orders");
                    artisanData.setRatings(listingModule.getAvg_ratings());
                    artisanData.setType(listingModule.getProvider().getVisible_name());
                    artisanData.setThumbnail(listingModule.getProvider().getImage());

                    bookingData.setListing(artisanData);

                    objectList.add(bookingData);
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


}
