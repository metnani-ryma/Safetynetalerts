package com.safetynet.alert.dto;



import java.util.List;

public class PhoneByFirestation {
    List<String> phoneNumbers;

    public PhoneByFirestation(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
