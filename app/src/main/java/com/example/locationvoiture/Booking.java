package com.example.locationvoiture;

public class Booking {
    String Bookingid;
    String brand, model, price, pickupDate, returnDate;

    Booking(){}

    public Booking(String Bookingid ,String brand, String model, String price, String pickupDate, String returnDate) {
        this.Bookingid=Bookingid;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
    }

    public String getBookingid() {
        return Bookingid;
    }

    public void setBookingid(String bookingid) {
        Bookingid = bookingid;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
