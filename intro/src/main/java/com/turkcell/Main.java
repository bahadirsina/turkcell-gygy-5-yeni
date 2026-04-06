package com.turkcell;

public class Main {
    public static void main(String[] args) {
        System.out.println("Merhaba Turkcell Javaya Hoşgeldiniz!"); 
        System.out.println(10);
        int x = 15;
        System.out.println(x);
        x=20;
        System.out.println(x);
        //String name = "Halit";
        //String age = "25";
        //boolean isStudent = true;
        //char gender = 'M';

        String[] names = {"Halit", "Ayşe", "Mehmet"};
        System.out.println(names[0]);

        int a = 10;
        int b = a;
        a = 20;
        System.out.println(a);
        System.out.println(b);
        int age2 = 18;
        if (age2 >= 18) {
            System.out.println("Yetkiniz var.");
        }else if (age2 == 18) {
            System.out.println("18 yaşındasınız, yetkiniz var.");
        }
        else {
            System.out.println("Yetkiniz yok.");        
        }
        System.out.println(CalculateGrade(65, "Halit"));
    }

    public static String CalculateGrade(int grade, String name) {
        
        if (grade >= 85) {
            return name + " Notunuz: A";
        } else if (grade >= 70) {
            return name + " Notunuz: B";
        } else if (grade >= 50) {    
            return name + " Notunuz: C";
        } else {
            return name + " Notunuz: F";
        }           
    }
    public static String CalculateGrade(int grade) {
        return CalculateGrade(grade, "öğrenci");
    }
}