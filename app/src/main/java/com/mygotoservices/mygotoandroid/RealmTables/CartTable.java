package com.mygotoservices.mygotoandroid.RealmTables;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by admin on 18/05/2018.
 */

public class CartTable extends RealmObject{

    @PrimaryKey
    int order_id;
    int transaction_id;

    String user_id,delivery_information,delivery_method,delivery_charge,payment_status,transaction_reference,payment_means,order_code;
    double processing_fee,amount;

    public CartTable(int order_id, int transaction_id, String user_id, String delivery_information, String delivery_method, String delivery_charge, String payment_status, String transaction_reference, String payment_means, String order_code, double processing_fee, double amount) {
        this.order_id = order_id;
        this.transaction_id = transaction_id;
        this.user_id = user_id;
        this.delivery_information = delivery_information;
        this.delivery_method = delivery_method;
        this.delivery_charge = delivery_charge;
        this.payment_status = payment_status;
        this.transaction_reference = transaction_reference;
        this.payment_means = payment_means;
        this.order_code = order_code;
        this.processing_fee = processing_fee;
        this.amount = amount;
    }

    public CartTable() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(String delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
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

    public double getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(double processing_fee) {
        this.processing_fee = processing_fee;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
