package com.ironsoft.catereo.api.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {

    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("isDelivery")
    @Expose
    private Object isDelivery;
    @SerializedName("totalAmount")
    @Expose
    private Object totalAmount;
    @SerializedName("userDataDTO")
    @Expose
    private List<UserDataDTO> userDataDTO;
    @SerializedName("menuItemDTO")
    @Expose
    private List<MenuItemDTO> menuItemDTO;
    @SerializedName("orderStatus")
    @Expose
    private List<Orderstatus> orderStatus;
    @SerializedName("orderShipment")
    @Expose
    private List<OrderShipment> orderShipment;
    @SerializedName("orderPayments")
    @Expose
    private List<OrderPayment> orderPayments;

    /**
     * No args constructor for use in serialization
     *
     */
    public Order() {
    }

    /**
     *
     * @param orderShipment
     * @param totalAmount
     * @param isDelivery
     * @param orderPayments
     * @param orderId
     * @param menuItemDTO
     * @param orderStatus
     * @param userDataDTO
     * @param orderDate
     */
    public Order(Integer orderId, String orderDate, Object isDelivery, Object totalAmount, List<UserDataDTO> userDataDTO, List<MenuItemDTO> menuItemDTO, List<Orderstatus> orderStatus, List<OrderShipment> orderShipment, List<OrderPayment> orderPayments) {
        super();
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.isDelivery = isDelivery;
        this.totalAmount = totalAmount;
        this.userDataDTO = userDataDTO;
        this.menuItemDTO = menuItemDTO;
        this.orderStatus = orderStatus;
        this.orderShipment = orderShipment;
        this.orderPayments = orderPayments;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Object getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(Object isDelivery) {
        this.isDelivery = isDelivery;
    }

    public Object getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Object totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<UserDataDTO> getUserDataDTO() {
        return userDataDTO;
    }

    public void setUserDataDTO(List<UserDataDTO> userDataDTO) {
        this.userDataDTO = userDataDTO;
    }

    public List<MenuItemDTO> getMenuItemDTO() {
        return menuItemDTO;
    }

    public void setMenuItemDTO(List<MenuItemDTO> menuItemDTO) {
        this.menuItemDTO = menuItemDTO;
    }

    public List<Orderstatus> getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(List<Orderstatus> orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderShipment> getOrderShipment() {
        return orderShipment;
    }

    public void setOrderShipment(List<OrderShipment> orderShipment) {
        this.orderShipment = orderShipment;
    }

    public List<OrderPayment> getOrderPayments() {
        return orderPayments;
    }

    public void setOrderPayments(List<OrderPayment> orderPayments) {
        this.orderPayments = orderPayments;
    }

}

