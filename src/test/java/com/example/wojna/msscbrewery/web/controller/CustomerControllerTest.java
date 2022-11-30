package com.example.wojna.msscbrewery.web.controller;

import com.example.wojna.msscbrewery.services.CustomerService;
import com.example.wojna.msscbrewery.web.model.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    @Test
    void getCustomerTest() throws Exception {

        UUID customerId = UUID.randomUUID();
        CustomerDto customerDto = CustomerDto.builder().id(customerId).build();
        when(customerService.getCustomerBydId(customerId)).thenReturn(customerDto);

        mockMvc.perform(get("/api/v1/customer/" + customerId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void handlePostTest() throws Exception {
        CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).build();
        when(customerService.saveCustomer(customerDto)).thenReturn(customerDto);
        String customerJson = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(post("/api/v1/customer/", customerDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());

        verify(customerService).saveCustomer(customerDto);
    }

    @Test
    void handleUpdateTest() throws Exception {
        CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).build();
        String customerJson = objectMapper.writeValueAsString(customerDto);
        mockMvc.perform(put("/api/v1/customer/" + customerDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isNoContent());

        verify(customerService).updateCustomer(customerDto.getId(), customerDto);

    }

    @Test
    void handleDeleteTest() throws Exception {
        UUID customerId = UUID.randomUUID();
        mockMvc.perform(delete("/api/v1/customer/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(customerService).deleteCustomer(customerId);
    }
}