package com.sm.syspago.se.Views;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.sm.syspago.se.R;
import com.sm.syspago.se.databinding.FragmentCajaRapidaBinding;
import com.sm.syspago.utils.SessionManager;

import java.text.DecimalFormat;

public class Caja_Rapida extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private FragmentCajaRapidaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_caja__rapida, container, false);
        initview();
        return binding.getRoot();
    }

    private String monto = "";
    private void initview() {

        ///#7
        binding.imageButton7.setOnClickListener(v -> {
            binding.textView11.setText(binding.textView11.getText().toString()+"7");
            monto = monto + 7;
        });

        ///8
        binding.ocho.setOnClickListener(v -> {
            binding.textView11.setText(binding.textView11.getText().toString()+"8");
            monto = monto + 7;
        });

        binding.nueve.setOnClickListener(v -> {
            binding.textView11.setText(binding.textView11.getText().toString() + "9");
            monto = monto + 9;
        });
        binding.imageButtonseis.setOnClickListener(v -> {
            binding.textView11.setText(binding.textView11.getText().toString() + "4");
            monto = monto + 4;
        });

        binding.imageButtontres.setOnClickListener(v -> {
            binding.textView11.setText(binding.textView11.getText().toString() + "3");
            monto = monto + 3;
        });
        binding.imageButtonuno.setOnClickListener(v -> {
            binding.textView11.setText(binding.textView11.getText().toString() + "1");
            monto = monto + 1;
        });
        binding.imageButtondos.setOnClickListener(v -> {
            binding.textView11.setText(binding.textView11.getText().toString() + "2");
            monto = monto + 2;
        });
        binding.imageButtoncinco.setOnClickListener(v -> {
            binding.textView11.setText(binding.textView11.getText().toString() + "5");
            monto = monto + 5;
        });
        binding.imageButtoncero.setOnClickListener(v -> {
            if (binding.textView11.getText().toString().equals("")) {
                binding.textView11.setText("0.");
                monto = "." + 0;
            } else {
                binding.textView11.setText(binding.textView11.getText().toString() + "0");
                monto = monto + 0;
            }
        });
        binding.imageButtoncuatro.setOnClickListener(v -> {
            binding.textView11.setText(binding.textView11.getText().toString() + "6");
            monto = monto + 6;
        });

        binding.imageButton13.setOnClickListener(v -> {

            char[] caracterpoint = binding.textView11.getText().toString().toCharArray();
            int contador = 0;
            for (int i = 0; i < caracterpoint.length; i++) {
                if (String.valueOf(caracterpoint[i]).equals(".")) {
                    contador = contador + 1;
                }
            }
            if (contador == 1) {
                //no hacer nada
            } else {
                // si hacer
                binding.textView11.setText(binding.textView11.getText().toString() + ".");
                monto = ".";
            }
        });

        binding.imageButton12.setOnClickListener(v -> {
            if (!binding.textView11.getText().toString().equals("")) {
                String result = null;
                if ((binding.textView11.getText().toString() != null) && (binding.textView11.getText().toString().length() > 0)) {
                    result = binding.textView11.getText().toString().substring(0, binding.textView11.getText().toString().length() - 1);
                    binding.textView11.setText(result);
                    binding.procesar.setText("COBRAR $" + result);
                    monto = result;
                }
            }
        });

        binding.procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monto=binding.textView11.getText().toString();

                binding.procesar.setText(binding.textView11.getText().toString());
                binding.textView13.setText(binding.textView11.getText().toString());
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.select_optionuser, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.sesion.setAdapter(adapter);
        binding.sesion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                if (spinner.getSelectedItem().toString().equals("Cerrar Sesíon")) {
                    Toast.makeText(getActivity(), "Cerrando Session", Toast.LENGTH_SHORT).show();
                    SessionManager manag = new SessionManager(getActivity());
                    manag.cleancredentials();
                    Caja_Rapida.this.getActivity().finish();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}