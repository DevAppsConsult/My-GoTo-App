package com.mygotoservices.mygotoandroid.RealmTables;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 18/05/2018.
 */

public class CartDetailTable extends RealmObject{

    @PrimaryKey
    int id;
    String detail_status,order_status,name,file_name;
    int listing_id,provider_id,quantity,order_id,user_id;
    double sub_total,unit_price,processing_fee;

    public CartDetailTable(int id, String detail_status, String order_status, int listing_id, int provider_id, int quantity, int order_id, int user_id, double sub_total, double unit_price, double processing_fee) {
        this.id = id;
        this.detail_status = detail_status;
        this.order_status = order_status;
        this.listing_id = listing_id;
        this.provider_id = provider_id;
        this.quantity = quantity;
        this.order_id = order_id;
        this.user_id = user_id;
        this.sub_total = sub_total;
        this.unit_price = unit_price;
        this.processing_fee = processing_fee;
    }

    public CartDetailTable(int id, String detail_status, String order_status, String name, String file_name, int listing_id, int provider_id, int quantity, int order_id, int user_id, double sub_total, double unit_price, double processing_fee) {
        this.id = id;
        this.detail_status = detail_status;
        this.order_status = order_status;
        this.name = name;
        this.file_name = file_name;
        this.listing_id = listing_id;
        this.provider_id = provider_id;
        this.quantity = quantity;
        this.order_id = order_id;
        this.user_id = user_id;
        this.sub_total = sub_total;
        this.unit_price = unit_price;
        this.processing_fee = processing_fee;
    }

    public CartDetailTable(int id, String detail_status, String order_status, String name, int listing_id, int provider_id, int quantity, int order_id, int user_id, double sub_total, double unit_price, double processing_fee) {
        this.id = id;
        this.detail_status = detail_status;
        this.order_status = order_status;
        this.name = name;
        this.listing_id = listing_id;
        this.provider_id = provider_id;
        this.quantity = quantity;
        this.order_id = order_id;
        this.user_id = user_id;
        this.sub_total = sub_total;
        this.unit_price = unit_price;
        this.processing_fee = processing_fee;
    }

    public CartDetailTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail_status() {
        return detail_status;
    }

    public void setDetail_status(String detail_status) {
        this.detail_status = detail_status;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
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

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(double processing_fee) {
        this.processing_fee = processing_fee;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
