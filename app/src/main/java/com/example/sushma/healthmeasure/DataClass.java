package com.example.sushma.healthmeasure;

import android.app.Application;

public class DataClass extends Application {

    private String someVariable;

    public DataClass(){

    }

    public String getSomeVariable(){
        return someVariable;
    }

    public void setSomeVariable(String someVariable){
        this.someVariable = someVariable;
    }
}
