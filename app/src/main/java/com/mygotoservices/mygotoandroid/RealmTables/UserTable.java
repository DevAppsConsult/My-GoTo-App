package com.mygotoservices.mygotoandroid.RealmTables;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by isaac on 5/15/18.
 */

public class UserTable extends RealmObject{
    @PrimaryKey
    int user_id;
    String uuid,name,email,phone,fcm_token,verification_code,status,profile_pic,social_provide_url,social_provider,sociel_provider_id;
    double balance;

    public UserTable(int user_id, String uuid, String name, String email, String phone, String fcm_token, String verification_code, String status, String profile_pic, String social_provide_url, String social_provider, String sociel_provider_id, double balance) {
        this.user_id = user_id;
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.fcm_token = fcm_token;
        this.verification_code = verification_code;
        this.status = status;
        this.profile_pic = profile_pic;
        this.social_provide_url = social_provide_url;
        this.social_provider = social_provider;
        this.sociel_provider_id = sociel_provider_id;
        this.balance = balance;
    }

    public UserTable() {
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

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getSocial_provide_url() {
        return social_provide_url;
    }

    public void setSocial_provide_url(String social_provide_url) {
        this.social_provide_url = social_provide_url;
    }

    public String getSocial_provider() {
        return social_provider;
    }

    public void setSocial_provider(String social_provider) {
        this.social_provider = social_provider;
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
}
