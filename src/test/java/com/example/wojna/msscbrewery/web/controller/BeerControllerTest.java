package com.example.wojna.msscbrewery.web.controller;

import com.example.wojna.msscbrewery.services.BeerService;
import com.example.wojna.msscbrewery.web.model.BeerDto;
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

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerTest() throws Exception {

        UUID beerId = UUID.randomUUID();
        BeerDto beerDto = BeerDto.builder().id(beerId).build();
        when(beerService.getBeerById(beerId)).thenReturn(beerDto);

        mockMvc.perform(get("/api/v1/beer/" + beerId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void handlePostTest() throws Exception {
        UUID beerId = UUID.randomUUID();
        BeerDto beerDto = BeerDto.builder().id(beerId).build();
        when(beerService.saveNewBeer(beerDto)).thenReturn(beerDto);
        String beerJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/", beerDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerJson))
                .andExpect(status().isCreated());

        verify(beerService).saveNewBeer(beerDto);
    }

    @Test
    void handleUpdateTest() throws Exception {
        UUID beerId = UUID.randomUUID();
        BeerDto beerDto = BeerDto.builder().id(beerId).build();
        String beerJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beer/" + beerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerJson))
                .andExpect(status().isNoContent());

        verify(beerService).updateBeer(beerId, beerDto);

    }

    @Test
    void handleDeleteTest() throws Exception {
        UUID beerId = UUID.randomUUID();
        mockMvc.perform(delete("/api/v1/beer/" + beerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(beerService).deleteBeer(beerId);
    }
}