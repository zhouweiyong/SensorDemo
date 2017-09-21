package com.vst.sensordemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static com.vst.sensordemo.R.id.iv_dialog;

/**
 * Created by zwy on 2017/4/15.
 * email:16681805@qq.com
 */

public class Dialog2Activity extends Activity implements View.OnClickListener {

    private Button btn1;
    private LinearLayout layout_root;
    private Handler mHandler = new Handler();

    private ImageView imageView;
    private DisplayMetrics dm;
    private TranslateAnimation showAnima;
    private ShakeSensor shakeSensor;
    private Vibrator mVibrator;
    private boolean isScreenShot = false;
    private Bitmap bitmap;
    private View mRootContent;
    private PopupWindow mPopupWindow;
    private FrameLayout rootContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        mRootContent = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        FrameLayout decorView =  (FrameLayout)this.getWindow().getDecorView();
        LinearLayout root = (LinearLayout) decorView.getChildAt(0);
        rootContent = (FrameLayout) root.getChildAt(1);


        btn1.setOnClickListener(this);
        layout_root = (LinearLayout) findViewById(R.id.layout_root);
        dm = getResources().getDisplayMetrics();
        showAnima = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        showAnima.setDuration(1000);

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        shakeSensor = new ShakeSensor(this, 4000);
        shakeSensor.setShakeListener(new ShakeSensor.OnShakeListener() {
            @Override
            public void onShakeComplete(SensorEvent event) {
                if (!isScreenShot) {
                    isScreenShot = true;
                    mVibrator.vibrate(300);
                    bitmap = ScreenShot.takeScreenShot(Dialog2Activity.this);
                    showDialog();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:

                break;
        }
    }

    private void showDialog() {

        View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null);
        final RelativeLayout layoutDialog = (RelativeLayout) view.findViewById(R.id.layout_dialog);
        imageView = (ImageView) view.findViewById(iv_dialog);
        layoutDialog.setBackgroundColor(Color.parseColor("#ffffff"));
        final ImageView ivDown = (ImageView) view.findViewById(R.id.iv_down);
        final Button btnDown = (Button) view.findViewById(R.id.btn_down);
        final RelativeLayout layoutDown = (RelativeLayout) view.findViewById(R.id.layout_down);
        layoutDown.setBackgroundColor(Color.parseColor("#ffffff"));
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dialog2Activity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        mPopupWindow = new PopupWindow(view, dm.widthPixels, dm.heightPixels);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layoutDown.getLayoutParams();
        layoutParams.topMargin = statusBarHeight1;
        mPopupWindow.showAtLocation(mRootContent, Gravity.NO_GRAVITY,0,200);

        layoutDialog.setBackgroundColor(Color.parseColor("#70000000"));
        imageView.setImageBitmap(bitmap);
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                layoutDialog.setBackgroundColor(Color.parseColor("#70000000"));
//                imageView.setImageBitmap(bitmap);
//            }
//        }, 500);
        imageView.animate().scaleX(0.7f).setDuration(1000);
        imageView.animate().scaleY(0.7f).setDuration(1000);
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                imageView.animate().scaleX(0.7f).setDuration(1000);
//                imageView.animate().scaleY(0.7f).setDuration(1000);
//
//            }
//        }, 2000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutDialog.setBackgroundColor(Color.parseColor("#00000000"));
                int ivDownWidth = ivDown.getWidth();
                int ivDownHeight = ivDown.getHeight();
                Log.i("zwy", "ivDownWidth:" + ivDownWidth + " ivDownHeight" + ivDownHeight);
                int imageViewWidth = imageView.getWidth();
                int imageViewHeight = imageView.getHeight();
                Log.i("zwy", "imageViewWidth:" + imageViewWidth + " imageViewHeight" + imageViewHeight);

                imageView.animate().scaleX((float) ivDownWidth / imageViewWidth).setDuration(1000);
                imageView.animate().scaleY((float) ivDownHeight / imageViewHeight).setDuration(1000);
                layoutDown.startAnimation(showAnima);
                layoutDown.setVisibility(View.VISIBLE);
            }
        }, 1000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                ivDown.getLocationInWindow(location);
                int[] imageLoc = new int[2];
                imageView.getLocationInWindow(imageLoc);
                imageView.animate().translationX(location[0] - imageLoc[0]).setDuration(2000);
                imageView.animate().translationY(location[1] - imageLoc[1]).setDuration(2000);

            }
        }, 2000);
        layoutDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isScreenShot = false;
            }
        });
    }

    @Override
    protected void onResume() {
        shakeSensor.register();
        super.onResume();
    }

    @Override
    protected void onStop() {
        shakeSensor.unregister();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            return;
        }

        super.onBackPressed();
    }
}
