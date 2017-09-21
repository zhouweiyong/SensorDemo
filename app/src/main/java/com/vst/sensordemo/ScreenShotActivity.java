package com.vst.sensordemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by zwy on 2017/4/13.
 * email:16681805@qq.com
 */

public class ScreenShotActivity extends Activity implements View.OnClickListener {

    private Button btn1;
    private ImageView iv_show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);
        initView();

    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(this);
        iv_show = (ImageView) findViewById(R.id.iv_show);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Bitmap bitmap = ScreenShot.takeScreenShot(this);
                iv_show.setImageBitmap(bitmap);
                File file = FileUtils.getSaveFile("vst", "screen_shot.jpg");
                ScreenShot.savePic(bitmap, file.getAbsolutePath());
                break;
        }
    }

    private void saveCurrentImage() {

    }
}
