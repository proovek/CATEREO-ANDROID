package com.ironsoft.catereo.helpers;

import android.graphics.Bitmap;

import com.ironsoft.catereo.api.Helpers.Ingredient;

import java.util.List;

public class FoodItemModel {
    private Integer menuItemId;
    private Bitmap imageUrl; // URL lub identyfikator zasobu obrazu
    private String title;    // Nazwa elementu
    private String description; // Opis elementu
    private String category; // Kategoria elementu
    private Float rating;
    private Double price;
    private String sku;
    private Boolean availability;
    private Boolean isVegetarian;
    private Boolean isVegan;
    private List<String> allergeninformation;
    private List<Ingredient> ingredients;

    // Konstruktor
    public FoodItemModel(Integer menuItemId, Bitmap imageUrl, String title, String description, String category, Float rating, Double price, String sku, Boolean availability, Boolean isVegan, Boolean isVegetarian, List<String> allergeninformation, List<Ingredient> ingredients) {
        this.menuItemId = menuItemId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.category = category;
        this.rating = rating;
        this.price = price;
        this.sku = sku;
        this.availability = availability;
        this.isVegan = isVegan;
        this.isVegetarian = isVegetarian;
        this.allergeninformation = allergeninformation;
        this.ingredients = ingredients;
    }

    // Gettery i Settery
    public Bitmap getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Bitmap imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Boolean getVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public Boolean getVegan() {
        return isVegan;
    }

    public void setVegan(Boolean vegan) {
        isVegan = vegan;
    }

    public List<String> getAllergeninformation() {
        return allergeninformation;
    }

    public void setAllergeninformation(List<String> allergeninformation) {
        this.allergeninformation = allergeninformation;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    // Możesz dodać więcej metod w zależności od potrzeb

}

