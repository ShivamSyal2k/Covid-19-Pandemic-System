/**
 * Author: Shivam Syal
 * Copyright 2020, all rights reserved
 */
package com.example.covid_19pandemicsystem;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4994.706e115af modeling language!*/


import java.util.*;

// line 75 "model.ump"
// line 121 "model.ump"
public class TestType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TestType Attributes
  private String testType;

  //TestType Associations
  private List<Test> tests;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TestType(String aTestType)
  {
    testType = aTestType;
    tests = new ArrayList<Test>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTestType(String aTestType)
  {
    boolean wasSet = false;
    testType = aTestType;
    wasSet = true;
    return wasSet;
  }

  public String getTestType()
  {
    return testType;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTests()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Test addTest(String aDateOfAdministration, String aTime, String aDateOfResults, String aOutcome, TestLocation aTestLocation, Patient aPatient, LabProcessingData aLabProcessingData)
  {
    return new Test(aDateOfAdministration, aTime, aDateOfResults, aOutcome, aTestLocation, this, aPatient, aLabProcessingData);
  }

  public boolean addTest(Test aTest)
  {
    boolean wasAdded = false;
    if (tests.contains(aTest)) { return false; }
    TestType existingTestType = aTest.getTestType();
    boolean isNewTestType = existingTestType != null && !this.equals(existingTestType);
    if (isNewTestType)
    {
      aTest.setTestType(this);
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
    //Unable to remove aTest, as it must always have a testType
    if (!this.equals(aTest.getTestType()))
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

  public void delete()
  {
    for(int i=tests.size(); i > 0; i--)
    {
      Test aTest = tests.get(i - 1);
      aTest.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "testType" + ":" + getTestType()+ "]";
  }
}