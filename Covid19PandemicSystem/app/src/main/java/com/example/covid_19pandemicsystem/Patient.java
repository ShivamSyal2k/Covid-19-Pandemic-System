/**
 * Author: Shivam Syal
 * Copyright 2020, all rights reserved
 */
package com.example.covid_19pandemicsystem;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4994.706e115af modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 93 "model.ump"
public class Patient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Patient Attributes
  private String name;
  private String address;
  private String phoneNumber;
  private int healthCardNumber;

  //Patient Associations
  private List<Test> tests;
  private List<SymptomDetails> symptomDetails;
  private List<ContactDetails> contactDetails;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Patient(String aName, String aAddress, String aPhoneNumber, int aHealthCardNumber)
  {
    name = aName;
    address = aAddress;
    phoneNumber = aPhoneNumber;
    healthCardNumber = aHealthCardNumber;
    tests = new ArrayList<Test>();
    symptomDetails = new ArrayList<SymptomDetails>();
    contactDetails = new ArrayList<ContactDetails>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setHealthCardNumber(int aHealthCardNumber)
  {
    boolean wasSet = false;
    healthCardNumber = aHealthCardNumber;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public int getHealthCardNumber()
  {
    return healthCardNumber;
  }
  /* Code from template association_GetMany */
  public Test getTest(int index)
  {
    Test aTest = tests.get(index);
    return aTest;
  }

  public List<Test> getTests()
  {
    List<Test> newTests = Collections.unmodifiableList(tests);
    return newTests;
  }

  public int numberOfTests()
  {
    int number = tests.size();
    return number;
  }

  public boolean hasTests()
  {
    boolean has = tests.size() > 0;
    return has;
  }

  public int indexOfTest(Test aTest)
  {
    int index = tests.indexOf(aTest);
    return index;
  }
  /* Code from template association_GetMany */
  public SymptomDetails getSymptomDetail(int index)
  {
    SymptomDetails aSymptomDetail = symptomDetails.get(index);
    return aSymptomDetail;
  }

  public List<SymptomDetails> getSymptomDetails()
  {
    List<SymptomDetails> newSymptomDetails = Collections.unmodifiableList(symptomDetails);
    return newSymptomDetails;
  }

  public int numberOfSymptomDetails()
  {
    int number = symptomDetails.size();
    return number;
  }

  public boolean hasSymptomDetails()
  {
    boolean has = symptomDetails.size() > 0;
    return has;
  }

  public int indexOfSymptomDetail(SymptomDetails aSymptomDetail)
  {
    int index = symptomDetails.indexOf(aSymptomDetail);
    return index;
  }
  /* Code from template association_GetMany */
  public ContactDetails getContactDetail(int index)
  {
    ContactDetails aContactDetail = contactDetails.get(index);
    return aContactDetail;
  }

  public List<ContactDetails> getContactDetails()
  {
    List<ContactDetails> newContactDetails = Collections.unmodifiableList(contactDetails);
    return newContactDetails;
  }

  public int numberOfContactDetails()
  {
    int number = contactDetails.size();
    return number;
  }

  public boolean hasContactDetails()
  {
    boolean has = contactDetails.size() > 0;
    return has;
  }

  public int indexOfContactDetail(ContactDetails aContactDetail)
  {
    int index = contactDetails.indexOf(aContactDetail);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTests()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Test addTest(String aDateOfAdministration, String aTime, String aDateOfResults, String aOutcome, TestLocation aTestLocation, TestType aTestType, LabProcessingData aLabProcessingData)
  {
    return new Test(aDateOfAdministration, aTime, aDateOfResults, aOutcome, aTestLocation, aTestType, this, aLabProcessingData);
  }

  public boolean addTest(Test aTest)
  {
    boolean wasAdded = false;
    if (tests.contains(aTest)) { return false; }
    Patient existingPatient = aTest.getPatient();
    boolean isNewPatient = existingPatient != null && !this.equals(existingPatient);
    if (isNewPatient)
    {
      aTest.setPatient(this);
    }
    else
    {
      tests.add(aTest);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTest(Test aTest)
  {
    boolean wasRemoved = false;
    //Unable to remove aTest, as it must always have a patient
    if (!this.equals(aTest.getPatient()))
    {
      tests.remove(aTest);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTestAt(Test aTest, int index)
  {  
    boolean wasAdded = false;
    if(addTest(aTest))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTests()) { index = numberOfTests() - 1; }
      tests.remove(aTest);
      tests.add(index, aTest);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTestAt(Test aTest, int index)
  {
    boolean wasAdded = false;
    if(tests.contains(aTest))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTests()) { index = numberOfTests() - 1; }
      tests.remove(aTest);
      tests.add(index, aTest);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTestAt(aTest, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSymptomDetails()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SymptomDetails addSymptomDetail(String aSymptom, String aStartDate, String aEndDate)
  {
    return new SymptomDetails(aSymptom, aStartDate, aEndDate, this);
  }

  public boolean addSymptomDetail(SymptomDetails aSymptomDetail)
  {
    boolean wasAdded = false;
    if (symptomDetails.contains(aSymptomDetail)) { return false; }
    Patient existingPatient = aSymptomDetail.getPatient();
    boolean isNewPatient = existingPatient != null && !this.equals(existingPatient);
    if (isNewPatient)
    {
      aSymptomDetail.setPatient(this);
    }
    else
    {
      symptomDetails.add(aSymptomDetail);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSymptomDetail(SymptomDetails aSymptomDetail)
  {
    boolean wasRemoved = false;
    //Unable to remove aSymptomDetail, as it must always have a patient
    if (!this.equals(aSymptomDetail.getPatient()))
    {
      symptomDetails.remove(aSymptomDetail);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSymptomDetailAt(SymptomDetails aSymptomDetail, int index)
  {  
    boolean wasAdded = false;
    if(addSymptomDetail(aSymptomDetail))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSymptomDetails()) { index = numberOfSymptomDetails() - 1; }
      symptomDetails.remove(aSymptomDetail);
      symptomDetails.add(index, aSymptomDetail);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSymptomDetailAt(SymptomDetails aSymptomDetail, int index)
  {
    boolean wasAdded = false;
    if(symptomDetails.contains(aSymptomDetail))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSymptomDetails()) { index = numberOfSymptomDetails() - 1; }
      symptomDetails.remove(aSymptomDetail);
      symptomDetails.add(index, aSymptomDetail);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSymptomDetailAt(aSymptomDetail, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfContactDetails()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addContactDetail(ContactDetails aContactDetail)
  {
    boolean wasAdded = false;
    if (contactDetails.contains(aContactDetail)) { return false; }
    contactDetails.add(aContactDetail);
    if (aContactDetail.indexOfPatient(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aContactDetail.addPatient(this);
      if (!wasAdded)
      {
        contactDetails.remove(aContactDetail);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeContactDetail(ContactDetails aContactDetail)
  {
    boolean wasRemoved = false;
    if (!contactDetails.contains(aContactDetail))
    {
      return wasRemoved;
    }

    int oldIndex = contactDetails.indexOf(aContactDetail);
    contactDetails.remove(oldIndex);
    if (aContactDetail.indexOfPatient(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aContactDetail.removePatient(this);
      if (!wasRemoved)
      {
        contactDetails.add(oldIndex,aContactDetail);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addContactDetailAt(ContactDetails aContactDetail, int index)
  {  
    boolean wasAdded = false;
    if(addContactDetail(aContactDetail))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContactDetails()) { index = numberOfContactDetails() - 1; }
      contactDetails.remove(aContactDetail);
      contactDetails.add(index, aContactDetail);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveContactDetailAt(ContactDetails aContactDetail, int index)
  {
    boolean wasAdded = false;
    if(contactDetails.contains(aContactDetail))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContactDetails()) { index = numberOfContactDetails() - 1; }
      contactDetails.remove(aContactDetail);
      contactDetails.add(index, aContactDetail);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addContactDetailAt(aContactDetail, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=tests.size(); i > 0; i--)
    {
      Test aTest = tests.get(i - 1);
      aTest.delete();
    }
    for(int i=symptomDetails.size(); i > 0; i--)
    {
      SymptomDetails aSymptomDetail = symptomDetails.get(i - 1);
      aSymptomDetail.delete();
    }
    ArrayList<ContactDetails> copyOfContactDetails = new ArrayList<ContactDetails>(contactDetails);
    contactDetails.clear();
    for(ContactDetails aContactDetail : copyOfContactDetails)
    {
      aContactDetail.removePatient(this);
    }
  }

  // line 10 "model.ump"
   public void patientDetails(){
    System.out.println("Name: "+ this.getName());
    System.out.println("Address: "+ this.getAddress());
    System.out.println("Phone #: "+ this.getPhoneNumber());
    System.out.println("Health Card #: "+ this.getHealthCardNumber());
    System.out.println();
    for(int j=0; j<this.numberOfSymptomDetails();j++){
      System.out.println("List of symptoms: ");
      System.out.println("("+this.getSymptomDetail(j).getSymptom()+") Started at: " +this.getSymptomDetail(j).getStartDate()+ " and ended at: "+ this.getSymptomDetail(j).getEndDate());
    }
    System.out.println();
    for(int k= 0; k<this.numberOfContactDetails(); k++){
      System.out.println("List of contacts: ");
      System.out.println("Name: "+this.getContactDetail(k).getName()+ "   Date of contact: "+ this.getContactDetail(k).getDateOfContact());
    }
    System.out.println();
    for(int i=0; i<this.numberOfTests(); i++){
      System.out.println("Test #: "+ (i+1));
      System.out.println("Date the test is given: "+this.getTest(i).getDateOfAdministration());
      System.out.println("Test time: "+this.getTest(i).getTime());
      System.out.println("Date results were received: "+this.getTest(i).getDateOfResults());
      System.out.println("Outcome of test: "+this.getTest(i).getOutcome());
      System.out.println("Test type: "+ this.getTest(i).getTestType().getTestType());
      System.out.println("Test location: "+ this.getTest(i).getTestLocation().getLocationType());
      System.out.println("Lab that processed test: "+ this.getTest(i).getLabProcessingData().getName());
       System.out.println();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "healthCardNumber" + ":" + getHealthCardNumber()+ "]";
  }
}