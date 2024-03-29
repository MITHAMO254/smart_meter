package com.example.smartmeter.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartmeter.R;
import com.example.smartmeter.models.Meter;
import com.example.smartmeter.utility.CustomEditPicker;

public class SpinnerActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView tview;
    private static final String TAG = "mutall";
    private Meter meter;
    private String meter_hex;
    private Button btn;
    private CustomEditPicker picker1, picker2;
    String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner_names);
        tview = findViewById(R.id.textView2);
        btn = findViewById(R.id.fetch);


        picker1 = new CustomEditPicker(this, R.id.date_from);
        picker2 = new CustomEditPicker(this, R.id.date_to);
        meter = new Meter();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               name = parent.getItemAtPosition(position).toString();
               try {
                   int meter_number = meter.getMeter(name);
                   tview.setText("The Selected meter is " + meter_number);
                   meter_hex = Integer.toHexString(meter_number);
               }catch (NullPointerException e){
                   Toast.makeText(SpinnerActivity.this, "Meter not found", Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SpinnerActivity.this, "nothing has been selected", Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    Intent intent = new Intent(SpinnerActivity.this, MainActivity.class);
                    intent.putExtra("meter", meter_hex);
                    intent.putExtra("date_from", picker1.getDate());
                    intent.putExtra("date_to", picker2.getDate());
                    intent.putExtra("name", name);
                    startActivity(intent);
                }else {
                    Toast.makeText(SpinnerActivity.this, "EDIT TEXT CANNOT BE EMPTY", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public boolean validate(){
        boolean result = true;
        EditText date_from = findViewById(R.id.date_from);
        EditText date_to = findViewById(R.id.date_to);

        if(date_from.getText().toString().isEmpty()){
            result = false;
        }

        if(date_to.getText().toString().isEmpty()){
            result = false;
        }

        return result;
    }
}


//date_from.setOnClickListener(new View.OnClickListener() {
//@TargetApi(Build.VERSION_CODES.N)
//@Override
//public void onClick(View v) {
//        Calendar calendar = Calendar.getInstance();
//        DatePickerDialog dialog  = new DatePickerDialog(SpinnerActivity.this, new DatePickerDialog.OnDateSetListener() {
//@Override
//public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//
//        Log.i(TAG, year+"-"+month+"-"+dayOfMonth);
//        date_from.setText(year+"-"+month+"-"+dayOfMonth);
//        }
//        },calendar.get(Calendar.YEAR) , calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
//        dialog.show();
//        }
//        });
//
//        date_to.setOnClickListener(new View.OnClickListener() {
//@TargetApi(Build.VERSION_CODES.N)
//@Override
//public void onClick(View v) {
//        Calendar calendar = Calendar.getInstance();
//        DatePickerDialog dialog  = new DatePickerDialog(SpinnerActivity.this, new DatePickerDialog.OnDateSetListener() {
//@Override
//public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//
//        Log.i(TAG, year+"-"+month+"-"+dayOfMonth);
//        date_to.setText(year+"-"+month+"-"+dayOfMonth);
//        }
//        },calendar.get(Calendar.YEAR) , calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
//        dialog.show();
//        }
//        });