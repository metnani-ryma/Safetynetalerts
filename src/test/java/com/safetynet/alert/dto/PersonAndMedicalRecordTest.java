package com.safetynet.alert.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class PersonAndMedicalRecordTest {

    @Test
    public void testPersonAndMedicalRecord() {
        // Création de quelques informations médicales
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "123-456-7890";
        int age = 35;
        List<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");

        // Création d'une instance de PersonAndMedicalRecord
        PersonAndMedicalRecord personAndMedicalRecord = new PersonAndMedicalRecord(firstName, lastName, phoneNumber, age, medications, allergies);

        // Vérification des informations médicales
        assertEquals(firstName, personAndMedicalRecord.getFirstName());
        assertEquals(lastName, personAndMedicalRecord.getLastName());
        assertEquals(phoneNumber, personAndMedicalRecord.getPhoneNumber());
        assertEquals(age, personAndMedicalRecord.getAge());
        assertEquals(medications, personAndMedicalRecord.getMedications());
        assertEquals(allergies, personAndMedicalRecord.getAllergies());
    }

    @Test
    public void testSetters() {
        // Création d'une instance de PersonAndMedicalRecord
        PersonAndMedicalRecord personAndMedicalRecord = new PersonAndMedicalRecord(null, null, null, 0, null, null);

        // Définition de nouvelles informations médicales
        String firstName = "Jane";
        String lastName = "Doe";
        String phoneNumber = "987-654-3210";
        int age = 25;
        List<String> medications = new ArrayList<>();
        medications.add("Medication3");
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy3");

        // Assignation des nouvelles informations médicales
        personAndMedicalRecord.setFirstName(firstName);
        personAndMedicalRecord.setLastName(lastName);
        personAndMedicalRecord.setPhoneNumber(phoneNumber);
        personAndMedicalRecord.setAge(age);
        personAndMedicalRecord.setMedications(medications);
        personAndMedicalRecord.setAllergies(allergies);

        // Vérification des informations médicales mises à jour
        assertEquals(firstName, personAndMedicalRecord.getFirstName());
        assertEquals(lastName, personAndMedicalRecord.getLastName());
        assertEquals(phoneNumber, personAndMedicalRecord.getPhoneNumber());
        assertEquals(age, personAndMedicalRecord.getAge());
        assertEquals(medications, personAndMedicalRecord.getMedications());
        assertEquals(allergies, personAndMedicalRecord.getAllergies());
    }
}
