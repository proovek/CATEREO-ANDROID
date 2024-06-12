package com.ironsoft.catereo.api.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDataDTO {

    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("profilePicture")
    @Expose
    private Object profilePicture;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("marginalRate")
    @Expose
    private Integer marginalRate;
    @SerializedName("customerCompanyDTO")
    @Expose
    private List<CustomerCompanyDTO> customerCompanyDTO;

    /**
     * No args constructor for use in serialization
     */
    public UserDataDTO() {
    }

    /**
     * @param profilePicture
     * @param role
     * @param displayName
     * @param marginalRate
     * @param userEmail
     * @param id
     * @param position
     * @param customerCompanyDTO
     * @param userId
     * @param username
     */
    public UserDataDTO(Integer id, String userId, String username, String userEmail, String displayName, String position, Object profilePicture, String role, Integer marginalRate, List<CustomerCompanyDTO> customerCompanyDTO) {
        super();
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.displayName = displayName;
        this.position = position;
        this.profilePicture = profilePicture;
        this.role = role;
        this.marginalRate = marginalRate;
        this.customerCompanyDTO = customerCompanyDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Object getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Object profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getMarginalRate() {
        return marginalRate;
    }

    public void setMarginalRate(Integer marginalRate) {
        this.marginalRate = marginalRate;
    }

    public List<CustomerCompanyDTO> getCustomerCompanyDTO() {
        return customerCompanyDTO;
    }

    public void setCustomerCompanyDTO(List<CustomerCompanyDTO> customerCompanyDTO) {
        this.customerCompanyDTO = customerCompanyDTO;
    }

}
