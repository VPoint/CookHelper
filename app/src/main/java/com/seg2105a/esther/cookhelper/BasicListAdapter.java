package com.seg2105a.esther.cookhelper;

/**
 * Created by Esther on 2016-12-05.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BasicListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    public BasicListAdapter(Context context, String[] values) {
        super(context, R.layout.activity_recipe_list_layout, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_default_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.itemName);
        textView.setText(values[position]);
        return rowView;
    }
}
