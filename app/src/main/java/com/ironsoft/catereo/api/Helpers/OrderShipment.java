package com.ironsoft.catereo.api.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderShipment {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phoneNumber")
    @Expose
    private Integer phoneNumber;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;
    @SerializedName("order")
    @Expose
    private List<Object> order;

    /**
     * No args constructor for use in serialization
     */
    public OrderShipment() {
    }

    /**
     * @param address
     * @param phoneNumber
     * @param createdTime
     * @param id
     * @param order
     */
    public OrderShipment(Integer id, String address, Integer phoneNumber, String createdTime, List<Object> order) {
        super();
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdTime = createdTime;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<Object> getOrder() {
        return order;
    }

    public void setOrder(List<Object> order) {
        this.order = order;
    }

}
