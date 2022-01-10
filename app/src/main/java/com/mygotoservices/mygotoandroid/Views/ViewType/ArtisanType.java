package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by isaac on 5/14/18.
 */

public class ArtisanType extends RecyclerViewEmptySupport.ViewHolder {
    TextView mainName,location,type,number_orders,avg_ratings,artisanCategory;
    ImageView thumbnail;
    RatingBar ratingBar;
    RelativeLayout cardView;

    public ArtisanType(@NonNull View itemView, TextView mainName, TextView location, TextView type, TextView number_orders, TextView avg_ratings, TextView artisanCategory, ImageView thumbnail, RatingBar ratingBar, RelativeLayout cardView) {
        super(itemView);
        this.mainName = mainName;
        this.location = location;
        this.type = type;
        this.number_orders = number_orders;
        this.avg_ratings = avg_ratings;
        this.artisanCategory = artisanCategory;
        this.thumbnail = thumbnail;
        this.ratingBar = ratingBar;
        this.cardView = cardView;
    }

    public ArtisanType(View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.card_view);
        mainName = itemView.findViewById(R.id.title);
        location = itemView.findViewById(R.id.artisanLocation);
        type = itemView.findViewById(R.id.artisanName);
        number_orders = itemView.findViewById(R.id.currentOrder);
        avg_ratings = itemView.findViewById(R.id.currentRatingNumber);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        ratingBar = itemView.findViewById(R.id.currentRating);
        artisanCategory = itemView.findViewById(R.id.artisanCategory);
    }

    public TextView getArtisanCategory() {
        return artisanCategory;
    }

    public void setArtisanCategory(TextView artisanCategory) {
        this.artisanCategory = artisanCategory;
    }

    public ArtisanType(View itemView, TextView name, TextView location, TextView type, TextView number_orders, TextView avg_ratings, ImageView thumbnail, RatingBar ratingBar) {
        super(itemView);
        this.cardView = itemView.findViewById(R.id.card_view);
        this.mainName = itemView.findViewById(R.id.title);
        this.location = itemView.findViewById(R.id.artisanLocation);
        this.type = itemView.findViewById(R.id.artisanName);
        this.number_orders = itemView.findViewById(R.id.currentOrder);
        this.avg_ratings = itemView.findViewById(R.id.currentRatingNumber);
        this.thumbnail = itemView.findViewById(R.id.thumbnail);
        this.ratingBar = itemView.findViewById(R.id.currentRating);
    }

    public ArtisanType(View itemView, TextView mainName, TextView location, TextView type, TextView number_orders, TextView avg_ratings, ImageView thumbnail, RatingBar ratingBar, RelativeLayout cardView) {
        super(itemView);
        this.mainName = mainName;
        this.location = location;
        this.type = type;
        this.number_orders = number_orders;
        this.avg_ratings = avg_ratings;
        this.thumbnail = thumbnail;
        this.ratingBar = ratingBar;
        this.cardView = cardView;
    }

    public RelativeLayout getCardView() {
        return cardView;
    }

    public void setCardView(RelativeLayout cardView) {
        this.cardView = cardView;
    }

    public TextView getMainName() {
        return mainName;
    }

    public void setMainName(TextView mainName) {
        this.mainName = mainName;
    }

    public TextView getLocation() {
        return location;
    }

    public void setLocation(TextView location) {
        this.location = location;
    }

    public TextView getType() {
        return type;
    }

    public void setType(TextView type) {
        this.type = type;
    }

    public TextView getNumber_orders() {
        return number_orders;
    }

    public void setNumber_orders(TextView number_orders) {
        this.number_orders = number_orders;
    }

    public TextView getAvg_ratings() {
        return avg_ratings;
    }

    public void setAvg_ratings(TextView avg_ratings) {
        this.avg_ratings = avg_ratings;
    }

    public ImageView getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImageView thumbnail) {
        this.thumbnail = thumbnail;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }
}
