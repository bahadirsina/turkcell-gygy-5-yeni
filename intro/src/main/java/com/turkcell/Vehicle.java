package com.turkcell;

public class Vehicle {
    private int year;
    private String model;   
    private String brand;
    private double pricePerDay; 


        public void setPricePerDay(double pricePerDay) {
        if (pricePerDay < 0) {
            System.out.println("Fiyat negatif olamaz.");
            pricePerDay = 0.0; // Negatif fiyat durumunda varsayılan olarak 0 atanabilir  
        }
        this.pricePerDay = pricePerDay;
    }
    public double getPricePerDay() {
        return this.pricePerDay;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
}

