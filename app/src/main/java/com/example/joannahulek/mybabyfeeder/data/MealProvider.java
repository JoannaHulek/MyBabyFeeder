package com.example.joannahulek.mybabyfeeder.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.joannahulek.mybabyfeeder.data.MealContract.MealEntry;

import java.text.ParseException;
import java.util.Date;

import static com.example.joannahulek.mybabyfeeder.specifics.BabyMeal.DATE_INSTANCE;
import static com.example.joannahulek.mybabyfeeder.data.MealContract.*;
import static com.example.joannahulek.mybabyfeeder.data.MealDbHelper.LOG_TAG;

/**
 * Created by Joasia on 15.07.2017.
 */

public class MealProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int MEALS = 100;
    private static final int MEAL_ID = 101;

    static {
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_MEALS, MEALS);
        sUriMatcher.addURI(CONTENT_AUTHORITY, PATH_MEALS + "/#", MEAL_ID);
    }

    private MealDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new MealDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case MEALS:
                cursor = database.query(MealEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case MEAL_ID:
                selection = MealEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(MealEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MEALS:
                return MealEntry.CONTENT_LIST_TYPE;
            case MEAL_ID:
                return MealEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MEALS:
                return insertMeal(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertMeal(Uri uri, ContentValues values) {
        validateValues(values);

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(MealEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    private void validateValues(ContentValues values) {
        String mealTypeAsString = values.getAsString(MealEntry.COLUMN_MEAL_TYPE);
        if (mealTypeAsString == null) {
            throw new IllegalArgumentException("Select type of meal");
        }
        MealType mealType = MealType.valueOf(mealTypeAsString);

        Short capacity = values.getAsShort(MealEntry.COLUMN_CAPACITY);
        if (capacity == null || capacity < 0) {
            throw new IllegalArgumentException("Set capacity of meal [ml]");
        }

        Short duration = values.getAsShort(MealEntry.COLUMN_DURATION);
        if (duration == null || duration < 0) {
            throw new IllegalArgumentException("Set duration time [min]");
        }

        try {
            Date date = DATE_INSTANCE.parse(values.getAsString(MealEntry.COLUMN_TIME));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Set date and time of meal");
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MEALS:
                rowsDeleted = database.delete(MealEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case MEAL_ID:
                selection = MealEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(MealEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MEALS:
                return updateMeal(uri, values, selection, selectionArgs);
            case MEAL_ID:
                selection = MealEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateMeal(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateMeal(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(MealEntry.COLUMN_MEAL_TYPE)) {
            MealType mealType = MealType.valueOf(values.getAsString(MealEntry.COLUMN_MEAL_TYPE));
            if (mealType == null) {
                throw new IllegalArgumentException("Select type of meal");
            }
        }

        if (values.containsKey(MealEntry.COLUMN_CAPACITY)) {
            Short capacity = values.getAsShort(MealEntry.COLUMN_CAPACITY);
            if (capacity == null || capacity < 0) {
                throw new IllegalArgumentException("Set capacity of meal [ml]");
            }
        }

        if (values.containsKey(MealEntry.COLUMN_DURATION)) {
            Byte duration = values.getAsByte(MealEntry.COLUMN_DURATION);
            if (duration == null || duration < 0) {
                throw new IllegalArgumentException("Set duration time [min]");
            }
        }

        if (values.containsKey(MealEntry.COLUMN_TIME)) {
            try {
                Date date = DATE_INSTANCE.parse(values.getAsString(MealEntry.COLUMN_TIME));
            } catch (ParseException e) {
                throw new IllegalArgumentException("Set date and time of meal");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(MealEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}