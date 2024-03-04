package com.safetynet.alert.controller;


import com.safetynet.alert.service.FirestationServiceImpl;
import com.safetynet.alert.service.PersonServiceImpl;
import com.safetynet.alert.dto.Household;
import com.safetynet.alert.dto.PeopleByFirestationNumber;
import com.safetynet.alert.dto.PhoneByFirestation;
import com.safetynet.alert.model.Firestation;
import com.safetynet.alert.service.FirestationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;


@ExtendWith(SpringExtension.class)
@WebMvcTest(FirestationController.class)
 class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationServiceImpl firestationService;

   @MockBean
   private PersonServiceImpl personService;

   private PeopleByFirestationNumber peopleByFirestationNumber;

   @BeforeEach
   void setUpPerEach() {
      peopleByFirestationNumber = new PeopleByFirestationNumber(null, 1, 1); 

   }

    @Test
     void testGetPeopleByFirestationNumber() throws Exception {
        //Arrange
        // Stub the service call
        when(firestationService.getAdultsAndMinorsCoveredByFirestationNumber(anyString()))
                .thenReturn(peopleByFirestationNumber);

        //Act
        mockMvc.perform(get("/firestation?stationNumber=1"))
                .andExpect(status().isOk());

        //Assert: Verify that the service was called once
        verify(firestationService, times(1))
                .getAdultsAndMinorsCoveredByFirestationNumber("1");
    }

   @Test
    void testGetPhoneNumbersOfPeopleByFirestation() throws Exception {
       // Arrange
       String firestationNumber = "1";
       List<String> phoneNumbers = Arrays.asList("123-456-7890", "098-765-4321");
       PhoneByFirestation mockPhoneNumbersByFirestation = new PhoneByFirestation(phoneNumbers);
       when(firestationService.getPhoneByFirestationNumber(firestationNumber))
               .thenReturn(mockPhoneNumbersByFirestation);

       // Act
       mockMvc.perform(get("/phoneAlert?firestation=1")).andExpect(status().isOk());

       //Assert
       verify(firestationService, times(1))
               .getPhoneByFirestationNumber("1");
   }

    @Test
    void testGetHousholds() throws Exception {
        // Arrange
        List<String> stationNumbers = Arrays.asList("1", "2", "3");
        List<Household> mockHousholds = new ArrayList<>(); // Populate with appropriate test data

        when(firestationService.getHouseholdsByFirestationNumbers(stationNumbers))
                .thenReturn(mockHousholds);

        // Act
        mockMvc.perform(get("/flood/stations")
                        .param("stations", "1")
                        .param("stations", "2")
                        .param("stations", "3"))
                .andExpect(status().isOk());

        // Assert
        verify(firestationService, times(1))
                .getHouseholdsByFirestationNumbers(stationNumbers);
    }

    @Test
    void testPostFirestation() throws Exception {
        // Arrange
        Firestation firestation = new Firestation("1 rue de paris", "1");
        when(firestationService.postFireStation(any(Firestation.class))).thenReturn(firestation);

        // Act
        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"1 rue de paris\", \"station\":\"1\"}"))
                .andExpect(status().isCreated());

        // Assert
        verify(firestationService, times(1)).postFireStation(any(Firestation.class));
    }

    @Test
    void testPutFirestationNumber() throws Exception {
        // Arrange
        Firestation firestationToEdit = new Firestation("1 rue de paris", "1");
        when(firestationService.putFireStaion(any(Firestation.class))).thenReturn(firestationToEdit);

        // Act
        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"1 rue de paris\", \"station\":\"1\"}"))
                .andExpect(status().isOk());

        // Assert
        verify(firestationService, times(1)).putFireStaion(any(Firestation.class));
    }


    @Test
    void testDeleteFirestationCoverageOfAddress() throws Exception {
        // Arrange
        Firestation firestationToDelete = new Firestation("1 rue de paris", "1");
        when(firestationService.deleteFirestation(any(Firestation.class))).thenReturn(firestationToDelete);;

        // Act
        mockMvc.perform(delete("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"1 rue de paris\", \"station\":\"1\"}"))
                .andExpect(status().isNoContent());

        // Assert
        verify(firestationService, times(1)).deleteFirestation(any(Firestation.class));
    }

}

