package com.demo.angularpractice.abc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterfaceBImpl implements InterfaceB {

    @Override
    public void eat() {
        System.out.println("sub class");
    }

    @Test
    public void testA() {
//        InterfaceB interfaceB = new InterfaceBImpl();
//        interfaceB.eat();

        C b = new C();

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");


    }
}


class B {

    static {
        System.out.println("B static");
    }

    {
        System.out.println("B empty");
    }


    B() {
        System.out.println("B no param constructor");
    }

}

class C extends B {
    static {
        System.out.println("C static");
    }

    {
        System.out.println("C empty");
    }

    C() {
        System.out.println("C empty constructor");
    }
}