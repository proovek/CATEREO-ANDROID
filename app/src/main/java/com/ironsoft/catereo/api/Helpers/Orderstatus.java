package com.ironsoft.catereo.api.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Orderstatus {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusDate")
    @Expose
    private String statusDate;
    @SerializedName("order")
    @Expose
    private List<Object> order;

    /**
     * No args constructor for use in serialization
     */
    public Orderstatus() {
    }

    /**
     * @param statusDate
     * @param id
     * @param status
     * @param order
     */
    public Orderstatus(Integer id, String status, String statusDate, List<Object> order) {
        super();
        this.id = id;
        this.status = status;
        this.statusDate = statusDate;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public List<Object> getOrder() {
        return order;
    }

    public void setOrder(List<Object> order) {
        this.order = order;
    }

}
