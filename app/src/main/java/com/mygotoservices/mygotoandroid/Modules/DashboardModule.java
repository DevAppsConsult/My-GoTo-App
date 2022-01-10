package com.mygotoservices.mygotoandroid.Modules;

import java.util.List;

/**
 * Created by admin on 15/05/2018.
 */

public class DashboardModule {
    String responseCode,responseMessage;
    List<SliderModule> adverts;
    List<TopArtisanModule> artisans;

    public DashboardModule(String responseCode, String responseMessage, List<SliderModule> adverts, List<TopArtisanModule> artisans) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.adverts = adverts;
        this.artisans = artisans;
    }

    public DashboardModule() {
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

    public List<SliderModule> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<SliderModule> adverts) {
        this.adverts = adverts;
    }

    public List<TopArtisanModule> getArtisans() {
        return artisans;
    }

    public void setArtisans(List<TopArtisanModule> artisans) {
        this.artisans = artisans;
    }
}
