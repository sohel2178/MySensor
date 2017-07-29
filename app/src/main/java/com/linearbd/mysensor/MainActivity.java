package com.linearbd.mysensor;

import android.graphics.Color;
import android.hardware.SensorEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.linearbd.mysensor.Sensor.MyEventListener;
import com.linearbd.mysensor.Sensor.MySensor;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements MyEventListener{
    private static final float ACC_LIMIT=4;
    private static final long MINIMUM_TIME_STAMP_DIFF=300000000;
    //Declare View Component
    private TextView tvXValue,tvYValue,tvZValue;
    private MySensor mySensor;

    //Formating Number
    DecimalFormat df;

    long xtimestamp;
    private int trigger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        df= new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);

        // Initialize View
        initView();

        mySensor = new MySensor(this);
        mySensor.setMyEventListener(this);
    }

    private void initView() {
        tvXValue = (TextView) findViewById(R.id.x_value);
        tvYValue = (TextView) findViewById(R.id.y_value);
        tvZValue = (TextView) findViewById(R.id.z_value);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySensor.registerLinearAccelerationSensor();
    }

    @Override
    protected void onPause() {
        mySensor.unregisterLinearAccelerationSensor();
        super.onPause();
    }

    @Override
    public void getEvent(SensorEvent event) {
        linearAccleration(event);
        //gyration(event);

        /*Log.d("TTTT",event.values[0]+"");
        Log.d("TTTT",event.values[1]+"");
        Log.d("TTTT",event.values[2]+"");*/

    }

    public void gyration(SensorEvent event){
        if(event.values[2] > 0.5f) { // anticlockwise
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        } else if(event.values[2] < -0.5f) { // clockwise
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
        }

    }


    public void linearAccleration(SensorEvent event){
        Log.d("SDSDSD",event.timestamp+"");

        if(Math.abs(event.values[0])>ACC_LIMIT){
            Log.d("TimeStamp",xtimestamp+"");
            if(xtimestamp==0){

                if(event.values[0]>0){
                    tvXValue.setText("MOVE TO LEFT");
                    trigger=-1;
                }else{
                    tvXValue.setText("MOVE TO RIGHT");
                    trigger=1;
                }

                xtimestamp=event.timestamp;

            }else{
                if(event.timestamp-xtimestamp>MINIMUM_TIME_STAMP_DIFF){
                    if(event.values[0]>0){

                        if(trigger==1){
                            tvXValue.setText("MOVE TO LEFT");
                            trigger=-1;
                        }


                    }else{
                        if(trigger==-1){
                            tvXValue.setText("MOVE TO RIGHT");
                            trigger=1;
                        }


                    }
                }
                xtimestamp=event.timestamp;
            }


        }

        








        /*if(Math.abs(event.values[1])>ACC_LIMIT){
            if(event.values[1]>0){
                tvYValue.setText("MOVE TO BOTTOM");
            }else{
                tvYValue.setText("MOVE TO TOP");
            }

        }

        if(Math.abs(event.values[2])>ACC_LIMIT){
            if(event.values[2]>0){
                tvZValue.setText("MOVE DOWN");
            }else{
                tvZValue.setText("MOVE UP");
            }

        }*/

    }

    private float getMax(float a, float b, float c){
        float ab = Math.max(a,b);
        float abc=Math.max(ab,c);

        return abc;
    }
}
