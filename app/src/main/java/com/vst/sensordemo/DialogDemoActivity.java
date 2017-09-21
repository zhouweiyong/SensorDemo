package com.vst.sensordemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by zwy on 2017/4/14.
 * email:16681805@qq.com
 */

public class DialogDemoActivity extends Activity implements View.OnClickListener {

    private Button btn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, DialogActivity.class));
                break;
        }
    }
}
