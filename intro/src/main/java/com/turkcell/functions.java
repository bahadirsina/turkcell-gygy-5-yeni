package com.turkcell;

public class functions {
    public static void main(String[] args) {
        System.out.println("Merhaba");
        String name = "Halit";
        SayWelcome(name);
        System.out.println(name);
    }
    public static void SayWelcome(String name){
        name = "Barış";
        System.out.println("Hoşgeldiniz " + name);
    }

}
