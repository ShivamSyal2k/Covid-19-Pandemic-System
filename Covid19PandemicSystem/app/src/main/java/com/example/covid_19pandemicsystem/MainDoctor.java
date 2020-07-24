/**
 * Author: Shivam Syal
 * Copyright 2020, all rights reserved
 */
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
    private TextView sympDisplay, contactDisplay, patientName, patientAddress, patientPhone, patientHealthNum, testType, testLocation, labLocation, testTime, testDate, labDate, outcome; //Creates a multitude of text fields to display information
    private String nameString, addressString,phoneString,testTypeString, testLocationString, labLocationString, testTimeString, testDateString, labDateString, outcomeString; //Strings which are used to store patient information which are taken from other activities via SharedPreferences
    private int healthNum; //Health Card Number taken from other activities via SharedPreferences
    private Button checkout; //Checkout will checkout a patient from the system removing there data from the app
    SharedPreferences sp, sp2; //sp is a SharedPreference used to get info from the MainNurse.class, sp2 is a SharedPreference used to get info from the MainLab.class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor);

        checkout = (Button) findViewById(R.id.checkOut); //Assigns the checkout button from the .xml file to a variable

        /*
        The following set of textViews are found from the .xml file
        corresponding to this activity and they are assignment variables
         */
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

        sp =getApplicationContext().getSharedPreferences("PatientInfo", Context.MODE_PRIVATE);//Gets info from the mainNurse class
        sp2 =getApplicationContext().getSharedPreferences("labInfo", Context.MODE_PRIVATE);////Gets info from the mainLab class

        nameString = sp.getString("patientName",""); //gets the patient name from the sharedPref via a key
        addressString = sp.getString("patientAddress","");//gets the patient address from the sharedPref via a key
        phoneString = sp.getString("patientPhone","");//gets the patient phone # from the sharedPref via a key
        testTypeString = sp.getString("testType","");//gets the test type from the sharedPref via a key
        testLocationString = sp.getString("testLocation","");//gets the test location from the sharedPref via a key
        labLocationString = sp2.getString("labLocation","");//gets the lab location from the sharedPref via a key
        testTimeString = sp.getString("testTime","");//gets the test time from the sharedPref via a key
        testDateString = sp.getString("testDate","");//gets the test date from the sharedPref via a key
        labDateString = sp2.getString("labDate","");//gets the lab date from the sharedPref via a key
        outcomeString = sp2.getString("testOutcome","");//gets the test outcome from the sharedPref via a key
        healthNum = sp.getInt("patientHeathNumber",0);//gets the health card # from the sharedPref via a key

        /*
        The following block of code assigns the string set above
        (as well as int from health card #) to the text views
         */
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

        Set<String> emptySet = Collections.<String>emptySet(); //If the nurse enter no information and the doctor attempts login the empty set will prevent the app from crashing
        Set<String> symptomsSet, symptomsStart, symptomsEnd;// set of symptoms, start and end dates which will be received via sp (sharedPref)

        symptomsSet = sp.getStringSet("symptomsList",emptySet); //gets the set of symptoms from MainNurse and sets it to said set, if the sharedPref has no set it will set it to a empty set
        List<String> items = new ArrayList<String>(); //Converts set to ArrayList
        items.addAll(symptomsSet);

        symptomsStart = sp.getStringSet("symptomsStartList",emptySet);//gets the set of start dates from MainNurse and sets it to said set, if the sharedPref has no set it will set it to a empty set
        List<String> items2 = new ArrayList<String>();//Converts set to ArrayList
        items2.addAll(symptomsStart);

        symptomsEnd = sp.getStringSet("symptomsEndList",emptySet);//gets the set of end dates from MainNurse and sets it to said set, if the sharedPref has no set it will set it to a empty set
        List<String> items3 = new ArrayList<String>();//Converts set to ArrayList
        items3.addAll(symptomsEnd);

        /*
        The following set of code will print the symptoms, start and end dates in a scrollable textView
         */
        String print = "";
        for(int i =0; i<symptomsSet.size();i++){

            print = print +"Symptom: "+items.get(i)+ "\n"+"Start date: "+items2.get(i)+"\n"+ "End date: "+items3.get(i)+"\n\n";

        }
        sympDisplay.setText(print);// sets info to display
        sympDisplay.setMovementMethod(new ScrollingMovementMethod()); // makes the textView scrollable

        Set<String> contactNameSet, contactDateSet;//sets for the contact names and dates
        contactNameSet = sp.getStringSet("contactList",emptySet);//gets the set of contact names from MainNurse and sets it to said set, if the sharedPref has no set it will set it to a empty set
        List<String> items4 = new ArrayList<String>();//Converts set to ArrayList
        items4.addAll(contactNameSet);

        contactDateSet = sp.getStringSet("contactDateList",emptySet);//gets the set of contact dates from MainNurse and sets it to said set, if the sharedPref has no set it will set it to a empty set
        List<String> items5 = new ArrayList<String>();//Converts set to ArrayList
        items5.addAll(contactDateSet);
         /*
        The following set of code will print the contact name and contact date in a scrollable textView
         */
        String print2 = "";
        for(int i =0; i<contactNameSet.size();i++){

            print2 = print2 +"Contact Name: "+items4.get(i)+ "\n"+"Contact Date: "+items5.get(i)+"\n\n";

        }
        contactDisplay.setText(print2);// sets info to display
        contactDisplay.setMovementMethod(new ScrollingMovementMethod());// makes the textView scrollable

        checkout.setOnClickListener(new View.OnClickListener() {//This set of code checks out the patient from the system removing there info from the app
            @Override
            public void onClick(View view) {
                /*
                The block of code bellow clears the textViews when the doctor checks out the patient
                 */
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
                /*
                The block of code below clears the sharedPrefs, removing all data about a patient from the app
                 */
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