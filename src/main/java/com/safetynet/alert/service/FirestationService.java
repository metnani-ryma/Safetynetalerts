package com.safetynet.alert.service;



import com.safetynet.alert.dto.Household;
import com.safetynet.alert.dto.PeopleByFirestationNumber;
import com.safetynet.alert.dto.PhoneByFirestation;
import com.safetynet.alert.model.Firestation;
import com.safetynet.alert.model.Person;

import java.util.List;

public interface FirestationService {
	
    List<Person> getPersonsByFireStationNumber(String firestationNumber);
    
    PeopleByFirestationNumber getAdultsAndMinorsCoveredByFirestationNumber(String firestationNumber);
    
    PhoneByFirestation getPhoneByFirestationNumber(String firestationNumber);
    
    List<Household> getHouseholdsByFirestationNumbers(List<String> firestationNumbers);

    Firestation postFireStation(Firestation firestation);

    Firestation putFireStaion(Firestation firestationToUpdate);

    Firestation deleteFirestation(Firestation firestationToDelete) ;
}

