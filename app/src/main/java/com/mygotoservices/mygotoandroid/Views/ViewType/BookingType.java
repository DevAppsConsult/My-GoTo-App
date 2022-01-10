package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by isaac on 5/14/18.
 */

public class BookingType extends RecyclerViewEmptySupport.ViewHolder {
    ImageView thumbnail;
    TextView productName,productUnitPrice,productSubTotal,productQuantity,bookingStatus,paymentStatus,estimatedTime;
    Button rateBtn,likeBtn;
    public BookingType(View itemView) {
        super(itemView);
        this.thumbnail = itemView.findViewById(R.id.thumbnail);
        this.productName = itemView.findViewById(R.id.productName);
        this.productUnitPrice = itemView.findViewById(R.id.productUnitPrice);
        this.productSubTotal = itemView.findViewById(R.id.productSubTotal);
        this.productQuantity = itemView.findViewById(R.id.productQuantity);
        this.rateBtn = itemView.findViewById(R.id.rateBtn);
        this.likeBtn = itemView.findViewById(R.id.likeBtn);
        this.estimatedTime = itemView.findViewById(R.id.estimatedTime);
        this.paymentStatus = itemView.findViewById(R.id.paymentStatus);
        this.bookingStatus = itemView.findViewById(R.id.bookingStatus);

    }

    public BookingType(View itemView, ImageView thumbnail, TextView productName, TextView productUnitPrice, TextView productSubTotal, TextView productQuantity, TextView bookingStatus, TextView paymentStatus, TextView estimatedTime, Button rateBtn, Button likeBtn) {
        super(itemView);
        this.thumbnail = thumbnail;
        this.productName = productName;
        this.productUnitPrice = productUnitPrice;
        this.productSubTotal = productSubTotal;
        this.productQuantity = productQuantity;
        this.bookingStatus = bookingStatus;
        this.paymentStatus = paymentStatus;
        this.estimatedTime = estimatedTime;
        this.rateBtn = rateBtn;
        this.likeBtn = likeBtn;
    }

    public BookingType(View itemView, ImageView thumbnail, TextView productName, TextView productUnitPrice, TextView productSubTotal, TextView productQuantity, Button rateBtn, Button likeBtn) {
        super(itemView);
        this.thumbnail = itemView.findViewById(R.id.thumbnail);
        this.productName = itemView.findViewById(R.id.productName);
        this.productUnitPrice = itemView.findViewById(R.id.productUnitPrice);
        this.productSubTotal = itemView.findViewById(R.id.productSubTotal);
        this.productQuantity = itemView.findViewById(R.id.productQuantity);
        this.rateBtn = itemView.findViewById(R.id.rateBtn);
        this.likeBtn = itemView.findViewById(R.id.likeBtn);

    }

    public TextView getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(TextView bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public TextView getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(TextView paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public TextView getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(TextView estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public ImageView getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImageView thumbnail) {
        this.thumbnail = thumbnail;
    }

    public TextView getProductName() {
        return productName;
    }

    public void setProductName(TextView productName) {
        this.productName = productName;
    }

    public TextView getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(TextView productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public TextView getProductSubTotal() {
        return productSubTotal;
    }

    public void setProductSubTotal(TextView productSubTotal) {
        this.productSubTotal = productSubTotal;
    }

    public TextView getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(TextView productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Button getRateBtn() {
        return rateBtn;
    }

    public void setRateBtn(Button rateBtn) {
        this.rateBtn = rateBtn;
    }

    public Button getLikeBtn() {
        return likeBtn;
    }

    public void setLikeBtn(Button likeBtn) {
        this.likeBtn = likeBtn;
    }
}
