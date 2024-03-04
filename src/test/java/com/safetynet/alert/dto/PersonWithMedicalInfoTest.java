package com.safetynet.alert.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class PersonWithMedicalInfoTest {

    @Test
    public void testPersonWithMedicalInfo() {
        // Création de quelques informations médicales
        String firstName = "John";
        String lastName = "Doe";
        String phone = "123-456-7890";
        int age = 35;
        List<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");

        // Création d'une instance de PersonWithMedicalInfo
        PersonWithMedicalInfo personWithMedicalInfo = new PersonWithMedicalInfo(firstName, lastName, phone, age, medications, allergies);

        // Vérification des informations médicales
        assertEquals(firstName, personWithMedicalInfo.getFirstName());
        assertEquals(lastName, personWithMedicalInfo.getLastName());
        assertEquals(phone, personWithMedicalInfo.getPhone());
        assertEquals(age, personWithMedicalInfo.getAge());
        assertEquals(medications, personWithMedicalInfo.getMedications());
        assertEquals(allergies, personWithMedicalInfo.getAllergies());
    }

    @Test
    public void testSetters() {
        // Création d'une instance de PersonWithMedicalInfo
        PersonWithMedicalInfo personWithMedicalInfo = new PersonWithMedicalInfo(null, null, null, 0, null, null);

        // Définition de nouvelles informations médicales
        String firstName = "Jane";
        String lastName = "Doe";
        String phone = "987-654-3210";
        int age = 25;
        List<String> medications = new ArrayList<>();
        medications.add("Medication3");
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy3");

        // Assignation des nouvelles informations médicales
        personWithMedicalInfo.setFirstName(firstName);
        personWithMedicalInfo.setLastName(lastName);
        personWithMedicalInfo.setPhone(phone);
        personWithMedicalInfo.setAge(age);
        personWithMedicalInfo.setMedications(medications);
        personWithMedicalInfo.setAllergies(allergies);

        // Vérification des informations médicales mises à jour
        assertEquals(firstName, personWithMedicalInfo.getFirstName());
        assertEquals(lastName, personWithMedicalInfo.getLastName());
        assertEquals(phone, personWithMedicalInfo.getPhone());
        assertEquals(age, personWithMedicalInfo.getAge());
        assertEquals(medications, personWithMedicalInfo.getMedications());
        assertEquals(allergies, personWithMedicalInfo.getAllergies());
    }
}
