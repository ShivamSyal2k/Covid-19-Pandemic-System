/**
 * Author: Shivam Syal
 * Copyright 2020, all rights reserved
 */
package com.example.covid_19pandemicsystem;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4994.706e115af modeling language!*/



// line 45 "model.ump"
// line 103 "model.ump"
public class Test
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Test Attributes
  private String dateOfAdministration;
  private String time;
  private String dateOfResults;
  private String outcome;

  //Test Associations
  private TestLocation testLocation;
  private TestType testType;
  private Patient patient;
  private LabProcessingData labProcessingData;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Test(String aDateOfAdministration, String aTime, String aDateOfResults, String aOutcome, TestLocation aTestLocation, TestType aTestType, Patient aPatient, LabProcessingData aLabProcessingData)
  {
    dateOfAdministration = aDateOfAdministration;
    time = aTime;
    dateOfResults = aDateOfResults;
    outcome = aOutcome;
    boolean didAddTestLocation = setTestLocation(aTestLocation);
    if (!didAddTestLocation)
    {
      throw new RuntimeException("Unable to create test due to testLocation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddTestType = setTestType(aTestType);
    if (!didAddTestType)
    {
      throw new RuntimeException("Unable to create test due to testType. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddPatient = setPatient(aPatient);
    if (!didAddPatient)
    {
      throw new RuntimeException("Unable to create test due to patient. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddLabProcessingData = setLabProcessingData(aLabProcessingData);
    if (!didAddLabProcessingData)
    {
      throw new RuntimeException("Unable to create test due to labProcessingData. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDateOfAdministration(String aDateOfAdministration)
  {
    boolean wasSet = false;
    dateOfAdministration = aDateOfAdministration;
    wasSet = true;
    return wasSet;
  }

  public boolean setTime(String aTime)
  {
    boolean wasSet = false;
    time = aTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDateOfResults(String aDateOfResults)
  {
    boolean wasSet = false;
    dateOfResults = aDateOfResults;
    wasSet = true;
    return wasSet;
  }

  public boolean setOutcome(String aOutcome)
  {
    boolean wasSet = false;
    outcome = aOutcome;
    wasSet = true;
    return wasSet;
  }

  public String getDateOfAdministration()
  {
    return dateOfAdministration;
  }

  public String getTime()
  {
    return time;
  }

  public String getDateOfResults()
  {
    return dateOfResults;
  }

  public String getOutcome()
  {
    return outcome;
  }
  /* Code from template association_GetOne */
  public TestLocation getTestLocation()
  {
    return testLocation;
  }
  /* Code from template association_GetOne */
  public TestType getTestType()
  {
    return testType;
  }
  /* Code from template association_GetOne */
  public Patient getPatient()
  {
    return patient;
  }
  /* Code from template association_GetOne */
  public LabProcessingData getLabProcessingData()
  {
    return labProcessingData;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTestLocation(TestLocation aTestLocation)
  {
    boolean wasSet = false;
    if (aTestLocation == null)
    {
      return wasSet;
    }

    TestLocation existingTestLocation = testLocation;
    testLocation = aTestLocation;
    if (existingTestLocation != null && !existingTestLocation.equals(aTestLocation))
    {
      existingTestLocation.removeTest(this);
    }
    testLocation.addTest(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTestType(TestType aTestType)
  {
    boolean wasSet = false;
    if (aTestType == null)
    {
      return wasSet;
    }

    TestType existingTestType = testType;
    testType = aTestType;
    if (existingTestType != null && !existingTestType.equals(aTestType))
    {
      existingTestType.removeTest(this);
    }
    testType.addTest(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPatient(Patient aPatient)
  {
    boolean wasSet = false;
    if (aPatient == null)
    {
      return wasSet;
    }

    Patient existingPatient = patient;
    patient = aPatient;
    if (existingPatient != null && !existingPatient.equals(aPatient))
    {
      existingPatient.removeTest(this);
    }
    patient.addTest(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLabProcessingData(LabProcessingData aLabProcessingData)
  {
    boolean wasSet = false;
    if (aLabProcessingData == null)
    {
      return wasSet;
    }

    LabProcessingData existingLabProcessingData = labProcessingData;
    labProcessingData = aLabProcessingData;
    if (existingLabProcessingData != null && !existingLabProcessingData.equals(aLabProcessingData))
    {
      existingLabProcessingData.removeTest(this);
    }
    labProcessingData.addTest(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    TestLocation placeholderTestLocation = testLocation;
    this.testLocation = null;
    if(placeholderTestLocation != null)
    {
      placeholderTestLocation.removeTest(this);
    }
    TestType placeholderTestType = testType;
    this.testType = null;
    if(placeholderTestType != null)
    {
      placeholderTestType.removeTest(this);
    }
    Patient placeholderPatient = patient;
    this.patient = null;
    if(placeholderPatient != null)
    {
      placeholderPatient.removeTest(this);
    }
    LabProcessingData placeholderLabProcessingData = labProcessingData;
    this.labProcessingData = null;
    if(placeholderLabProcessingData != null)
    {
      placeholderLabProcessingData.removeTest(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "dateOfAdministration" + ":" + getDateOfAdministration()+ "," +
            "time" + ":" + getTime()+ "," +
            "dateOfResults" + ":" + getDateOfResults()+ "," +
            "outcome" + ":" + getOutcome()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "testLocation = "+(getTestLocation()!=null?Integer.toHexString(System.identityHashCode(getTestLocation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "testType = "+(getTestType()!=null?Integer.toHexString(System.identityHashCode(getTestType())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "patient = "+(getPatient()!=null?Integer.toHexString(System.identityHashCode(getPatient())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "labProcessingData = "+(getLabProcessingData()!=null?Integer.toHexString(System.identityHashCode(getLabProcessingData())):"null");
  }
}