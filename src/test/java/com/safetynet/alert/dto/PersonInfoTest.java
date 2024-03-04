package com.safetynet.alert.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonInfoTest {

    @Test
    public void testPersonInfo() {
        // Création d'une instance de PersonInfo
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Street";
        String city = "City";
        String zip = "12345";
        String email = "email@example.com";

        // Création d'une instance de PersonInfo
        PersonInfo personInfo = new PersonInfo(firstName, lastName, address, city, zip, email);

        // Vérification des informations
        assertEquals(firstName, personInfo.getFirstName());
        assertEquals(lastName, personInfo.getLastName());
        assertEquals(address, personInfo.getAddress());
        assertEquals(city, personInfo.getCity());
        assertEquals(zip, personInfo.getZip());
        assertEquals(email, personInfo.getEmail());
    }

    @Test
    public void testSetters() {
        // Création d'une instance de PersonInfo
        PersonInfo personInfo = new PersonInfo(null, null, null, null, null, null);

        // Définition de nouvelles informations
        String firstName = "Jane";
        String lastName = "Doe";
        String address = "456 Street";
        String city = "City2";
        String zip = "54321";
        String email = "another@example.com";

        // Assignation des nouvelles informations
        personInfo.setFirstName(firstName);
        personInfo.setLastName(lastName);
        personInfo.setAddress(address);
        personInfo.setCity(city);
        personInfo.setZip(zip);
        personInfo.setEmail(email);

        // Vérification des informations mises à jour
        assertEquals(firstName, personInfo.getFirstName());
        assertEquals(lastName, personInfo.getLastName());
        assertEquals(address, personInfo.getAddress());
        assertEquals(city, personInfo.getCity());
        assertEquals(zip, personInfo.getZip());
        assertEquals(email, personInfo.getEmail());
    }
}
