package com.androidfu.intro2android.model;

import com.google.gson.annotations.Expose;

public class Features {

    @Expose
    private Integer conditions;

    /**
     * @return The conditions
     */
    public Integer getConditions() {
        return conditions;
    }

    /**
     * @param conditions The conditions
     */
    public void setConditions(Integer conditions) {
        this.conditions = conditions;
    }

}
