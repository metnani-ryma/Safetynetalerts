package com.safetynet.alert.model;

import java.util.List;

import lombok.Data;

@Data
public class SafetyNet{
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<MedicalRecord> medicalrecords;


    public SafetyNet(List<Person> persons, List<Firestation> firestations,
                     List<MedicalRecord> medicalRecords) {
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalRecords;
    }

}