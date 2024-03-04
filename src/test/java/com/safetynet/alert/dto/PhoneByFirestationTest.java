package com.safetynet.alert.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class PhoneByFirestationTest {

    @Test
    public void testPhoneByFirestation() {
        // Création de quelques numéros de téléphone
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("123-456-7890");
        phoneNumbers.add("234-567-8901");
        phoneNumbers.add("345-678-9012");

        // Création d'une instance de PhoneByFirestation
        PhoneByFirestation phoneByFirestation = new PhoneByFirestation(phoneNumbers);

        // Vérification que les numéros de téléphone sont correctement assignés
        assertEquals(phoneNumbers, phoneByFirestation.getPhoneNumbers());
    }

    @Test
    public void testSetPhoneNumbers() {
        // Création de quelques numéros de téléphone
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("111-222-3333");
        phoneNumbers.add("444-555-6666");

        // Création d'une instance de PhoneByFirestation
        PhoneByFirestation phoneByFirestation = new PhoneByFirestation(new ArrayList<>());

        // Assignation des nouveaux numéros de téléphone
        phoneByFirestation.setPhoneNumbers(phoneNumbers);

        // Vérification que les numéros de téléphone sont correctement assignés
        assertEquals(phoneNumbers, phoneByFirestation.getPhoneNumbers());
    }
}
