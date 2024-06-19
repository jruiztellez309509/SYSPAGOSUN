package com.sm.syspago.se.basic;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.sm.syspago.se.BaseAppCompatActivity;
import com.sm.syspago.se.MyApplication;
import com.sm.syspago.se.R;
import com.sm.syspago.se.utils.LogUtil;
import com.sunmi.pay.hardware.aidl.AidlConstants.KBBeepMode;
import com.sunmi.pay.hardware.aidl.AidlConstants.SysParam;

public class KBBeepModeActivity extends BaseAppCompatActivity {
    private RadioGroup rdoGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kb_beep_mode);
        initView();
    }

    private void initView() {
        initToolbarBringBack(R.string.basic_kb_beep_mode);
        rdoGroup = findViewById(R.id.rdo_group_mode);
        findViewById(R.id.btn_ok).setOnClickListener((v) -> setKeyboardBeepMode());
    }

    private void setKeyboardBeepMode() {
        try {
            String mode = KBBeepMode.MODE_ON;
            if (rdoGroup.getCheckedRadioButtonId() == R.id.rdo_mode_disable) {
                mode = KBBeepMode.MODE_OFF;
            }
            int code = MyApplication.app.basicOptV2.setSysParam(SysParam.KB_BEEP_MODE, mode);
            LogUtil.e(TAG, "setKeyboardBeepMode() code:" + code);
            showToast(code == 0 ? "success" : "failed, code:" + code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
