package com.sm.syspago.se.basic;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sm.syspago.se.BaseAppCompatActivity;
import com.sm.syspago.se.MyApplication;
import com.sm.syspago.se.R;
import com.sm.syspago.se.utils.Utility;

public class PedActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ped);
        initView();
    }

    private void initView() {
        initToolbarBringBack(R.string.basic_ped_test);
        findViewById(R.id.btn_get_mode).setOnClickListener(this);
        findViewById(R.id.btn_set_mode).setOnClickListener(this);
        findViewById(R.id.btn_get_keys_info).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_mode:
                getPedMode();
                break;
            case R.id.btn_set_mode:
                setPedMode();
                break;
            case R.id.btn_get_keys_info:
                getPedKeysInfo();
                break;
        }
    }

    /** Get PED mode */
    private void getPedMode() {
        try {
            int mode = MyApplication.app.basicOptV2.getPedMode();
            if (mode < 0) {
                toastHint(mode);
                return;
            }
            TextView tvResult = findViewById(R.id.tv_ped_mode);
            tvResult.setText("PED mode: " + mode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPedMode() {
        try {
            String modeStr = this.<EditText>findViewById(R.id.edt_get_mode).getText().toString();
            if (TextUtils.isEmpty(modeStr)) {
                showToast("PED mode shouldn't be empty");
                return;
            }
            int mode = Integer.parseInt(modeStr);
            if (mode < 1 || mode > 3) {
                showToast("PED mode shouldn't be in [1,3]");
                return;
            }
            int code = MyApplication.app.basicOptV2.setPedMode(mode);
            toastHint(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getPedKeysInfo() {
        try {
            Bundle out = new Bundle();
            int code = MyApplication.app.basicOptV2.getPedKeysInfo(out);
            if (code < 0) {
                toastHint(code);
                return;
            }
            String keysInfo = Utility.bundle2String(out);
            TextView tvResult = findViewById(R.id.tv_ped_keys_info);
            tvResult.setText(keysInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
