package com.example.covid_19pandemicsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.util.ArrayList;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainLab extends AppCompatActivity {

    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab);

        name = (TextView) findViewById(R.id.patientSize);

        SharedPreferences sp =getApplicationContext().getSharedPreferences("PatientInfo", Context.MODE_PRIVATE);
        String nameString = sp.getString("patientName","");
        String addressString = sp.getString("patientAddress","");
        String phoneString = sp.getString("patientPhone","");
        int healthCardNum = sp.getInt("patientHeathNumber",0);

        Patient temp = new Patient(nameString,addressString,phoneString,healthCardNum);
        name.setText(nameString);

    }
}