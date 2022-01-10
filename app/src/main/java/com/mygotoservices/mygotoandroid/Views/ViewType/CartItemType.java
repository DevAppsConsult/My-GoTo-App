package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by admin on 05/06/2018.
 */

public class CartItemType extends RecyclerViewEmptySupport.ViewHolder {
    ImageView imageView;
    TextView productName,productUnitPrice,productSubTotal,productQuantity,removeItem;
    Button minusBtn,plusBtn;

    public CartItemType(View itemView, ImageView imageView, TextView productName, TextView productUnitPrice, TextView productSubTotal, TextView productQuantity, Button minusBtn, Button plusBtn) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.thumbnail);
        this.productName = itemView.findViewById(R.id.productName);
        this.productUnitPrice = itemView.findViewById(R.id.productUnitPrice);
        this.productSubTotal = itemView.findViewById(R.id.productSubTotal);
        this.productQuantity = itemView.findViewById(R.id.productQuantity);
        this.minusBtn = itemView.findViewById(R.id.minusBtn);
        this.plusBtn = itemView.findViewById(R.id.plusBtn);
    }

    public CartItemType(View itemView, ImageView imageView, TextView productName, TextView productUnitPrice, TextView productSubTotal, TextView productQuantity, TextView removeItem, Button minusBtn, Button plusBtn) {
        super(itemView);
        this.imageView = imageView;
        this.productName = productName;
        this.productUnitPrice = productUnitPrice;
        this.productSubTotal = productSubTotal;
        this.productQuantity = productQuantity;
        this.removeItem = removeItem;
        this.minusBtn = minusBtn;
        this.plusBtn = plusBtn;
    }

    public CartItemType(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.thumbnail);
        productName = itemView.findViewById(R.id.productName);
        productUnitPrice = itemView.findViewById(R.id.productUnitPrice);
        productSubTotal = itemView.findViewById(R.id.productSubTotal);
        productQuantity = itemView.findViewById(R.id.productQuantity);
        minusBtn = itemView.findViewById(R.id.minusBtn);
        plusBtn = itemView.findViewById(R.id.plusBtn);
        removeItem = itemView.findViewById(R.id.closeBtn);
    }

    public TextView getRemoveItem() {
        return removeItem;
    }

    public void setRemoveItem(TextView removeItem) {
        this.removeItem = removeItem;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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

    public Button getMinusBtn() {
        return minusBtn;
    }

    public void setMinusBtn(Button minusBtn) {
        this.minusBtn = minusBtn;
    }

    public Button getPlusBtn() {
        return plusBtn;
    }

    public void setPlusBtn(Button plusBtn) {
        this.plusBtn = plusBtn;
    }
}
