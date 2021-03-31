package com.Lesson7;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args)  {
        try {
            Tester.testClass(TestingClass.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
