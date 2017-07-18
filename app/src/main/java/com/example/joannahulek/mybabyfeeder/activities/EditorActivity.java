package com.example.joannahulek.mybabyfeeder.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joannahulek.mybabyfeeder.R;
import com.example.joannahulek.mybabyfeeder.data.MealContract;
import com.example.joannahulek.mybabyfeeder.data.MealContract.MealEntry;
import com.example.joannahulek.mybabyfeeder.data.MealType;
import com.example.joannahulek.mybabyfeeder.fragments.DatePickerFragment;
import com.example.joannahulek.mybabyfeeder.specifics.BabyMeal;

import java.security.InvalidParameterException;
import java.text.ParseException;

import static com.example.joannahulek.mybabyfeeder.specifics.BabyMeal.DATE_INSTANCE;

public class EditorActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_editor);

        final MealType mealType = (MealType) getIntent().getExtras().get("meal_type");
        createMealTypeImage(mealType);

        final EditText capacityEditText = (EditText) findViewById(R.id.capacity_input);
        final EditText durationEditText = (EditText) findViewById(R.id.duration_input);
        final TextView timeTextView = (TextView) findViewById(R.id.time_input);

        Button timeButton = (Button) findViewById(R.id.time_button);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        Button addMealButton = (Button) findViewById(R.id.add_meal_button);
        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMeal(capacityEditText, durationEditText, timeTextView, mealType);
            }
        });
    }

    private void addMeal(EditText capacityEditText, EditText durationEditText, TextView timeTextView, MealType mealType) {
        BabyMeal meal = null;
        Uri mealUri = null;
        try {
            Short capacity = new Short(capacityEditText.getText().toString());
            Short duration = new Short(durationEditText.getText().toString());
            meal = new BabyMeal(null, mealType, capacity, duration, DATE_INSTANCE.parse(timeTextView.getText().toString()));
            mealUri = getContentResolver().insert(MealEntry.CONTENT_URI, meal.transformToContentValues());
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.insert_failed), Toast.LENGTH_SHORT).show();
        }
        if (mealUri != null) {
            Toast.makeText(EditorActivity.this, getString(R.string.meal_saved), Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(EditorActivity.this, MealsActivity.class);
        startActivity(i);
    }

    private void createMealTypeImage(MealType mealType) {
        ImageView mealTypeImageView = (ImageView) findViewById(R.id.meal_type_image);

        switch (mealType) {
            case LEFT:
                mealTypeImageView.setImageResource(R.drawable.left);
                break;
            case RIGHT:
                mealTypeImageView.setImageResource(R.drawable.right);
                break;
            case BOTTLE:
                mealTypeImageView.setImageResource(R.drawable.bottle);
                break;
            case SPOON:
                mealTypeImageView.setImageResource(R.drawable.spoon);
                break;
        }
    }
}
