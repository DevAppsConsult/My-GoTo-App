package com.mygotoservices.mygotoandroid;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mygotoservices.mygotoandroid.Api.ApiEndpoint;
import com.mygotoservices.mygotoandroid.Api.ApiLocation;
import com.mygotoservices.mygotoandroid.Modules.BookingModule;
import com.mygotoservices.mygotoandroid.Modules.UserModule;
import com.mygotoservices.mygotoandroid.RealmTables.ForgotPasswordTable;
import com.mygotoservices.mygotoandroid.RealmTables.UserTable;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;

import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    TextView forgot,register;
    Button loginBtn;
    ConstraintLayout constraintLayout;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.loginUsernameET);
        password = findViewById(R.id.loginPasswordET);
        forgot = findViewById(R.id.loginForgotTV);
        register = findViewById(R.id.loginRegisterTV);
        loginBtn = findViewById(R.id.loginBtn);
        constraintLayout = findViewById(R.id.mainLayout);
        progressDialog = new ProgressDialog(LoginActivity.this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validEmail(email.getText().toString().trim()))
                {
                    Snackbar.make(constraintLayout, "Kindly provide a valid email address", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else if(!validPassword(password.getText().toString().trim()))
                {
                    Snackbar.make(constraintLayout, "Check the provided password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                    loginAction();
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForgotDialog();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    boolean validEmail(String email)
    {
        if(email.contains("@") && email.contains("."))
            return true;
        return false;
    }

    boolean validPassword(String password)
    {
        if(password.length() > 4)
            return  true;
        return false;
    }

    void loginAction()
    {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Submitting your request to login ...");
        progressDialog.setTitle("Please wait ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<UserModule> login = endpoints.login(email.getText().toString().trim(),password.getText().toString().trim());
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
                       UserTable userTable = new UserTable();
                       userTable.setUser_id(user.getUser_id());
                       userTable.setBalance(user.getBalance());
                       userTable.setEmail(user.getEmail());
                       userTable.setName(user.getName());
                       userTable.setPhone(user.getPhone());
                       userTable.setProfile_pic(user.getProfile_pic());
                       userTable.setStatus(user.getStatus());
                       userTable.setVerification_code(user.getVerification_code());
                       userTable.setUuid(user.getUuid());
                       userTable.setFcm_token(user.getFcm_token());
                       userTable.setSociel_provider_id(user.getSociel_provider_id());
                       userTable.setSocial_provider(user.getSocial_provider());
                       userTable.setSocial_provide_url(user.getSocial_provide_url());
                       realm.copyToRealmOrUpdate(userTable);
                       realm.commitTransaction();
                       realm.close();
                       loginAction2();
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




    void loginAction2() {
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
                    UserModule user = response.body();
                    if (response.isSuccess() && response.code() == 200) {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    // new BackgroundTask().execute();


                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    loginAction2();
                    // new BackgroundTask().execute();

                }
            });
        }
        else
        {
            // new BackgroundTask().execute();

        }
    }


    void showForgotDialog()
    {


        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.forgot_password_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView backToLogin = dialog.findViewById(R.id.backToLogin);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                dialog.dismiss();
            }
        });
        final TextInputEditText textInputEditText = dialog.findViewById(R.id.registerEmail);
        Button sendForgotCodeBtn = dialog.findViewById(R.id.sendForgotCodeBtn);

        sendForgotCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validEmail(getOtherString(textInputEditText)))
                {
                    Snackbar.make(constraintLayout, "Kindly provide a valid email address", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                    dialog.hide();
                    dialog.dismiss();
                    sendForgot(getOtherString(textInputEditText));
                }
            }
        });

        dialog.show();
        //Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        //startActivity(intent);
        //finish();
    }

    String getOtherString(TextInputEditText editText)
    {
        return editText.getText().toString().trim();
    }



    void sendForgot(final String string)
    {

        progressDialog.setMessage("Please wait ...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<UserModule> login = endpoints.forgotPassword(string);
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
                        ForgotPasswordTable forgotPasswordTable = new ForgotPasswordTable();
                        forgotPasswordTable.setPrimaryId(1);
                        forgotPasswordTable.setEmail(string);
                        realm.copyToRealmOrUpdate(forgotPasswordTable);
                        realm.commitTransaction();
                        realm.close();
                        Toast.makeText(getApplicationContext(),"Kindly check your email for your reset code",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
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



}
