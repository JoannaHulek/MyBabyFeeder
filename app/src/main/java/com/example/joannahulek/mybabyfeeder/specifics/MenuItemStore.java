package com.example.joannahulek.mybabyfeeder.specifics;

import com.example.joannahulek.mybabyfeeder.R;
import com.example.joannahulek.mybabyfeeder.data.MealType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static com.example.joannahulek.mybabyfeeder.data.MealType.LEFT;

/**
 * Created by Joasia on 14.07.2017.
 */

public class MenuItemStore implements Serializable {
    private final static List<MenuItem> ADD_MEAL_MENU_ITEMS = Arrays.asList(
            new MenuItem(R.drawable.left, R.string.add_left, MealType.LEFT),
            new MenuItem(R.drawable.right, R.string.add_right, MealType.RIGHT),
            new MenuItem(R.drawable.bottle, R.string.add_bottle, MealType.BOTTLE),
            new MenuItem(R.drawable.spoon, R.string.add_spoon, MealType.SPOON)
    );

    private final static List<MenuItem> SEE_MEALS_LIST_MENU_ITMS = Arrays.asList(
           new MenuItem(R.drawable.meals_list, R.string.see_meals, null)
    );

    private int currentPosition = 0;

    public List<MenuItem> getAddMealMenuItemsArray() {
        return ADD_MEAL_MENU_ITEMS;
    }

    public List<MenuItem> getSeeMealsListMenuItemsArray() {
        return SEE_MEALS_LIST_MENU_ITMS;
    }
    public MenuItem current(int position) {
        currentPosition = position;
        return current();
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public MenuItem current() {
        return ADD_MEAL_MENU_ITEMS.get(currentPosition);
    }
}

