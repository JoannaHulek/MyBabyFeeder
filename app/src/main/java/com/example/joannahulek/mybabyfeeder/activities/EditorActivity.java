package com.example.joannahulek.mybabyfeeder.activities;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.joannahulek.mybabyfeeder.R;
import com.example.joannahulek.mybabyfeeder.data.MealContract;
import com.example.joannahulek.mybabyfeeder.data.MealContract.MealEntry;
import com.example.joannahulek.mybabyfeeder.data.MealType;
import com.example.joannahulek.mybabyfeeder.fragments.DatePickerFragment;
import com.example.joannahulek.mybabyfeeder.specifics.BabyMeal;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Date;

import static android.R.attr.type;
import static com.example.joannahulek.mybabyfeeder.R.drawable.meal;

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
        final EditText timeEditText = (EditText) findViewById(R.id.time_input);

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
                addMeal(capacityEditText, durationEditText, timeEditText, mealType);
            }
        });


    }

    private void addMeal(EditText capacityEditText, EditText durationEditText, EditText timeEditText, MealType mealType) {
        Short capacity = new Short(capacityEditText.getText().toString());
        Short duration = new Short(durationEditText.getText().toString());

        BabyMeal meal = null;
        try {
            meal = new BabyMeal(null, mealType, capacity, duration, BabyMeal.DATE_INSTANCE.parse(timeEditText.getText().toString()));
        } catch (ParseException e) {
            throw new InvalidParameterException(timeEditText.getText().toString() + " has bad format");
        }
        Uri newUri = getContentResolver().insert(MealEntry.CONTENT_URI, meal.transformToContentValues());
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
