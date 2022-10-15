package com.lessercodes.msscbrevery.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lessercodes.msscbrevery.web.model.BeerDto;

@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder()
                .id(beerId)
                .beerName("Mango Bay")
                .beerStyle("Milkshake IPA")
                .build();
    }

    @Override
    public BeerDto createNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        // TODO: Update beer data
    }

}
