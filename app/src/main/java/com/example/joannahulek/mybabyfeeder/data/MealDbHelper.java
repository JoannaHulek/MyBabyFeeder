package com.example.joannahulek.mybabyfeeder.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joasia on 15.07.2017.
 */

public class MealDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = MealDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "mealslist.db";
    private static final int DATABASE_VERSION = 1;


    public MealDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_MEALS_TABLE = "CREATE TABLE " + MealContract.MealEntry.TABLE_NAME + " ("
                + MealContract.MealEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MealContract.MealEntry.COLUMN_MEAL_TYPE + " MealType NOT NULL, "
                + MealContract.MealEntry.COLUMN_CAPACITY + " INTEGER NOT NULL, "
                + MealContract.MealEntry.COLUMN_DURATION + " INTEGER NOT NULL, "
                + MealContract.MealEntry.COLUMN_TIME + " DATE NOT NULL);";

        db.execSQL(SQL_CREATE_MEALS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}