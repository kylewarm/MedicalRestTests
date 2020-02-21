package com.ctco.medical.card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class MedicalCardPage {

    MedicalCardData data;

    public MedicalCardData getData() {
        return data;
    }

    public void setData(MedicalCardData data) {
        this.data = data;
    }

}
