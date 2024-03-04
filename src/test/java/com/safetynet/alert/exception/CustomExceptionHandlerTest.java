package com.safetynet.alert.exception;

import com.safetynet.alert.exception.SafetyNetExceptions.*;
import org.junit.jupiter.api.Test;


import com.safetynet.alert.model.Firestation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.repository.FirestationRepositoryImpl;
import com.safetynet.alert.repository.MedicalRecordRepositoryImpl;
import com.safetynet.alert.repository.PersonRepositoryImpl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepositoryImpl personRepositoryImpl;

    @MockBean
    private FirestationRepositoryImpl firestationRepositoryImpl;

    @MockBean
    private MedicalRecordRepositoryImpl medicalRecordRepositoryImpl;

    @Test
    void testGetPersonInfoAndMedicalRecords_personNotFound() throws Exception {

        // Arrange
        String firstName = "NonExistentFirstName";
        String lastName = "NonExistentLastName";
        when(personRepositoryImpl.findPeopleByName(firstName, lastName))
                .thenThrow(new PersonNotFoundException("Person not found"));

        // Act & Assert
        mockMvc.perform(get("/personInfo")
                        .param("firstName", firstName)
                        .param("lastName", lastName))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Person not found"));
    }

    @Test
    void testFindAddressByFirestationNumber_firestationNotFound() throws Exception {

        // Arrange
        String firestationNumber = "999"; // an invalid firestation number
        when(firestationRepositoryImpl.findAddressByFirestationNumber(firestationNumber))
                .thenThrow(new FirestationNotFoundException("No fire station found with the specified station number"));

        // Act & Assert
        mockMvc.perform(get("/firestation")
                        .param("stationNumber", firestationNumber))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value("No fire station found with the specified station number"));
    }

    @Test
    void testAddFirestation_duplicateFirestation() throws Exception {

        // Arrange
        Firestation firestation = new Firestation("1509 Culver St", "3");
        when(firestationRepositoryImpl.addFirestation(firestation))
                .thenThrow(new DuplicateFirestationException("Firestation already exists"));

        // Act & Assert
        mockMvc.perform(post("/firestation")
                        .contentType("application/json")
                        .content("{\"address\":\"1509 Culver St\",\"station\":\"3\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Firestation already exists"));
    }

    @Test
    void testAddMedicalRecord_duplicateMedicalRecord() throws Exception {

        // Arrange
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "01/01/2000",
                null, null);
        when(medicalRecordRepositoryImpl.addNewMedicalRecord(medicalRecord))
                .thenThrow(new DuplicateMedicalRecordException("Medical record already exists"));

        // Act & Assert
        mockMvc.perform(post("/medicalRecord")
                        .contentType("application/json")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"birthdate\":\"01/01/2000\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Medical record already exists"));
    }

    @Test
    void testUpdateMedicalRecord_medicalRecordNotFound() throws Exception {

        // Arrange
        MedicalRecord medicalRecord = new MedicalRecord("NonExistentFirstName", "NonExistentLastName",
                "01/01/2000", null, null);
        when(medicalRecordRepositoryImpl.updateMedicalRecordByFirstAndLastName(medicalRecord))
                .thenThrow(new MedicalRecordNotFoundException("Medical record not found"));

        // Act & Assert
        mockMvc.perform(put("/medicalRecord")
                        .contentType("application/json")
                        .content("{\"firstName\":\"NonExistentFirstName\",\"lastName\":\"NonExistentLastName\"," +
                                "\"birthdate\":\"01/01/2000\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Medical record not found"));
    }

    @Test
    void testAddPerson_duplicatePerson() throws Exception {

        // Arrange
        Person person = new Person("John", "Doe", "1509 Culver St", "Culver",
                "97451", "841-874-6512", "john.doe@example.com");
        when(personRepositoryImpl.addPerson(person))
                .thenThrow(new DuplicatedPersonException("Person already exists"));

        // Act & Assert
        mockMvc.perform(post("/person")
                        .contentType("application/json")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"address\":\"1509 Culver St\"," +
                                "\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\"," +
                                "\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Person already exists"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void testHandleIncompleteRequestException() throws Exception {

        // Arrange
        when(personRepositoryImpl.addPerson(any(Person.class)))
                .thenThrow(new IncompleteRequestException("Incomplete request"));

        // Act & Assert
        mockMvc.perform(post("/person")
                        .contentType("application/json")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"address\":\"1509 Culver St\"," +
                                "\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\"}")) // Email is missing
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Incomplete Request. Request must contain all fields"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

}