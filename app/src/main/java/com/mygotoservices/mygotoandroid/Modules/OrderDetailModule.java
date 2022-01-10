package com.mygotoservices.mygotoandroid.Modules;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by isaac on 6/7/18.
 */

public class OrderDetailModule {

    public OrderDetailModule(int id, int listing_id, int provider_id, int quantity, int order_id, int user_id, double unit_price, double sub_total, double processing_fee, String order_status, String detail_status) {
        this.id = id;
        this.listing_id = listing_id;
        this.provider_id = provider_id;
        this.quantity = quantity;
        this.order_id = order_id;
        this.user_id = user_id;
        this.unit_price = unit_price;
        this.sub_total = sub_total;
        this.processing_fee = processing_fee;
        this.order_status = order_status;
        this.detail_status = detail_status;
    }

    public OrderDetailModule() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public double getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(double processing_fee) {
        this.processing_fee = processing_fee;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getDetail_status() {
        return detail_status;
    }

    public void setDetail_status(String detail_status) {
        this.detail_status = detail_status;
    }
    int id,listing_id,provider_id,quantity,order_id,user_id;
    double unit_price,sub_total,processing_fee;
    String order_status,detail_status;

    public JSONObject toJSon() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id",this.id);
        jsonObject.put("listing_id",this.listing_id);
        jsonObject.put("provider_id",this.provider_id);
        jsonObject.put("quantity",this.quantity);
        jsonObject.put("order_id",this.order_id);
        jsonObject.put("user_id",this.user_id);
        jsonObject.put("unit_price",this.unit_price);
        jsonObject.put("sub_total",this.sub_total);
        jsonObject.put("processing_fee",this.processing_fee);
        jsonObject.put("order_status",this.order_status);
        jsonObject.put("detail_status",this.detail_status);

        return  jsonObject;
    }
}
