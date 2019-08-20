package com.example.smartmeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        readingsList = new ArrayList<>();
        getMeterReadings();

    }
    public void getMeterReadings(){
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

                    for (int i = 0; i<readings.length(); i++){
                        JSONObject individual = readings.getJSONObject(i);
                        String prev_value = individual.getString("initial_reading");
                        String current_value = individual.getString("final_reading");
                        String date = individual.getString("flow_timestamp");
                        String usage = individual.getString("consumption_estimate");

                        Reading reading = new Reading(prev_value, current_value,date, usage);
                        readingsList.add(reading);
                    }

                    CustomAdapter adapter = new CustomAdapter(MainActivity.this, readingsList);
                    listView.setAdapter(adapter);

                }catch (JSONException e){
                    Log.e(TAG, e.getMessage());
                }
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
                params.put("modem_hex", "72E1C5");
                params.put("from_date", "2019-08-06");
                params.put("to_date", "2019-08-15");

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
