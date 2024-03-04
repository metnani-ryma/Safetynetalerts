package com.safetynet.alert.service;



import com.safetynet.alert.dto.MinorAndFamily;
import com.safetynet.alert.dto.PeopleMedicalRecordFirestation;
import com.safetynet.alert.dto.PersonInfoAndMedicalRecord;
import com.safetynet.alert.model.Person;

import java.util.List;

public interface PersonService {
    List<MinorAndFamily> getMinorsAndFamilyByAddress(String address);
    PeopleMedicalRecordFirestation getPeopleMedicalRecordsAndFirestationByAddress(String address);
    List<PersonInfoAndMedicalRecord> getPersonInfoAndMedicalRecordByName(String firstName, String lastName);
    List<String> getEmailsByCity(String city);
    Person postNewPerson(Person newPersonToAd);
    Person putPerson(Person personToEdit);
    Person deletePerson(String firstName, String lastName);
}