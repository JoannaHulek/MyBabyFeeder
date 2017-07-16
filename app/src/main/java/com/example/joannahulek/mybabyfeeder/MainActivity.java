package com.example.joannahulek.mybabyfeeder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import static com.example.joannahulek.mybabyfeeder.R.layout.add_meal_menu_item;
import static com.example.joannahulek.mybabyfeeder.R.layout.see_meals_menu_item;

public class MainActivity extends AppCompatActivity {

    public static final MenuItemStore ADD_MEAL_MENU_ITEM_STORE = new MenuItemStore();
    public static final MenuItemStore SEE_MEALS_LIST_MENU_ITEM_STORE = new MenuItemStore();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuItemAdapter addMealAdapter = new MenuItemAdapter(this, ADD_MEAL_MENU_ITEM_STORE.getAddMealMenuItemsArray(), R.layout.add_meal_menu_item);
        GridView addMealMenuGridView = (GridView) findViewById(R.id.add_meal_menu);
        addMealMenuGridView.setAdapter(addMealAdapter);

        MenuItemAdapter seeMealsListAdapter = new MenuItemAdapter(this, SEE_MEALS_LIST_MENU_ITEM_STORE.getSeeMealsListMenuItemsArray(), R.layout.see_meals_menu_item);
        GridView seeMealsListMenuGridView = (GridView) findViewById(R.id.see_meals_menu);
        seeMealsListMenuGridView.setAdapter(seeMealsListAdapter);
    }
}
