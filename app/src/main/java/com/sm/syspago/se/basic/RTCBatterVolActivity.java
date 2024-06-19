package com.sm.syspago.se.basic;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sm.syspago.se.BaseAppCompatActivity;
import com.sm.syspago.se.MyApplication;
import com.sm.syspago.se.R;

public class RTCBatterVolActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_rtc);
        initView();
    }

    private void initView() {
        initToolbarBringBack(R.string.basic_rtc_battery_info);
        findViewById(R.id.btn_get_rtc_battery_vol).setOnClickListener(v -> getRtcInfo());
    }

    private void getRtcInfo() {
        try {
            TextView textView = findViewById(R.id.txt_rtc_battery_vol);
            Bundle bundle = new Bundle();
            int ret = MyApplication.app.basicOptV2.getRtcBatVol(bundle);
            if (ret != 0) {
                textView.setText("getRtcBatVol error:" + ret);
                return;
            }
            int vol = bundle.getInt("vol", -1);
            int fromAdc = bundle.getInt("FromAdc", -1);
            String info = "Vol:" + vol + "  FromAdc:" + fromAdc;
            textView.setText(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
