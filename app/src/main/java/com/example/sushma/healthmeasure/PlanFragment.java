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
                addMealIntent.putExtra("mealAdded",1);
                startActivity(addMealIntent);

                break;
            case R.id.btn_snack1:
                Intent addMealIntent_snack = new Intent(getActivity(),AddMeal.class);
                addMealIntent_snack.putExtra("mealAdded",2);
                startActivity(addMealIntent_snack);

                break;
            case R.id.btn_lunch:
                Intent addMealIntent_lunch = new Intent(getActivity(),AddMeal.class);
                addMealIntent_lunch.putExtra("mealAdded",3);
                startActivity(addMealIntent_lunch);

                break;
            case R.id.btn_snack2:
                Intent addMealIntent_snack2 = new Intent(getActivity(),AddMeal.class);
                addMealIntent_snack2.putExtra("mealAdded",4);
                startActivity(addMealIntent_snack2);

                break;
            case R.id.btn_dinner:
                Intent addMealIntent_dinner = new Intent(getActivity(),AddMeal.class);
                addMealIntent_dinner.putExtra("mealAdded",5);
                startActivity(addMealIntent_dinner);

                break;
            default:
                break;
        }
    }


}
