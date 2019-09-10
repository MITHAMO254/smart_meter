package com.example.smartmeter.utility;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class CustomEditPicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText editText;
    Context context;
    String date;
    public CustomEditPicker(Context context, int id){
        Activity activity = (Activity)context;
        editText  = activity.findViewById(id);
        this.context = context;
        editText.setOnClickListener(this);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int new_month = month + 1;
        editText.setText(year+"-"+new_month+"-"+dayOfMonth);
        date = year+"-"+new_month+"-"+dayOfMonth;

    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(context, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


    public String getDate(){
        return date;
    }
}
