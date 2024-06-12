package com.ironsoft.catereo.api.POST.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuItemDTO {

    @SerializedName("menuItemId")
    @Expose
    private Integer menuItemId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("popularityRating")
    @Expose
    private Float popularityRating;

    /**
     * No args constructor for use in serialization
     */
    public MenuItemDTO() {
    }

    /**
     * @param popularityRating
     * @param quantity
     * @param price
     * @param name
     * @param menuItemId
     */
    public MenuItemDTO(Integer menuItemId, String name, Integer quantity, Double price, Float popularityRating) {
        super();
        this.menuItemId = menuItemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.popularityRating = popularityRating;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Float getPopularityRating() {
        return popularityRating;
    }

    public void setPopularityRating(Float popularityRating) {
        this.popularityRating = popularityRating;
    }

}
