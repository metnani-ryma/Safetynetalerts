package com.safetynet.alert.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
    public void testPerson() {
        // Création d'une instance de Person
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main St";
        String city = "Anytown";
        String zip = "12345";
        String phone = "555-555-5555";
        String email = "john.doe@example.com";

        Person person = new Person(firstName, lastName, address, city, zip, phone, email);

        // Vérification des attributs
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(address, person.getAddress());
        assertEquals(city, person.getCity());
        assertEquals(zip, person.getZip());
        assertEquals(phone, person.getPhone());
        assertEquals(email, person.getEmail());
    }

    @Test
    public void testSetters() {
        // Création d'une instance de Person
        Person person = new Person(null, null, null, null, null, null, null);

        // Assignation de nouvelles valeurs
        String firstName = "Jane";
        String lastName = "Doe";
        String address = "456 Elm St";
        String city = "Smalltown";
        String zip = "54321";
        String phone = "555-123-4567";
        String email = "jane.doe@example.com";

        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress(address);
        person.setCity(city);
        person.setZip(zip);
        person.setPhone(phone);
        person.setEmail(email);

        // Vérification des valeurs mises à jour
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(address, person.getAddress());
        assertEquals(city, person.getCity());
        assertEquals(zip, person.getZip());
        assertEquals(phone, person.getPhone());
        assertEquals(email, person.getEmail());
    }
}
