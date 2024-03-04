package com.safetynet.alert.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class PersonInfoAndMedicalRecordTest {

    @Test
    public void testPersonInfoAndMedicalRecord() {
        // Création des informations personnelles
        PersonInfo personInfo = new PersonInfo("John", "Doe", "123 Street", "City", "12345", "email@example.com");
        
        // Création des informations médicales
        int age = 35;
        List<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");

        // Création d'une instance de PersonInfoAndMedicalRecord
        PersonInfoAndMedicalRecord personInfoAndMedicalRecord = new PersonInfoAndMedicalRecord(personInfo, age, medications, allergies);

        // Vérification des informations
        assertEquals(personInfo, personInfoAndMedicalRecord.getPersonInfo());
        assertEquals(age, personInfoAndMedicalRecord.getAge());
        assertEquals(medications, personInfoAndMedicalRecord.getMedication());
        assertEquals(allergies, personInfoAndMedicalRecord.getAllergies());
    }

    @Test
    public void testSetters() {
        // Création d'une instance de PersonInfoAndMedicalRecord
        PersonInfoAndMedicalRecord personInfoAndMedicalRecord = new PersonInfoAndMedicalRecord(null, 0, null, null);

        // Définition de nouvelles informations
        PersonInfo personInfo = new PersonInfo("Jane", "Doe", "456 Street", "City", "54321", "another@example.com");
        int age = 25;
        List<String> medications = new ArrayList<>();
        medications.add("Medication3");
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy3");

        // Assignation des nouvelles informations
        personInfoAndMedicalRecord.setPersonInfo(personInfo);
        personInfoAndMedicalRecord.setAge(age);
        personInfoAndMedicalRecord.setMedication(medications);
        personInfoAndMedicalRecord.setAllergies(allergies);

        // Vérification des informations mises à jour
        assertEquals(personInfo, personInfoAndMedicalRecord.getPersonInfo());
        assertEquals(age, personInfoAndMedicalRecord.getAge());
        assertEquals(medications, personInfoAndMedicalRecord.getMedication());
        assertEquals(allergies, personInfoAndMedicalRecord.getAllergies());
    }
}
