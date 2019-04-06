package com.example.sushma.healthmeasure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CaloriePerServings extends AppCompatActivity {
    TextView textView;
    private RequestQueue mQueue;
    private Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_per_servings);

        //textView = (TextView) findViewById(R.id.food_txt);
        //String recieverFood = getIntent().getStringExtra("foodSelected");
        saveButton = (Button) findViewById(R.id.saveButton_btn);
        //Log.d("Post",saveButton.toString());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("post Call","entered");
                //jsonParse();
            }
        });


        //textView.setText(recieverFood);

    }

    public void jsonParse(){

        String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";
        Log.d("Check",url);

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    Log.d("Post", response.toString());
                }catch (Exception e){}
               /* try {
                    /*JSONArray jsonArray= response.getJSONArray(xmlHead);

                    for(int i=0; i<jsonArray.length();i++)
                    {
                        JSONObject employee= jsonArray.getJSONObject(i);

                        String firstName=employee.getString("food_name");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("check","Error");
                //Toast.makeText(AddMeal.this,"This item is not found",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){
            @Override
            public Map getHeaders() throws AuthFailureError
            {
                HashMap headers = new HashMap();
                headers.put("x-app-id", "a7204bb4");
                headers.put("x-app-key", "88c2a1513a9f43b9ab83474a9a3d5ce7");
                headers.put("Content-Type","application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("query", "2 eggs");
                params.put("timezone", "US/Eastern");


                return params;
            }


        };

        mQueue.add(request);



    }
}
