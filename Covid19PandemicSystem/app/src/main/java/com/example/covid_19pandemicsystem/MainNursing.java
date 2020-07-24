/**
 * Author: Shivam Syal
 * Copyright 2020, all rights reserved
 */
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
    private Button clear, submit, addSymptom, addContact;//buttons from the .xml format corresponding to this class
    private EditText patientName, address, phone, healthCardNumber, typeTest, locationTest, symptomField,startField, endField, contactNameField, contactDateField;// feilds where nurse enters info
    private SimpleDateFormat dateTime;// Format variable for the time and date of the test
    public Patient person; //Creates a patient variable from the information given
    public SharedPreferences sp;//sp shares info to other activities
    public TestLocation testLocation;// test location
    public TestType testType;// the type of test given
    public LabProcessingData lab; // Place holder, the lab will give this info after
    public Test patientTest; // The test which was conducted on person
    public ArrayList<String> symptoms, startDate, endDate, contactName,contactDate; //arrayLists which will be converted to sets and sent to other activities via sharedPrefs
    public ArrayList<SymptomDetails> symptomDetails;//Info combined into 1 ArrayList for symptoms for the future when Gson is implemented
    public ArrayList<ContactDetails> contactDetails;//Info combined into 1 ArrayLst for contacts for the future when Gson is implemented
    private boolean flag, flag1; //Flags which will give the nurse a prompt that info was saved when both are true


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nursing);

        flag = false;// initially false will be set true if all fields in patient info are given
        flag1 = false;// initially false will be set true if all fields in test info are given

        /*
        The following block of code will set the buttons from the
        .xml file corresponding to this class the a textView variable
         */
        clear = (Button) findViewById(R.id.button1);
        submit = (Button) findViewById(R.id.button2);
        addSymptom = (Button) findViewById(R.id.button3);
        addContact = (Button) findViewById(R.id.button4);

        symptoms = new ArrayList<String>();//initializes ArrayList (see above for what the ArrayList is for)
        startDate = new ArrayList<String>();//initializes ArrayList (see above for what the ArrayList is for)
        endDate = new ArrayList<String>();//initializes ArrayList (see above for what the ArrayList is for)
        contactName = new ArrayList<String>();//initializes ArrayList (see above for what the ArrayList is for)
        contactDate = new ArrayList<String>();//initializes ArrayList (see above for what the ArrayList is for)

        symptomDetails = new ArrayList<SymptomDetails>();//initializes ArrayList (see above for what the ArrayList is for)
        contactDetails = new ArrayList<ContactDetails>();//initializes ArrayList (see above for what the ArrayList is for)

        /*
        The following block of code will set the EditTexts from the
        .xml file corresponding to this class the a EditText variable
         */
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



        sp = getSharedPreferences("PatientInfo", Context.MODE_PRIVATE);// sp will send info to other activities with the key of PatientInfo

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });//Clear() clears all the patient and test fields

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//Submit sends out all info to other activities via sharedPref
                createPatient();//method infos are below
                createTest();
                sendSymptoms();
                sendContacts();
                clear();
                if(flag == true && flag1 ==true) {//If both flags are true (If all fields aren't empty for test and patient) then sends out the msg below
                    Toast.makeText(MainNursing.this, "Information Sent.", Toast.LENGTH_LONG).show();
                }
            }
        });
        addSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//Adds a symptom, start and end to lists
                createSymptoms();
                /*
                clears all fields after
                 */
                symptomField.setText("");
                startField.setText("");
                endField.setText("");

            }
        });
        addContact.setOnClickListener(new View.OnClickListener() {//Adds a contact and date to lists
            @Override
            public void onClick(View view) {
                createContacts();
                /*
                clears all fields after
                 */
                contactNameField.setText("");
                contactDateField.setText("");
                Toast.makeText(MainNursing.this,"Contact Saved.",Toast.LENGTH_LONG).show();//gives msg that contact was saved
            }
        });

    }
    public void clear() {
         /*
         Clears all text in patient and test fields
         */
        patientName.setText("");
        address.setText("");
        phone.setText("");
        healthCardNumber.setText("");
        typeTest.setText("");
        locationTest.setText("");

    }
    public void createPatient() {//creates the patient info
        String patientNameString = patientName.getText().toString(); //patient name is taken and put into a String
        String addressString = address.getText().toString();//patient address is taken and put into a String
        String phoneString = phone.getText().toString();//phone # is taken and put into a String
        String healthCardString= healthCardNumber.getText().toString();//health card num will be put into a String than parsed into a int
        int healthNum = Integer.parseInt(healthCardString);// healthNum is turned into a int
        if(patientNameString.length()>=1 && addressString.length()>=1 && phoneString.length()>=1 && healthCardString.length()>=1) {
            person = new Patient(patientNameString, addressString, phoneString, healthNum);// A person is created it can't be send via sharedPref so it is just made for future work via Gson library
            SharedPreferences.Editor editor = sp.edit();//Info is send to other activities via this editor
            editor.putString("patientName", person.getName());//String is sent with a key
            editor.putString("patientPhone", person.getPhoneNumber());//String is sent with a key
            editor.putString("patientAddress", person.getAddress());//String is sent with a key
            editor.putInt("patientHeathNumber", person.getHealthCardNumber());//String is sent with a key
            editor.commit();//commits the variables
            flag = true;//flag is set true, this is for the info saved msg, check the submit button for more info
        }else{
            /*
            Below we address all errors and give a msg based on said error
             */
            if(patientNameString.length()<1){
                Toast.makeText(MainNursing.this, "Error: patient name missing.", Toast.LENGTH_LONG).show();
            }
            if(addressString.length()<1){
                Toast.makeText(MainNursing.this, "Error: patient address missing.", Toast.LENGTH_LONG).show();
            }
            if(phoneString.length()<1){
                Toast.makeText(MainNursing.this, "Error: patient phone # missing.", Toast.LENGTH_LONG).show();
            }
            if(healthCardString.length()<1){
                Toast.makeText(MainNursing.this, "Error: patient health card # missing.", Toast.LENGTH_LONG).show();
            }
            flag = false;
        }

    }
    public void createTest() {// creates the test info to be sent to other activities
        String testTypeString = typeTest.getText().toString();//taken and put into a String
        String testLocationString = locationTest.getText().toString();//taken and put into a String
        if(testLocationString.length()>=1 && testTypeString.length()>=1) {// Prevents nurse from sending empty fields
            testLocation = new TestLocation(testLocationString);// location where test is done
            testType = new TestType(testTypeString);//test type of the test which was conducted on person
            lab = new LabProcessingData("");//lab which will process data, null for now the lab will enter in the data for its name

            dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//The date and time format
            Date currentDate = new Date();
            String dateAndTime = dateTime.format(currentDate);// date and time is converted into a string
            String dateString = dateAndTime.substring(0, 10);//substring of date is created
            String timeString = dateAndTime.substring(11);//substring of time is made

            patientTest = new Test(dateString, timeString, "Null", "Null", testLocation, testType, person, lab);// Creates a patient which can be used in the future when the Gson library is added
            person.addTest(patientTest);//adds the test conducted to the patient person

            SharedPreferences.Editor editor = sp.edit();//editor will sent info via sp
            editor.putString("testLocation", patientTest.getTestLocation().getLocationType());// sends info to other activities via a key
            editor.putString("testType", patientTest.getTestType().getTestType());// sends info to other activities via a key
            editor.putString("testDate", dateString); // sends info to other activities via a key
            editor.putString("testTime", timeString);   // sends info to other activities via a key
            editor.commit();// commits the keys
            flag1 = true;//flag is set true, this is for the info saved msg, check the submit button for more info
        }else{
            /*
            The code below addresses if the contact fields are empty
             */
            if(testLocationString.length()<1){
                Toast.makeText(MainNursing.this, "Error: test location missing.", Toast.LENGTH_LONG).show();
            }
            if(testTypeString.length()<1){
                Toast.makeText(MainNursing.this, "Error: test type missing.", Toast.LENGTH_LONG).show();
            }
            flag1 = false;
        }
    }
    public void createSymptoms(){//Class creates a symptom, will display errors if a field is left empty in symptoms
        String symp = symptomField.getText().toString(); //taken and put into a String
        String startSymp = startField.getText().toString(); //taken and put into a String
        String endSymp = endField.getText().toString();  //taken and put into a String
        if(symp.length()>=1 && startSymp.length()>=1 && endSymp.length()>=1) {//if fields are not empty a symptom, start and end date will be made
            symptoms.add(symp);
            startDate.add(startSymp);
            endDate.add(endSymp);
            //SymptomDetails sympFull = new SymptomDetails(symp,startSymp,endSymp,person);
            //symptomDetails.add(sympFull);
            //person.addSymptomDetail(sympFull);
            Toast.makeText(MainNursing.this,"Symptom Saved.",Toast.LENGTH_LONG).show();//Displays that symptom is saved

        }else {
            /*
            The code below will display an error if a field is empty and say they type of error
             */
            if (symp.length() < 1) {
                Toast.makeText(MainNursing.this, "Error: symptom detail field is empty.", Toast.LENGTH_LONG).show();
            }
            if (startSymp.length() < 1) {
                Toast.makeText(MainNursing.this, "Error: symptom start field is empty.", Toast.LENGTH_LONG).show();
            }
            if (endSymp.length() < 1) {
                Toast.makeText(MainNursing.this, "Error: symptom end field is empty.", Toast.LENGTH_LONG).show();
            }

        }
    }
    public void sendSymptoms(){
        SharedPreferences.Editor editor = sp.edit();// editor sends symptoms via sp
        Set<String> setSymp = new HashSet<String>(symptoms);// converts the ArrayList into a set
        Set<String> setEnd = new HashSet<String>(endDate);// converts the ArrayList into a set
        Set<String> setStart = new HashSet<String>(startDate);// converts the ArrayList into a set
        editor.putStringSet("symptomsList",setSymp);// send a set of string via a key
        editor.putStringSet("symptomsEndList",setEnd); // send a set of string via a key
        editor.putStringSet("symptomsStartList",setStart); // send a set of string via a key
        editor.commit();//editor commits and sends the info
    }
    public void createContacts(){
        String contactNameString = contactNameField.getText().toString();//taken and put into a String
        String contactDateString = contactDateField.getText().toString(); //taken and put into a String
        if(contactDateString.length()>=1 && contactNameString.length()>=1) {//if fields are not empty a contact and date will be made
            contactName.add(contactNameString);// adds the contact name to the contact name String ArrayList
            contactDate.add(contactDateString);// adds the contact date to the contact date String ArrayList
            //ContactDetails contactFull = new ContactDetails(contactNameString,contactDateString);
            //contactDetails.add(contactFull);
            // person.addContactDetail(contactFull);
        }else{
            /*
                The code below addressed if the contact fields are left empty with an error msg
             */
            if(contactDateString.length()<1){
                Toast.makeText(MainNursing.this, "Error: contact date field empty.", Toast.LENGTH_LONG).show();
            }
            if(contactNameString.length()<1) {
                Toast.makeText(MainNursing.this, "Error: contact name field empty.", Toast.LENGTH_LONG).show();
            }
        }
    }
    public  void sendContacts(){
        SharedPreferences.Editor editor = sp.edit();//editor will send contact info
        Set<String> setContactName = new HashSet<String>(contactName); //converts the ArrayList into a set
        Set<String> setContactDate = new HashSet<String>(contactDate); //converts the ArrayList into a set
        editor.putStringSet("contactList",setContactName); // sends set via a key
        editor.putStringSet("contactDateList",setContactDate); //sends set via a key
        editor.commit(); //editor commits and sends the info
    }
}