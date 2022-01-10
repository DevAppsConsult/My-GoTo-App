package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by isaacbd on 04/12/2018.
 */

public class EndedNotificationType  extends RecyclerViewEmptySupport.ViewHolder  {
    TextView dateTime,information;
    Button confirmButton;

    public EndedNotificationType(View itemView, TextView dateTime, TextView information, Button confirmButton) {
        super(itemView);
        this.dateTime = itemView.findViewById(R.id.dateTime);
        this.information = itemView.findViewById(R.id.information);
        this.confirmButton = itemView.findViewById(R.id.confirmButton);
    }

    public EndedNotificationType(View itemView) {
        super(itemView);
        this.dateTime = itemView.findViewById(R.id.dateTime);
        this.information = itemView.findViewById(R.id.information);
        this.confirmButton = itemView.findViewById(R.id.confirmButton);

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

    public Button getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(Button confirmButton) {
        this.confirmButton = confirmButton;
    }
}
