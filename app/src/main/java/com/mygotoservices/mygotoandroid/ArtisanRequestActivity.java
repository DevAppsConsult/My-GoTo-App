package com.mygotoservices.mygotoandroid;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.Modules.UserModule;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ArtisanRequestActivity extends AppCompatActivity {
    EditText visibleName,location,landMark,description;
    String str_visibleName,str_location,str_landMark,str_description;
    Button pickLocation,loginBtn;
    double lon, lat;
    ConstraintLayout constraintLayout;
    ProgressDialog progressDialog;
    TextView textId;
    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artisan_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Send Your Request");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(ArtisanRequestActivity.this);

        visibleName = findViewById(R.id.visibleName);
        location = findViewById(R.id.location);
        landMark = findViewById(R.id.landMark);
        constraintLayout = findViewById(R.id.constraintLayout);
        description = findViewById(R.id.description);
        pickLocation = findViewById(R.id.pickLocation);
        loginBtn = findViewById(R.id.loginBtn);
        textId = findViewById(R.id.textId);

        pickLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                if (ActivityCompat.checkSelfPermission(ArtisanRequestActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ArtisanRequestActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                try {
                    startActivityForResult(builder.build(ArtisanRequestActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }


            }
        });
        textId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                if (ActivityCompat.checkSelfPermission(ArtisanRequestActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ArtisanRequestActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                try {
                    startActivityForResult(builder.build(ArtisanRequestActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                String mError="";
                if(!validPassword(visibleName))
                {
                    mError = "Visible name is required";
                    error = true;
                }
                else
                    if(!validPassword(location))
                    {
                        mError = " Location is required";
                        error = true;

                    }
                    else
                        if(!validPassword(landMark))
                        {
                            mError = "Landmark is required";
                            error = true;

                        }
                        else
                            if(!validPassword(description))
                            {
                                mError = "Description is required";
                                error = true;
                            }
                            else
                                if(lon == 0.00)
                                {
                                    mError = "Kindly select a location";
                                    error = true;

                                }
                                else
                                    if(lat == 0.00 )
                                    {
                                        mError = "Kindly select a location";
                                        error = true;
                                    }

                if(error == false)
                {
                    str_description = description.getText().toString().trim();
                    str_landMark = landMark.getText().toString().trim();
                    str_location = location.getText().toString().trim();
                    str_visibleName = visibleName.getText().toString().trim();

                    sendRequest();
                }
                else
                {
                    //Toast.ma(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                    Toast.makeText(ArtisanRequestActivity.this,mError,Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //getActivity();

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, ArtisanRequestActivity.this);
                lon = place.getLatLng().longitude;
                lat = place.getLatLng().latitude;

            }
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

    boolean validPassword(EditText password)
    {
        if(password.getText().toString().trim().length() > 3)
            return  true;
        return false;
    }

    private void sendRequest()
    {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Submitting your request ...");
        progressDialog.setTitle("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<UserModule> login = endpoints.sendArtisanRequest(str_location,userTable.getUser_id(),str_landMark,str_visibleName,str_description,lon,lat);
        login.enqueue(new Callback<UserModule>() {
            @Override
            public void onResponse(Response<UserModule> response, Retrofit retrofit) {
                UserModule user = response.body();
                if(response.isSuccess() && response.code() == 200)
                {
                    if(user.getResponseCode() == 200)
                    {
                       Toast.makeText(ArtisanRequestActivity.this,user.getResponseMessage(),Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(ArtisanRequestActivity.this,MainActivity.class);
                       startActivity(intent);
                    }
                    else
                    {
                        Snackbar.make(constraintLayout, user.getResponseMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                }
                else
                {
                    Snackbar.make(constraintLayout, "Sorry an error was encountered. Error code "+response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Sorry an error was encountered",Toast.LENGTH_LONG).show();

            }
        });


    }

}
