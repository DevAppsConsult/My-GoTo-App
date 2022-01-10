package com.mygotoservices.mygotoandroid.Modules;

/**
 * Created by admin on 15/05/2018.
 */

public class TopArtisanModule {
    double rating;
    int listing_id,bookings;
    String image,name,user,type;

    public TopArtisanModule(double rating, int listing_id, int bookings, String image, String name, String user, String type) {
        this.rating = rating;
        this.listing_id = listing_id;
        this.bookings = bookings;
        this.image = image;
        this.name = name;
        this.user = user;
        this.type = type;
    }

    public TopArtisanModule() {
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getListing_id() {
        return listing_id;
    }

    public void setListing_id(int listing_id) {
        this.listing_id = listing_id;
    }

    public int getBookings() {
        return bookings;
    }

    public void setBookings(int bookings) {
        this.bookings = bookings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
