package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    TextView result;
    Spinner spnFrom, spnTo;
    int intAmount = 0;
    double tempVND = 0;
    double dbResult = 0;

    final String[] lsMoney = {"USD", "VND", "EUR", "JPY", "RUB", "HKD", "THB", "MYR", "INR", "KRW"};
    double[] exchange = {23000, 1, 26900, 217.81, 302.63, 2945.49, 727.4, 5535.86, 313.93, 19.61};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnFrom = findViewById(R.id.from);
        spnTo = findViewById(R.id.to);
        amount = findViewById(R.id.amount);
        result = findViewById(R.id.result);



        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lsMoney);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnFrom.setAdapter(adapter);
        spnTo.setAdapter(adapter);

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               // Log.e("TAG", "before");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.e("TAG", "on");
            }

            @Override
            public void afterTextChanged(Editable s) {

                //Log.e("TAG", s.toString());
                if(!s.toString().equals("")){
                    intAmount  = Integer.parseInt(s.toString());
                    //Log.e("TAG", String.valueOf(amount));
                }

                Trans();

            }
        });

        spnFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Trans();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Trans();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    private void Trans(){
        for(int i = 0; i<lsMoney.length; i++){
            if(spnFrom.getSelectedItem().toString().equals(lsMoney[i])){
                tempVND = intAmount * exchange[i];
                break;
            }
        }

        for(int i = 0; i < lsMoney.length; i++){
            if(spnTo.getSelectedItem().toString().equals(lsMoney[i])){
                dbResult = tempVND / exchange[i];
                break;
            }
        }
        result.setText(String.format("%.2f", dbResult));
    }
}