package com.safetynet.alert.util;


import com.safetynet.alert.model.Firestation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.model.SafetyNet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExtractObjectTest {

    @Test
    @DisplayName("test that json is being deserialized, SafetyNet should have at the least" +
            "one list of Firestations, one list of Person and one list of MedicalRecords")
    void testExtractDateFromJason(){
        // Arrange: SafetyNet object should have a minimum of one of each list
            //(Firestations, Persons, MedicalRecords)
        int minNumberOfFirestations = 1;
        int minNumberOfPersons = 1;
        int minNumberOfMedicalRecords = 1;

        // Act:
        SafetyNet extractedSafetyNet =  JsonDataExtractor.extractDataFromJson();
        List<Firestation> firestations = extractedSafetyNet.getFirestations();
        List<Person> persons = extractedSafetyNet.getPersons();
        List<MedicalRecord> medicalRecords = extractedSafetyNet.getMedicalrecords();

        // Assert:
        assertThat(firestations).hasSizeGreaterThanOrEqualTo(minNumberOfFirestations);
        assertThat(persons).hasSizeGreaterThanOrEqualTo(minNumberOfPersons);
        assertThat(medicalRecords).hasSizeGreaterThanOrEqualTo(minNumberOfMedicalRecords);
    }
}
