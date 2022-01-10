package com.mygotoservices.mygotoandroid;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.mygotoservices.mygotoandroid.Adaptors.MultiCustomAdapter;
import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.AppController.AppController;
import com.mygotoservices.mygotoandroid.Misc.AppHelper;
import com.mygotoservices.mygotoandroid.Misc.VolleyMultipartRequest;
import com.mygotoservices.mygotoandroid.Modules.BookingModule;
import com.mygotoservices.mygotoandroid.Modules.ImageModule;
import com.mygotoservices.mygotoandroid.Modules.ListingModule;
import com.mygotoservices.mygotoandroid.Modules.RatingModule;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;
import com.mygotoservices.mygotoandroid.Views.ViewData.ImageData;
import com.mygotoservices.mygotoandroid.Views.ViewData.ReviewData;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.wdullaer.materialdatetimepicker.time.Timepoint;
import com.wdullaer.materialdatetimepicker.time.TimepointLimiter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import worker8.com.github.radiogroupplus.RadioGroupPlus;

public class BookServiceActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    static ListingModule listingModule;
    static int listingId;
    LinearLayout mainView, manageDate, manageTime;
    ImageView thumbnail, image1, image2, image3;
    TextView myVisibleName, myLocation, categoryName, otherInfo, rate;
    Button buttonBook, editBtnImage1, editBtnImage2, editBtnImage3;
    EditText detailEditText;
    RatingBar currentRating;
    RecyclerView imagesRecycle, commentsRecycler;
    ProgressDialog progressDialog;
    TextView changeTime, changeDate;
    ProgressDialog pd;
    Toolbar toolbar;
    static double lon, lat;
    private PopupWindow popWindow;
    static String location, type, paymentChannel, startTime, phoneNumber, token;
    static int count = 0;
    static double cost = 0.00;
    Calendar date;
    static int paymentMethod;
    static int currentImage = 0;
    public static String path, redirectUrl;
    TextInputLayout tokenTIL;
    TimePickerDialog timePickerDialog;
    int PLACE_PICKER_REQUEST = 1;
    //private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_service);
        Intent myIntent = getIntent();
        redirectUrl = "";
        network = "";
        token = "";
        progressDialog = new ProgressDialog(BookServiceActivity.this);
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();
        if (userTable == null) {
            Intent intent = new Intent(BookServiceActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        listingId = (int) myIntent.getExtras().get("listing_id");
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        lon = (double) myIntent.getExtras().get("lon");
        lat = (double) myIntent.getExtras().get("lat");
        location = (String) myIntent.getExtras().get("currentLocation");

        type = (String) myIntent.getExtras().get("type");
        listingModule = (ListingModule) myIntent.getExtras().get("listing");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        detailEditText = findViewById(R.id.detailEditText);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        thumbnail = findViewById(R.id.thumbnail);
        myVisibleName = findViewById(R.id.visibleName);
        myLocation = findViewById(R.id.locationName);
        categoryName = findViewById(R.id.categoryName);
        otherInfo = findViewById(R.id.otherInformation);
        buttonBook = findViewById(R.id.buttonBook);
        currentRating = findViewById(R.id.currentRating);
        imagesRecycle = findViewById(R.id.picturesRecycler);
        commentsRecycler = findViewById(R.id.commentsRecycler);
        mainView = findViewById(R.id.mainLayout);
        manageDate = findViewById(R.id.manageDate);
        manageTime = findViewById(R.id.manageTime);
        changeDate = findViewById(R.id.changeDate);
        changeTime = findViewById(R.id.changeTime);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        editBtnImage1 = findViewById(R.id.editBtnImage1);
        editBtnImage2 = findViewById(R.id.editBtnImage2);
        editBtnImage3 = findViewById(R.id.editBtnImage3);
        cost = listingModule.getCategory().getPrice() * 1;
        path = "";
        editBtnImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImage = 3;
                ImagePicker
                        .cameraOnly()
                        .start(BookServiceActivity.this); // start image picker activity with request code
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImage = 3;
                ImagePicker
                        .cameraOnly()
                        .start(BookServiceActivity.this); // start image picker activity with request code
            }
        });

        editBtnImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImage = 2;
                ImagePicker
                        .cameraOnly()
                        .start(BookServiceActivity.this); // start image picker activity with request code
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImage = 2;
                ImagePicker
                        .cameraOnly()
                        .start(BookServiceActivity.this); // start image picker activity with request code
            }
        });

        editBtnImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImage = 1;
                //ImagePicker.cameraOnly().start(activity);
                ImagePicker
                        .cameraOnly()
                        .start(BookServiceActivity.this); // start image picker activity with request code
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImage = 1;
                //ImagePicker.cameraOnly().start(activity);
                ImagePicker
                        .cameraOnly()
                        .start(BookServiceActivity.this); // start image picker activity with request code
            }
        });

        myVisibleName.setText("");
        myLocation.setText("");
        categoryName.setText("");
        otherInfo.setText("");
        int month =currentDate.get(Calendar.MONTH) +1;
        startTime = currentDate.get(Calendar.YEAR) + "-" + month + "-" + currentDate.get(Calendar.DATE) + " " + currentDate.get(Calendar.HOUR_OF_DAY) + ":" + currentDate.get(Calendar.MINUTE) + ":00";
        String currentD = getDay(currentDate.get(Calendar.DAY_OF_WEEK)) + ", " + currentDate.get(Calendar.DATE) + " " + getMonth(currentDate.get(Calendar.MONTH)) + ", " + currentDate.get(Calendar.YEAR);
        String currentT = currentDate.get(Calendar.HOUR_OF_DAY) + " : " + currentDate.get(Calendar.MINUTE);
        changeDate.setText(currentD);
        changeTime.setText(currentT);
        manageDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });

        manageTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });

        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onShowHourlyPopup(v);
                if (location == null || location.equalsIgnoreCase("") || location.equalsIgnoreCase(" ")) {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    if (ActivityCompat.checkSelfPermission(BookServiceActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(BookServiceActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    //Location currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    //LatLngBounds latLng = new LatLngBounds();
                    //builder.setLatLngBounds(latLng);

                    try {
                        startActivityForResult(builder.build(BookServiceActivity.this), PLACE_PICKER_REQUEST);
                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }


                }
                else
                {
                    showDialog();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadService();
    }
    public String getDay (int dayWeek)
    {
        switch (dayWeek)
        {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return "Not Applicable";
        }
    }
    public String getMonth (int monthNumber)
    {
        switch (monthNumber)
        {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";

            default:
                return "Not Applicable";
        }
    }

    public void showDialog()
    {
        final Dialog dialog = new Dialog(BookServiceActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.complete_session_booking);

        TextView pricePerSession = dialog.findViewById(R.id.pricePerSession);
        pricePerSession.setText(listingModule.getCategory().getPrice() +" "+listingModule.getCurrency());

        final RadioGroupPlus radio_group_plus = dialog.findViewById(R.id.radio_group_plus);
        Button scheduledBtn = dialog.findViewById(R.id.scheduledBtn);
        final RadioButton cashPayment = dialog.findViewById(R.id.cashPayment);
        TextView cashPaymentText = dialog.findViewById(R.id.cashPaymentText);
        cashPaymentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashPayment.setChecked(true);

            }
        });

        final RadioButton mobileMoneyPayment = dialog.findViewById(R.id.mobileMoneyPayment);
        TextView mobileMoneyPaymentText = dialog.findViewById(R.id.mobileMoneyPaymentText);
        mobileMoneyPaymentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileMoneyPayment.setChecked(true);
            }
        });

        mobileMoneyPayment.setSelected(true);
        mobileMoneyPayment.setChecked(true);



        scheduledBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                switch (radio_group_plus.getCheckedRadioButtonId())
                {
                    case R.id.cashPayment:
                        paymentChannel = "cash";
                        paymentMethod = 1;
                        dialog.dismiss();
                        submitBooking();
                        break;
                    case R.id.mobileMoneyPayment:
                        paymentChannel = "mobileMoney";
                        paymentMethod = 2;
                        submitBooking();
                        break;

                    default:
                        paymentChannel = "cash";
                        paymentMethod = 1;
                        dialog.dismiss();
                        submitBooking();
                        break;
                }
                dialog.hide();

            }
        });


        dialog.show();

    }

    void showMobileMoneyDialog()
    {
        final Dialog dialog = new Dialog(BookServiceActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.complete_mobile_wallet_transaction);
        TextView estimatedCost = dialog.findViewById(R.id.estimatedCost);
        RadioGroupPlus radioGroupPlus = dialog.findViewById(R.id.radio_group_plus);
        final RadioButton mtnMobileMoney = dialog.findViewById(R.id.mtnMobileMoney);
        TextView mtnMobileMoneyText = dialog.findViewById(R.id.mtnMobileMoneyText);
        final RadioButton airtelMoney = dialog.findViewById(R.id.airtelMoney);
        TextView airtelMoneyText = dialog.findViewById(R.id.airtelMoneyText);
        final RadioButton tigoCash = dialog.findViewById(R.id.tigoCash);
        TextView tigoCashText = dialog.findViewById(R.id.tigoCashText);
        final RadioButton vodafoneCash = dialog.findViewById(R.id.vodafoneCash);
        TextView vodafoneCashText = dialog.findViewById(R.id.vodafoneCash);
        final EditText vodafoneToken = dialog.findViewById(R.id.vodafoneToken);
        final EditText mPhoneNumber = dialog.findViewById(R.id.phoneNumber);
        tokenTIL = dialog.findViewById(R.id.tokenTIL);
        Button scheduledBtn = dialog.findViewById(R.id.scheduledBtn);

        mtnMobileMoney.setChecked(true);
        estimatedCost.setText(cost+" GhC");
        mtnMobileMoney.setSelected(true);
        vodafoneToken.setEnabled(false);

        mtnMobileMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Mtn";
                vodafoneToken.setEnabled(false);

            }
        });

        mtnMobileMoneyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Mtn";
                mtnMobileMoney.setChecked(true);
                vodafoneToken.setEnabled(false);

            }
        });

        airtelMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Airtel";

            }
        });

        airtelMoneyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Airtel";
                airtelMoney.setChecked(true);
                vodafoneToken.setEnabled(false);

            }
        });


        tigoCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Tigo";
                vodafoneToken.setEnabled(false);

            }
        });

        tigoCashText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Tigo";
                tigoCash.setChecked(true);
                vodafoneToken.setEnabled(false);

            }
        });


        vodafoneCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Vodafone";
                vodafoneToken.setEnabled(true);

            }
        });

        vodafoneCashText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network = "Vodafone";
                vodafoneCash.setChecked(true);
                vodafoneToken.setEnabled(true);

            }
        });


        scheduledBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = false;
                if(network.equals("Vodafone"))
                {
                    token = vodafoneToken.getText().toString().trim();
                    if(token.length() > 4)
                    {
                        isValid = true;
                    }
                }
                else
                {
                    isValid = true;
                }
                if(mPhoneNumber.getText().toString().trim().length() > 8 && isValid)
                {

                    phoneNumber = mPhoneNumber.getText().toString().trim();
                    if(validateUsing_libphonenumber("233",phoneNumber))
                    {
                        submitMMBooking();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Kindly provide a valid phone number",Toast.LENGTH_LONG).show();
                }
                if(!isValid)
                {
                    Toast.makeText(getApplicationContext(),"Kindly provide a valid token",Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    static String network;
    void submitMMBooking()
    {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Submitting your booking request ..");
        progressDialog.setTitle("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();
        int user_id = 1;
        if(userTable != null)
        {
            user_id = userTable.getUser_id();
        }
        Call<BookingModule> login = endpoints.bookmMService(cost,user_id, paymentChannel, network, listingModule.getListing_id(),startTime,0,detailEditText.getText().toString().trim(),lon+"",lat+"",location,location,listingModule.getProvider_id(),listingModule.getName(),listingModule.getCategory().getPrice(),cost,phoneNumber,token);
        login.enqueue(new Callback<BookingModule>() {
            @Override
            public void onResponse(Response<BookingModule> response, Retrofit retrofit) {
                BookingModule user = response.body();
                if(response.isSuccess() && response.code() == 200)
                {
                    sendBookingImages();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Sorry booking failed with error code "+response.code(),Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Sorry booking failed",Toast.LENGTH_LONG).show();

            }
        });

    }


    void loadBooking(int listingId, final String date)
    {
        count++;
        final ProgressDialog progressDialog = new ProgressDialog(BookServiceActivity.this);
        progressDialog.setMessage("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<List<BookingModule>> data = endpoints.getBookings(listingId,date);

        data.enqueue(new Callback<List<BookingModule>>() {
            @Override
            public void onResponse(Response<List<BookingModule>> response, Retrofit retrofit) {
                progressDialog.hide();
                progressDialog.dismiss();
                List<BookingModule> bookingModules= response.body();
                Toast.makeText(BookServiceActivity.this,date,Toast.LENGTH_LONG).show();


                for (int i = 0; i < bookingModules.size(); i++ )
                {
                    List<String> duration = bookingModules.get(i).getDuration();

                    for(int j = 0; j< duration.size();j++)
                    {
                        String[] splt = duration.get(j).split("\\s+");
                        String[] timeSide = splt[1].split(":");
                        System.out.println("Adding time ");
                        System.out.println(splt[1]);
                        Timepoint timepoint = new Timepoint(Integer.parseInt(timeSide[0]),Integer.parseInt(timeSide[1]));
                        timepointList.add(timepoint);
                    }
                }

                timePickerDialog.vibrate(true);
                timePickerDialog.setDisabledTimes(timepointList.toArray(new Timepoint[timepointList.size()]));

                timePickerDialog.show(getFragmentManager(),"New Huh");


            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                progressDialog.hide();
                progressDialog.dismiss();

            }
        });

    }



    void submitBooking()
    {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Submitting your booking request ..");
        progressDialog.setTitle("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();
        int user_id = 1;
        if(userTable != null)
        {
            user_id = userTable.getUser_id();
        }

        Call<BookingModule> login = endpoints.bookService(cost,user_id, paymentChannel, network, listingModule.getListing_id(),startTime,0,detailEditText.getText().toString().trim(),lon+"",lat+"",location,location,listingModule.getProvider_id(),listingModule.getName(),listingModule.getCategory().getPrice(),cost);
        login.enqueue(new Callback<BookingModule>() {
            @Override
            public void onResponse(Response<BookingModule> response, Retrofit retrofit) {
                BookingModule user = response.body();
                if(response.isSuccess() && response.code() == 200)
                {

                    sendBookingImages();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Sorry booking failed with error code "+response.code(),Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Sorry booking failed",Toast.LENGTH_LONG).show();

            }
        });

    }


    private void decideAction()
    {

        Toast.makeText(getApplicationContext(),"Booking completed. The artisan will send you an estimate shortly",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(BookServiceActivity.this,ThankYouActivity.class);
        startActivity(intent);
        finish();

    }
    static boolean imageSet1 = false;
    static boolean imageSet2 = false;
    static boolean imageSet3 = false;


    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data)
    {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                location = place.getAddress()+"";
                lon = place.getLatLng().longitude;
                lat = place.getLatLng().latitude;
                Toast.makeText(this, "You selected "+place.getAddress(), Toast.LENGTH_LONG).show();
                showDialog();
            }
        }

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
           Image images = ImagePicker.getFirstImageOrNull(data);

            if(currentImage == 1)
            {
                imageSet1 = true;
                currentImage = 0;
                image1.setImageURI(Uri.parse(images.getPath()));
            }
            if(currentImage == 2)
            {
                imageSet2 = true;
                currentImage = 0;
                image2.setImageURI(Uri.parse(images.getPath()));
            }
            if(currentImage == 3)
            {
                imageSet3 = true;
                currentImage = 0;
                image3.setImageURI(Uri.parse(images.getPath()));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    List<Timepoint> timepointList = new ArrayList<>();


    public void showDateTimePicker()
    {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();

        Calendar now = Calendar.getInstance();

        timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);
                //Log.v(TAG, "The choosen one " + date.getTime());
                String currentD = getDay(date.get(Calendar.DAY_OF_WEEK))+", "+date.get(Calendar.DATE)+" "+getMonth(date.get(Calendar.MONTH) )+", "+date.get(Calendar.YEAR);
                String currentT = date.get(Calendar.HOUR_OF_DAY)+" : "+date.get(Calendar.MINUTE);
                changeDate.setText(currentD);
                changeTime.setText(currentT);
                int month =date.get(Calendar.MONTH) +1;

                startTime = date.get(Calendar.YEAR)+"-"+month+"-"+date.get(Calendar.DATE)+" "+date.get(Calendar.HOUR_OF_DAY)+":"+date.get(Calendar.MINUTE)+":00";

            }
        }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false);

        //timePickerDialog.show();

        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        date.set(year, monthOfYear, dayOfMonth);
                        monthOfYear++;
                        loadBooking(listingId,""+year+"-"+monthOfYear+"-"+dayOfMonth);

                        //timePickerDialog.setMinTime(new Timepoint(currentDate.get(Calendar.HOUR_OF_DAY),currentDate.get(Calendar.MINUTE)));
                    }
                }
                ,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMinDate(currentDate);
        dpd.vibrate(true);
        dpd.show(getFragmentManager(), "Datepickerdialog");


    }

    void loadService()
    {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.goicon)
                .error(R.drawable.goicon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        if(listingModule.getImages().size()>0)
        {
            Glide.with(BookServiceActivity.this).load(ApiLocation.getImage()+""+listingModule.getImages().get(0).getImage())
                    .thumbnail(0.5f)
                    .apply(options)
                    .into(thumbnail);
        }
        else
        {
            Glide.with(BookServiceActivity.this).load(ApiLocation.getImage()+""+listingModule.getProvider().getImage())
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
//                    listingModule.getUnit_price();
        currentRating.setNumStars(5);
        currentRating.setMax(5);
        currentRating.setRating(Float.valueOf(listingModule.getAvg_ratings()+""));

        List<Object> reviews = new ArrayList<>();
        List<Object> images = new ArrayList<>();
        MultiCustomAdapter reviewAdapter = new MultiCustomAdapter(getApplication(), reviews);
        MultiCustomAdapter imageAdapter = new MultiCustomAdapter(getApplication(), images);
        toolbar.setTitle(listingModule.getName());

    }

    private boolean validateUsing_libphonenumber(String countryCode, String phNumber)
    {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        String isoCode = phoneNumberUtil.getRegionCodeForCountryCode(Integer.parseInt(countryCode));
        Phonenumber.PhoneNumber mPhoneNumber = null;
        try {
            //phoneNumber = phoneNumberUtil.parse(phNumber, "IN");  //if you want to pass region code
            mPhoneNumber = phoneNumberUtil.parse(phNumber, isoCode);
        } catch (NumberParseException e) {
            System.err.println(e);
        }
        boolean isValid = phoneNumberUtil.isValidNumber(mPhoneNumber);
        if (isValid) {
            phoneNumber = phoneNumberUtil.format(mPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            return true;
        } else {
            //Toast.makeText(this, "Phone Number is Invalid " + phoneNumber, Toast.LENGTH_LONG).show();
            return false;
        }
    }


    private void sendBookingImages()
    {

        pd  = ProgressDialog.show(BookServiceActivity.this,"Please wait ..."," Uploading image(s) ", true);
        pd.show();

        String url = ApiLocation.getLocation() + "mobile/booking-image";
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                decideAction();
                pd.hide();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(pd != null)
                {
                    pd.hide();
                }

                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
                    } else if (error.getClass().equals(NoConnectionError.class)) {
                        errorMessage = "Failed to connect server";
                    }
                } else {
                    String result = new String(networkResponse.data);
                    try {
                        JSONObject response = new JSONObject(result);
                        String status = response.getString("status");
                        String message = response.getString("message");

                        Log.e("Error Status", status);
                        Log.e("Error Message", message);

                        if (networkResponse.statusCode == 404) {
                            errorMessage = "Resource not found";
                        } else if (networkResponse.statusCode == 401) {
                            errorMessage = message+" Please login again";
                        } else if (networkResponse.statusCode == 400) {
                            errorMessage = message+ " Check your inputs";
                        } else if (networkResponse.statusCode == 500) {
                            errorMessage = message+" Something is getting wrong";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Error", errorMessage);
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Realm realm = Realm.getDefaultInstance();
                UserTable userTable = realm.where(UserTable.class).findFirst();
                Map<String, String> params = new HashMap<>();
                params.put("api_token", "gh659gjhvdyudo973823tt9gvjf7i6ric75r76");
                params.put("user_id", userTable.getUser_id()+"");
                params.put("booking_id", userTable.getUser_id()+"");
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                try {
                    if(imageSet1)
                        params.put("file1", new DataPart("file_avatar1.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), image1.getDrawable()), "image/jpeg"));
                }
                catch (Exception e)
                {

                }
                try {
                    if(imageSet2)
                        params.put("file2", new DataPart("file_avatar2.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), image2.getDrawable()), "image/jpeg"));
                }
                catch (Exception e)
                {

                }

                try {
                    if(imageSet3)
                        params.put("file3", new DataPart("file_avatar3.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), image3.getDrawable()), "image/jpeg"));
                }
                catch (Exception e)
                {

                }

                return params;
            }
        };

        multipartRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 999999;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }

        });

        AppController.getInstance().addToRequestQueue(multipartRequest);

        //  AppController.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
