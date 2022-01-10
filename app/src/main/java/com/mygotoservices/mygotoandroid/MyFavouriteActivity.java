package com.mygotoservices.mygotoandroid;

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
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;
import com.mygotoservices.mygotoandroid.Views.ViewData.ArtisanData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MyFavouriteActivity extends AppCompatActivity {
    public static List<Object> objectList;

    public MultiCustomAdapter adapter;
    static SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite);



        objectList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new MultiCustomAdapter(MyFavouriteActivity.this, objectList,MyFavouriteActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);

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
        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();

        Call<List<ArtisanModule>> data = endpoints.getMyFav(userTable.getUser_id());



        data.enqueue(new Callback<List<ArtisanModule>>() {
            @Override
            public void onResponse(Response<List<ArtisanModule>> response, Retrofit retrofit) {
                objectList.clear();
                objectList.clear();

                List<ArtisanModule> artisanModuleList = response.body();
                if(response.code() == 200 && response.isSuccess())
                {
                    for (int i = 0; i < artisanModuleList.size(); i++) {
                        ArtisanData artisanData = new ArtisanData();
                        ArtisanModule artisanModule = artisanModuleList.get(i);
                        artisanData.setId(artisanModule.getListing_id());
                        artisanData.setLocation(artisanModule.getProvider().getLocation()+", "+artisanModule.getProvider().getLandmark());
                        artisanData.setMainName(artisanModule.getName());
                        artisanData.setNumber_orders(artisanModule.getBookings()+" Orders");
                        artisanData.setRatings(artisanModule.getAvg_ratings());
                        artisanData.setType(artisanModule.getProvider().getVisible_name());
                        artisanData.setThumbnail(artisanModule.getProvider().getImage());
                        objectList.add(artisanData);
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
}
