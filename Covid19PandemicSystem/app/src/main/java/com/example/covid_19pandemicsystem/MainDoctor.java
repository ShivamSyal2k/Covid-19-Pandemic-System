package com.example.covid_19pandemicsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainDoctor extends AppCompatActivity {
    private TextView sympDisplay, contactDisplay, patientName, patientAddress, patientPhone, patientHealthNum, testType, testLocation, labLocation, testTime, testDate, labDate, outcome;
    private String nameString, addressString,phoneString,testTypeString, testLocationString, labLocationString, testTimeString, testDateString, labDateString, outcomeString;
    private int healthNum;
    private Button checkout;
    SharedPreferences sp, sp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor);

        checkout = (Button) findViewById(R.id.checkOut);

        sympDisplay = (TextView) findViewById(R.id.symptomDisplay);
        contactDisplay = (TextView) findViewById(R.id.contactDisplay);
        patientName = (TextView) findViewById(R.id.nameDisplay);
        patientAddress = (TextView) findViewById(R.id.addressDisplay);
        patientPhone = (TextView) findViewById(R.id.phoneDisplay);
        patientHealthNum = (TextView) findViewById(R.id.healthNumDisplay);
        testType = (TextView) findViewById(R.id.typeTestDisplay);
        testLocation = (TextView) findViewById(R.id.testLocDisplay);
        labLocation = (TextView) findViewById(R.id.labDisplay);
        testTime = (TextView) findViewById(R.id.testTimeDisplay);
        testDate = (TextView) findViewById(R.id.dateTestDisplay);
        labDate = (TextView) findViewById(R.id.labDateDisplay);
        outcome = (TextView) findViewById(R.id.outcomeDisplay);

        sp =getApplicationContext().getSharedPreferences("PatientInfo", Context.MODE_PRIVATE);
        sp2 =getApplicationContext().getSharedPreferences("labInfo", Context.MODE_PRIVATE);
        nameString = sp.getString("patientName","");
        addressString = sp.getString("patientAddress","");
        phoneString = sp.getString("patientPhone","");
        testTypeString = sp.getString("testType","");
        testLocationString = sp.getString("testLocation","");
        labLocationString = sp2.getString("labLocation","");
        testTimeString = sp.getString("testTime","");
        testDateString = sp.getString("testDate","");
        labDateString = sp2.getString("labDate","");
        outcomeString = sp2.getString("testOutcome","");
        healthNum = sp.getInt("patientHeathNumber",0);

        patientName.setText("Name: "+nameString);
        patientAddress.setText("Address: "+addressString);
        patientPhone.setText("Phone #: "+phoneString);
        patientHealthNum.setText("Health #: "+healthNum);
        testType.setText("Test Type: "+testTypeString);
        testLocation.setText("Test Location: "+testLocationString);
        labLocation.setText("Lab Location: "+labLocationString);
        testTime.setText("Test Time: "+testTimeString);
        testDate.setText("Test Date: "+testDateString);
        labDate.setText("Lab Date: "+labDateString);
        outcome.setText("Outcome: "+outcomeString);
        Set<String> emptySet = Collections.<String>emptySet();

        Set<String> symptomsSet, symptomsStart, symptomsEnd;

        symptomsSet = sp.getStringSet("symptomsList",emptySet);
        List<String> items = new ArrayList<String>();
        items.addAll(symptomsSet);

        symptomsStart = sp.getStringSet("symptomsStartList",emptySet);
        List<String> items2 = new ArrayList<String>();
        items2.addAll(symptomsStart);

        symptomsEnd = sp.getStringSet("symptomsEndList",emptySet);
        List<String> items3 = new ArrayList<String>();
        items3.addAll(symptomsEnd);

        String print = "";
        for(int i =0; i<symptomsSet.size();i++){

            print = print +"Symptom: "+items.get(i)+ "\n"+"Start date: "+items2.get(i)+"\n"+ "End date: "+items3.get(i)+"\n\n";

        }
        sympDisplay.setText(print);
        sympDisplay.setMovementMethod(new ScrollingMovementMethod());

        Set<String> contactNameSet, contactDateSet;
        contactNameSet = sp.getStringSet("contactList",emptySet);
        List<String> items4 = new ArrayList<String>();
        items4.addAll(contactNameSet);

        contactDateSet = sp.getStringSet("contactDateList",emptySet);
        List<String> items5 = new ArrayList<String>();
        items5.addAll(contactDateSet);

        String print2 = "";
        for(int i =0; i<contactNameSet.size();i++){

            print2 = print2 +"Contact Name: "+items4.get(i)+ "\n"+"Contact Date: "+items5.get(i)+"\n\n";

        }
        contactDisplay.setText(print2);
        contactDisplay.setMovementMethod(new ScrollingMovementMethod());

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patientName.setText("Name: ");
                patientAddress.setText("Address: ");
                patientPhone.setText("Phone #: ");
                patientHealthNum.setText("Health #: ");
                testType.setText("Test Type: ");
                testLocation.setText("Test Location: ");
                labLocation.setText("Lab Location: ");
                testTime.setText("Test Time: ");
                testDate.setText("Test Date: ");
                labDate.setText("Lab Date: ");
                outcome.setText("Outcome: ");
                sympDisplay.setText("Symptoms: ");
                contactDisplay.setText("Contacts:");

                SharedPreferences.Editor editor = sp.edit();
                SharedPreferences.Editor editor2 = sp2.edit();
                editor.clear();
                editor.commit();
                editor2.clear();
                editor2.commit();
            }
        });
    }
}