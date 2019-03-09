package com.example.sushma.healthmeasure;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.Map;

public class GraphFragment extends Fragment  {

    private static final String TAG = "Graph Fragment";

    private LineChart mchart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_graphs,container,false);
        mchart = (LineChart) view.findViewById(R.id.linechart);

        //mchart.setOnChartGestureListener(GraphFragment.this);
        //mchart.setOnChartValueSelectedListener(GraphFragment.this);
        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0,60f));
        yValues.add(new Entry(1,50f));
        yValues.add(new Entry(2,80f));
        yValues.add(new Entry(3,40f));
        yValues.add(new Entry(4,20f));
        yValues.add(new Entry(5,90f));
        yValues.add(new Entry(6,75f));

        LineDataSet set1 = new LineDataSet(yValues, "Dataset 1");
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(10f); //also color

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        mchart.setData(data);


        return view;



    }
}
