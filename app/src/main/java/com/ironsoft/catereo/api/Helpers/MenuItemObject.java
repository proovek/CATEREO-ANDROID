package com.ironsoft.catereo.api.Helpers;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuItemObject {

    @SerializedName("menuItemId")
    @Expose
    private Integer menuItemId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("availability")
    @Expose
    private Boolean availability;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("popularityRating")
    @Expose
    private Integer popularityRating;
    @SerializedName("allergeninformation")
    @Expose
    private List<String> allergeninformation;
    @SerializedName("availableDays")
    @Expose
    private List<String> availableDays;
    @SerializedName("isVegetarian")
    @Expose
    private Boolean isVegetarian;
    @SerializedName("isVegan")
    @Expose
    private Boolean isVegan;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients;
    @SerializedName("category")
    @Expose
    private List<Category> category;

    /**
     * No args constructor for use in serialization
     *
     */
    public MenuItemObject() {
    }

    /**
     *
     * @param popularityRating
     * @param image
     * @param description
     * @param availability
     * @param isVegan
     * @param menuItemId
     * @param lastUpdated
     * @param allergeninformation
     * @param price
     * @param name
     * @param isVegetarian
     * @param ingredients
     * @param sku
     * @param category
     * @param availableDays
     */
    public MenuItemObject(Integer menuItemId, String name, String description, String sku, Double price, Boolean availability, String image, Integer popularityRating, List<String> allergeninformation, List<String> availableDays, Boolean isVegetarian, Boolean isVegan, String lastUpdated, List<Ingredient> ingredients, List<Category> category) {
        super();
        this.menuItemId = menuItemId;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.availability = availability;
        this.image = image;
        this.popularityRating = popularityRating;
        this.allergeninformation = allergeninformation;
        this.availableDays = availableDays;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
        this.lastUpdated = lastUpdated;
        this.ingredients = ingredients;
        this.category = category;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPopularityRating() {
        return popularityRating;
    }

    public void setPopularityRating(Integer popularityRating) {
        this.popularityRating = popularityRating;
    }

    public List<String> getAllergeninformation() {
        return allergeninformation;
    }

    public void setAllergeninformation(List<String> allergeninformation) {
        this.allergeninformation = allergeninformation;
    }

    public List<String> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<String> availableDays) {
        this.availableDays = availableDays;
    }

    public Boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    public Boolean getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(Boolean isVegan) {
        this.isVegan = isVegan;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

}
