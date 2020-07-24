/**
 * Author: Shivam Syal
 * Copyright 2020, all rights reserved
 */
package com.example.covid_19pandemicsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button1, button2;// button1 is the login button, and button2 lets a guest read up on Covid-19
    private EditText userID; //This is where the user enters the type of user they are
    private EditText pass; //This is where the user enters in the password corresponding to the user tpye

    public String userType, password; //The password and Id will be converted to string

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);// Finds and sets the login button from the .xml file
        button2 = (Button) findViewById(R.id.button2);// Finds and sets the guest button from the .xml file
        userID = (EditText) findViewById(R.id.userType);// Finds and sets the field where you enter the user ID from the .xml file
        pass = (EditText) findViewById(R.id.userPassword);// Finds and sets the field where you enter the password from the .xml file



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPatient();
            }
        }); // when the user clicks the guest button the openPatient() Function is called
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // when the login button the following occurs
                userType = userID.getText().toString().toLowerCase();// Gets the user id and coverts in into a lowercase string because id's are not case sensitive
                password = pass.getText().toString(); // converts the user password to a string
                if(userType.equals("nurse") && password.equals("nursing123")) { // if the id and password match openNurse() is called
                    openNurse();
                }
                else if(userType.equals("lab") && password.equals("lab321")) { // if the id and password match openLab(); is called
                    openLab();
                }
                else if (userType.equals("doctor") && password.equals("doc")){ // if the id and password match openDoctor(); is called
                    openDoctor();
                }else{
                    if(userType.equals("nurse") && !password.equals("nursing123")){ // if the user id exists, but the password doesn't the following error will popup
                        Toast.makeText(MainActivity.this, "Password does not match Nurse.", Toast.LENGTH_LONG).show();
                    }
                    else if(userType.equals("lab") && !password.equals("lab321")){ // if the user id exists, but the password doesn't the following error will popup
                        Toast.makeText(MainActivity.this, "Password does not match Lab.", Toast.LENGTH_LONG).show();
                    }
                    else if(userType.equals("doctor") && !password.equals("doc")){ // if the user id exists, but the password doesn't the following error will popup
                        Toast.makeText(MainActivity.this, "Password does not match Doctor.", Toast.LENGTH_LONG).show();
                    }
                    else{ // if the user id does not exist and error telling the user that the id doesn't exist will popup
                        Toast.makeText(MainActivity.this, "This type of user does not exist.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void openNurse(){
        Intent intent = new Intent(this, MainNursing.class); //Creates an intent which will allow another activity to be launched
        startActivity(intent); // opens the MainNursing.class activity
    }
    public void openPatient(){
        Intent intent = new Intent(this, MainPatient.class); //Creates an intent which will allow another activity to be launched
        startActivity(intent); // opens the MainPatient.class activity
    }
    public void openLab(){
        Intent intent = new Intent(this, MainLab.class); //Creates an intent which will allow another activity to be launched
        startActivity(intent); // opens the MainLab.class activity
    }
    public void openDoctor(){
        Intent intent = new Intent(this, MainDoctor.class); //Creates an intent which will allow another activity to be launched
        startActivity(intent); // opens the  MainDoctor.class activity
    }
}