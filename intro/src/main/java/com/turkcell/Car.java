package com.turkcell;

public class Car extends Vehicle {

    private boolean hasSunroof;
    public boolean isHasSunroof() {
        return hasSunroof;
    }
    public void setHasSunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
    }
    private String[] specs;

    public Car(boolean hasSunroof, String Brand) {
        this.hasSunroof = hasSunroof;
        super.setBrand(Brand);
    }
    public Car() {
        // Default constructor
    }

    public void setSpecs(String[] specs) {
        this.specs = specs.clone();
    }
    public String[] getSpecs() {
        return specs.clone();
    }


}
