package com.example.joannahulek.mybabyfeeder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    public static final MenuItemStore ADD_MEAL_MENU_ITEM_STORE = new MenuItemStore();
    public static final MenuItemStore SEE_MEALS_LIST_MENU_ITEM_STORE = new MenuItemStore();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuItemAdapter addMealAdapter = new MenuItemAdapter(this, ADD_MEAL_MENU_ITEM_STORE.getAddMealMenuItemsArray());
        GridView addMealMenuGridView = (GridView) findViewById(R.id.add_meal_menu);
        addMealMenuGridView.setAdapter(addMealAdapter);

        MenuItemAdapter seeMealsListAdapter = new MenuItemAdapter(this, SEE_MEALS_LIST_MENU_ITEM_STORE.getSeeMealsListMenuItemsArray());
        GridView seeMealsListMenuGridView = (GridView) findViewById(R.id.see_meals_menu);
        seeMealsListMenuGridView.setAdapter(seeMealsListAdapter);
    }
}
