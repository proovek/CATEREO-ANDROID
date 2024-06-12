package com.ironsoft.catereo.helpers;

public class FoodInCartItemModel {
    private int imageUrl; // Resource ID obrazu
    private String title; // Nazwa dania
    private float rating; // Ocena dania
    private double price; // Cena dania
    private int quantity; // Ilość dania

    public FoodInCartItemModel(int imageUrl, String title, float rating, double price, int quantity) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.rating = rating;
        this.price = price;
        this.quantity = quantity;
    }

    // Gettery
    public int getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Settery
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

