package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by admin on 05/06/2018.
 */

public class CartTotalType extends RecyclerViewEmptySupport.ViewHolder {

    TextView subTotal,deliveryCost,totalCost;
    Button checkoutNow,emptyCart;

    public CartTotalType(View itemView, TextView subTotal, TextView deliveryCost, TextView totalCost, Button checkoutNow, Button emptyCart) {
        super(itemView);
        this.subTotal = itemView.findViewById(R.id.subTotal);
        this.deliveryCost = itemView.findViewById(R.id.deliveryCost);
        this.totalCost = itemView.findViewById(R.id.totalCost);
        this.checkoutNow = itemView.findViewById(R.id.checkoutNow);
        this.emptyCart = itemView.findViewById(R.id.emptyCart);
    }

    public CartTotalType(View itemView) {
        super(itemView);
        subTotal = itemView.findViewById(R.id.subTotal);
        deliveryCost = itemView.findViewById(R.id.deliveryCost);
        totalCost = itemView.findViewById(R.id.totalCost);
        checkoutNow = itemView.findViewById(R.id.checkoutNow);
        emptyCart = itemView.findViewById(R.id.emptyCart);
    }

    public TextView getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(TextView subTotal) {
        this.subTotal = subTotal;
    }

    public TextView getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(TextView deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public TextView getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(TextView totalCost) {
        this.totalCost = totalCost;
    }

    public Button getCheckoutNow() {
        return checkoutNow;
    }

    public void setCheckoutNow(Button checkoutNow) {
        this.checkoutNow = checkoutNow;
    }

    public Button getEmptyCart() {
        return emptyCart;
    }

    public void setEmptyCart(Button emptyCart) {
        this.emptyCart = emptyCart;
    }
}
