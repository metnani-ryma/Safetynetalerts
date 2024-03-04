package com.safetynet.alert.service;


import com.safetynet.alert.dto.*;
import com.safetynet.alert.exception.SafetyNetExceptions.IncompleteRequestException;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.*;
import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Primary
public class PersonServiceImpl implements PersonService{
    private final FirestationRepository firestationRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final PersonRepository personRepository;

    //@Autowired
    public PersonServiceImpl(FirestationRepository firestationRepository,
                             MedicalRecordRepository medicalRecordRepository,
                             PersonRepository personRepository){

        this.firestationRepository = firestationRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<MinorAndFamily> getMinorsAndFamilyByAddress(String address){

        log.debug("getMinorsAndFamilyByAddress()" + address);
        List<Person> personsAtSameAddress = personRepository.findPeopleByAddress(address);

        //List with age for each person from list 'personAtSameAddress'
        List<Integer> ages = medicalRecordRepository.calculateAgesByDatesOfBirth( medicalRecordRepository
                .convertListDateStringsToListOfDatesOfBirth(medicalRecordRepository
                        .findDatesOfBirthInMedicalRecordsByPersons(personsAtSameAddress)));

        List<MinorAndFamily> minorsAgeAndFamily = new ArrayList<>();

        // Iterate over all ages and create MinorAndFamily instances for minors
        for (int i = 0; i < ages.size(); i++) {
            if(ages.get(i) < 19){
                List<Person> familyMembersAtSameAddress = new ArrayList<>();
                // adds all other family member except the minor on index i of personsAtSameAddress to new list
                for (int j = 0; j < personsAtSameAddress.size(); j++) {
                    if (j != i) {
                        familyMembersAtSameAddress.add(personsAtSameAddress.get(j));
                    }
                }
                // Create and add a new MinorAndFamily instance to the list
                minorsAgeAndFamily.add(new MinorAndFamily(personsAtSameAddress.get(i), ages.get(i)
                        ,familyMembersAtSameAddress)
                );
            }
        }
        log.info("Completed getMinorsAndFamilyByAddress()" + address);
        return minorsAgeAndFamily;
    }

    @Override
    public PeopleMedicalRecordFirestation getPeopleMedicalRecordsAndFirestationByAddress(String address){

        log.debug("getPeopleMedicalRecordsAndFirestationByAddress()" + address);
        List<PersonAndMedicalRecord> listOfPersonsMedicalRecordAndFireStation = new ArrayList<>();

        //List off people at inputted address
        List<Person> personsAtSameAddress = personRepository.findPeopleByAddress(address);

        //List of MedicalRecords of the people at that address
        List<MedicalRecord> medicalRecordsOfPeopleAtSameAddress = medicalRecordRepository
                .findMedicalRecordsByPersons(personsAtSameAddress);

        //list of dates of people of the people in personsAtSameAddress
        List<Integer> ages = medicalRecordRepository.calculateAgesByDatesOfBirth(medicalRecordRepository
                .convertListDateStringsToListOfDatesOfBirth(medicalRecordRepository
                        .findDatesOfBirthInMedicalRecordsByPersons(personsAtSameAddress)));

        /*
        goes through list of people at same address and for each person adds creats a
        PeopleMedicalRecordAndFirestation object that is added to listOfPersonsMedicalRecordAndFireStation
        and then returned for creation of dto object
        */
        for (int i=0; i < personsAtSameAddress.size(); i++){
            listOfPersonsMedicalRecordAndFireStation
                    .add(i, new PersonAndMedicalRecord(personsAtSameAddress.get(i).getFirstName()
                            ,personsAtSameAddress.get(i).getLastName(), personsAtSameAddress.get(i).getPhone()
                            ,ages.get(i), medicalRecordsOfPeopleAtSameAddress.get(i).getMedications()
                            ,medicalRecordsOfPeopleAtSameAddress.get(i).getAllergies()));
        }

        log.info("Completed getPeopleMedicalRecordsAndFirestationByAddress()" + address);
        return new PeopleMedicalRecordFirestation(listOfPersonsMedicalRecordAndFireStation
                , firestationRepository.findFirestationNumberByAddress(personsAtSameAddress.get(0).getAddress()));
    }

    @Override
    public List<PersonInfoAndMedicalRecord> getPersonInfoAndMedicalRecordByName(String firstName, String lastName){

        log.debug("getPersonInfoAndMedicalRecordByName()" + firstName + " " + lastName);
        //stores list of people found  with that first and last name.
        List<Person> personByName = personRepository.findPeopleByName(firstName, lastName);

        //stores list of personInfo found  with that first and last name.
        List<PersonInfo> personsInfo = getPersonInfo(firstName, lastName);

        //stores their medical records
        List<MedicalRecord> medicalRecordsOfPersonByName = medicalRecordRepository
                .findMedicalRecordsByPersons(personByName);
        //stores their ages
        List<Integer> ages = medicalRecordRepository.calculateAgesByDatesOfBirth(medicalRecordRepository
                .convertListDateStringsToListOfDatesOfBirth(medicalRecordRepository
                        .findDatesOfBirthInMedicalRecordsByPersons(personByName)));

        //for method return
        List<PersonInfoAndMedicalRecord> personInfoAndMedicalRecords = new ArrayList<>();

        /*
        go through list of person, age and medical records and use the same index of each list to fetch information
        needed to creat personInfoAndMedicalRecords object. this object is added to as list of the same type in case
        there are multiple people with the same name
         */
        for (int i= 0; i < personByName.size(); i++){
            PersonInfo personInfo = personsInfo.get(i);
            MedicalRecord medicalRecord = medicalRecordsOfPersonByName.get(i);
            int age = ages.get(i);

        personInfoAndMedicalRecords.add(new PersonInfoAndMedicalRecord(personInfo, age,
                medicalRecord.getMedications(), medicalRecord.getAllergies()));
        }
        log.info("Completed getPersonInfoAndMedicalRecordByName()" + firstName + " " + lastName);
        return personInfoAndMedicalRecords;
    }

    //gets person info without phone number to creat PersonInfAndMedicalRecord
    public List<PersonInfo> getPersonInfo(String firstName, String lastName){

        log.debug("getPersonInfo() " + firstName + " " + lastName);
        //stores list of people found  with that first and lsat name.
        List<Person> personByName = personRepository.findPeopleByName(firstName, lastName);
        List<PersonInfo> personInfoByName= new ArrayList<>();
        for(Person person :  personByName){
                personInfoByName.add(new PersonInfo(person.getFirstName(), person.getLastName(), person.getAddress(),
                        person.getCity(), person.getZip(), person.getEmail()));
        }
        log.debug("completed getPersonInfo() " + firstName + " " + lastName);
        return personInfoByName;
    }

    @Override
    public List<String> getEmailsByCity(String city){

        log.debug("getEmailsByCity()" + city);
        List<Person> personsFromCity = personRepository.findPeopleByCity(city);

        //retrieves the email from each person in that list of people from that city
        List<String> personsEmailFromCity = new ArrayList<>();
        for (Person person : personsFromCity){
            if (person.getCity().equals(city)){
            personsEmailFromCity.add(person.getEmail());
            }
        }
        log.info("Completed getEmailsByCity()" + city);
        return personsEmailFromCity;
    }

    //adds newPersonToAd as a list of one newPersonToAd, since safetyNet take a list of Persons
    @Override
    public Person postNewPerson(Person newPersonToAd){
        if (newPersonToAd.getLastName() == null || newPersonToAd.getLastName().isEmpty() || newPersonToAd
                .getFirstName() == null || newPersonToAd.getFirstName().isEmpty() || newPersonToAd
                .getAddress() == null || newPersonToAd.getAddress().isEmpty() || newPersonToAd
                .getZip() == null || newPersonToAd.getZip().isEmpty() || newPersonToAd
                .getPhone() == null || newPersonToAd.getPhone().isEmpty() || newPersonToAd
                .getEmail() == null || newPersonToAd.getEmail().isEmpty()){

            log.error("Incomplete request exception");
            throw new IncompleteRequestException("Incomplete Request. Request must contain all fields");
        }

        log.debug("postNewPerson()" + newPersonToAd);
        return personRepository.addPerson(newPersonToAd);
    }

    @Override
    public Person putPerson(Person personToEdit){

        if (personToEdit.getLastName() == null || personToEdit.getLastName().isEmpty() || personToEdit
                .getFirstName() == null || personToEdit.getFirstName().isEmpty() ){

            log.error("Incomplete request exception");
            throw new IncompleteRequestException("Incomplete Request. Request must contain 'first name', 'last name");
        }

        log.debug("putPerson()" + personToEdit);
       return personRepository.updatePerson(personToEdit);
    }

    @Override
    public Person deletePerson(String firstName, String lastName) {

        log.debug("deletePerson()" + firstName + " " + lastName);
        return personRepository.removePerson(firstName, lastName);
    }
}
