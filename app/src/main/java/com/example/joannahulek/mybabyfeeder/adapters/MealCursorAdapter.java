package com.example.joannahulek.mybabyfeeder.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.joannahulek.mybabyfeeder.R;
import com.example.joannahulek.mybabyfeeder.data.MealContract;

/**
 * Created by Joasia on 16.07.2017.
 */

public class MealCursorAdapter extends CursorAdapter {

    public MealCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.meals_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView mealTypeTextView = (TextView) view.findViewById(R.id.meal_type);
        TextView capacityTextView = (TextView) view.findViewById(R.id.capacity);
        TextView durationTextView = (TextView) view.findViewById(R.id.duration);
        TextView timeTextView = (TextView) view.findViewById(R.id.time);

        int mealTypeColumnIndex = cursor.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_TYPE);
        int capacityColumnIndex = cursor.getColumnIndex(MealContract.MealEntry.COLUMN_CAPACITY);
        int durationColumnIndex = cursor.getColumnIndex(MealContract.MealEntry.COLUMN_DURATION);
        int timeColumnIndex = cursor.getColumnIndex(MealContract.MealEntry.COLUMN_TIME);

        String mealType = cursor.getString(mealTypeColumnIndex);
        String capacity = cursor.getString(capacityColumnIndex);
        String duration = cursor.getString(durationColumnIndex);
        String time = cursor.getString(timeColumnIndex);

        mealTypeTextView.setText(mealType);
        capacityTextView.setText(capacity);
        durationTextView.setText(duration);
        timeTextView.setText(time);
    }
}
