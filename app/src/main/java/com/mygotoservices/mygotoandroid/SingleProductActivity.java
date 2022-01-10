package com.mygotoservices.mygotoandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.mygotoservices.mygotoandroid.Adaptors.MultiCustomAdapter;
import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.Modules.ImageModule;
import com.mygotoservices.mygotoandroid.Modules.ListingModule;
import com.mygotoservices.mygotoandroid.Modules.RatingModule;
import com.mygotoservices.mygotoandroid.RealmTables.CartDetailTable;
import com.mygotoservices.mygotoandroid.Views.ViewData.ImageData;
import com.mygotoservices.mygotoandroid.Views.ViewData.ReviewData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SingleProductActivity extends AppCompatActivity {
    static ListingModule listingModule;
    static int listingId;
    LinearLayout mainView;
    ImageView thumbnail;
    TextView unitPrice,description,name;
    EditText quantity;
    Button addToCart,buyNowBtn;
    RatingBar currentRating;
    RecyclerView imagesRecycle,commentsRecycler;
    Toolbar toolbar;
    static double lon,lat;
    static String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Intent myIntent = getIntent();
        listingId = (int)myIntent.getExtras().get("listing_id");
        lon = (double)myIntent.getExtras().get("lon");
        lat = (double)myIntent.getExtras().get("lat");
        location = (String)myIntent.getExtras().get("currentLocation");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        thumbnail = findViewById(R.id.thumbnail);
        unitPrice = findViewById(R.id.unitPrice);
        //myLocation = findViewById(R.id.locationName);
        description = findViewById(R.id.aboutContent);
        name = findViewById(R.id.itemName);
        //categoryName = findViewById(R.id.categoryName);
        //otherInfo = findViewById(R.id.otherInformation);
        //rate = findViewById(R.id.currentRate);
        addToCart = findViewById(R.id.addToCartBtn);
        buyNowBtn = findViewById(R.id.buyNowBtn);
        currentRating = findViewById(R.id.currentRating);
        imagesRecycle = findViewById(R.id.picturesRecycler);
        commentsRecycler = findViewById(R.id.commentsRecycler);
        mainView = findViewById(R.id.mainLayout);
        quantity = findViewById(R.id.quantity);
        unitPrice.setText("");
        name.setText("");
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkInCart())
                {
                    addToCart();
                }
                else
                {
                    updateCart();
                }
                Snackbar.make(v, "Your cart has been updated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                initVoid();

            }
        });

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkInCart())
                {
                    addToCart();
                }
                else
                {
                    updateCart();
                }
                initVoid();
                //checkout
                goToCart();

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.icons8_checkout_100);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<CartDetailTable> cartDetailTableRealmResults = realm.where(CartDetailTable.class).findAll();
        if(!cartDetailTableRealmResults.isEmpty())
        {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToCart();
                }
            });
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"Sorry you dont have any item in your cart",Toast.LENGTH_LONG).show();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadService();
        initVoid();
      }

    private void initVoid()
    {
        if(checkInCart())
        {
            buyNowBtn.setText("Check Out");
            addToCart.setText("Update QTY");
            quantity.setText(getCartDetail().getQuantity()+"");
        }

    }

    private void addToCart()
    {
        int qty;

        if(quantity.getText().toString().trim() != "")
        {
            qty = Integer.parseInt(quantity.getText().toString().trim());
        }
        else
        {
            qty =1;
        }
        if(qty > 0)
        {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            CartDetailTable cartDetailTable = new CartDetailTable();
            cartDetailTable.setId(listingId);
            cartDetailTable.setListing_id(listingId);
            cartDetailTable.setQuantity(qty);
            cartDetailTable.setUnit_price(listingModule.getUnit_price());
            cartDetailTable.setProvider_id(listingModule.getProvider().getProvider_id());
            cartDetailTable.setSub_total(cartDetailTable.getUnit_price() * cartDetailTable.getQuantity());

            cartDetailTable.setName(listingModule.getName());
            cartDetailTable.setFile_name("");
            if(listingModule.getImages().size() > 0)
                cartDetailTable.setFile_name(listingModule.getImages().get(0).getImage());

            realm.copyToRealmOrUpdate(cartDetailTable);
            realm.commitTransaction();
        }
        initVoid();
    }

    private void updateCart()
    {
        int qty = 0;
        if(quantity.getText().toString().trim() != "")
        {
            qty = Integer.parseInt(quantity.getText().toString().trim());
        }
        Realm realm = Realm.getDefaultInstance();
        CartDetailTable cartDetailTable = getCartDetail();
        realm.beginTransaction();
        if(qty == 0)
        {
            cartDetailTable.deleteFromRealm();
        }
        else
        {
            cartDetailTable.setQuantity(qty);
            cartDetailTable.setUnit_price(listingModule.getUnit_price());
            cartDetailTable.setProvider_id(listingModule.getProvider().getProvider_id());
            cartDetailTable.setName(listingModule.getName());
            cartDetailTable.setFile_name("");
            if(listingModule.getImages().size() > 0)
            cartDetailTable.setFile_name(listingModule.getImages().get(0).getImage());
            cartDetailTable.setSub_total(cartDetailTable.getUnit_price() * cartDetailTable.getQuantity());
            realm.copyToRealmOrUpdate(cartDetailTable);
        }
        realm.commitTransaction();
        initVoid();
    }

    private CartDetailTable getCartDetail()
    {

        Realm realm = Realm.getDefaultInstance();
        CartDetailTable cartDetailTable = realm.where(CartDetailTable.class).equalTo("listing_id",listingId).findFirst();
        return  cartDetailTable;

    }

    private boolean checkInCart()
    {
        boolean inCart = false;

        Realm realm = Realm.getDefaultInstance();
        CartDetailTable cartDetailTable = realm.where(CartDetailTable.class).equalTo("listing_id",listingId).findFirst();
        if(cartDetailTable != null)
        {
            inCart = true;
        }

        return inCart;
    }

    private void goToCart()
    {
        Intent intent = new Intent(SingleProductActivity.this,CheckOutActivity.class);
        startActivity(intent);

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

    static int count = 0;



    void loadService()
    {
        count++;
        final ProgressDialog progressDialog = new ProgressDialog(SingleProductActivity.this);
        progressDialog.setMessage("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<ListingModule> data = endpoints.getListing(listingId);

        data.enqueue(new Callback<ListingModule>() {
            @Override
            public void onResponse(Response<ListingModule> response, Retrofit retrofit) {
                progressDialog.hide();
                progressDialog.dismiss();
                if(response.isSuccess() && response.code() == 200)
                {
                    listingModule = response.body();
                    RequestOptions options = new RequestOptions()
                            .placeholder(R.drawable.goicon)
                            .error(R.drawable.goicon)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .priority(Priority.HIGH);

                    Glide.with(SingleProductActivity.this).load(ApiLocation.getImage()+""+listingModule.getProvider().getImage())
                            .thumbnail(0.5f)
                            .apply(options)
                            .into(thumbnail);
                    String currency = "GhC";
                    if(!listingModule.getCurrency().equals("") && !listingModule.getCurrency().equals(" "))
                    {
                        currency = listingModule.getCurrency();
                    }
                    unitPrice.setText(listingModule.getUnit_price()+" "+currency);
                    name.setText(listingModule.getName());
//                    myLocation.setText(listingModule.getProvider().getLocation()+", "+listingModule.getProvider().getLandmark());
                    //categoryName.setText(listingModule.getName());
                    //otherInfo.setVisibility(View.GONE);
                    description.setText(listingModule.getDescription());
                    /*
                    if(listingModule.getCategory().getCharge_type().equals("Hourly"))
                    {
                        rate.setText("Rate :"+listingModule.getCategory().getPrice()+" "+currency+" per hour");
                    }
                    else
                    {
                        rate.setText("Rate :"+listingModule.getCategory().getPrice()+" "+currency+" per session");
                    }
                    */
                    currentRating.setNumStars(5);
                    currentRating.setMax(5);
                    currentRating.setRating(Float.valueOf(listingModule.getAvg_ratings()+""));

                    List<Object> reviews = new ArrayList<>();
                    List<Object> images = new ArrayList<>();
                    MultiCustomAdapter reviewAdapter = new MultiCustomAdapter(getApplication(), reviews);
                    MultiCustomAdapter imageAdapter = new MultiCustomAdapter(getApplication(), images);
                    toolbar.setTitle(listingModule.getName());
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplication(), 3);
                    imagesRecycle.setLayoutManager(mLayoutManager);
                    imagesRecycle.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
                    imagesRecycle.setItemAnimator(new DefaultItemAnimator());
                    imagesRecycle.setHasFixedSize(true);
                    imagesRecycle.setAdapter(imageAdapter);

                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplication(), 1);
                    commentsRecycler.setLayoutManager(layoutManager);
                    commentsRecycler.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
                    commentsRecycler.setItemAnimator(new DefaultItemAnimator());
                    commentsRecycler.setHasFixedSize(true);
                    commentsRecycler.setAdapter(reviewAdapter);

                    for(int i = 0; i < listingModule.getRatings().size(); i++)
                    {
                        RatingModule ratingModule = listingModule.getRatings().get(i);
                        ReviewData reviewData = new ReviewData();
                        reviewData.setBooking_id(ratingModule.getBooking_id());
                        reviewData.setDescription(ratingModule.getDescription());
                        reviewData.setListing_id(ratingModule.getListing_id());
                        reviewData.setRating(ratingModule.getRating());
                        reviewData.setUuid(ratingModule.getUuid());
                        reviewData.setRatings_id(ratingModule.getRatings_id());
                        reviewData.setUser_id(ratingModule.getUser_id());
                        reviews.add(reviewData);

                    }

                    for (int i = 0; i < listingModule.getImages().size(); i++ )
                    {
                        ImageModule imageModule = listingModule.getImages().get(i);
                        ImageData imageData = new ImageData();
                        imageData.setImage(imageModule.getImage());
                        images.add(imageData);
                    }


                }
                else
                {
                    if(count < 3)
                    {
                        loadService();
                    }
                    else
                    {
                        Snackbar.make(mainView, "Kindly check your internet connection", Snackbar.LENGTH_LONG)
                                .setAction("Ok", null).show();

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                progressDialog.hide();
                progressDialog.dismiss();

            }
        });

    }




    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
