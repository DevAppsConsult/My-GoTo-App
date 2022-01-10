package com.mygotoservices.mygotoandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.hbb20.CountryCodePicker;
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

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText registerNameET,registerEmail,registerPasswordET,registerConfirmPasswordET,phoneText;
    LinearLayout linearLayout;
    Button registerBtn;
    TextView toLoginTV;
    CountryCodePicker countryCodePicker;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toLoginTV = findViewById(R.id.toLoginTV);
        toLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
        registerBtn = findViewById(R.id.registerBtn);
        registerNameET = findViewById(R.id.registerNameET);
        registerEmail = findViewById(R.id.registerEmail);
        registerPasswordET = findViewById(R.id.registerPasswordET);
        registerConfirmPasswordET = findViewById(R.id.registerConfirmPasswordET);
        linearLayout = findViewById(R.id.linearLayout);
        phoneText = findViewById(R.id.phoneText);
        countryCodePicker = findViewById(R.id.ccp);
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validEmail(registerEmail.getText().toString().trim()))
                {
                    Snackbar.make(linearLayout, "Kindly provide a valid email address", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else if(!validPassword(registerPasswordET.getText().toString().trim()))
                {
                    Snackbar.make(linearLayout, "Check the provided password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else if(!validPassword(registerConfirmPasswordET.getText().toString().trim()))
                {
                    Snackbar.make(linearLayout, "Confirm your password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else if(!validateUsing_libphonenumber(countryCodePicker.getSelectedCountryCodeWithPlus().toString(),phoneText.getText().toString().trim()))
                {
                    Snackbar.make(linearLayout, "Confirm your phone number", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                    attemptRegistration();
                }
            }
        });

    }
    static String internationalFormat = "";
    private boolean validateUsing_libphonenumber(String countryCode, String phNumber) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        String isoCode = phoneNumberUtil.getRegionCodeForCountryCode(Integer.parseInt(countryCode));
        Phonenumber.PhoneNumber phoneNumber = null;
        try {
            //phoneNumber = phoneNumberUtil.parse(phNumber, "IN");  //if you want to pass region code
            phoneNumber = phoneNumberUtil.parse(phNumber, isoCode);
        } catch (NumberParseException e) {
            System.err.println(e);
        }

        boolean isValid = phoneNumberUtil.isValidNumber(phoneNumber);
        if (isValid) {
            internationalFormat = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            return true;
        } else {
            Toast.makeText(this, "Phone Number is Invalid ", Toast.LENGTH_LONG).show();
            return false;
        }
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


    void attemptRegistration()
    {

        progressDialog.setMessage("Registering your account");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiLocation.getLocation())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiEndpoint endpoints = retrofit.create(ApiEndpoint.class);

        Call<UserModule> login = endpoints.register(getOtherString(registerEmail),getOtherString(registerNameET),getOtherString(phoneText),getOtherString(registerConfirmPasswordET));
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
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Snackbar.make(linearLayout, user.getResponseMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                }
                else
                {
                    Snackbar.make(linearLayout, "Sorry an error was encountered. Error code "+response.code(), Snackbar.LENGTH_LONG)
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


    String getString(EditText editText)
    {
        return editText.getText().toString().trim();
    }

    String getOtherString(TextInputEditText editText)
    {
        return editText.getText().toString().trim();
    }


}
