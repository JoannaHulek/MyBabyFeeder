package com.example.joannahulek.mybabyfeeder.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Joasia on 15.07.2017.
 */

public class MealContract {

    private MealContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.joannahulek.mybabyfeeder";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MEALS = "meals";

    public static final class MealEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MEALS);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEALS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEALS;

        public final static String TABLE_NAME = "meals";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_MEAL_TYPE = "meal";

        public final static String COLUMN_CAPACITY = "capacity";

        public final static String COLUMN_DURATION = "duration";

        public final static String COLUMN_TIME = "time";
    }
}

