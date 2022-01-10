package com.mygotoservices.mygotoandroid.Views.ViewType;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mygotoservices.mygotoandroid.Misc.RecyclerViewEmptySupport;
import com.mygotoservices.mygotoandroid.R;

/**
 * Created by isaac on 5/14/18.
 */

public class SliderType extends RecyclerViewEmptySupport.ViewHolder {
    public SliderType(View itemView) {
        super(itemView);
        this.viewPager = (ViewPager) itemView.findViewById(R.id.view_pager);
        this.layoutDots = (LinearLayout) itemView.findViewById(R.id.layoutDots);
        this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative);
        this.headline = (TextView) itemView.findViewById(R.id.headline);
    }
    private ViewPager viewPager;
    private LinearLayout layoutDots;
    private TextView headline;
    private RelativeLayout relativeLayout;

    public SliderType(View itemView, ViewPager viewPager, LinearLayout layoutDots, TextView headline, RelativeLayout relativeLayout) {
        super(itemView);
        this.viewPager = (ViewPager) itemView.findViewById(R.id.view_pager);
        this.layoutDots = (LinearLayout) itemView.findViewById(R.id.layoutDots);
        this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative);
        this.headline = (TextView) itemView.findViewById(R.id.headline);
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public LinearLayout getLayoutDots() {
        return layoutDots;
    }

    public void setLayoutDots(LinearLayout layoutDots) {
        this.layoutDots = layoutDots;
    }

    public TextView getHeadline() {
        return headline;
    }

    public void setHeadline(TextView headline) {
        this.headline = headline;
    }

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public void setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
    }
}
