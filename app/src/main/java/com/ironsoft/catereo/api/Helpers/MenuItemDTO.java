package com.ironsoft.catereo.api.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuItemDTO {

    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
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
    @SerializedName("order")
    @Expose
    private List<Object> order;
    @SerializedName("menuCard")
    @Expose
    private Object menuCard;

    /**
     * No args constructor for use in serialization
     */
    public MenuItemDTO() {
    }

    /**
     * @param popularityRating
     * @param image
     * @param quantity
     * @param menuCard
     * @param price
     * @param name
     * @param description
     * @param id
     * @param availability
     * @param sku
     * @param menuItemId
     * @param order
     */
    public MenuItemDTO(Integer id, Integer menuItemId, String name, String description, String sku, Integer quantity, Double price, Boolean availability, String image, Integer popularityRating, List<Object> order, Object menuCard) {
        super();
        this.id = id;
        this.menuItemId = menuItemId;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
        this.availability = availability;
        this.image = image;
        this.popularityRating = popularityRating;
        this.order = order;
        this.menuCard = menuCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Object> getOrder() {
        return order;
    }

    public void setOrder(List<Object> order) {
        this.order = order;
    }

    public Object getMenuCard() {
        return menuCard;
    }

    public void setMenuCard(Object menuCard) {
        this.menuCard = menuCard;
    }

}
