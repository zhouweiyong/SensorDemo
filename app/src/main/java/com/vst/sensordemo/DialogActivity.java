package com.vst.sensordemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by zwy on 2017/4/14.
 * email:16681805@qq.com
 */

public class DialogActivity extends Activity {

    private ImageView iv_dialog;
    private Handler mHandler = new Handler();
    private LinearLayout layout_dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
    }

    private void initView() {
        iv_dialog = (ImageView) findViewById(R.id.iv_dialog);
        layout_dialog = (LinearLayout) findViewById(R.id.layout_dialog);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv_dialog.setImageResource(R.mipmap.img04);
//                layout_dialog.setBackgroundColor(Color.parseColor("#80000000"));


            }
        }, 500);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                iv_dialog.animate().scaleX(0.7f).setDuration(2000);
                iv_dialog.animate().scaleY(0.7f).setDuration(2000);
            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv_dialog.animate().scaleX(0.3f).setDuration(2000);
                iv_dialog.animate().scaleY(0.3f).setDuration(2000);
            }
        },3000);
    }
}
