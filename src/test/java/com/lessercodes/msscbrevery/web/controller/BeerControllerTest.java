package com.lessercodes.msscbrevery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessercodes.msscbrevery.service.BeerService;
import com.lessercodes.msscbrevery.web.model.BeerDto;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    private static final String API_URL = "/api/v1/beer/";

    @MockBean
    private BeerService beerService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private BeerDto validBeer;

    @BeforeEach
    public void setup() {
        validBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Beer1")
                .beerStyle("PALE_ALE")
                .upc(123456789012L)
                .build();
    }

    @Test
    @SneakyThrows
    public void getBeer(){
        Mockito.when(beerService.getBeerById(Mockito.any(UUID.class))).thenReturn(validBeer);

        val getBeerRequest = MockMvcRequestBuilders.get(API_URL + validBeer.getId())
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(getBeerRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is("Beer1")));
    }

    @Test
    @SneakyThrows
    public void saveBeer() {
        val savedBeerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();
        Mockito.when(beerService.createNewBeer(Mockito.any())).thenReturn(savedBeerDto);

        val beerDto = BeerDto.builder().build();
        val beerDtoJson = objectMapper.writeValueAsString(beerDto);

        val saveBeerRequest = MockMvcRequestBuilders.post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson);
        mockMvc.perform(saveBeerRequest)
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    public void updateBeer() {
        val beerDtoJson = objectMapper.writeValueAsString(validBeer);

        val updateBeerRequest = MockMvcRequestBuilders.put(API_URL + validBeer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson);
        mockMvc.perform(updateBeerRequest)
                .andExpect(status().isNoContent());

        Mockito.verify(beerService).updateBeer(Mockito.any(), Mockito.any());
    }

}