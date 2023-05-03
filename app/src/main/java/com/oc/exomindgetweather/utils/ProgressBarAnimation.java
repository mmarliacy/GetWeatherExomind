package com.oc.exomindgetweather.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.oc.exomindgetweather.view.MainDisplay;

public class ProgressBarAnimation extends Animation {

    private final Context context;
    private final ProgressBar fProgressBar;
    private final TextView percentIndicator;
    private final int from;
    private final int to;

    public ProgressBarAnimation(
            Context context,
            ProgressBar pProgressBar,
            TextView pPercentIndicator,
            int pFrom,
            int pTo) {
        this.context = context;
        this.fProgressBar = pProgressBar;
        this.percentIndicator = pPercentIndicator;
        this.from = pFrom;
        this.to = pTo;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        int value = (int) (from + (to - from) * interpolatedTime);
        percentIndicator.setText(value + " %");
        fProgressBar.setProgress((int) value);
        if(value == to){
            context.startActivity(new Intent(context, MainDisplay.class));
        }

    }


}
