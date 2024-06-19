package com.sm.syspago.se;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.sm.syspago.model.ResponseModel;
import com.sm.syspago.se.Views.InitMenu;
import com.sm.syspago.se.basic.BasicActivity;
import com.sm.syspago.se.biometric.BiometricActivity;
import com.sm.syspago.se.card.CardActivity;
import com.sm.syspago.se.core.ApiService;
import com.sm.syspago.se.databinding.ActivityMainBinding;
import com.sm.syspago.se.emv.EMVActivity;
import com.sm.syspago.se.etc.ETCActivity;
import com.sm.syspago.se.m112.M112Activity;
import com.sm.syspago.se.other.OtherActivity;
import com.sm.syspago.se.pin.PinActivity;
import com.sm.syspago.se.print.PrintActivity;
import com.sm.syspago.se.scan.ScanActivity;
import com.sm.syspago.se.security.SecurityActivity;
import com.sm.syspago.se.tax.TaxTestActivity;
import com.sm.syspago.se.utils.DeviceUtil;
import com.sm.syspago.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            binding.versiont.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }

        SessionManager sessionManager = new SessionManager(MainActivity.this);
        if (sessionManager.getLogin()) {
            Intent intent = new Intent(MainActivity.this, InitMenu.class);
            startActivity(intent);
            finish();
        } else {
            binding.appCompatButton.setOnClickListener(v -> {
                if (binding.t1.getText().toString().equalsIgnoreCase("") || binding.t2.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña no pueden ir vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject data = new JSONObject();
                    try {
                        login(binding.t1.getText().toString(), binding.t2.getText().toString());
                    } catch (Exception e) {
                        Toast.makeText(this, "Errores", Toast.LENGTH_SHORT).show();
                    }
                }
            });
           // binding.checkRecordarme.setOnCheckedChangeListener((buttonView, isChecked) -> flag = isChecked);
        }
        //initView();
    }


//
    private void login(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://production.multiredglobalapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseModel> call = apiService.getUserInfo("Basic " + Base64.encodeToString((username + ":" + password).getBytes(), Base64.NO_WRAP));
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    ResponseModel userInfo = response.body();
                    ResponseModel userInfo2= response.body();
                    SessionManager sessionManager = new SessionManager(MainActivity.this);
                    JSONObject data = new JSONObject();
                    JSONObject data2 = new JSONObject();
                    sessionManager.setLogin(username, password, true);
                    try {
                        data2.put("systiendaUsername", username);
                        data2.put("systiendaPassword", password);
                        data.put("fn", "checkUser");
                        data.put("data", data2);

                        Intent intent = new Intent(MainActivity.this, InitMenu.class);
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    // Aquí maneja la respuesta exitosa
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



   /* private void initView() {
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SunmiSDKTestDemo");

        findViewById(R.id.card_view_basic).setOnClickListener(this);
        findViewById(R.id.card_view_card).setOnClickListener(this);
        findViewById(R.id.card_view_pin_pad).setOnClickListener(this);
        findViewById(R.id.card_view_security).setOnClickListener(this);
        findViewById(R.id.card_view_emv).setOnClickListener(this);
        findViewById(R.id.card_view_scan).setOnClickListener(this);
        findViewById(R.id.card_view_print).setOnClickListener(this);
        findViewById(R.id.card_view_other).setOnClickListener(this);
        findViewById(R.id.card_view_etc).setOnClickListener(this);
        findViewById(R.id.card_view_comm).setOnClickListener(this);
        findViewById(R.id.card_view_tax).setOnClickListener(this);
        findViewById(R.id.card_view_biometric).setOnClickListener(this);
        View viewM112 = findViewById(R.id.card_view_m112);
        viewM112.setOnClickListener(this);
        if (DeviceUtil.isFinanceDevice()) {
            viewM112.setVisibility(View.INVISIBLE);
        }
    }*/

   /* @Override
    protected void onResume() {
        super.onResume();
        if (!MyApplication.app.isConnectPaySDK()) {
            MyApplication.app.bindPaySDKService();
        }
    }*/

   /* @Override
    public void onClick(View v) {
        if (!MyApplication.app.isConnectPaySDK()) {
            MyApplication.app.bindPaySDKService();
            showToast(R.string.connect_loading);
            return;
        }
        final int id = v.getId();
        switch (id) {
            case R.id.card_view_basic:
                openActivity(BasicActivity.class);
                break;
            case R.id.card_view_card:
                openActivity(CardActivity.class);
                break;
            case R.id.card_view_pin_pad:
                openActivity(PinActivity.class);
                break;
            case R.id.card_view_security:
                openActivity(SecurityActivity.class);
                break;
            case R.id.card_view_emv:
                openActivity(EMVActivity.class);
                break;
            case R.id.card_view_scan:
                openActivity(ScanActivity.class);
                break;
            case R.id.card_view_print:
                openActivity(PrintActivity.class);
                break;
            case R.id.card_view_etc:
                openActivity(ETCActivity.class);
                break;
            case R.id.card_view_other:
                openActivity(OtherActivity.class);
                break;
            case R.id.card_view_tax:
                openActivity(TaxTestActivity.class);
                break;
            case R.id.card_view_m112:
                openActivity(M112Activity.class);
                break;
            case R.id.card_view_biometric:
                openActivity(BiometricActivity.class);
                break;
        }
    }*/

   /* public static void reStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }*/


}
