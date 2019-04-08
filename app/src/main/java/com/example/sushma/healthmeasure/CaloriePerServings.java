package com.example.sushma.healthmeasure;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CaloriePerServings extends AppCompatActivity {
    EditText textView;
    private RequestQueue mQueue;
    private Button saveButton;
    String finalCount;
    EditText showResult;
    EditText servings;
    String recieverFood;
    DatabaseHelper myDB;
    int recieverMealOption;
    String val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_per_servings);

        textView = (EditText) findViewById(R.id.food_txt);
        showResult = (EditText) findViewById((R.id.tot_calories_txt));
        servings = (EditText) findViewById(R.id.servings_txt);

        //Recieving data from previous activity
        Bundle extras = getIntent().getExtras();
        recieverFood = extras.getString("foodSelected");
        recieverMealOption = extras.getInt("mealAdded");


        saveButton = (Button) findViewById(R.id.saveButton_btn);

        textView.setText(recieverFood);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String servingsVal = servings.getText().toString();
                val = servingsVal + recieverFood;
                Log.d("post Call", "entered");
                jsonParse(val);

            }
        });


    }


    public void jsonParse(String val) {

        try {

            final String storeVal = val;
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("query", val);

            final String mRequestBody = jsonBody.toString();
            String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    String[] temp = response.split("\"nf_calories\":");

                    String[] calCount = temp[1].split(",");
                    finalCount = calCount[0];

                    showResult.setText(finalCount);

                    //INSETRT TO DB

                    myDB = new DatabaseHelper(getApplicationContext());
                    //Creating model
                    if(myDB.getDataBasedOnDate(getDate()).getCount() == 0){
                        CalorieEntryDTO calorieEntryDTO = getCalorieDTO();
                        boolean res = myDB.insertData(calorieEntryDTO);

                        if (res) {
                            Toast.makeText(CaloriePerServings.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        CalorieEntryDTO calorieEntryDTO = getCalorieDTO();
                        boolean isUpdate = myDB.updateData(calorieEntryDTO);
                        if (isUpdate) {
                            Toast.makeText(CaloriePerServings.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }
                    }



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_RESPONSE", error.getMessage());
                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                    headers.put("x-app-id", "a7204bb4");
                    headers.put("x-app-key", "88c2a1513a9f43b9ab83474a9a3d5ce7");
                    return headers;
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }

            };

            requestQueue.add(stringRequest);
        } catch (Exception e) {

        }

    }

    public CalorieEntryDTO getCalorieDTO(){
        String date = getDate();

        CalorieEntryDTO calorieEntryDTO = new CalorieEntryDTO(date);

        if(recieverMealOption == 1){
            calorieEntryDTO.setBreakfast(val);
            calorieEntryDTO.setBreakfast_calorie(finalCount);
            calorieEntryDTO.setSnack1("");
            calorieEntryDTO.setSnack1_calorie(0);
            calorieEntryDTO.setLunch("");
            calorieEntryDTO.setLunch_calorie(0);
            calorieEntryDTO.setSnack2("");
            calorieEntryDTO.setSnack2_calorie(0);
            calorieEntryDTO.setDinner("");
            calorieEntryDTO.setDinner_calorie(0);
        }
        else if(recieverMealOption == 2){
            calorieEntryDTO.setBreakfast("");
            calorieEntryDTO.setBreakfast_calorie(0);
            calorieEntryDTO.setSnack1(val);
            calorieEntryDTO.setSnack1_calorie(finalCount);
            calorieEntryDTO.setLunch("");
            calorieEntryDTO.setLunch_calorie(0);
            calorieEntryDTO.setSnack2("");
            calorieEntryDTO.setSnack2_calorie(0);
            calorieEntryDTO.setDinner("");
            calorieEntryDTO.setDinner_calorie(0);
        }
        else if(recieverMealOption == 3){
            calorieEntryDTO.setBreakfast("");
            calorieEntryDTO.setBreakfast_calorie(0);
            calorieEntryDTO.setSnack1("");
            calorieEntryDTO.setSnack1_calorie(0);
            calorieEntryDTO.setLunch(val);
            calorieEntryDTO.setLunch_calorie(finalCount);
            calorieEntryDTO.setSnack2("");
            calorieEntryDTO.setSnack2_calorie(0);
            calorieEntryDTO.setDinner("");
            calorieEntryDTO.setDinner_calorie(0);
        }

        else if(recieverMealOption == 4){
            calorieEntryDTO.setBreakfast("");
            calorieEntryDTO.setBreakfast_calorie(0);
            calorieEntryDTO.setSnack1("");
            calorieEntryDTO.setSnack1_calorie(0);
            calorieEntryDTO.setLunch("");
            calorieEntryDTO.setLunch_calorie(0);
            calorieEntryDTO.setSnack2(val);
            calorieEntryDTO.setSnack2_calorie(finalCount);
            calorieEntryDTO.setDinner("");
            calorieEntryDTO.setDinner_calorie(0);
        }
        else if(recieverMealOption == 5){
            calorieEntryDTO.setBreakfast("");
            calorieEntryDTO.setBreakfast_calorie(0);
            calorieEntryDTO.setSnack1("");
            calorieEntryDTO.setSnack1_calorie(0);
            calorieEntryDTO.setLunch("");
            calorieEntryDTO.setLunch_calorie(0);
            calorieEntryDTO.setSnack2("");
            calorieEntryDTO.setSnack2_calorie(0);
            calorieEntryDTO.setDinner(val);
            calorieEntryDTO.setDinner_calorie(finalCount);
        }

        return calorieEntryDTO;

    }

    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String date = sdf.format(new Date());
        return date;
    }




}
