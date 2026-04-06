package com.turkcell;

public class Car extends Vehicle {

    private boolean hasSunroof;
    private String[] specs;

    public void setSpecs(String[] specs) {
        this.specs = specs.clone();
    }
    public String[] getSpecs() {
        return specs.clone();
    }


}
