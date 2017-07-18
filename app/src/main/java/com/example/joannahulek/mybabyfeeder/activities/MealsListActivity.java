package com.example.joannahulek.mybabyfeeder.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.joannahulek.mybabyfeeder.R;
import com.example.joannahulek.mybabyfeeder.adapters.BabyMealCursorAdapter;
import com.example.joannahulek.mybabyfeeder.data.MealContract.MealEntry;

public class MealsListActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    BabyMealCursorAdapter mCursorAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_list);

        ListView mealsListView = (ListView) findViewById(R.id.meals_list_view);

        View emptyView = findViewById(R.id.empty_view);
        mealsListView.setEmptyView(emptyView);

        mCursorAdapter = new BabyMealCursorAdapter(this, null);
        mealsListView.setAdapter(mCursorAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                MealEntry._ID,
                MealEntry.COLUMN_MEAL_TYPE,
                MealEntry.COLUMN_CAPACITY,
                MealEntry.COLUMN_DURATION,
                MealEntry.COLUMN_TIME};


        return new CursorLoader(this,   // Parent activity context
                MealEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update {@link PetCursorAdapter} with this new cursor containing updated pet data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);
    }
}
