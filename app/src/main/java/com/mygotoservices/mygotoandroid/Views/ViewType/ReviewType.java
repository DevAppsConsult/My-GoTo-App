package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by isaac on 5/19/18.
 */

public class ReviewType extends RecyclerViewEmptySupport.ViewHolder  {
    RatingBar ratingBar;
    TextView ratingValue,userName,reviewDescription;

    public ReviewType(View itemView, RatingBar ratingBar, TextView ratingValue, TextView userName, TextView reviewDescription) {
        super(itemView);
        this.ratingBar = itemView.findViewById(R.id.currentRating);
        this.ratingValue = itemView.findViewById(R.id.ratingValue);
        this.userName = itemView.findViewById(R.id.userNameTV);
        this.reviewDescription = itemView.findViewById(R.id.reviewDescription);
    }

    public ReviewType(View itemView) {
        super(itemView);
        ratingBar = itemView.findViewById(R.id.currentRating);
        ratingValue = itemView.findViewById(R.id.ratingValue);
        userName = itemView.findViewById(R.id.userNameTV);
        reviewDescription = itemView.findViewById(R.id.reviewDescription);
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }

    public TextView getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(TextView ratingValue) {
        this.ratingValue = ratingValue;
    }

    public TextView getUserName() {
        return userName;
    }

    public void setUserName(TextView userName) {
        this.userName = userName;
    }

    public TextView getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(TextView reviewDescription) {
        this.reviewDescription = reviewDescription;
    }
}
