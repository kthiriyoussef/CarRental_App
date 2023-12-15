package com.example.locationvoiture;

public class Car {
    private String carId;
    private String brand;
    private String model;
    private Float price;
    private String imageUrl;
    // Add other relevant fields


    public Car() {}

    public Car(String carId, String brand, String model, Float price, String imageUrl) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
