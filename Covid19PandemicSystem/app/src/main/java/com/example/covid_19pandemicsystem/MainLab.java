package com.example.covid_19pandemicsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainLab extends AppCompatActivity {

    private TextView healthCard, testLocation, testType, adminDate, adminTime;
    private Button send, clear;
    private EditText labLocationField, testOutcomeField;
    public Patient patient;
    public Test test;
    public SharedPreferences sp2;
    public String labLocation, testOutcome;
    private SimpleDateFormat dateTime;
    private String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab);

        healthCard = (TextView) findViewById(R.id.healthCardNum);
        testLocation = (TextView) findViewById(R.id.testLocation);
        testType = (TextView) findViewById(R.id.testType);
        adminDate = (TextView) findViewById(R.id.adminDate);
        adminTime = (TextView) findViewById(R.id.adminTime);

        send = (Button) findViewById(R.id.sendToDoctor);
        clear = (Button) findViewById(R.id.clearLabInfo);

        labLocationField = (EditText) findViewById(R.id.labLocation);
        testOutcomeField = (EditText) findViewById(R.id.testOutcome);

        sp2 = getSharedPreferences("labInfo", Context.MODE_PRIVATE);

        SharedPreferences sp =getApplicationContext().getSharedPreferences("PatientInfo", Context.MODE_PRIVATE);

        String nameString = sp.getString("patientName","");
        String addressString = sp.getString("patientAddress","");
        String phoneString = sp.getString("patientPhone","");
        int healthCardNum = sp.getInt("patientHeathNumber",0);

        String dateAdminString = sp.getString("testDate","");
        String timeAdminString = sp.getString("testTime","");
        String testLocationString = sp.getString("testLocation","");
        String testTypeString = sp.getString("testType","");

        patient = new Patient(nameString,addressString,phoneString,healthCardNum);
        //test = new Test(dateAdminString,timeAdminString,);

        healthCard.setText("#: "+healthCardNum);
        testLocation.setText("Location: "+testLocationString);
        testType.setText("Type: "+testTypeString);
        adminDate.setText("Date: "+dateAdminString);
        adminTime.setText("Time: "+timeAdminString);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labLocation = labLocationField.getText().toString();
                testOutcome = testOutcomeField.getText().toString();

                dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date currentDate = new Date();
                String dateAndTime = dateTime.format(currentDate);
                dateString = dateAndTime.substring(0, 10);

                SharedPreferences.Editor editor = sp2.edit();
                editor.putString("labLocation",labLocation);
                editor.putString("testOutcome",testOutcome);
                editor.putString("labDate",dateString);
                editor.commit();

                healthCard.setText("Currently we have no patient.");
                testLocation.setText("Wait till next test arrives.");
                testType.setText("*");
                adminDate.setText("*");
                adminTime.setText("*");
                labLocationField.setText("");
                testOutcomeField.setText("");
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labLocationField.setText("");
                testOutcomeField.setText("");
            }
        });

    }
}