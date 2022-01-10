package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by admin on 15/05/2018.
 */

public class TopArtisanType extends RecyclerViewEmptySupport.ViewHolder {
    ImageView thumbnail;
    RatingBar ratingBar;
    RelativeLayout cardView;
    TextView title,artisan_name,current_rating_number,order_numbers;
    public TopArtisanType(View itemView) {
        super(itemView);
        this.thumbnail = itemView.findViewById(R.id.thumbnail);
        this.ratingBar = itemView.findViewById(R.id.currentRating);
        this.title = itemView.findViewById(R.id.title);
        this.artisan_name = itemView.findViewById(R.id.artisanName);
        this.current_rating_number = itemView.findViewById(R.id.currentRatingNumber);
        this.order_numbers = itemView.findViewById(R.id.currentOrder);
        this.cardView = itemView.findViewById(R.id.card_view);
    }

    public TopArtisanType(View itemView, ImageView thumbnail, RatingBar ratingBar, TextView title, TextView artisan_name, TextView current_rating_number, TextView order_numbers) {
        super(itemView);
        this.thumbnail = itemView.findViewById(R.id.thumbnail);
        this.ratingBar = itemView.findViewById(R.id.currentRating);
        this.title = itemView.findViewById(R.id.title);
        this.artisan_name = itemView.findViewById(R.id.artisanName);
        this.current_rating_number = itemView.findViewById(R.id.currentRatingNumber);
        this.order_numbers = itemView.findViewById(R.id.currentOrder);
        this.cardView = itemView.findViewById(R.id.card_view);
    }

    public TopArtisanType(View itemView, ImageView thumbnail, RatingBar ratingBar, CardView cardView, TextView title, TextView artisan_name, TextView current_rating_number, TextView order_numbers) {
        super(itemView);
        this.thumbnail = itemView.findViewById(R.id.thumbnail);
        this.ratingBar = itemView.findViewById(R.id.currentRating);
        this.title = itemView.findViewById(R.id.title);
        this.artisan_name = itemView.findViewById(R.id.artisanName);
        this.current_rating_number = itemView.findViewById(R.id.currentRatingNumber);
        this.order_numbers = itemView.findViewById(R.id.currentOrder);
        this.cardView = itemView.findViewById(R.id.card_view);
    }

    public RelativeLayout getCardView() {
        return cardView;
    }

    public void setCardView(RelativeLayout cardView) {
        this.cardView = cardView;
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

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getArtisan_name() {
        return artisan_name;
    }

    public void setArtisan_name(TextView artisan_name) {
        this.artisan_name = artisan_name;
    }

    public TextView getCurrent_rating_number() {
        return current_rating_number;
    }

    public void setCurrent_rating_number(TextView current_rating_number) {
        this.current_rating_number = current_rating_number;
    }

    public TextView getOrder_numbers() {
        return order_numbers;
    }

    public void setOrder_numbers(TextView order_numbers) {
        this.order_numbers = order_numbers;
    }
}
