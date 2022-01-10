package com.mygotoservices.mygotoandroid.Modules;

import java.io.Serializable;

/**
 * Created by admin on 17/05/2018.
 */

public class ImageModule implements Serializable{
    int id,listing_id;
    String image;

    public ImageModule(int id, int listing_id, String image) {
        this.id = id;
        this.listing_id = listing_id;
        this.image = image;
    }

    public ImageModule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListing_id() {
        return listing_id;
    }

    public void setListing_id(int listing_id) {
        this.listing_id = listing_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
