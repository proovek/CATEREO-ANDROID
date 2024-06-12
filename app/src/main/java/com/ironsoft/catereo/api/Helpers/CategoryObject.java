package com.ironsoft.catereo.api.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryObject {

    @SerializedName("productCategoryId")
    @Expose
    private Integer productCategoryId;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;

    /**
     * No args constructor for use in serialization
     *
     */
    public CategoryObject() {
    }

    /**
     *
     * @param image
     * @param productCategoryId
     * @param description
     * @param categoryName
     */
    public CategoryObject(Integer productCategoryId, String categoryName, String description, String image) {
        super();
        this.productCategoryId = productCategoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.image = image;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}