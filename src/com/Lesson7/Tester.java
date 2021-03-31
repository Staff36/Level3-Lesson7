package com.Lesson7;

import com.Lesson7.MyTestAnnotations.AfterSuite;
import com.Lesson7.MyTestAnnotations.BeforeSuite;
import com.Lesson7.MyTestAnnotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class Tester {


    public static void testClass(Class testingClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        ArrayList<Method> allClassMethods = new ArrayList<>(Arrays.asList(testingClass.getDeclaredMethods()));
        Method beforeSuite = null;
        Method afterSuite = null;

        Iterator<Method> iterator = allClassMethods.iterator();
        while (iterator.hasNext()) {
            Method method = iterator.next();
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeSuite != null) {
                    throw new RuntimeException("Class " + testingClass.getName() + " has more then one method with @BeforeSuite Annotation");
                }
                iterator.remove();
                beforeSuite = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterSuite != null) {
                    throw new RuntimeException("Class " + testingClass.getName() + " has more then one method with @AfterSuite Annotation");
                }
                iterator.remove();
                afterSuite = method;
            } else if (method.getAnnotation(Test.class) != null) {
            } else {
                iterator.remove();
            }
        }

        allClassMethods.sort(Tester::compareArray);

        beforeSuite.invoke(testingClass.getDeclaredConstructor().newInstance());
        for (Method method : allClassMethods) {
            if (method.getParameterCount() != 0) {
                runMethodWithOnePrimitiveParameter(method, testingClass);
            } else {
                method.invoke(testingClass.getDeclaredConstructor().newInstance());
            }
        }
        afterSuite.invoke(testingClass.getDeclaredConstructor().newInstance());
    }


private static void runMethodWithOnePrimitiveParameter(Method method, Class testingClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Parameter[] parameters = method.getParameters();
    if (parameters.length>1){
        throw new RuntimeException("Testing class " + testingClass.getName() + " contain Method " + method.getName() + ", which needs more then one argument. Tester.class can't test methods with args");
    }
    switch (parameters[0].getType().toString()){
            case "int":
                method.invoke(testingClass.getDeclaredConstructor().newInstance(), 5);
                break;
            case "char":
                method.invoke(testingClass.getDeclaredConstructor().newInstance(), 'a');
                break;
            case "float":
                method.invoke(testingClass.getDeclaredConstructor().newInstance(), 5.0f);
                break;
            case "double":
                method.invoke(testingClass.getDeclaredConstructor().newInstance(), 5.0d);
                break;
            case "String":
                method.invoke(testingClass.getDeclaredConstructor().newInstance(), "test");
                break;
            case "boolean":
                method.invoke(testingClass.getDeclaredConstructor().newInstance(), true);
                break;
            case "byte":
                method.invoke(testingClass.getDeclaredConstructor().newInstance(), 5);
                break;
            case "short":
                method.invoke(testingClass.getDeclaredConstructor().newInstance(), 5);
                break;
            default:
                throw new RuntimeException("Testing class " + testingClass.getName() + " contain Method " + method.getName() + ", which needs reference data type arguments. Tester.class can't test this");
        }
    }

    private static int compareArray(Method o1, Method o2) {
        int o1Priority = o1.getAnnotation(Test.class).priority();
        int o2Priority = o2.getAnnotation(Test.class).priority();
        if (o1Priority > o2Priority) {
            return 1;
        } else if (o1Priority < o2Priority) {
            return -1;
        }
        return 0;
    }
}


