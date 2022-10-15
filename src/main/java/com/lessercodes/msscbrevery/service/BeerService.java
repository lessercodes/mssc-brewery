package com.lessercodes.msscbrevery.service;

import java.util.UUID;

import com.lessercodes.msscbrevery.web.model.BeerDto;

public interface BeerService {

    BeerDto getBeerById(UUID beerId);

    BeerDto createNewBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

    void deleteBeer(UUID beerId);

}
