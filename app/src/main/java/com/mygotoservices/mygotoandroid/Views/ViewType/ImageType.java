package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.view.View;
import android.widget.ImageView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by isaac on 5/19/18.
 */

public class ImageType extends RecyclerViewEmptySupport.ViewHolder  {
    ImageView imageView;
    public ImageType(View itemView, ImageView imageView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.thumbnail);
    }

    public ImageType(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.thumbnail);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
