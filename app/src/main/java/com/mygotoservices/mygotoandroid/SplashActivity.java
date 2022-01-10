package com.mygotoservices.mygotoandroid;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.Modules.CategoryModule;
import com.mygotoservices.mygotoandroid.Modules.DashboardModule;
import com.mygotoservices.mygotoandroid.Modules.PlaceModule;
import com.mygotoservices.mygotoandroid.Modules.ProductModule;
import com.mygotoservices.mygotoandroid.Modules.TopArtisanModule;
import com.mygotoservices.mygotoandroid.Modules.UserModule;
import com.mygotoservices.mygotoandroid.RealmTables.CategoryTable;
import com.mygotoservices.mygotoandroid.RealmTables.ForgotPasswordTable;
import com.mygotoservices.mygotoandroid.RealmTables.PlaceTable;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;
import com.mygotoservices.mygotoandroid.Views.ViewData.SliderData;
import com.mygotoservices.mygotoandroid.Views.ViewData.TopArtisanData;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loadCategories();
        //loginAction();
    }


    void loadCategories()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
        Call<List<CategoryModule>> data = endpoints.listCategories();
        data.enqueue(new Callback<List<CategoryModule>>() {
            @Override
            public void onResponse(Response<List<CategoryModule>> response, Retrofit retrofit) {
                List<CategoryModule> categoryModuleList = response.body();
                loadPlaces(categoryModuleList);
            }
            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                loadCategories();
                Toast.makeText(getApplicationContext(),"Unable to reach MyGoTo network",Toast.LENGTH_LONG).show();
            }
        });

    }



    void loginAction() {
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
        status.getPermissionStatus().getEnabled();
        String token = status.getSubscriptionStatus().getUserId();
        if (token != null && userTable != null)
        {
            Call<UserModule> login = endpoints.updateSignal(userTable.getUser_id(), token);
            login.enqueue(new Callback<UserModule>() {
                @Override
                public void onResponse(Response<UserModule> response, Retrofit retrofit) {
                    if (response.isSuccess() && response.code() == 200) {
                        loadCategories();
                    } else {
                        loadCategories();
                    }
                   // new BackgroundTask().execute();


                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    //loginAction();
                    Toast.makeText(getApplicationContext(),"Unable to reach MyGoTo network",Toast.LENGTH_LONG).show();
                   // new BackgroundTask().execute();

                }
            });
        }
        else
        {
           // new BackgroundTask().execute();
            loadCategories();

        }
    }


    void loadPlaces(final List<CategoryModule> categoryModuleList)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);
        Call<List<PlaceModule>> data = endpoints.listPlaces();
        data.enqueue(new Callback<List<PlaceModule>>() {
            @Override
            public void onResponse(Response<List<PlaceModule>> response, Retrofit retrofit) {

                List<PlaceModule> placeModuleList = response.body();
                Realm realm = Realm.getDefaultInstance();
                for(int i = 0; i< placeModuleList.size(); i++)
                {
                    PlaceModule placeModule = placeModuleList.get(i);
                    System.out.println("Name "+placeModule.getCity());
                    realm.beginTransaction();
                        PlaceTable placeTable = new PlaceTable();
                        placeTable.setCity(placeModule.getCity());
                        placeTable.setCountry(placeModule.getCountry());
                        placeTable.setPlace_id(placeModule.getPlace_id());
                        placeTable.setPlace_latitude(placeModule.getPlace_latitude());
                        placeTable.setPlace_longitude(placeModule.getPlace_longitude());
                        placeTable.setPlace_name(placeModule.getPlace_name());
                    realm.copyToRealmOrUpdate(placeTable);
                    realm.commitTransaction();
                }

                for (int i = 0; i< categoryModuleList.size(); i++)
                {
                  CategoryModule categoryModule = categoryModuleList.get(i);
                    System.out.println("Name "+categoryModule.getName());
                  realm.beginTransaction();
                    CategoryTable categoryTable = new CategoryTable();
                    categoryTable.setCategory_id(categoryModule.getCategory_id());
                    categoryTable.setCharge_type(categoryModule.getCharge_type());
                    categoryTable.setDescription(categoryModule.getDescription());
                    categoryTable.setFile(categoryModule.getFile());
                    categoryTable.setName(categoryModule.getName());
                    categoryTable.setPrice(categoryModule.getPrice());
                    categoryTable.setUuid(categoryModule.getUuid());
                  realm.copyToRealmOrUpdate(categoryTable);
                  realm.commitTransaction();
                }
                Intent intent = new Intent(SplashActivity.this,ForgotPasswordActivity.class);
                    ForgotPasswordTable forgotPasswordTable = realm.where(ForgotPasswordTable.class).findFirst();
                    if(forgotPasswordTable == null)
                    {
                         intent = new Intent(SplashActivity.this,MainActivity.class);
                    }
                startActivity(intent);
                finish();
            }
            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                loadCategories();
                Toast.makeText(getApplicationContext(),"Unable to reach MyGoTo network",Toast.LENGTH_LONG).show();
            }
        });

    }


}
