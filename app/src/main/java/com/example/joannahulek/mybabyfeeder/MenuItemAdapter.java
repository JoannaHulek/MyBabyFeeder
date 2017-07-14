package com.example.joannahulek.mybabyfeeder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.R.attr.resource;

/**
 * Created by Joasia on 14.07.2017.
 */

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    public MenuItemAdapter(@NonNull Context context, @NonNull List<MenuItem> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View menuItemView = convertView;
        if (menuItemView == null) {
            menuItemView = LayoutInflater.from(getContext()).inflate(R.layout.add_meal_menu_item, parent, false);
        }

        MenuItem currentItem = getItem(position);

        ImageView itemImageView = (ImageView) menuItemView.findViewById(R.id.item_image);
        itemImageView.setImageResource(Integer.parseInt(String.valueOf(currentItem.getImageResource())));

        TextView itemTextView = (TextView) menuItemView.findViewById(R.id.item_text);
        itemTextView.setText(currentItem.getTextResource());

        return menuItemView;
    }
}
