package com.ironsoft.catereo.api.POST.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPayment {

    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("paymentName")
    @Expose
    private String paymentName;

    /**
     * No args constructor for use in serialization
     */
    public OrderPayment() {
    }

    /**
     * @param value
     * @param paymentName
     */
    public OrderPayment(Double value, String paymentName) {
        super();
        this.value = value;
        this.paymentName = paymentName;
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

}
