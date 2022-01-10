package com.mygotoservices.mygotoandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.Modules.UserModule;
import com.mygotoservices.mygotoandroid.RealmTables.ForgotPasswordTable;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ForgotPasswordActivity extends AppCompatActivity {
    TextInputEditText verificationCode,password,confirmPassword;
    Button sendForgotCodeBtn;
    TextView resendActivationCode,backToLogin;
    ProgressDialog progressDialog;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        verificationCode = findViewById(R.id.verificationCode);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        sendForgotCodeBtn = findViewById(R.id.sendForgotCodeBtn);
        resendActivationCode = findViewById(R.id.resendActivationCode);
        backToLogin = findViewById(R.id.backToLogin);
        progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
        constraintLayout = findViewById(R.id.constraintLayout);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                Realm realm = Realm.getDefaultInstance();
                ForgotPasswordTable forgotPasswordTable = realm.where(ForgotPasswordTable.class).findFirst();
                realm.beginTransaction();
                    forgotPasswordTable.deleteFromRealm();
                realm.commitTransaction();
                startActivity(intent);
                finish();

            }
        });

        resendActivationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptPasswordResend();
            }
        });


        sendForgotCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validCode(getOtherString(verificationCode)))
                {
                    Snackbar.make(constraintLayout, "Kindly provide a valid code", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else if(!validPassword(getOtherString(confirmPassword)))
                {
                    Snackbar.make(constraintLayout, "Check the provided password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else if(!validPassword(getOtherString(password)))
                {
                    Snackbar.make(constraintLayout, "Confirm your password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else if(!getOtherString(password).equals(getOtherString(confirmPassword)))
                {
                    Snackbar.make(constraintLayout, "Confirm your password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                    attemptResetPassword();
                }

            }
        });

    }


    boolean validPassword(String password)
    {
        if(password.length() > 4)
            return  true;
        return false;
    }


    boolean validCode(String password)
    {
        System.out.println("password "+password.length());
        if(password.length() > 5)
            return  true;
        return false;
    }

    void attemptPasswordResend()
    {
        progressDialog.setMessage("Please wait ...");
        progressDialog.show();
        Realm realm = Realm.getDefaultInstance();
        ForgotPasswordTable forgotPasswordTable = realm.where(ForgotPasswordTable.class).findFirst();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<UserModule> login = endpoints.resendForgotCode(forgotPasswordTable.getEmail());
        login.enqueue(new Callback<UserModule>() {
            @Override
            public void onResponse(Response<UserModule> response, Retrofit retrofit) {
                UserModule user = response.body();
                if(response.isSuccess() && response.code() == 200)
                {
                    if(user.getResponseCode() == 200)
                    {
                        Toast.makeText(getApplicationContext(),"Kindly check your email for your reset code",Toast.LENGTH_LONG).show();
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
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Sorry an error was encountered",Toast.LENGTH_LONG).show();

            }
        });
    }

    void attemptResetPassword()
    {

        progressDialog.setMessage("Please wait ...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<UserModule> login = endpoints.resetForgotPassword(getOtherString(verificationCode),getOtherString(password),getOtherString(confirmPassword));
        login.enqueue(new Callback<UserModule>() {
            @Override
            public void onResponse(Response<UserModule> response, Retrofit retrofit) {
                UserModule user = response.body();
                if(response.isSuccess() && response.code() == 200)
                {
                    if(user.getResponseCode() == 200)
                    {
                        Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                                ForgotPasswordTable forgotPasswordTable = realm.where(ForgotPasswordTable.class).findFirst();
                                        forgotPasswordTable.deleteFromRealm();
                                    realm.commitTransaction();
                                realm.close();
                            Toast.makeText(getApplicationContext(),"Thank you kindly login in now",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
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
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Sorry an error was encountered",Toast.LENGTH_LONG).show();

            }
        });


    }

    String getOtherString(TextInputEditText editText)
    {
        return editText.getText().toString().trim();
    }



}
