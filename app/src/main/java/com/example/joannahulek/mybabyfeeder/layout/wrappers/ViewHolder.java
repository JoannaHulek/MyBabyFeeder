package com.example.joannahulek.mybabyfeeder.layout.wrappers;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Joasia on 17.07.2017.
 */

public class ViewHolder {
    private final TextView mealType;
    private final TextView capacity;
    private final TextView duration;
    private final TextView time;

    public TextView getMealType() {
        return mealType;
    }

    public TextView getCapacity() {
        return capacity;
    }

    public TextView getDuration() {
        return duration;
    }

    public TextView getTime() {
        return time;
    }

    public ViewHolder(TextView mealType, TextView capacity, TextView duration, TextView time) {
        this.mealType = mealType;
        this.capacity = capacity;
        this.duration = duration;
        this.time = time;
    }
}