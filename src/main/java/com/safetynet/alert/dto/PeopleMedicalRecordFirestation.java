package com.safetynet.alert.dto;


import java.util.List;

public class PeopleMedicalRecordFirestation {
    private List<PersonAndMedicalRecord> listOfPeopleMedicalRecordsAndFirestationByAddress;
    private String fireStationNumber;

    public PeopleMedicalRecordFirestation(List<PersonAndMedicalRecord> listOfPersonsMedicalRecordsAndFirestationByAddress, String fireStationNumber) {
        this.listOfPeopleMedicalRecordsAndFirestationByAddress = listOfPersonsMedicalRecordsAndFirestationByAddress;
        this.fireStationNumber = fireStationNumber;
    }

    public List<PersonAndMedicalRecord> getListOfPersonsMedicalRecordsAndFirestationByAddress() {
        return listOfPeopleMedicalRecordsAndFirestationByAddress;
    }

    public void setListOfPersonsMedicalRecordsAndFirestationByAddress(List<PersonAndMedicalRecord> listOfPersonsMedicalRecordsAndFirestationByAddress) {
        listOfPeopleMedicalRecordsAndFirestationByAddress = listOfPersonsMedicalRecordsAndFirestationByAddress;
    }

    public String getFireStationNumber() {
        return fireStationNumber;
    }

    public void setFireStationNumber(String fireStationNumber) {
        this.fireStationNumber = fireStationNumber;
    }
}