package com.ironsoft.catereo.api.POST.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerCompanyDTO {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;

    /**
     * No args constructor for use in serialization
     */
    public CustomerCompanyDTO() {
    }

    /**
     * @param address
     * @param name
     */
    public CustomerCompanyDTO(String name, String address) {
        super();
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
