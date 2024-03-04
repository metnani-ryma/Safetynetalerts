package com.safetynet.alert.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SafetyNetTest {

    @Test
    public void testSafetyNet() {
        // Création de quelques personnes
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person("John", "Doe", "123 Street", "City", "12345", "123-456-7890", "john@example.com");
        Person person2 = new Person("Jane", "Smith", "456 Avenue", "Town", "54321", "987-654-3210", "jane@example.com");
        persons.add(person1);
        persons.add(person2);

        // Création de quelques stations d'incendie
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation1 = new Firestation("123 Main St", "1");
        Firestation firestation2 = new Firestation("456 Elm St", "2");
        firestations.add(firestation1);
        firestations.add(firestation2);

        // Création de quelques dossiers médicaux
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord1 = new MedicalRecord("John", "Doe", "01/01/1990", new ArrayList<>(), new ArrayList<>());
        MedicalRecord medicalRecord2 = new MedicalRecord("Jane", "Smith", "02/02/1995", new ArrayList<>(), new ArrayList<>());
        medicalRecords.add(medicalRecord1);
        medicalRecords.add(medicalRecord2);

        // Création d'une instance de SafetyNet
        SafetyNet safetyNet1 = new SafetyNet(persons, firestations, medicalRecords);
        SafetyNet safetyNet2 = new SafetyNet(persons, firestations, medicalRecords);

        // Vérification de l'égalité
        assertEquals(safetyNet1, safetyNet2);
        assertEquals(safetyNet1.hashCode(), safetyNet2.hashCode());
        assertEquals(safetyNet1.toString(), safetyNet2.toString());
        assertTrue(safetyNet1.canEqual(safetyNet2));
    }

   
}
