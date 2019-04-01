package com.example.sushma.healthmeasure;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Dialog myDialog;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText("Home");
                    selectedFragment = new WaterFragment();
                    //return true;
                    break;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText("Graphs");
                    selectedFragment = new GraphFragment();
                    //return true;
                    break;
                case R.id.navigation_notifications:
                    //mTextMessage.setText("Plans");
                    selectedFragment = new PlanFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_actions,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment selectedFragment = null;
        switch(item.getItemId()){

            case R.id.item1:
                Log.d("switch","Entered item 2");
                selectedFragment = new Popupfragment();
                break;
            case R.id.item2:
                selectedFragment = new UsefulLinksFragments();
                break;
            //default:  return super.onOptionsItemSelected(item);


        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new WaterFragment()).commit();
        myDialog = new Dialog(this);
    }

}
