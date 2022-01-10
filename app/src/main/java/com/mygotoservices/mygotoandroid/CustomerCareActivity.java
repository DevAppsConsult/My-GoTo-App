package com.mygotoservices.mygotoandroid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class CustomerCareActivity extends AppCompatActivity {
    EditText subjectText,textBody;
    Button loginBtn,callBack;
    ConstraintLayout constraintLayout;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_care);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Contact Support");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        subjectText = findViewById(R.id.subjectText);
        textBody = findViewById(R.id.textBody);
        loginBtn = findViewById(R.id.loginBtn);
        callBack = findViewById(R.id.callBack);
        constraintLayout = findViewById(R.id.mainLayout);
        progressDialog = new ProgressDialog(CustomerCareActivity.this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });

        callBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCallBack();
            }
        });

    }

    void requestCallBack()
    {
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();
        sendRequest(userTable.getUser_id(),"Call","","");
    }

    void sendMsg()
    {
        Realm realm = Realm.getDefaultInstance();
        UserTable userTable = realm.where(UserTable.class).findFirst();
        sendRequest(userTable.getUser_id(),"Message",subjectText.getText().toString().trim(),textBody.getText().toString().trim());
        subjectText.setText("");
        textBody.setText("");
    }

    void sendRequest(int userId, String type, String subject,String body)
    {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Submitting your request ...");
        progressDialog.setTitle("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<UserModule> login = endpoints.customerService(userId,type,subject,body);
        login.enqueue(new Callback<UserModule>() {
            @Override
            public void onResponse(Response<UserModule> response, Retrofit retrofit) {
                UserModule user = response.body();
                if(response.isSuccess() && response.code() == 200)
                {
                    Snackbar.make(constraintLayout, user.getResponseMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
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
