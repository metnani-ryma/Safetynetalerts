package com.safetynet.alert.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirestationTest {

    @Test
    public void testFirestation() {
        // Création d'une instance de Firestation
        String address = "123 Main St";
        String station = "1";

        Firestation firestation = new Firestation(address, station);

        // Vérification des attributs
        assertEquals(address, firestation.getAddress());
        assertEquals(station, firestation.getStation());
    }

    @Test
    public void testSetters() {
        // Création d'une instance de Firestation
        Firestation firestation = new Firestation(null, null);

        // Définition de nouvelles valeurs
        String address = "456 Elm St";
        String station = "2";

        // Assignation des nouvelles valeurs
        firestation.setAddress(address);
        firestation.setStation(station);

        // Vérification des valeurs mises à jour
        assertEquals(address, firestation.getAddress());
        assertEquals(station, firestation.getStation());
    }
}
