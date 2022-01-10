package com.mygotoservices.mygotoandroid.Views.ViewData;

/**
 * Created by isaac on 5/14/18.
 */

public class ProductData {
    int id;
    String mainName,type,number_orders,thumbnail,currency;
    double ratings,unit_price;

    public ProductData() {
    }

    public ProductData(int id, String mainName, String type, String number_orders, String thumbnail, String currency, double ratings, double unit_price) {
        this.id = id;
        this.mainName = mainName;
        this.type = type;
        this.number_orders = number_orders;
        this.thumbnail = thumbnail;
        this.currency = currency;
        this.ratings = ratings;
        this.unit_price = unit_price;
    }

    public ProductData(int id, String mainName, String type, String number_orders, String thumbnail, double ratings) {
        this.id = id;
        this.mainName = mainName;
        this.type = type;
        this.number_orders = number_orders;
        this.thumbnail = thumbnail;
        this.ratings = ratings;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
}
