package com.example.joannahulek.mybabyfeeder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joannahulek.mybabyfeeder.R;
import com.example.joannahulek.mybabyfeeder.specifics.MenuItem;

import java.util.List;

/**
 * Created by Joasia on 14.07.2017.
 */

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    private final int menuTemplateResource;

    public MenuItemAdapter(@NonNull Context context, @NonNull List<MenuItem> objects, int menuTemplateResource) {
        super(context, 0, objects);
        this.menuTemplateResource = menuTemplateResource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View menuItemView = convertView;
        if (menuItemView == null) {
            menuItemView = LayoutInflater.from(getContext()).inflate(menuTemplateResource, parent, false);
        }

        MenuItem currentItem = getItem(position);

        ImageView itemImageView = (ImageView) menuItemView.findViewById(R.id.item_image);
        itemImageView.setImageResource(Integer.parseInt(String.valueOf(currentItem.getImageResource())));

        TextView itemTextView = (TextView) menuItemView.findViewById(R.id.item_text);
        itemTextView.setText(currentItem.getTextResource());

        return menuItemView;
    }
}
