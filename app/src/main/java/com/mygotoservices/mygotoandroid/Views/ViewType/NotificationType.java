package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.view.View;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by isaac on 12/9/18.
 */

public class NotificationType  extends RecyclerViewEmptySupport.ViewHolder  {
    TextView dateTime,information;
    public NotificationType(View itemView) {
        super(itemView);
        this.dateTime = itemView.findViewById(R.id.dateTime);
        this.information = itemView.findViewById(R.id.information);

    }

    public NotificationType(View itemView, TextView dateTime, TextView information) {
        super(itemView);
        this.dateTime = itemView.findViewById(R.id.dateTime);
        this.information = itemView.findViewById(R.id.information);
    }

    public TextView getDateTime() {
        return dateTime;
    }

    public void setDateTime(TextView dateTime) {
        this.dateTime = dateTime;
    }

    public TextView getInformation() {
        return information;
    }

    public void setInformation(TextView information) {
        this.information = information;
    }
}
