package com.mygotoservices.mygotoandroid.RealmTables;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 12/06/2018.
 */

public class PlaceTable  extends RealmObject {
    @PrimaryKey
    int place_id;
    String place_name,city,country,place_longitude,place_latitude;

    public PlaceTable(int place_id, String place_name, String city, String country, String place_longitude, String place_latitude) {
        this.place_id = place_id;
        this.place_name = place_name;
        this.city = city;
        this.country = country;
        this.place_longitude = place_longitude;
        this.place_latitude = place_latitude;
    }

    public PlaceTable() {
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlace_longitude() {
        return place_longitude;
    }

    public void setPlace_longitude(String place_longitude) {
        this.place_longitude = place_longitude;
    }

    public String getPlace_latitude() {
        return place_latitude;
    }

    public void setPlace_latitude(String place_latitude) {
        this.place_latitude = place_latitude;
    }
}
