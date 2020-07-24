/**
 * Author: Shivam Syal
 * Copyright 2020, all rights reserved
 */
package com.example.covid_19pandemicsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainPatient extends AppCompatActivity {
    private TextView text;//Will display info about covid-19
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);
        text = (TextView) findViewById(R.id.patientName);//sets the textView to a variable
        /*
        the code below shows info on the virus from
        http://www.bccdc.ca/health-info/diseases-conditions/covid-19/about-covid-19
        All the text in the textView belongs to them (Thank You bccdc)
         */
        text.setText("Coronaviruses are a large family of viruses found mostly in animals. In humans, they can cause diseases ranging from the common cold to more severe diseases such as Severe Acute Respiratory Syndrome (SARS) and Middle East Respiratory Syndrome (MERS). The disease caused by the new coronavirus has been named COVID-19.\n" +
                "\n" +
                "While many of the characteristics of COVID-19 are still unknown, mild to severe illness has been reported for confirmed cases. "+"\n\n"+"Source: http://www.bccdc.ca/health-info/diseases-conditions/covid-19/about-covid-19");
    }
}