package com.ironsoft.catereo.api.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDetails {

    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("expiration")
    @Expose
    private String expiration;
    @SerializedName("user")
    @Expose
    private User user;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginDetails() {
    }

    /**
     *
     * @param expiration
     * @param accessToken
     * @param user
     */
    public LoginDetails(String accessToken, String expiration, User user) {
        super();
        this.accessToken = accessToken;
        this.expiration = expiration;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

