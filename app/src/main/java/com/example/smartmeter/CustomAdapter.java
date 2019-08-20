package com.example.smartmeter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private Context cx;
    private List<Reading> list = new ArrayList<>();

    public CustomAdapter(Context context, List<Reading> list){
        super(context, 0, list);
        this.cx = context;
        this.list = list;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        if (convertView ==  null){
            convertView = LayoutInflater.from(cx).inflate(R.layout.single_row, parent, false);
            Reading single_reading = list.get(position);
            //get the text view
            TextView date = convertView.findViewById(R.id.date_value);
            TextView previous = convertView.findViewById(R.id.prev_value);
            TextView current = convertView.findViewById(R.id.curr_value);
            TextView usage = convertView.findViewById(R.id.consump);

            //set the values
            previous.setText(single_reading.previous);
            date.setText(single_reading.date);
            current.setText(single_reading.current);
            usage.setText(single_reading.consumption);
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}


