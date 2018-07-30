package com.sapient;

public class SapientClass {

    String name;

    SapientClass (String name) {
        this.name = name;
    }
    SapientClass () {
        this(makeRandomName ());
    }

    static String makeRandomName () {
        int x = (int) (Math.random() * 5);
        String name = new String [] {"Fluffy", "Scooby", "Dogy", " Rover", "Spike"} [x];
        return name;
    }
    public static void main(String[] args) {
        SapientClass a = new SapientClass();
        System.out.println(a.name);
    }
}
