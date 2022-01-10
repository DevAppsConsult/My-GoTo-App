package com.mygotoservices.mygotoandroid.Modules;

import java.io.Serializable;

/**
 * Created by admin on 18/05/2018.
 */

public class RatingModule implements Serializable{
    String uuid,description;
    int ratings_id,user_id,listing_id, booking_id;
    double rating;

    public RatingModule() {

    }

    public RatingModule(String uuid, String description, int ratings_id, int user_id, int listing_id, int booking_id, double rating) {
        this.uuid = uuid;
        this.description = description;
        this.ratings_id = ratings_id;
        this.user_id = user_id;
        this.listing_id = listing_id;
        this.booking_id = booking_id;
        this.rating = rating;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRatings_id() {
        return ratings_id;
    }

    public void setRatings_id(int ratings_id) {
        this.ratings_id = ratings_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getListing_id() {
        return listing_id;
    }

    public void setListing_id(int listing_id) {
        this.listing_id = listing_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
