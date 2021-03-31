package com.Lesson7;

import com.Lesson7.MyTestAnnotations.AfterSuite;
import com.Lesson7.MyTestAnnotations.BeforeSuite;
import com.Lesson7.MyTestAnnotations.Test;

public class TestingClass {

    private int testingIntValue;

    @BeforeSuite
    public void preparing(){
        System.out.println("Preparing to work");
    }

    @AfterSuite
    public void cleaningAfter(){
        System.out.println("Cleaning after work");
    }



    @Test(priority = 1)
    public void testingMethod1(){
        System.out.println("Testing method 1 is working");
    }

    @Test(priority = 2)
    public void testingMethod2(){
        System.out.println("Testing method 2 is working");
    }

    @Test(priority = 3)
    public void testingMethod3(){
        System.out.println("Testing method 3 is working");
    }

    @Test(priority = 4)
    public void testingMethod4(){
        System.out.println("Testing method 4 is working");
    }

    @Test(priority = 1)
    public void testingMethod8(){
        System.out.println("Testing method 1-1 is working");
    }

    @Test(priority = 4)
    public void testingMethod5(int incomingValue){
        System.out.println("Testing method 5 is working, arguments int = " + incomingValue);
    }

    @Test(priority = 6)
    public void testingMethod6(char incomingValue){
        System.out.println("Testing method 5 is working, arguments char = " + incomingValue);
    }

    @Test(priority = 6)
    public void testingMethod7(char incomingValue){
        System.out.println("Testing method 5 is working, arguments char = " + incomingValue);
    }



}
