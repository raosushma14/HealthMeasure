package com.example.sushma.healthmeasure;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SearchEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;
import static android.provider.Settings.NameValueTable.VALUE;

public class AddMeal extends AppCompatActivity {

    ImageButton button;
    SearchView mysearchView;
    ListView listView;
    private RequestQueue mQueue;
    public String STATIC_STRING;
    TextView txtview;
    private SwipeRefreshLayout refreshLayout;
    int mealOptionSelected;


    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        mealOptionSelected =  getIntent().getIntExtra("mealAdded",0);

        button = (ImageButton) findViewById(R.id.barcode_btn);
        mysearchView = (SearchView) findViewById(R.id.searchview_id);
        listView = (ListView) findViewById(R.id.id_listview);
        txtview = (TextView) findViewById(R.id.txt_set);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshLayout);



        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);



        mysearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                jsonParse("instant?query="+query,"common");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                    return false;

            }
        });

        mQueue= Volley.newRequestQueue(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScanCodeActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemClicked = list.get(position).toString();
                Intent intent = new Intent(getApplicationContext(),CaloriePerServings.class);
                Bundle extras = new Bundle();
                extras.putString("foodSelected",itemClicked);
                extras.putInt("mealAdded",mealOptionSelected);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                refreshLayout.setRefreshing(false);
            }
        });


    }

    protected void onResume(){
        super.onResume();


        try {
            final String s = ((DataClass) this.getApplication()).getSomeVariable();

            jsonParse("item?upc=" + s, "foods");



        }catch(Exception e){
            Log.d("Check",e.getMessage());
        }
    }



    public void jsonParse(String query, final String xmlHead){

        // String url=getString(R.string.apiURL);
        String url="https://trackapi.nutritionix.com/v2/search/"+query.trim();
        Log.d("Check",url);

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray(xmlHead);

                    for(int i=0; i<jsonArray.length();i++)
                    {
                        JSONObject employee= jsonArray.getJSONObject(i);

                        String firstName=employee.getString("food_name");
                        list.add(jsonArray.getJSONObject(i).getString("food_name"));

                       adapter.notifyDataSetChanged();
                        Log.d("list","Entered");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("check","Error");
                Toast.makeText(AddMeal.this,"This item is not found",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){
            @Override
            public Map getHeaders() throws AuthFailureError
            {
                HashMap headers = new HashMap();
                headers.put("x-app-id", "a7204bb4");
                headers.put("x-app-key", "88c2a1513a9f43b9ab83474a9a3d5ce7");
                return headers;
            }


        };

        mQueue.add(request);



    }






}
