package com.safetynet.alert.repository;

import java.time.LocalDate;
import java.util.List;

import com.safetynet.alert.exception.SafetyNetExceptions.MedicalRecordNotFoundException;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;

public interface MedicalRecordRepository {
    List<MedicalRecord> findMedicalRecordsByPersons(List<Person> listOfPersons);

    List<String> findDatesOfBirthInMedicalRecordsByPersons(List<Person> listOfPeopleToCheck);

    List<LocalDate> convertListDateStringsToListOfDatesOfBirth(List<String> datesOfBirthStrings);

    List<Integer> calculateAgesByDatesOfBirth(List<LocalDate> datesOfBirth);

    Integer countAmountOfAdults(List<Integer> ages);

    int countAmountOfMinors(List<Integer> ages);

    MedicalRecord addNewMedicalRecord(MedicalRecord medicalRecordToAdd);

    MedicalRecord updateMedicalRecordByFirstAndLastName(MedicalRecord updatedMedicalRecord) throws MedicalRecordNotFoundException;;

    MedicalRecord removeMedicalRecordByName(String firstName, String lastName) throws MedicalRecordNotFoundException;
}