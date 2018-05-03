package com.demo.angularpractice.abc;

public interface InterfaceB {

    default void eat() {
        System.out.println("default impl");
    }
}
