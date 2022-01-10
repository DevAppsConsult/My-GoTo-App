package com.mygotoservices.mygotoandroid.Modules;

import java.util.List;

/**
 * Created by isaac on 5/26/18.
 */

public class BookingModule {
    String responseCode,redirect_url;
    List<String> duration;

    public BookingModule(String responseCode, String redirect_url) {
        this.responseCode = responseCode;
        this.redirect_url = redirect_url;
    }

    public BookingModule(String responseCode, String redirect_url, List<String> duration) {
        this.responseCode = responseCode;
        this.redirect_url = redirect_url;
        this.duration = duration;
    }

    public BookingModule() {
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public List<String> getDuration() {
        return duration;
    }

    public void setDuration(List<String> duration) {
        this.duration = duration;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }
}
