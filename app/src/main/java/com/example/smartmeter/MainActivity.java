package com.example.smartmeter;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String TAG = "mutall";
    private JSONArray jsonArray;
    private List<Reading> readingsList;
    ListView listView;
    private String meter_no, date_from, date_to;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        meter_no = getIntent().getStringExtra("meter");
        date_from = getIntent().getStringExtra("date_from");
        date_to = getIntent().getStringExtra("date_to");
        dialog = new ProgressDialog(this);

        listView = findViewById(R.id.list);
        readingsList = new ArrayList<>();
        getMeterReadings();



    }

    public void getMeterReadings(){
        dialog.setMessage("Fetching from Server");
        dialog.show();
        //creating a request queue
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://afriot.net/afriot_consumer_api/afriotAPI.php";

        StringRequest readings = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                convertStringToJson(response);
                try {
                    JSONObject object = jsonArray.getJSONObject(1);
                    JSONArray readings = object.getJSONArray("modem_usage");
                    if(readings.length() < 1 ){
                        Toast.makeText(MainActivity.this, "No READINGS FOR THE SPECIFIED DATES", Toast.LENGTH_LONG).show();
                    }

                    for (int i = 0; i<readings.length(); i++){
                        JSONObject individual = readings.getJSONObject(i);
                        String prev_value = individual.getString("initial_reading");
                        String current_value = individual.getString("final_reading");
                        String date = individual.getString("flow_timestamp");
                        String usage = individual.getString("consumption_estimate");

                        Reading reading = new Reading(prev_value, current_value,date, usage);
                        readingsList.add(reading);
                    }

                    OurAdapter adapter = new OurAdapter(MainActivity.this, readingsList);
                    listView.setAdapter(adapter);
                }catch (JSONException e){
                    Log.e(TAG, e.getMessage());
                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("auth_key", "0203ndfwu506bxfuopva5ybdge");
                params.put("id_request", "13");
                params.put("modem_hex", meter_no);
                params.put("from_date", date_from);
                params.put("to_date", date_to);

                return params;
            }
        };
        queue.add(readings);

    }

    private void convertStringToJson(String data){
        try {
            jsonArray = new JSONArray(data);
        }catch (JSONException e){
            Log.e(TAG, e.getMessage());
        }
    }

}
