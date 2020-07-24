/**
 * Author: Shivam Syal
 * Copyright 2020, all rights reserved
 */
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

    private TextView healthCard, testLocation, testType, adminDate, adminTime; //The lab employee gets the see this info from the main.nurse class
    private Button send, clear; //The send button sends the result of the test and the lab location to the doctor via sharedPref2 (sp2)
    private EditText labLocationField, testOutcomeField; //The lab employee enters the lab location and test outcome in these fields
    public SharedPreferences sp2; //sp2 will share info the the doctor determined by the lab employee
    public String labLocation, testOutcome; //The labLocation and TestOutcome in string format to send via sp2
    private SimpleDateFormat dateTime; // This variable creates a date format which will be used for the date of the lab data being processed
    private String dateString; //The data of the lab data being processed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab);
        /*
        The following block of code sets the textViews
        from the corresponding .xml file to textView variables
         */
        healthCard = (TextView) findViewById(R.id.healthCardNum);
        testLocation = (TextView) findViewById(R.id.testLocation);
        testType = (TextView) findViewById(R.id.testType);
        adminDate = (TextView) findViewById(R.id.adminDate);
        adminTime = (TextView) findViewById(R.id.adminTime);

        send = (Button) findViewById(R.id.sendToDoctor);// The send button is set to a variable
        clear = (Button) findViewById(R.id.clearLabInfo);// The clear button is set to a variable

        labLocationField = (EditText) findViewById(R.id.labLocation); //The EditView where the lab employee enters the lab location is set from the .xml file
        testOutcomeField = (EditText) findViewById(R.id.testOutcome);//The EditView where the lab employee enters the test outcome is set from the .xml file

        sp2 = getSharedPreferences("labInfo", Context.MODE_PRIVATE); //sp2 is created with a key of LabInfo so the MainDoctor can retrieve data from the lab

        SharedPreferences sp =getApplicationContext().getSharedPreferences("PatientInfo", Context.MODE_PRIVATE);//sp retrieves data from the MainNurse

        int healthCardNum = sp.getInt("patientHeathNumber",0); // gets the health card number from MainNurse and if it does not exists it sets to a default of ""

        String dateAdminString = sp.getString("testDate",""); // gets the date of test from MainNurse and if it does not exists it sets to a default of ""
        String timeAdminString = sp.getString("testTime","");// gets the time of test from MainNurse and if it does not exists it sets to a default of ""
        String testLocationString = sp.getString("testLocation","");// gets the test location from MainNurse and if it does not exists it sets to a default of ""
        String testTypeString = sp.getString("testType","");// gets the test type from MainNurse and if it does not exists it sets to a default of ""

        healthCard.setText("#: "+healthCardNum);// sets a textView the health card number
        testLocation.setText("Location: "+testLocationString);// sets a textView to the location of test
        testType.setText("Type: "+testTypeString);// sets a textView to the type of test
        adminDate.setText("Date: "+dateAdminString);// sets a textView to the date of test
        adminTime.setText("Time: "+timeAdminString);// sets a textView to the time of test

        send.setOnClickListener(new View.OnClickListener() {//sends of the info the the MainDoctor class
            @Override
            public void onClick(View view) {
                labLocation = labLocationField.getText().toString();//gets info from editView and sets as String
                testOutcome = testOutcomeField.getText().toString();//gets info from editView and sets as String

                dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//creates format for date of lab being processed
                Date currentDate = new Date();//creates a date variable
                String dateAndTime = dateTime.format(currentDate);//gets date and time (current)
                dateString = dateAndTime.substring(0, 10);//extracts only the date

                SharedPreferences.Editor editor = sp2.edit();//sp2 will share info to the mainDoctor class
                editor.putString("labLocation",labLocation); //sends labLocation with a key
                editor.putString("testOutcome",testOutcome);//sends test outcome with a key
                editor.putString("labDate",dateString);// sends lab date with a key
                editor.commit();// commits changes
                /*
                The following block of code will clear the fields
                after the data has been sent to the doctor
                 */
                healthCard.setText("Currently we have no patient.");
                testLocation.setText("Wait till next test arrives.");
                testType.setText("*");
                adminDate.setText("*");
                adminTime.setText("*");
                labLocationField.setText("");
                testOutcomeField.setText("");
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {//Clears all editView fields
            @Override
            public void onClick(View view) {
                labLocationField.setText("");
                testOutcomeField.setText("");
            }
        });

    }
}