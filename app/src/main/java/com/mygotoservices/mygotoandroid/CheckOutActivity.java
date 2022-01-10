package com.mygotoservices.mygotoandroid;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.mygotoservices.mygotoandroid.Adaptors.MultiCustomAdapter;
import com.mygotoservices.mygotoandroid.Fragments.HomeFragment;
import com.mygotoservices.mygotoandroid.RealmTables.CartDetailTable;
import com.mygotoservices.mygotoandroid.Views.ViewData.CartItemData;
import com.mygotoservices.mygotoandroid.Views.ViewData.CartTotalData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CheckOutActivity extends AppCompatActivity {

    public static List<Object> objectList;

    public MultiCustomAdapter adapter;
    static SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Check Out");
        setSupportActionBar(toolbar);

        objectList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new MultiCustomAdapter(CheckOutActivity.this, objectList,CheckOutActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<CartDetailTable> cartDetailTables = realm.where(CartDetailTable.class).notEqualTo("quantity",0).findAll();
        if(cartDetailTables.size() == 0)
        {
            Intent intent = new Intent(CheckOutActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }


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

    public static void loadData()
    {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<CartDetailTable> cartDetailTables = realm.where(CartDetailTable.class).notEqualTo("quantity",0).findAll();

        objectList.clear();
        System.out.println("Refreshing");

        for(int i =0; i<cartDetailTables.size(); i++)
        {

            CartItemData cartItemData = new CartItemData();
            CartDetailTable cartDetailTable = cartDetailTables.get(i);

            cartItemData.setQuantity(cartDetailTable.getQuantity());
            cartItemData.setDetail_status(cartDetailTable.getDetail_status());
            cartItemData.setId(cartDetailTable.getId());
            cartItemData.setListing_id(cartDetailTable.getListing_id());
            cartItemData.setName(cartDetailTable.getName());

            cartItemData.setOrder_id(cartDetailTable.getOrder_id());
            cartItemData.setOrder_status(cartDetailTable.getOrder_status());
            cartItemData.setProcessing_fee(cartDetailTable.getProcessing_fee());


            cartItemData.setProvider_id(cartDetailTable.getProvider_id());
            cartItemData.setSub_total(cartDetailTable.getSub_total());
            cartItemData.setUnit_price(cartDetailTable.getUnit_price());
            cartItemData.setUser_id(cartDetailTable.getUser_id());
            objectList.add(cartItemData);
        }
        CartTotalData cartTotalData = new CartTotalData();
        objectList.add(cartTotalData);
    }
//        adapter.notifyDataSetChanged();

}
