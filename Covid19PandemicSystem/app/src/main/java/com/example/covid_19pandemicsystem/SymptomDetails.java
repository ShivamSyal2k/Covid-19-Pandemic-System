/**
 * Author: Shivam Syal
 * Copyright 2020, all rights reserved
 */
package com.example.covid_19pandemicsystem;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4994.706e115af modeling language!*/



// line 68 "model.ump"
// line 126 "model.ump"
public class SymptomDetails
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SymptomDetails Attributes
  private String symptom;
  private String startDate;
  private String endDate;

  //SymptomDetails Associations
  private Patient patient;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SymptomDetails(String aSymptom, String aStartDate, String aEndDate, Patient aPatient)
  {
    symptom = aSymptom;
    startDate = aStartDate;
    endDate = aEndDate;
    boolean didAddPatient = setPatient(aPatient);
    if (!didAddPatient)
    {
      throw new RuntimeException("Unable to create symptomDetail due to patient. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSymptom(String aSymptom)
  {
    boolean wasSet = false;
    symptom = aSymptom;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(String aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(String aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public String getSymptom()
  {
    return symptom;
  }

  public String getStartDate()
  {
    return startDate;
  }

  public String getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetOne */
  public Patient getPatient()
  {
    return patient;
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
      existingPatient.removeSymptomDetail(this);
    }
    patient.addSymptomDetail(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Patient placeholderPatient = patient;
    this.patient = null;
    if(placeholderPatient != null)
    {
      placeholderPatient.removeSymptomDetail(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "symptom" + ":" + getSymptom()+ "," +
            "startDate" + ":" + getStartDate()+ "," +
            "endDate" + ":" + getEndDate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "patient = "+(getPatient()!=null?Integer.toHexString(System.identityHashCode(getPatient())):"null");
  }
}