package com.example.joannahulek.mybabyfeeder.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.joannahulek.mybabyfeeder.specifics.BabyMeal;
import com.example.joannahulek.mybabyfeeder.specifics.MenuItem;
import com.example.joannahulek.mybabyfeeder.adapters.MenuItemAdapter;
import com.example.joannahulek.mybabyfeeder.specifics.MenuItemStore;
import com.example.joannahulek.mybabyfeeder.R;
import com.example.joannahulek.mybabyfeeder.data.MealContract;
import com.example.joannahulek.mybabyfeeder.data.MealType;

import java.util.Date;

public class MealsActivity extends AppCompatActivity {

    public static final String LOG_TAG = MealsActivity.class.getSimpleName();
    public static final MenuItemStore ADD_MEAL_MENU_ITEM_STORE = new MenuItemStore();
    public static final MenuItemStore SEE_MEALS_LIST_MENU_ITEM_STORE = new MenuItemStore();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        MenuItemAdapter addMealAdapter = new MenuItemAdapter(this, ADD_MEAL_MENU_ITEM_STORE.getAddMealMenuItemsArray(), R.layout.add_meal_menu_item);
        GridView addMealMenuGridView = (GridView) findViewById(R.id.add_meal_menu);
        addMealMenuGridView.setAdapter(addMealAdapter);

        addMealMenuGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ADD_MEAL_MENU_ITEM_STORE.current(position);
                MenuItem current = ADD_MEAL_MENU_ITEM_STORE.current();
                Intent i = new Intent(MealsActivity.this, EditorActivity.class);
                i.putExtra("meal_type", current.getMealType());
                startActivity(i);
            }
        });

        Log.i(LOG_TAG,BabyMeal.DATE_INSTANCE.format(new Date()));
        MenuItemAdapter seeMealsListAdapter = new MenuItemAdapter(this, SEE_MEALS_LIST_MENU_ITEM_STORE.getSeeMealsListMenuItemsArray(), R.layout.see_meals_menu_item);
        GridView seeMealsListMenuGridView = (GridView) findViewById(R.id.see_meals_menu);
        seeMealsListMenuGridView.setAdapter(seeMealsListAdapter);

        seeMealsListMenuGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MealsActivity.this, MealsListActivity.class);
                startActivity(i);
            }
        });
    }
}
