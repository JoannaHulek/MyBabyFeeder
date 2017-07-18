package com.example.joannahulek.mybabyfeeder.specifics;

import com.example.joannahulek.mybabyfeeder.data.MealType;

import java.io.Serializable;

/**
 * Created by Joasia on 14.07.2017.
 */

public class MenuItem implements Serializable {
    private final int imageResource;
    private final int textResource;
    private final MealType mealType;

    public MenuItem(int imgResource, int textResource, MealType mealType) {
        this.imageResource = imgResource;
        this.textResource = textResource;
        this.mealType = mealType;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getTextResource() {
        return textResource;
    }

    public MealType getMealType() {
        return mealType;
    }
}
