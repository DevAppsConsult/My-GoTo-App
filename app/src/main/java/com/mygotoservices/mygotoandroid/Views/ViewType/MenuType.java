package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.view.View;
import android.widget.Button;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by admin on 15/05/2018.
 */

public class MenuType extends RecyclerViewEmptySupport.ViewHolder {
    Button freeService,dailyOffer,bestPerson,playWin;
    public MenuType(View itemView) {
        super(itemView);
        freeService = itemView.findViewById(R.id.freeServiceBtn);
        dailyOffer = itemView.findViewById(R.id.dailyOfferBtn);
        bestPerson = itemView.findViewById(R.id.bestPersonBtn);
        playWin = itemView.findViewById(R.id.playWinBtn);
    }

    public MenuType(View itemView, Button freeService, Button dailyOffer, Button bestPerson, Button playWin) {
        super(itemView);
        this.freeService = itemView.findViewById(R.id.freeServiceBtn);
        this.dailyOffer = itemView.findViewById(R.id.dailyOfferBtn);
        this.bestPerson = itemView.findViewById(R.id.bestPersonBtn);
        this.playWin = itemView.findViewById(R.id.playWinBtn);
    }

    public Button getFreeService() {
        return freeService;
    }

    public void setFreeService(Button freeService) {
        this.freeService = freeService;
    }

    public Button getDailyOffer() {
        return dailyOffer;
    }

    public void setDailyOffer(Button dailyOffer) {
        this.dailyOffer = dailyOffer;
    }

    public Button getBestPerson() {
        return bestPerson;
    }

    public void setBestPerson(Button bestPerson) {
        this.bestPerson = bestPerson;
    }

    public Button getPlayWin() {
        return playWin;
    }

    public void setPlayWin(Button playWin) {
        this.playWin = playWin;
    }
}
