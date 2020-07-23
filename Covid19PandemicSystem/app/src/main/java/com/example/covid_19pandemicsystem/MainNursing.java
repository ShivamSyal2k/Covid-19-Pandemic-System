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
import java.util.Date;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



public class MainNursing extends AppCompatActivity {
    private Button clear, submit, addSymptom, addContact;
    private EditText patientName, address, phone, healthCardNumber, typeTest, locationTest, symptomField,startField, endField, contactNameField, contactDateField;
    private SimpleDateFormat dateTime;
    public String patientNameString, addressString, phoneString, testLocationString, testTypeString, timeString, dateString;
    public int healthNum;
    public Patient person;
    public SharedPreferences sp;
    public TestLocation testLocation;
    public TestType testType;
    public LabProcessingData lab;
    public Test patientTest;
    public ArrayList<String> symptoms, startDate, endDate, contactName,contactDate;
    public ArrayList<SymptomDetails> symptomDetails;
    public ArrayList<ContactDetails> contactDetails;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nursing);

        clear = (Button) findViewById(R.id.button1);
        submit = (Button) findViewById(R.id.button2);
        addSymptom = (Button) findViewById(R.id.button3);
        addContact = (Button) findViewById(R.id.button4);

        symptoms = new ArrayList<String>();
        startDate = new ArrayList<String>();
        endDate = new ArrayList<String>();
        contactName = new ArrayList<String>();
        contactDate = new ArrayList<String>();

        symptomDetails = new ArrayList<SymptomDetails>();
        contactDetails = new ArrayList<ContactDetails>();


        patientName = (EditText) findViewById(R.id.patientNameField);
        address = (EditText) findViewById(R.id.addressField);
        phone = (EditText) findViewById(R.id.phoneNumField);
        healthCardNumber = (EditText) findViewById(R.id.healthNumberField);

        typeTest = (EditText) findViewById(R.id.typeTest);
        locationTest = (EditText) findViewById(R.id.locationTest);

        symptomField = (EditText) findViewById(R.id.symptomType);
        startField = (EditText) findViewById(R.id.startSymptom);
        endField = (EditText) findViewById(R.id.endSymptom);

        contactDateField = (EditText) findViewById(R.id.contactDate);
        contactNameField = (EditText) findViewById(R.id.contactName);

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

                createPatient();
                createTest();
                sendSymptoms();
                sendContacts();

                clear();
                Toast.makeText(MainNursing.this,"Information Sent.",Toast.LENGTH_LONG).show();
            }
        });
        addSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSymptoms();
                symptomField.setText("");
                startField.setText("");
                endField.setText("");
                Toast.makeText(MainNursing.this,"Symptom Saved.",Toast.LENGTH_LONG).show();
            }
        });
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createContacts();
                contactNameField.setText("");
                contactDateField.setText("");
                Toast.makeText(MainNursing.this,"Contact Saved.",Toast.LENGTH_LONG).show();
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
        lab = new LabProcessingData("");

        dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
        String dateAndTime = dateTime.format(currentDate);
        dateString = dateAndTime.substring(0, 10);
        timeString = dateAndTime.substring(11);

        patientTest = new Test(dateString,timeString,"Null","Null",testLocation,testType,person,lab);
        person.addTest(patientTest);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("testLocation",patientTest.getTestLocation().getLocationType());
        editor.putString("testType",patientTest.getTestType().getTestType());
        editor.putString("testDate",dateString);
        editor.putString("testTime",timeString);
        editor.commit();
    }
    public void createSymptoms(){
        String symp = symptomField.getText().toString();
        symptoms.add(symp);

        String startSymp = startField.getText().toString();
        startDate.add(startSymp);

        String endSymp = endField.getText().toString();
        endDate.add(endSymp);

        //SymptomDetails sympFull = new SymptomDetails(symp,startSymp,endSymp,person);
        //symptomDetails.add(sympFull);

        //person.addSymptomDetail(sympFull);

    }
    public void sendSymptoms(){
        SharedPreferences.Editor editor = sp.edit();
        Set<String> setSymp = new HashSet<String>(symptoms);
        Set<String> setEnd = new HashSet<String>(endDate);
        Set<String> setStart = new HashSet<String>(startDate);
        editor.putStringSet("symptomsList",setSymp);
        editor.putStringSet("symptomsEndList",setEnd);
        editor.putStringSet("symptomsStartList",setStart);
        editor.commit();
    }
    public void createContacts(){
        String contactNameString = contactNameField.getText().toString();
        contactName.add(contactNameString);

        String contactDateString = contactDateField.getText().toString();
        contactDate.add(contactDateString);

       //ContactDetails contactFull = new ContactDetails(contactNameString,contactDateString);
        //contactDetails.add(contactFull);

       // person.addContactDetail(contactFull);

    }
    public  void sendContacts(){
        SharedPreferences.Editor editor = sp.edit();
        Set<String> setContactName = new HashSet<String>(contactName);
        Set<String> setContactDate = new HashSet<String>(contactDate);
        editor.putStringSet("contactList",setContactName);
        editor.putStringSet("contactDateList",setContactDate);
        editor.commit();
    }
}