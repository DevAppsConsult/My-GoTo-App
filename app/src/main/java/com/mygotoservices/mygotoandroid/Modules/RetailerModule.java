package com.mygotoservices.mygotoandroid.Modules;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 17/05/2018.
 */

public class RetailerModule implements Serializable{
    int listing_id,category_id,provider_id,instock,sold;
    String name,description,currency,listing_type,status,bookings;
    double unit_price,avg_ratings;
    ListingModule listing;
    ProviderModule provider;
    UserModule user;
    List<ImageModule> images;
    CategoryModule category;

    public RetailerModule(int listing_id, int category_id, int provider_id, int instock, int sold, String name, String description, String currency, String listing_type, String status, double unit_price, double avg_ratings, ListingModule listing, ProviderModule provider, UserModule user, List<ImageModule> images, CategoryModule category) {
        this.listing_id = listing_id;
        this.category_id = category_id;
        this.provider_id = provider_id;
        this.instock = instock;
        this.sold = sold;
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.listing_type = listing_type;
        this.status = status;
        this.unit_price = unit_price;
        this.avg_ratings = avg_ratings;
        this.listing = listing;
        this.provider = provider;
        this.user = user;
        this.images = images;
        this.category = category;
    }

    public RetailerModule(int listing_id, int category_id, int provider_id, int instock, int sold, String name, String description, String currency, String listing_type, String status, String bookings, double unit_price, double avg_ratings, ListingModule listing, ProviderModule provider, UserModule user, List<ImageModule> images, CategoryModule category) {
        this.listing_id = listing_id;
        this.category_id = category_id;
        this.provider_id = provider_id;
        this.instock = instock;
        this.sold = sold;
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.listing_type = listing_type;
        this.status = status;
        this.bookings = bookings;
        this.unit_price = unit_price;
        this.avg_ratings = avg_ratings;
        this.listing = listing;
        this.provider = provider;
        this.user = user;
        this.images = images;
        this.category = category;
    }

    public RetailerModule() {
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

    public int getInstock() {
        return instock;
    }

    public void setInstock(int instock) {
        this.instock = instock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getAvg_ratings() {
        return avg_ratings;
    }

    public void setAvg_ratings(double avg_ratings) {
        this.avg_ratings = avg_ratings;
    }

    public ListingModule getListing() {
        return listing;
    }

    public void setListing(ListingModule listing) {
        this.listing = listing;
    }

    public ProviderModule getProvider() {
        return provider;
    }

    public void setProvider(ProviderModule provider) {
        this.provider = provider;
    }

    public UserModule getUser() {
        return user;
    }

    public void setUser(UserModule user) {
        this.user = user;
    }

    public List<ImageModule> getImages() {
        return images;
    }

    public void setImages(List<ImageModule> images) {
        this.images = images;
    }

    public CategoryModule getCategory() {
        return category;
    }

    public void setCategory(CategoryModule category) {
        this.category = category;
    }

    public String getBookings() {
        return bookings;
    }

    public void setBookings(String bookings) {
        this.bookings = bookings;
    }
}
