package com.ctco.medical.card;

public class MedicalCard {



    PersonalInfo[] data;
    String guid;
    Boolean active;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public PersonalInfo[] getData() {
        return data;
    }

    public void setData(PersonalInfo[] data) {
        this.data = data;
    }

}
