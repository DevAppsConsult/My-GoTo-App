package com.mygotoservices.mygotoandroid.Modules;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 17/05/2018.
 */

public class ListingModule implements Serializable{
    int listing_id,category_id,provider_id,sold,instock;
    double unit_price,avg_ratings;
    String name,description,listing_type,status,currency;
    List<ImageModule> images;
    List<RatingModule> ratings;
    ServiceProviderModule provider;
    CategoryModule category;

    public ListingModule(int listing_id, int category_id, int provider_id, int sold, int instock, double unit_price, double avg_ratings, String name, String description, String listing_type, String status, String currency, List<ImageModule> images, List<RatingModule> ratings, ServiceProviderModule provider) {
        this.listing_id = listing_id;
        this.category_id = category_id;
        this.provider_id = provider_id;
        this.sold = sold;
        this.instock = instock;
        this.unit_price = unit_price;
        this.avg_ratings = avg_ratings;
        this.name = name;
        this.description = description;
        this.listing_type = listing_type;
        this.status = status;
        this.currency = currency;
        this.images = images;
        this.ratings = ratings;
        this.provider = provider;
    }

    public ListingModule(int listing_id, int category_id, int provider_id, int sold, int instock, double unit_price, double avg_ratings, String name, String description, String listing_type, String status, String currency, List<ImageModule> images, List<RatingModule> ratings, ServiceProviderModule provider, CategoryModule category) {
        this.listing_id = listing_id;
        this.category_id = category_id;
        this.provider_id = provider_id;
        this.sold = sold;
        this.instock = instock;
        this.unit_price = unit_price;
        this.avg_ratings = avg_ratings;
        this.name = name;
        this.description = description;
        this.listing_type = listing_type;
        this.status = status;
        this.currency = currency;
        this.images = images;
        this.ratings = ratings;
        this.provider = provider;
        this.category = category;
    }

    public double getAvg_ratings() {
        return avg_ratings;
    }

    public void setAvg_ratings(double avg_ratings) {
        this.avg_ratings = avg_ratings;
    }

    public List<ImageModule> getImages() {
        return images;
    }

    public void setImages(List<ImageModule> images) {
        this.images = images;
    }

    public List<RatingModule> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingModule> ratings) {
        this.ratings = ratings;
    }

    public ServiceProviderModule getProvider() {
        return provider;
    }

    public void setProvider(ServiceProviderModule provider) {
        this.provider = provider;
    }

    /**
         * "listing": {
         "": 2,
         "": "Hammer",
         "": 0,
         "": "kioo",
         "created_at": "2017-12-24 04:28:08",
         "updated_at": "2017-12-24 05:25:49",
         "": 1,
         "": "20.00",
         "": "",
         "": 0,
         "": 0,
         "": "Retailer",
         "": "Active"
         },
     * **/

    public ListingModule(int listing_id, int category_id, int provider_id, int sold, int instock, double unit_price, String name, String description, String listing_type, String status, String currency) {
        this.listing_id = listing_id;
        this.category_id = category_id;
        this.provider_id = provider_id;
        this.sold = sold;
        this.instock = instock;
        this.unit_price = unit_price;
        this.name = name;
        this.description = description;
        this.listing_type = listing_type;
        this.status = status;
        this.currency = currency;
    }

    public ListingModule() {
    }

    public int getListing_id() {
        return listing_id;
    }

    public void setListing_id(int listing_id) {
        this.listing_id = listing_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getInstock() {
        return instock;
    }

    public void setInstock(int instock) {
        this.instock = instock;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListing_type() {
        return listing_type;
    }

    public void setListing_type(String listing_type) {
        this.listing_type = listing_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CategoryModule getCategory() {
        return category;
    }

    public void setCategory(CategoryModule category) {
        this.category = category;
    }
}
