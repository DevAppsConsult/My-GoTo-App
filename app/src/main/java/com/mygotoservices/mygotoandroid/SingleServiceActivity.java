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
import android.widget.Button;
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
import com.mygotoservices.mygotoandroid.Fragments.HomeFragment;
import com.mygotoservices.mygotoandroid.Modules.BookingModule;
import com.mygotoservices.mygotoandroid.Modules.ImageModule;
import com.mygotoservices.mygotoandroid.Modules.ListingModule;
import com.mygotoservices.mygotoandroid.Modules.RatingModule;
import com.mygotoservices.mygotoandroid.Modules.RetailerModule;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;
import com.mygotoservices.mygotoandroid.Views.ViewData.BookingData;
import com.mygotoservices.mygotoandroid.Views.ViewData.ImageData;
import com.mygotoservices.mygotoandroid.Views.ViewData.ProductData;
import com.mygotoservices.mygotoandroid.Views.ViewData.ReviewData;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SingleServiceActivity extends AppCompatActivity {
    static ListingModule listingModule;
    static int listingId;
    LinearLayout mainView;
    ImageView thumbnail;
    TextView myVisibleName,myLocation,categoryName,otherInfo,rate;
    Button scheduleBtn,likeBtn;
    RatingBar currentRating;
    RecyclerView imagesRecycle,commentsRecycler;
    Toolbar toolbar;
    static double lon,lat;
    static String location;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_service);
        Intent myIntent = getIntent();
        listingId = (int)myIntent.getExtras().get("listing_id");
        lon = (double)myIntent.getExtras().get("lon");
        lat = (double)myIntent.getExtras().get("lat");
        location = (String)myIntent.getExtras().get("currentLocation");
        System.out.print("Current listing "+listingId);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        likeBtn = findViewById(R.id.likeBtn);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        thumbnail = findViewById(R.id.thumbnail);
        myVisibleName = findViewById(R.id.visibleName);
        myLocation = findViewById(R.id.locationName);
        categoryName = findViewById(R.id.categoryName);
        otherInfo = findViewById(R.id.otherInformation);
        rate = findViewById(R.id.currentRate);
        scheduleBtn = findViewById(R.id.scheduledBtn);
        currentRating = findViewById(R.id.currentRating);
        imagesRecycle = findViewById(R.id.picturesRecycler);
        commentsRecycler = findViewById(R.id.commentsRecycler);
        mainView = findViewById(R.id.mainLayout);
        myVisibleName.setText("");
        myLocation.setText("");
        categoryName.setText("");
        otherInfo.setText("");
        rate.setText("");

        scheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SingleServiceActivity.this,BookServiceActivity.class);
                intent.putExtra("lon",lon);
                intent.putExtra("lat",lat);
                intent.putExtra("currentLocation",location);
                intent.putExtra("listing_id",listingModule.getListing_id());
                intent.putExtra("listing",listingModule);
                intent.putExtra("type","Scheduled");
                startActivity(intent);
            }
        });
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeArtisan();
            }
        });
        /*
        asapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleServiceActivity.this,BookServiceActivity.class);
                intent.putExtra("lon",lon);
                intent.putExtra("lat",lat);
                intent.putExtra("currentLocation",location);
                intent.putExtra("listing_id",listingModule.getListing_id());
                intent.putExtra("listing",listingModule);
                intent.putExtra("type","ASAP");

                startActivity(intent);
            }
        });
        */

        loadService();
    }
    static int count = 0;



    void loadService()
    {
        count++;
        final ProgressDialog progressDialog = new ProgressDialog(SingleServiceActivity.this);
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

                    RequestOptions options = new RequestOptions()
                            .placeholder(R.drawable.goicon)
                            .error(R.drawable.goicon)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .priority(Priority.HIGH);

                    listingModule = response.body();
                    if(listingModule.getImages().size()>0)
                    {
                        Glide.with(SingleServiceActivity.this).load(ApiLocation.getImage()+""+listingModule.getImages().get(0).getImage())
                                .thumbnail(0.5f)
                                .apply(options)
                                .into(thumbnail);
                    }
                    else
                    {
                        Glide.with(SingleServiceActivity.this).load(ApiLocation.getImage()+""+listingModule.getProvider().getImage())
                                .thumbnail(0.5f)
                                .apply(options)
                                .into(thumbnail);

                    }
                    myVisibleName.setText(listingModule.getProvider().getVisible_name());
                    myLocation.setText(listingModule.getProvider().getLocation()+", "+listingModule.getProvider().getLandmark());
                    categoryName.setText(listingModule.getName());
                    otherInfo.setVisibility(View.GONE);
                    String currency = "GhC";
                    if(!listingModule.getCurrency().equals("") && !listingModule.getCurrency().equals(" "))
                    {
                        currency = listingModule.getCurrency();
                    }
                    if(listingModule.getCategory().getCharge_type().equals("Hourly"))
                    {
                        rate.setText("Min. Rate :"+listingModule.getCategory().getPrice()+" "+currency);
                    }
                    else
                    {
                        rate.setText("Min. Rate :"+listingModule.getCategory().getPrice()+" "+currency);
                    }
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

                    //imagesRecycle = findViewById(R.id.picturesRecycler);
                    //commentsRecycler = findViewById(R.id.commentsRecycler);

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


    private void likeArtisan()
    {
        final ProgressDialog progressDialog = new ProgressDialog(SingleServiceActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Completing request ...");
        progressDialog.setTitle("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JSONArray jsonArray = new JSONArray();
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);


        Call<BookingModule> login = endpoints.addToFav(userTable.getUser_id(),listingId,userTable.getUser_id());
        login.enqueue(new Callback<BookingModule>() {
            @Override
            public void onResponse(Response<BookingModule> response, Retrofit retrofit) {
                BookingModule user = response.body();
                if(response.code() == 200 && user.getResponseCode().equals("200"))
                {
                    Toast.makeText(SingleServiceActivity.this,"This listing has been added to your favourites",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SingleServiceActivity.this,"Sorry, Try again later",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SingleServiceActivity.this,"Sorry add to favourites failed",Toast.LENGTH_LONG).show();

            }
        });

    }

}
