package com.sm.syspago.se.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sm.syspago.se.R;
import com.sm.syspago.se.databinding.ActivityInitMenuBinding;

public class InitMenu extends AppCompatActivity {
    private ActivityInitMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_init_menu);
        // setContentView(binding.getRoot());
        replaceFragment(new Caja_Rapida());
        binding.bottomNavigationView.setBackground(null);

        try {
            binding.bottomNavigationView.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.caja_rapida2) {
                    replaceFragment(new Caja_Rapida());
                }
                /*if (item.getItemId() == R.id.mov) {
                    if (Constantes.buttoncheck) {
                        item.setCheckable(true);
                        replaceFragment(new Movimientos());
                    } else {
                        item.setCheckable(false);
                    }
                }*/
                return true;
            });

        } catch (Exception e) {

        }
        binding.bottomNavigationView.setItemIconTintList(null);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}