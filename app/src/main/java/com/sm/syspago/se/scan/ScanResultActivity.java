package com.sm.syspago.se.scan;

import android.os.Bundle;
import android.widget.TextView;

import com.sm.syspago.se.BaseAppCompatActivity;
import com.sm.syspago.se.R;

public class ScanResultActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        initToolbarBringBack(R.string.other_scan_result);
        initView();
    }

    private void initView() {
        String type = getIntent().getStringExtra("type");
        String value = getIntent().getStringExtra("value");

        TextView tvInfo = findViewById(R.id.tv_info);

        String info = "Type: " + type + "\n";
        info += "Value: " + value;
        tvInfo.setText(info);
    }


}
