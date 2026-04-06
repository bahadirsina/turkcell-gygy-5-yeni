package com.turkcell;

public class PgCarRepository {
    public void addCar(Car car) {
        // Veritabanına ekleme işlemi burada yapılır
        System.out.println("Car added to PostgreSQL database: " + car.getBrand() + " " + car.getModel());
    }

}
