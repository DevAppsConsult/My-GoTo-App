package com.mygotoservices.mygotoandroid.Views.ViewData;

/**
 * Created by isaac on 5/14/18.
 */

public class MarketData {
    int id;
    String mainName,type,number_orders,thumbnail;
    double ratings;

    public MarketData(int id, String mainName, String type, String number_orders, String thumbnail, double ratings) {
        this.id = id;
        this.mainName = mainName;
        this.type = type;
        this.number_orders = number_orders;
        this.thumbnail = thumbnail;
        this.ratings = ratings;
    }

    public MarketData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber_orders() {
        return number_orders;
    }

    public void setNumber_orders(String number_orders) {
        this.number_orders = number_orders;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
}
