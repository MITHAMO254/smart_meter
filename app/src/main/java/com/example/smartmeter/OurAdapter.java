package com.example.smartmeter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OurAdapter extends ArrayAdapter {
    private Context context;
    private List<Reading> readingList = new ArrayList<>();
    public OurAdapter(Context c, List<Reading> list){
        super(c, 0, list);
        this.context = c;
        this.readingList = list;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.single_row, parent, false);
            Reading reading = readingList.get(position);
            TextView date_of_reading = convertView.findViewById(R.id.date_value);
            TextView prev_value = convertView.findViewById(R.id.prev_value);
            TextView curr_value = convertView.findViewById(R.id.curr_value);
            TextView usage = convertView.findViewById(R.id.consump);

            date_of_reading.setText(reading.date);
            prev_value.setText(reading.previous);
            curr_value.setText(reading.current);
            usage.setText(reading.consumption);
        }
        return convertView;
    }

   }
