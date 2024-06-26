package com.sm.syspago.se.basic;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sm.syspago.se.BaseAppCompatActivity;
import com.sm.syspago.se.MyApplication;
import com.sm.syspago.se.R;
import com.sunmi.pay.hardware.aidlv2.system.BasicOptV2;

public class SetSysParamActivity extends BaseAppCompatActivity {

    private EditText mEditKey;
    private EditText mEditValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_set_sys_param);
        initToolbarBringBack(R.string.basic_set_sys_param);
        initView();
    }

    private void initView() {
        mEditKey = findViewById(R.id.edit_key);
        mEditValue = findViewById(R.id.edit_value);

        findViewById(R.id.mb_ok).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.mb_ok:
                setSysParam();
                break;
        }
    }

    private void setSysParam() {
        try {
            BasicOptV2 basicOptV2 = MyApplication.app.basicOptV2;
            String name = mEditKey.getText().toString();
            String value = mEditValue.getText().toString();
            if (name.trim().length() == 0) {
                showToast(R.string.basic_sys_key_hint);
                return;
            }
            addStartTimeWithClear("setSysParam()");
            int result = basicOptV2.setSysParam(name, value);
            addEndTime("setSysParam()");
            toastHint(result);
            showSpendTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
