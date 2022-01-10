package com.mygotoservices.mygotoandroid.Modules;

/**
 * Created by isaac on 6/15/18.
 */

public class OrderPlacementModule {
    String responseCode,responseMessage,redirect_url;

    public OrderPlacementModule(String responseCode, String responseMessage, String redirect_url) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.redirect_url = redirect_url;
    }

    public OrderPlacementModule() {
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }
}
