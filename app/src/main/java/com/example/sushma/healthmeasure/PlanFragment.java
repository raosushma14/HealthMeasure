package com.example.sushma.healthmeasure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.nio.channels.FileLock;

public class PlanFragment extends Fragment implements View.OnClickListener {
    View view;
    FloatingActionButton breakfastButton;
   FloatingActionButton snack1Button;
   FloatingActionButton lunchButton;
    FloatingActionButton snack2Button;
    FloatingActionButton dinnerButton;



    Context context = getContext();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_plans,container,false);


        Log.d("switch","Entered item onCreate");



        breakfastButton = (FloatingActionButton) view.findViewById(R.id.btn_breakfast);
        snack1Button = (FloatingActionButton) view.findViewById(R.id.btn_snack1);
        lunchButton = (FloatingActionButton) view.findViewById(R.id.btn_lunch);
        snack2Button = (FloatingActionButton) view.findViewById(R.id.btn_snack2);
        dinnerButton = (FloatingActionButton) view.findViewById(R.id.btn_dinner);

        breakfastButton.setOnClickListener(this);
        snack1Button.setOnClickListener(this);
        lunchButton.setOnClickListener(this);
        snack2Button.setOnClickListener(this);
        dinnerButton.setOnClickListener(this);




        return view;
    }

    @Override
    public void onClick(View v) {
        Log.d("switch","Entered item 1");
        switch(v.getId()){
            case R.id.btn_breakfast:
                Intent addMealIntent = new Intent(getActivity(),AddMeal.class);
                startActivity(addMealIntent);
                Log.d("switch","Entered item 1");
                break;
            case R.id.btn_snack1:
                Intent addMealIntent_snack = new Intent(getActivity(),AddMeal.class);
                startActivity(addMealIntent_snack);
                //Log.d("switch","Entered item 1");
                break;
            case R.id.btn_lunch:
                Intent addMealIntent_lunch = new Intent(getActivity(),AddMeal.class);
                startActivity(addMealIntent_lunch);
                //Log.d("switch","Entered item 1");
                break;
            case R.id.btn_snack2:
                Intent addMealIntent_snack2 = new Intent(getActivity(),AddMeal.class);
                startActivity(addMealIntent_snack2);
                //Log.d("switch","Entered item 1");
                break;
            case R.id.btn_dinner:
                Intent addMealIntent_dinner = new Intent(getActivity(),AddMeal.class);
                startActivity(addMealIntent_dinner);
               // Log.d("switch","Entered item 1");
                break;
            default:
                break;
        }
    }
}
