package com.mygotoservices.mygotoandroid.Modules;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by isaac on 6/7/18.
 */

public class OrderModule {

    public OrderModule(int order_id, int user_id, double amount, double delivery_charge, double processing_fee, String delivery_information, String delivery_method, String status, String payment_status, String delivered_at, String transaction_reference, String payment_means, String order_code, String transaction_id) {
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
    }

    public OrderModule() {
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

    public OrderModule(int order_id, int user_id, double amount, double delivery_charge, double processing_fee, String delivery_information, String delivery_method, String status, String payment_status, String delivered_at, String transaction_reference, String payment_means, String order_code, String transaction_id, String phone, String network, String token) {
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

    int order_id,user_id;
    double amount,delivery_charge,processing_fee;
    String delivery_information,delivery_method,status,payment_status,delivered_at,transaction_reference,payment_means,order_code,transaction_id,phone,network,token;
    public JSONObject toJSon() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("order_id",this.order_id);
        jsonObject.put("user_id",this.user_id);
        jsonObject.put("amount",this.amount);
        jsonObject.put("delivery_charge",this.delivery_charge);
        jsonObject.put("delivery_information",this.delivery_information);
        jsonObject.put("delivery_method",this.delivery_method);
        jsonObject.put("status",this.status);
        jsonObject.put("payment_status",this.payment_status);
        jsonObject.put("delivered_at",this.delivered_at);
        jsonObject.put("transaction_reference",this.transaction_reference);
        jsonObject.put("processing_fee;",this.processing_fee);
        jsonObject.put("payment_means",this.payment_means);
        jsonObject.put("order_code",this.order_code);
        jsonObject.put("transaction_id",this.transaction_id);
        jsonObject.put("phone",this.phone);
        jsonObject.put("network",this.network);
        jsonObject.put("token",this.token);

        return  jsonObject;
    }
}
