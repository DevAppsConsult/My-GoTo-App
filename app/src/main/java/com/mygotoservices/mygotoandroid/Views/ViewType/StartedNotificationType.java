package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.view.View;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by isaacbd on 04/12/2018.
 */

public class StartedNotificationType extends RecyclerViewEmptySupport.ViewHolder  {
    TextView dateTime,information;

    public StartedNotificationType(View itemView, TextView dateTime, TextView information) {
        super(itemView);
        this.dateTime = itemView.findViewById(R.id.dateTime);
        this.information = itemView.findViewById(R.id.information);
    }

    public StartedNotificationType(View itemView) {
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
