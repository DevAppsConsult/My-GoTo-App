package com.mygotoservices.mygotoandroid.Views.ViewData;

import java.io.Serializable;

/**
 * Created by isaac on 5/14/18.
 */

public class ArtisanData implements Serializable{
    int id;
    String mainName,location,type,number_orders,thumbnail,category;

    double ratings;
    String image;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArtisanData(int id, String mainName, String location, String type, String number_orders, String thumbnail, String category, double ratings, String image) {
        this.id = id;
        this.mainName = mainName;
        this.location = location;
        this.type = type;
        this.number_orders = number_orders;
        this.thumbnail = thumbnail;
        this.category = category;
        this.ratings = ratings;
        this.image = image;
    }

    public ArtisanData(int id, String mainName, String location, String type, String number_orders, String thumbnail, double ratings, String image) {
        this.id = id;
        this.mainName = mainName;
        this.location = location;
        this.type = type;
        this.number_orders = number_orders;
        this.thumbnail = thumbnail;
        this.ratings = ratings;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArtisanData() {
    }

    public ArtisanData(int id, String mainName, String location, String type, String number_orders, String thumbnail, double ratings) {
        this.id = id;
        this.mainName = mainName;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
