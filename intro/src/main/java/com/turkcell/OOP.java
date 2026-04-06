package com.turkcell;

public class OOP {
    public static void main(String[] args) {
        Car car1 = new Car(); //Car instance oluşturduk
        car1.setYear(2020);
        car1.setModel("Civic");
        car1.setBrand("Honda");
        //car1.price = 20000; //price private olduğu için erişim sağlanamaz

         //pricePerDay değerini setter ile atadık

        car1.setPricePerDay(450.0);
        System.out.println(car1.getPricePerDay());
        System.out.println(car1.getModel());
        System.out.println(car1.getBrand());
        System.out.println(car1.getYear());
        
        String[] specs = {"Air Conditioning", "Leather Seats", "Bluetooth"};
        car1.setSpecs(specs);
        
        System.out.println(car1.getSpecs()[0]); // Air Conditioning
        Bike bike1 = new Bike();
        bike1.setYear(2021);
        
        Car car2 = new Car(true, "Toyota");
        System.out.println(car2.getBrand()); // Toyota


    }

}
