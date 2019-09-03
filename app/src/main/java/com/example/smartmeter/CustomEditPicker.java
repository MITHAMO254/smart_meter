package com.example.smartmeter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class CustomEditPicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    //
    // define edittext of type EditText.
    EditText editText;
    Context context;
    String date;

    public CustomEditPicker(Context context, int id){
        //
        // create an activity and assing it to context.
        Activity activity = (Activity)context;
        //
        //asign date_value to the editetxt.
        editText  = activity.findViewById(id);

        this.context = context;
        //
        //set onclick event listener to the EditText.
        editText.setOnClickListener(this);
    }

    //
    // Override the onDateSet method.
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int new_month = month + 1;
        //
        //Display the year, month and day on the edit text.
        editText.setText(year+"-"+new_month+"-"+dayOfMonth);
        date = year+"-"+new_month+"-"+dayOfMonth;

    }

    @Override
    public void onClick(View v) {
       //
       //get an instance of calender and store it in calendar.
        Calendar calendar = Calendar.getInstance();
        //
        //instantiate DatePickerDialog
        DatePickerDialog dialog = new DatePickerDialog(context, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


    public String getDate(){
        return date;
    }
}
