package com.ucheikenna.musalasoftdroneproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucheikenna.musalasoftdroneproject.pojo.requests.MedicationRequest;
import com.ucheikenna.musalasoftdroneproject.services.serviceInterface.MedicationServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {MedicationController.class})
@ExtendWith(SpringExtension.class)
class MedicationControllerTest {

    @Autowired
    private MedicationController medicationController;

    @MockBean
    private MedicationServices medicationService;


    @Test
    void testToRegisterMedication() throws Exception {
        when(medicationService.registerMedication(any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        MedicationRequest medicationRequest = new MedicationRequest();
        medicationRequest.setImage("https://medi1.png");
        medicationRequest.setName("Alabukun");
        medicationRequest.setWeight(10.0);
        String content = (new ObjectMapper()).writeValueAsString(medicationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/medication/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultAction = MockMvcBuilders.standaloneSetup(medicationController)
                .build()
                .perform(requestBuilder);
        resultAction.andExpect(MockMvcResultMatchers.status().is(201));
    }


    @Test
    void testToGetAllMedications() throws Exception {
        when(medicationService.getAllMedications()).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/medication/all/get_medications");
        ResultActions resultAction = MockMvcBuilders.standaloneSetup(medicationController)
                .build()
                .perform(requestBuilder);
        resultAction.andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    void testToGetMedication() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/medication/single/get_medication");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("medId", String.valueOf(1L));
        ResultActions resultAction = MockMvcBuilders.standaloneSetup(medicationController)
                .build()
                .perform(requestBuilder);
        resultAction.andExpect(MockMvcResultMatchers.status().is(400));
    }

}