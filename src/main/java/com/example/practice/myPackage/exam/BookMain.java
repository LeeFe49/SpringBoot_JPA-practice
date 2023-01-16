package com.example.practice.myPackage.exam;

import java.lang.reflect.Method;

public class BookMain {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Class.forName("com.example.practice.myPackage.exam.Book");

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

    }
}
