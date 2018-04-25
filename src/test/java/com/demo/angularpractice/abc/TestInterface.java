package com.demo.angularpractice.abc;

import org.junit.Test;

import java.lang.reflect.Field;

public class TestInterface {


    @Test
    public void test() throws ClassNotFoundException {
        ClassA classA = new ClassA();
        System.out.println(ClassA.i);

        Class clazz = Class.forName("com.demo.angularpractice.abc.InterfaceA");
        Field[] fields = clazz.getFields();
    }
}
