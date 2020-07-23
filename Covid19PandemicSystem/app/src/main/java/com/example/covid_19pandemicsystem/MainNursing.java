package com.example.covid_19pandemicsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class MainNursing extends AppCompatActivity {
    private Button clear, submit;
    private EditText patientName, address, phone, healthCardNumber, typeTest, locationTest, locationLab;
    public String patientNameString, addressString, phoneString, testLocationString, testTypeString, labString;
    public int healthNum;
    public Patient person;
    public SharedPreferences sp;
    public TestLocation testLocation;
    public TestType testType;
    public LabProcessingData lab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nursing);

        clear = (Button) findViewById(R.id.button1);
        submit = (Button) findViewById(R.id.button2);

        patientName = (EditText) findViewById(R.id.patientNameField);
        address = (EditText) findViewById(R.id.addressField);
        phone = (EditText) findViewById(R.id.phoneNumField);
        healthCardNumber = (EditText) findViewById(R.id.healthNumberField);

        typeTest = (EditText) findViewById(R.id.typeTest);
        locationTest = (EditText) findViewById(R.id.locationTest);
        locationLab = (EditText) findViewById(R.id.locationLab);


        sp = getSharedPreferences("PatientInfo", Context.MODE_PRIVATE);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                patientNameString = patientName.getText().toString();
                addressString = address.getText().toString();
                phoneString = phone.getText().toString();
                healthNum = Integer.parseInt(healthCardNumber.getText().toString());

                testTypeString = typeTest.getText().toString();
                testLocationString = locationTest.getText().toString();
                labString = locationLab.getText().toString();

                createPatient();
                createTest();

                clear();
                Toast.makeText(MainNursing.this,"Information Saved.",Toast.LENGTH_LONG).show();
            }
        });

    }
    public void clear() {
        patientName.setText("");
        address.setText("");
        phone.setText("");
        healthCardNumber.setText("");
    }
    public void createPatient() {
        person = new Patient(patientNameString,addressString,phoneString,healthNum);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("patientName",person.getName());
        editor.putString("patientPhone",person.getPhoneNumber());
        editor.putString("patientAddress",person.getAddress());
        editor.putInt("patientHeathNumber",person.getHealthCardNumber());
        editor.commit();


    }
    public void createTest() {
        testLocation = new TestLocation(testLocationString);
        testType = new TestType(testTypeString);
        lab = new LabProcessingData(labString);

        SharedPreferences.Editor editor = sp.edit();
        //editor.putString("testLocation",);
        //editor.putString("testType",);
        //editor.putString("labLocation",);
        editor.commit();
    }
}