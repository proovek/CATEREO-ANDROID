package com.ironsoft.catereo.api.POST.Helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Orderstatus {

    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     */
    public Orderstatus() {
    }

    /**
     * @param status
     */
    public Orderstatus(String status) {
        super();
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
