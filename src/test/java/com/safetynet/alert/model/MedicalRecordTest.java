package com.safetynet.alert.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordTest {

    @Test
    public void testMedicalRecord() {
        // Création de quelques médicaments et allergies
        List<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");

        // Création d'une instance de MedicalRecord
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "01/01/1990", medications, allergies);

        // Vérification des attributs
        assertEquals("John", medicalRecord.getFirstName());
        assertEquals("Doe", medicalRecord.getLastName());
        assertEquals("01/01/1990", medicalRecord.getBirthdate());
        assertEquals(medications, medicalRecord.getMedications());
        assertEquals(allergies, medicalRecord.getAllergies());
    }

    @Test
    public void testSetters() {
        // Création d'une instance de MedicalRecord
        MedicalRecord medicalRecord = new MedicalRecord(null, null, null, null, null);

        // Création de quelques listes de médicaments et allergies
        List<String> medications = new ArrayList<>();
        medications.add("Medication3");
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy3");

        // Assignation des nouvelles valeurs
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("02/02/1995");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);

        // Vérification des valeurs mises à jour
        assertEquals("Jane", medicalRecord.getFirstName());
        assertEquals("Doe", medicalRecord.getLastName());
        assertEquals("02/02/1995", medicalRecord.getBirthdate());
        assertEquals(medications, medicalRecord.getMedications());
        assertEquals(allergies, medicalRecord.getAllergies());
    }
}
