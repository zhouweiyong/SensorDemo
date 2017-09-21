package com.vst.sensordemo;

import android.app.Activity;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by zwy on 2017/4/13.
 * email:16681805@qq.com
 */

public class SensorActivity extends Activity {

    private ShakeSensor mSensor;
    private int index;
    private Vibrator mVibrator;//手机震动

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        mSensor = new ShakeSensor(this, 6000);
        mSensor.setShakeListener(new ShakeSensor.OnShakeListener() {
            @Override
            public void onShakeComplete(SensorEvent event) {
                mVibrator.vibrate(300);
                Toast.makeText(SensorActivity.this, "Hello World" + index++, Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    protected void onResume() {
        mSensor.register();
        super.onResume();
    }

    @Override
    protected void onStop() {
        mSensor.unregister();
        super.onStop();
    }
}
