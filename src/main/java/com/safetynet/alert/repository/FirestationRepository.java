package com.safetynet.alert.repository;

import java.util.List;
import com.safetynet.alert.exception.SafetyNetExceptions.FirestationNotFoundException;

import com.safetynet.alert.model.Firestation;

public interface FirestationRepository {

    List<String> findAddressByFirestationNumber(String firestationNumber);

    String findFirestationNumberByAddress(String address);

    Firestation addFirestation(Firestation firestationToAdd);

    Firestation updateFirestationByAddress(String addressCoveredByFirestation, String firestationNumberToUpdate);

    Firestation removeFirestationByAddress(String firestationCoveredAddress)throws FirestationNotFoundException;;
}
