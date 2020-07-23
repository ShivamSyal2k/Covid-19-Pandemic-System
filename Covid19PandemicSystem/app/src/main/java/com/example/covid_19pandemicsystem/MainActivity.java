package com.example.covid_19pandemicsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button1, button2;
    private EditText userID;
    private EditText pass;

    public String userType, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        userID = (EditText) findViewById(R.id.userType);
        pass = (EditText) findViewById(R.id.userPassword);



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPatient();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userType = userID.getText().toString().toLowerCase();
                password = pass.getText().toString();
                if(userType.equals("nurse") && password.equals("nursing123")) {
                    openNurse();
                }
                else if(userType.equals("lab") && password.equals("lab321")) {
                    openLab();
                }
            }
        });
    }
    public void openNurse(){
        Intent intent = new Intent(this, MainNursing.class);
        startActivity(intent);
    }
    public void openPatient(){
        Intent intent = new Intent(this, MainPatient.class);
        startActivity(intent);
    }
    public void openLab(){
        Intent intent = new Intent(this, MainLab.class);
        startActivity(intent);
    }
}