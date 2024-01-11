package com.safetynet.alert.repository;



import static com.safetynet.alert.util.JsonDataExtractor.extractDataFromJson;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.safetynet.alert.exception.SafetyNetExceptions.DuplicateMedicalRecordException;
import com.safetynet.alert.exception.SafetyNetExceptions.MedicalRecordNotFoundException;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@Primary
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {



    List<MedicalRecord> listOfAllMedicalRecords;


    public MedicalRecordRepositoryImpl() {
        this.listOfAllMedicalRecords = extractDataFromJson().getMedicalrecords();
    }


    public MedicalRecordRepositoryImpl(List<MedicalRecord> listOfAllMedicalRecords) {
        this.listOfAllMedicalRecords = listOfAllMedicalRecords;
    }



    @Override
    public List<MedicalRecord> findMedicalRecordsByPersons(List<Person> listOfPersons) {

        log.debug("findMedicalRecordsByPersons()" + listOfPersons);
        List<MedicalRecord> medicalRecordsOfPersons = new ArrayList<>();

        for (Person person : listOfPersons) {
            for (MedicalRecord medicalRecord : listOfAllMedicalRecords) {
                if (person.getFirstName().equals(medicalRecord
                        .getFirstName()) && person.getLastName().equals(medicalRecord
                        .getLastName())) {
                    medicalRecordsOfPersons.add(medicalRecord);
                }
            }
        }
        log.debug("completed findMedicalRecordsByPersons()" + listOfPersons);
        return medicalRecordsOfPersons;
    }



    @Override
    public List<String> findDatesOfBirthInMedicalRecordsByPersons(List<Person> listOfPeopleToCheck) {

        log.debug("findDatesOfBirthInMedicalRecordsByPersons()" + listOfPeopleToCheck);
        ArrayList<String> datesOfBirth = new ArrayList<>();

        for (Person person : listOfPeopleToCheck) {
            String lastName = person.getLastName();
            String firstName = person.getFirstName();

            for (MedicalRecord medicalRecord : listOfAllMedicalRecords) {
                if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)) {
                    datesOfBirth.add(medicalRecord.getBirthdate());
                }
            }
        }
        log.debug("completed findDatesOfBirthInMedicalRecordsByPersons()" + listOfPeopleToCheck);
        return datesOfBirth;
    }


    @Override
    public List<LocalDate> convertListDateStringsToListOfDatesOfBirth(List<String> datesOfBirthStrings) {

        log.debug("convertListDateStringsToListOfDatesOfBirth()" + datesOfBirthStrings);
        ArrayList<LocalDate> datesOfBirthLocalDateFormat = new ArrayList<>();

        for (String dateOfBirth : datesOfBirthStrings) {
            datesOfBirthLocalDateFormat.add(convertDateStringToLocalDate(dateOfBirth));
        }
        log.debug("completed convertListDateStringsToListOfDatesOfBirth()" + datesOfBirthStrings);
        return datesOfBirthLocalDateFormat;
    }


    private LocalDate convertDateStringToLocalDate(String dateOfBirthString) {

        log.debug("convertDateStringToLocalDate()" + dateOfBirthString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(dateOfBirthString, formatter);
    }


    @Override
    public List<Integer> calculateAgesByDatesOfBirth(List<LocalDate> datesOfBirth) {

        log.debug("calculateAgesByDatesOfBirth()" + datesOfBirth);
        ArrayList<Integer> ages = new ArrayList<>();
        for (LocalDate dateOfBirth : datesOfBirth) {
            LocalDate now = LocalDate.now();
            Period period = Period.between(dateOfBirth, now);
            ages.add(period.getYears());
        }
        log.debug("completed calculateAgesByDatesOfBirth()" + datesOfBirth);
        return ages;
    }

    @Override
    public Integer countAmountOfAdults(List<Integer> ages) {

        log.debug("countAmountOfAdults()" + ages);
        int amountOfAdults = 0;
        for (int age : ages) {
            if (age >= 19) {
                amountOfAdults++;
            }
        }
        log.debug("completed countAmountOfAdults()" + ages);
        return amountOfAdults;
    }

    @Override
    public int countAmountOfMinors(List<Integer> ages) {

        log.debug("countAmountOfMinors()" + ages);
        int amountOfMinors = 0;
        for (int age : ages) {
            if (age < 19) {
                amountOfMinors++;
            }
        }
        log.debug("completed countAmountOfMinors()" + ages);
        return amountOfMinors;
    }

    @Override
    public MedicalRecord addNewMedicalRecord(MedicalRecord medicalRecordToAdd) {

        log.debug("addNewMedicalRecord()" + medicalRecordToAdd);
        //check if there is already a medical record for this Person and throw exception if duplicate
        for(MedicalRecord medicalRecord : listOfAllMedicalRecords){
            if (medicalRecord.getLastName().equals(medicalRecordToAdd.getLastName()) && medicalRecord.getFirstName()
                    .equals(medicalRecordToAdd.getFirstName())) {
                log.error("Duplicate Medical Record");
                throw new DuplicateMedicalRecordException("Medical record already exist for this person");
            }
        }
        listOfAllMedicalRecords.add(medicalRecordToAdd);
        log.info("completed addNewMedicalRecord()" + medicalRecordToAdd);
        return medicalRecordToAdd;
    }


    @Override
    public MedicalRecord updateMedicalRecordByFirstAndLastName(MedicalRecord updatedMedicalRecord) {

        log.debug("updateMedicalRecordByFirstAndLastName()" + updatedMedicalRecord);
        MedicalRecord updateMedicalRecord = null;
        for(MedicalRecord medicalRecord : listOfAllMedicalRecords){
            if (medicalRecord.getLastName().equals(updatedMedicalRecord.getLastName()) && medicalRecord.getFirstName()
                    .equals(updatedMedicalRecord.getFirstName())) {

                medicalRecord.setBirthdate(updatedMedicalRecord.getBirthdate());
                medicalRecord.setMedications(updatedMedicalRecord.getMedications());
                medicalRecord.setAllergies(updatedMedicalRecord.getAllergies());

                updateMedicalRecord = new MedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName(),
                        medicalRecord.getBirthdate(), medicalRecord.getMedications() , medicalRecord.getAllergies());
                break;
            }
        }
        if (updateMedicalRecord == null){
            log.error("failed updateMedicalRecordByFirstAndLastName() - Medical Record not found");
            throw new MedicalRecordNotFoundException();

        }
        log.info("completed updateMedicalRecordByFirstAndLastName()" + updatedMedicalRecord);
        return updateMedicalRecord;
    }


    @Override
    public MedicalRecord removeMedicalRecordByName(String firstName, String lastName) {

        log.debug("removeMedicalRecordByName()" + firstName + " " + lastName);
        MedicalRecord deletedMedicalRecord = null;
        for(int i = 0 ; i < listOfAllMedicalRecords.size(); i++){
            MedicalRecord medicalRecord = listOfAllMedicalRecords.get(i);
            if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)){

                deletedMedicalRecord = new MedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName(),
                        medicalRecord.getBirthdate(), medicalRecord.getMedications(), medicalRecord. getAllergies());
                listOfAllMedicalRecords.remove(i);
                break;
            }
        }
        if (deletedMedicalRecord == null){
            log.error("Medical Record Not Found Exception");
            throw new MedicalRecordNotFoundException("No Medical record found with specified first and last name");
        }

        log.info("completed removeMedicalRecordByName()" + firstName + " " + lastName);
        return deletedMedicalRecord;
    }
}

