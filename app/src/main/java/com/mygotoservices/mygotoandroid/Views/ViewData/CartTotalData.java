package com.mygotoservices.mygotoandroid.Views.ViewData;

/**
 * Created by admin on 05/06/2018.
 */

public class CartTotalData {
    double subTotal,delivery,total;

    public CartTotalData(double subTotal, double delivery, double total) {
        this.subTotal = subTotal;
        this.delivery = delivery;
        this.total = total;
    }

    public CartTotalData() {
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
