package com.ironsoft.catereo.api.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderPayment {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("paymentName")
    @Expose
    private String paymentName;
    @SerializedName("paymentDate")
    @Expose
    private String paymentDate;
    @SerializedName("order")
    @Expose
    private List<Object> order;

    /**
     * No args constructor for use in serialization
     */
    public OrderPayment() {
    }

    /**
     * @param id
     * @param paymentDate
     * @param value
     * @param paymentName
     * @param order
     */
    public OrderPayment(Integer id, Double value, String paymentName, String paymentDate, List<Object> order) {
        super();
        this.id = id;
        this.value = value;
        this.paymentName = paymentName;
        this.paymentDate = paymentDate;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public List<Object> getOrder() {
        return order;
    }

    public void setOrder(List<Object> order) {
        this.order = order;
    }

}
