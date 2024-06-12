package com.ironsoft.catereo.api.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("ingredientId")
    @Expose
    private Integer ingredientId;
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("productSKU")
    @Expose
    private Integer productSKU;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productDescription")
    @Expose
    private String productDescription;
    @SerializedName("productCategoryId")
    @Expose
    private String productCategoryId;
    @SerializedName("productQuantity")
    @Expose
    private Integer productQuantity;
    @SerializedName("productNettoPrice")
    @Expose
    private Double productNettoPrice;
    @SerializedName("productVatRate")
    @Expose
    private Integer productVatRate;
    @SerializedName("productExpiresDays")
    @Expose
    private Integer productExpiresDays;
    @SerializedName("productExpiresDate")
    @Expose
    private String productExpiresDate;
    @SerializedName("productImage")
    @Expose
    private String productImage;

    /**
     * No args constructor for use in serialization
     *
     */
    public Ingredient() {
    }

    /**
     *
     * @param ingredientId
     * @param productSKU
     * @param productQuantity
     * @param productCategoryId
     * @param productNettoPrice
     * @param productImage
     * @param productId
     * @param productExpiresDate
     * @param productExpiresDays
     * @param productName
     * @param productDescription
     * @param productVatRate
     */
    public Ingredient(Integer ingredientId, Integer productId, Integer productSKU, String productName, String productDescription, String productCategoryId, Integer productQuantity, Double productNettoPrice, Integer productVatRate, Integer productExpiresDays, String productExpiresDate, String productImage) {
        super();
        this.ingredientId = ingredientId;
        this.productId = productId;
        this.productSKU = productSKU;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategoryId = productCategoryId;
        this.productQuantity = productQuantity;
        this.productNettoPrice = productNettoPrice;
        this.productVatRate = productVatRate;
        this.productExpiresDays = productExpiresDays;
        this.productExpiresDate = productExpiresDate;
        this.productImage = productImage;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductSKU() {
        return productSKU;
    }

    public void setProductSKU(Integer productSKU) {
        this.productSKU = productSKU;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getProductNettoPrice() {
        return productNettoPrice;
    }

    public void setProductNettoPrice(Double productNettoPrice) {
        this.productNettoPrice = productNettoPrice;
    }

    public Integer getProductVatRate() {
        return productVatRate;
    }

    public void setProductVatRate(Integer productVatRate) {
        this.productVatRate = productVatRate;
    }

    public Integer getProductExpiresDays() {
        return productExpiresDays;
    }

    public void setProductExpiresDays(Integer productExpiresDays) {
        this.productExpiresDays = productExpiresDays;
    }

    public String getProductExpiresDate() {
        return productExpiresDate;
    }

    public void setProductExpiresDate(String productExpiresDate) {
        this.productExpiresDate = productExpiresDate;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

}