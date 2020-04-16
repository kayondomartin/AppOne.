package com.example.volleyweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView intro;
    Thread thread;
    TextView result;
    EditText getcity;
    Button search;
    public static final String BaseUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String api = "&appid=ea4fe7082e28ec795f0202dd24c16189";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        intro = findViewById(R.id.instruction);
        result = findViewById(R.id.result);
        getcity = findViewById(R.id.getCity);
        search = findViewById(R.id.searchBtn);


          search.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String BASE_URL = new String();
                  if(getcity.getText().toString().equalsIgnoreCase("") || getcity.getText()==null)
                  {
                      Toast.makeText(getApplicationContext(), "Please enter a city name." , Toast.LENGTH_LONG);

                  }else {

                      BASE_URL = new String( BaseUrl + getcity.getText().toString() + api);
                  }
                  JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, BASE_URL, null,
                          new Response.Listener<JSONObject>() {
                              @Override
                              public void onResponse(JSONObject response) {

                                  try {
                                      String res = "";
                                      //"main":{"temp":295.15,"feels_like":298.63,"temp_min":295.15,"temp_max":295.15,"pressure":1018,"humidity":94}
                                      JSONObject jsonObject = response.getJSONObject("main");
                                      res+= "City: " +response.getString("name")+
                                              "\nTemperature: " + jsonObject.getInt("temp")+"Kelvin"
                                              +"\nPressure: "+jsonObject.getInt("pressure")+"pascal"+
                                              "\nHumidity: "+jsonObject.getInt("humidity")+"percentage";
                                      result.setText(res);

                                  } catch (JSONException ex) {
                                      ex.printStackTrace();
                                  }


                              }
                          }, new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "An error occurred.", Toast.LENGTH_LONG).show();
                      }
                  }
                  );
                  Singleton.getSingleton(getApplicationContext()).addRequestQueue(json);

              }
          });


    }
}
