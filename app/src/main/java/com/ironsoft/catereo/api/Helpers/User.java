package com.ironsoft.catereo.api.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("customerCompanyDTO")
    @Expose
    private List<CustomerCompanyDTO> customerCompanyDTO;
    @SerializedName("credits")
    @Expose
    private Double credits;

    @SerializedName("workDays")
    @Expose
    private Integer workDays;

    @SerializedName("dailyCredits")
    @Expose
    private Double dailyCredits;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param role
     * @param phone
     * @param displayName
     * @param userEmail
     * @param position
     * @param userName
     * @param isActive
     * @param customerCompanyDTO
     * @param userId
     * @param credits
     * @param workDays
     * @param dailyCredits
     */
    public User(String userId, String userName, String userEmail, String phone, String displayName, String role, String position, Boolean isActive, List<CustomerCompanyDTO> customerCompanyDTO, Double credits, Integer workDays, Double dailyCredits) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.phone = phone;
        this.displayName = displayName;
        this.role = role;
        this.position = position;
        this.isActive = isActive;
        this.customerCompanyDTO = customerCompanyDTO;
        this.credits = credits;
        this.workDays = workDays;
        this.dailyCredits = dailyCredits;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<CustomerCompanyDTO> getCustomerCompanyDTO() {
        return customerCompanyDTO;
    }

    public void setCustomerCompanyDTO(List<CustomerCompanyDTO> customerCompanyDTO) {
        this.customerCompanyDTO = customerCompanyDTO;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public Integer getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Integer workDays) {
        this.workDays = workDays;
    }

    public Double getDailyCredits() {
        return dailyCredits;
    }

    public void setDailyCredits(Double dailyCredits) {
        this.dailyCredits = dailyCredits;
    }
}