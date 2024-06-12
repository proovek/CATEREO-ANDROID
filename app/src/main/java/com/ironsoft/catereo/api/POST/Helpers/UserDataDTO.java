package com.ironsoft.catereo.api.POST.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDataDTO {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("customerCompanyDTO")
    @Expose
    private List<CustomerCompanyDTO> customerCompanyDTO;

    /**
     * No args constructor for use in serialization
     */
    public UserDataDTO() {
    }

    /**
     * @param role
     * @param displayName
     * @param marginalRate
     * @param userEmail
     * @param position
     * @param customerCompanyDTO
     * @param userId
     * @param username
     */
    public UserDataDTO(String userId, String username, String userEmail, String displayName, String position, String role, List<CustomerCompanyDTO> customerCompanyDTO) {
        super();
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.displayName = displayName;
        this.position = position;
        this.role = role;
        this.customerCompanyDTO = customerCompanyDTO;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<CustomerCompanyDTO> getCustomerCompanyDTO() {
        return customerCompanyDTO;
    }

    public void setCustomerCompanyDTO(List<CustomerCompanyDTO> customerCompanyDTO) {
        this.customerCompanyDTO = customerCompanyDTO;
    }

}
