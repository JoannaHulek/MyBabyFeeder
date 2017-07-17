package com.example.joannahulek.mybabyfeeder.specifics;

import android.content.ContentValues;

import com.example.joannahulek.mybabyfeeder.data.MealContract;
import com.example.joannahulek.mybabyfeeder.data.MealContract.MealEntry;
import com.example.joannahulek.mybabyfeeder.data.MealType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Joasia on 17.07.2017.
 */

public class BabyMeal {
    public final static DateFormat DATE_INSTANCE = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private final String id;
    private final MealType type;
    private final Short capacity;
    private final Short duration;
    private final Date time;

    public BabyMeal(String id, MealType type, Short capacity, Short duration, Date time) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
        this.duration = duration;
        this.time = time;
    }
    public ContentValues transformToContentValues(){
        ContentValues values = new ContentValues();
        values.put(MealEntry.COLUMN_MEAL_TYPE, type.name());
        values.put(MealEntry.COLUMN_CAPACITY, capacity);
        values.put(MealEntry.COLUMN_DURATION, duration);
        values.put(MealEntry.COLUMN_TIME, DATE_INSTANCE.format(time));
        return values;
    }
}
