package com.example.smartmeter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.TimeZone;

public class EditDatePicker implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    private Context _context;
    private EditText _editText;


    public EditDatePicker(Context context, int resId){
        this._context = context;
        Activity act = (Activity)context;
        this._editText = act.findViewById(resId);
        this._editText.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder builder = new StringBuilder()
                                    .append(year)
                                    .append("-")
                                    .append(month)
                                    .append("-")
                                    .append(dayOfMonth);
        _editText.setText(builder.toString());
        Log.i("mutall", _editText.getText().toString());

    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(_context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
