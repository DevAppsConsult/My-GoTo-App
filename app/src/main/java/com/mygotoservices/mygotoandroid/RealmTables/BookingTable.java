package com.mygotoservices.mygotoandroid.RealmTables;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 04/06/2018.
 */

public class BookingTable extends RealmObject{
    @PrimaryKey
    int id;
    int booking_id,ratings;
    String remarks;
    boolean is_rated;


    public BookingTable(int id, int booking_id, int ratings, String remarks, boolean is_rated) {
        this.id = id;
        this.booking_id = booking_id;
        this.ratings = ratings;
        this.remarks = remarks;
        this.is_rated = is_rated;
    }

    public BookingTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isIs_rated() {
        return is_rated;
    }

    public void setIs_rated(boolean is_rated) {
        this.is_rated = is_rated;
    }
}
