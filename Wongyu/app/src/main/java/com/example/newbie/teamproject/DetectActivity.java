package com.example.newbie.teamproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public class DetectActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "DetectActivity";

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private LineChart mChart;
    private float tempx = 0;
    private float tempy = 0;
    private float tempz = 0;
    private String name[] = {"X axis","Y axis","Z axis"};
    private int color[] = {Color.RED,Color.GREEN,Color.BLUE};
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        if(mAccelerometer != null) {
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
        mChart = (LineChart) findViewById(R.id.chart1);

        mChart.getDescription().setEnabled(true);
        mChart.getDescription().setText("Real Time Accelerometer Data Plot");

        mChart.setTouchEnabled(false);
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.setPinchZoom(false);
        mChart.setBackgroundColor(Color.BLACK);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

        Legend l = mChart.getLegend();

        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis xl = mChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(true);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMaximum(10f);
        leftAxis.setAxisMinimum(-10f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getXAxis().setDrawGridLines(false);
        mChart.setDrawBorders(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    private  void addEntry(SensorEvent event){
        LineData data = mChart.getData();
        if(data != null){
            ILineDataSet set = data.getDataSetByIndex(0);
            ILineDataSet set2 = data.getDataSetByIndex(0);
            ILineDataSet set3 = data.getDataSetByIndex(0);
            if(set==null){
                set = createSet(0);
                set2 = createSet(1);
                set3 = createSet(2);
                data.addDataSet(set);
                data.addDataSet(set2);
                data.addDataSet(set3);
            }

            data.addEntry(new Entry(set.getEntryCount(), event.values[0]), 0);
            data.addEntry(new Entry(set2.getEntryCount(), event.values[1]), 1);
            data.addEntry(new Entry(set3.getEntryCount(), event.values[2]), 2);

            data.notifyDataChanged();
            mChart.notifyDataSetChanged();
            mChart.setVisibleXRangeMaximum(50);
            mChart.moveViewToX(data.getEntryCount());
        }
    }

    private LineDataSet createSet(int index){
        LineDataSet set = new LineDataSet(null,  name[index]);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(3f);
        set.setColor(color[index]);
        set.setHighlightEnabled(false);
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setCubicIntensity(0.2f);
        return set;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if(tempx - event.values[0] > 10 || tempx - event.values[0] < -10
                || tempy - event.values[1] > 10 || tempy - event.values[1] < -10
                || tempz - event.values[2] > 10 || tempz - event.values[2] < -10){
            Intent intent1 = new Intent(getApplicationContext(), PopupActivity.class);
            startActivity(intent1);
        }
        tempx = event.values[0];
        tempy = event.values[1];
        tempz = event.values[2];
        addEntry(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy(){
        mSensorManager.unregisterListener(DetectActivity.this);
        super.onDestroy();
    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
    }
}