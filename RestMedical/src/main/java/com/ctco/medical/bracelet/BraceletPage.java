package com.ctco.medical.bracelet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BraceletPage {

    BraceletData data;

    public BraceletData getData() {
        return data;
    }

    public void setData(BraceletData data) {
        this.data = data;
    }
}
