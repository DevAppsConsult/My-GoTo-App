package com.mygotoservices.mygotoandroid.Modules;

/**
 * Created by admin on 17/05/2018.
 */

public class UserModule {
    int user_id,responseCode;
    String uuid,name,email,phone,fcm_token,password,verification_code,forgot_code,status,social_provider,social_provide_url,profile_pic,sociel_provider_id,responseMessage;
    double balance;

    public UserModule(int user_id, int responseCode, String uuid, String name, String email, String phone, String fcm_token, String password, String verification_code, String forgot_code, String status, String social_provider, String social_provide_url, String profile_pic, String sociel_provider_id, String responseMessage, double balance) {
        this.user_id = user_id;
        this.responseCode = responseCode;
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.fcm_token = fcm_token;
        this.password = password;
        this.verification_code = verification_code;
        this.forgot_code = forgot_code;
        this.status = status;
        this.social_provider = social_provider;
        this.social_provide_url = social_provide_url;
        this.profile_pic = profile_pic;
        this.sociel_provider_id = sociel_provider_id;
        this.responseMessage = responseMessage;
        this.balance = balance;
    }

    /**
     *
     *

     "user": {
     "": 5,
     "": "951c26a7-86e9-56f6-99ce-13ca998a584d",
     "": "Isaac Bremang Darko",
     "": "isaacbremang@gmail.com",
     "": "+233262141279",
     "": "",
     "": "$2y$10$XXdB/s6CUDsV.E9w885xVOb/LGbyty1Dxmn79FUoRDb9/ngOqCnz2",
     "": "",
     "": "9F6K4X",
     "": "Active",
     "created_at": "2017-12-11 20:19:34",
     "updated_at": "2017-12-11 21:19:34",
     "": null,
     "": null,
     "": null,
     "": null,
     "": "0.00"
     },
     * */


    public UserModule(int user_id, String uuid, String name, String email, String phone, String fcm_token, String password, String verification_code, String forgot_code, String status, String social_provider, String social_provide_url, String profile_pic, String sociel_provider_id, double balance) {
        this.user_id = user_id;
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.fcm_token = fcm_token;
        this.password = password;
        this.verification_code = verification_code;
        this.forgot_code = forgot_code;
        this.status = status;
        this.social_provider = social_provider;
        this.social_provide_url = social_provide_url;
        this.profile_pic = profile_pic;
        this.sociel_provider_id = sociel_provider_id;
        this.balance = balance;
    }

    public UserModule() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public String getForgot_code() {
        return forgot_code;
    }

    public void setForgot_code(String forgot_code) {
        this.forgot_code = forgot_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSocial_provider() {
        return social_provider;
    }

    public void setSocial_provider(String social_provider) {
        this.social_provider = social_provider;
    }

    public String getSocial_provide_url() {
        return social_provide_url;
    }

    public void setSocial_provide_url(String social_provide_url) {
        this.social_provide_url = social_provide_url;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getSociel_provider_id() {
        return sociel_provider_id;
    }

    public void setSociel_provider_id(String sociel_provider_id) {
        this.sociel_provider_id = sociel_provider_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
