package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by isaacbd on 04/12/2018.
 */

public class EstimateNotificationType extends RecyclerViewEmptySupport.ViewHolder  {
    TextView dateTime,information;
    Button confirmButton,declineButton;

    public EstimateNotificationType(View itemView, TextView dateTime, TextView information, Button confirmButton) {
        super(itemView);
        this.dateTime = itemView.findViewById(R.id.dateTime);
        this.information = itemView.findViewById(R.id.information);
        this.confirmButton = itemView.findViewById(R.id.confirmButton);
    }

    public EstimateNotificationType(@NonNull View itemView, TextView dateTime, TextView information, Button confirmButton, Button declineButton) {
        super(itemView);
        this.dateTime = itemView.findViewById(R.id.dateTime);
        this.information = itemView.findViewById(R.id.information);
        this.confirmButton = itemView.findViewById(R.id.confirmButton);
        this.declineButton = itemView.findViewById(R.id.declineButton);
    }

    public EstimateNotificationType(View itemView) {
        super(itemView);
        this.dateTime = itemView.findViewById(R.id.dateTime);
        this.information = itemView.findViewById(R.id.information);
        this.confirmButton = itemView.findViewById(R.id.confirmButton);
        this.declineButton = itemView.findViewById(R.id.declineButton);
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

    public Button getDeclineButton() {
        return declineButton;
    }

    public void setDeclineButton(Button declineButton) {
        this.declineButton = declineButton;
    }

    public void setConfirmButton(Button confirmButton) {
        this.confirmButton = confirmButton;
    }
}
