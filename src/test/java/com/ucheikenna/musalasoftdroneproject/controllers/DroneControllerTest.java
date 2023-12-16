package com.ucheikenna.musalasoftdroneproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucheikenna.musalasoftdroneproject.enums.Model;
import com.ucheikenna.musalasoftdroneproject.pojo.requests.DroneRequest;
import com.ucheikenna.musalasoftdroneproject.services.serviceInterface.DroneServices;
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


@ContextConfiguration(classes = {DroneController.class})
@ExtendWith(SpringExtension.class)
class DroneControllerTest {

    @Autowired
    private DroneController droneController;
    @MockBean
    private DroneServices droneService;

    @Test
    void testToRegisterDroneWithRegistrationApiCall() throws Exception {

        when(droneService.registerDrone(any())).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        DroneRequest droneRequest = new DroneRequest();
        droneRequest.setModel(Model.LIGHT_WEIGHT);
        String content = (new ObjectMapper()).writeValueAsString(droneRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/drone/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultAction = MockMvcBuilders.standaloneSetup(droneController)
                .build()
                .perform(requestBuilder);
        resultAction.andExpect(MockMvcResultMatchers.status().is(201));
    }


    @Test
    void testToGetAllDronesWithApiCall() throws Exception {

        when(droneService.getAllDrones()).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/drone/all/get_drones");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(droneController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    void testToGetAvailableDronesForLoadingWithApiCall() throws Exception {

        when(droneService.getAllAvailableDrones()).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/drone/available_drones");
        ResultActions resultAction = MockMvcBuilders.standaloneSetup(droneController)
                .build()
                .perform(requestBuilder);
        resultAction.andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testToGetDroneBatteryLevelWithApiCall() throws Exception {

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/drone/battery_level");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("drone-id", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(droneController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    void testToGetSingleDronesWithApiCall() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/drone/single/get_drone");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        ResultActions resultAction = MockMvcBuilders.standaloneSetup(droneController)
                .build()
                .perform(requestBuilder);
        resultAction.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testToLoadDroneWithMedicationItemsOnApiCall() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/v1/drone/load_drone");
        MockHttpServletRequestBuilder paramResult = postResult.param("drone_id", String.valueOf(1L));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("med_id", String.valueOf(1L));
        ResultActions resultAction = MockMvcBuilders.standaloneSetup(droneController)
                .build()
                .perform(requestBuilder);
        resultAction.andExpect(MockMvcResultMatchers.status().is(400));
    }
}