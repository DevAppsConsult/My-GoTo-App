package com.mygotoservices.mygotoandroid.Views.ViewData;

import com.mygotoservices.mygotoandroid.Modules.ArtisanModule;
import com.mygotoservices.mygotoandroid.Modules.CategoryModule;
import com.mygotoservices.mygotoandroid.Modules.ImageModule;
import com.mygotoservices.mygotoandroid.Modules.ListingModule;
import com.mygotoservices.mygotoandroid.Modules.RatingModule;

import java.util.List;

/**
 * Created by admin on 18/06/2018.
 */

public class BookingData {

    ArtisanData listing;
    int order_id,user_id,id,listing_id,provider_id;
    double amount,delivery_charge,processing_fee,number_hours,total_charge,ratings,lon,lat;
    String delivery_information,delivery_method,delivered_at,payment_means,order_code,transaction_id,phone,network,token,uuid,start_time,status,payment_status,description,created_at,location,landmark,transaction_reference,date,time;
    List<ImageModule> images;
    CategoryModule category;
    RatingModule rated;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BookingData(ArtisanData listing, int order_id, int user_id, int id, int listing_id, int provider_id, double amount, double delivery_charge, double processing_fee, double number_hours, double total_charge, double ratings, double lon, double lat, String delivery_information, String delivery_method, String delivered_at, String payment_means, String order_code, String transaction_id, String phone, String network, String token, String uuid, String start_time, String status, String payment_status, String description, String created_at, String location, String landmark, String transaction_reference, String date, String time, List<ImageModule> images, CategoryModule category, RatingModule rated) {
        this.listing = listing;
        this.order_id = order_id;
        this.user_id = user_id;
        this.id = id;
        this.listing_id = listing_id;
        this.provider_id = provider_id;
        this.amount = amount;
        this.delivery_charge = delivery_charge;
        this.processing_fee = processing_fee;
        this.number_hours = number_hours;
        this.total_charge = total_charge;
        this.ratings = ratings;
        this.lon = lon;
        this.lat = lat;
        this.delivery_information = delivery_information;
        this.delivery_method = delivery_method;
        this.delivered_at = delivered_at;
        this.payment_means = payment_means;
        this.order_code = order_code;
        this.transaction_id = transaction_id;
        this.phone = phone;
        this.network = network;
        this.token = token;
        this.uuid = uuid;
        this.start_time = start_time;
        this.status = status;
        this.payment_status = payment_status;
        this.description = description;
        this.created_at = created_at;
        this.location = location;
        this.landmark = landmark;
        this.transaction_reference = transaction_reference;
        this.date = date;
        this.time = time;
        this.images = images;
        this.category = category;
        this.rated = rated;
    }

    public BookingData(ArtisanData listing, int order_id, int user_id, int id, int listing_id, int provider_id, double amount, double delivery_charge, double processing_fee, double number_hours, double total_charge, double ratings, double lon, double lat, String delivery_information, String delivery_method, String delivered_at, String payment_means, String order_code, String transaction_id, String phone, String network, String token, String uuid, String start_time, String status, String payment_status, String description, String created_at, String location, String landmark, String transaction_reference, List<ImageModule> images, CategoryModule category, RatingModule rated) {
        this.listing = listing;
        this.order_id = order_id;
        this.user_id = user_id;
        this.id = id;
        this.listing_id = listing_id;
        this.provider_id = provider_id;
        this.amount = amount;
        this.delivery_charge = delivery_charge;
        this.processing_fee = processing_fee;
        this.number_hours = number_hours;
        this.total_charge = total_charge;
        this.ratings = ratings;
        this.lon = lon;
        this.lat = lat;
        this.delivery_information = delivery_information;
        this.delivery_method = delivery_method;
        this.delivered_at = delivered_at;
        this.payment_means = payment_means;
        this.order_code = order_code;
        this.transaction_id = transaction_id;
        this.phone = phone;
        this.network = network;
        this.token = token;
        this.uuid = uuid;
        this.start_time = start_time;
        this.status = status;
        this.payment_status = payment_status;
        this.description = description;
        this.created_at = created_at;
        this.location = location;
        this.landmark = landmark;
        this.transaction_reference = transaction_reference;
        this.images = images;
        this.category = category;
        this.rated = rated;
    }

    public BookingData(ArtisanData listing, int order_id, int user_id, int id, int listing_id, int provider_id, double amount, double delivery_charge, double processing_fee, double number_hours, double total_charge, double ratings, double lon, double lat, String delivery_information, String delivery_method, String delivered_at, String payment_means, String order_code, String transaction_id, String phone, String network, String token, String uuid, String start_time, String status, String payment_status, String description, String created_at, String location, String landmark, String transaction_reference, List<ImageModule> images, CategoryModule category) {
        this.listing = listing;
        this.order_id = order_id;
        this.user_id = user_id;
        this.id = id;
        this.listing_id = listing_id;
        this.provider_id = provider_id;
        this.amount = amount;
        this.delivery_charge = delivery_charge;
        this.processing_fee = processing_fee;
        this.number_hours = number_hours;
        this.total_charge = total_charge;
        this.ratings = ratings;
        this.lon = lon;
        this.lat = lat;
        this.delivery_information = delivery_information;
        this.delivery_method = delivery_method;
        this.delivered_at = delivered_at;
        this.payment_means = payment_means;
        this.order_code = order_code;
        this.transaction_id = transaction_id;
        this.phone = phone;
        this.network = network;
        this.token = token;
        this.uuid = uuid;
        this.start_time = start_time;
        this.status = status;
        this.payment_status = payment_status;
        this.description = description;
        this.created_at = created_at;
        this.location = location;
        this.landmark = landmark;
        this.transaction_reference = transaction_reference;
        this.images = images;
        this.category = category;
    }

    public BookingData(int order_id, int user_id, double amount, double delivery_charge, double processing_fee, String delivery_information, String delivery_method, String status, String payment_status, String delivered_at, String transaction_reference, String payment_means, String order_code, String transaction_id, String phone, String network, String token, ArtisanData listing) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.amount = amount;
        this.delivery_charge = delivery_charge;
        this.processing_fee = processing_fee;
        this.delivery_information = delivery_information;
        this.delivery_method = delivery_method;
        this.status = status;
        this.payment_status = payment_status;
        this.delivered_at = delivered_at;
        this.transaction_reference = transaction_reference;
        this.payment_means = payment_means;
        this.order_code = order_code;
        this.transaction_id = transaction_id;
        this.phone = phone;
        this.network = network;
        this.token = token;
        this.listing = listing;
    }

    public BookingData(ArtisanData listing, int order_id, int user_id, int id, int listing_id, int provider_id, double amount, double delivery_charge, double processing_fee, double number_hours, double total_charge, double ratings, double lon, double lat, String delivery_information, String delivery_method, String delivered_at, String payment_means, String order_code, String transaction_id, String phone, String network, String token, String uuid, String start_time, String status, String payment_status, String description, String created_at, String location, String landmark, String transaction_reference) {
        this.listing = listing;
        this.order_id = order_id;
        this.user_id = user_id;
        this.id = id;
        this.listing_id = listing_id;
        this.provider_id = provider_id;
        this.amount = amount;
        this.delivery_charge = delivery_charge;
        this.processing_fee = processing_fee;
        this.number_hours = number_hours;
        this.total_charge = total_charge;
        this.ratings = ratings;
        this.lon = lon;
        this.lat = lat;
        this.delivery_information = delivery_information;
        this.delivery_method = delivery_method;
        this.delivered_at = delivered_at;
        this.payment_means = payment_means;
        this.order_code = order_code;
        this.transaction_id = transaction_id;
        this.phone = phone;
        this.network = network;
        this.token = token;
        this.uuid = uuid;
        this.start_time = start_time;
        this.status = status;
        this.payment_status = payment_status;
        this.description = description;
        this.created_at = created_at;
        this.location = location;
        this.landmark = landmark;
        this.transaction_reference = transaction_reference;
    }

    public BookingData() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(double delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public double getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(double processing_fee) {
        this.processing_fee = processing_fee;
    }

    public String getDelivery_information() {
        return delivery_information;
    }

    public void setDelivery_information(String delivery_information) {
        this.delivery_information = delivery_information;
    }

    public String getDelivery_method() {
        return delivery_method;
    }

    public void setDelivery_method(String delivery_method) {
        this.delivery_method = delivery_method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getDelivered_at() {
        return delivered_at;
    }

    public void setDelivered_at(String delivered_at) {
        this.delivered_at = delivered_at;
    }

    public String getTransaction_reference() {
        return transaction_reference;
    }

    public void setTransaction_reference(String transaction_reference) {
        this.transaction_reference = transaction_reference;
    }

    public String getPayment_means() {
        return payment_means;
    }

    public void setPayment_means(String payment_means) {
        this.payment_means = payment_means;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArtisanData getListing() {
        return listing;
    }

    public void setListing(ArtisanData listing) {
        this.listing = listing;
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

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public double getNumber_hours() {
        return number_hours;
    }

    public void setNumber_hours(double number_hours) {
        this.number_hours = number_hours;
    }

    public double getTotal_charge() {
        return total_charge;
    }

    public void setTotal_charge(double total_charge) {
        this.total_charge = total_charge;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
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

    public RatingModule getRated() {
        return rated;
    }

    public void setRated(RatingModule rated) {
        this.rated = rated;
    }
}
