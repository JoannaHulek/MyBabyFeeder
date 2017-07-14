package com.example.joannahulek.mybabyfeeder;

import java.util.List;

/**
 * Created by Joasia on 14.07.2017.
 */

public class MenuItem {
    private final int imageResource;
    private final int textResource;

    public MenuItem(int imgResource, int textResource) {
        this.imageResource = imgResource;
        this.textResource = textResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getTextResource() {
        return textResource;
    }
}
