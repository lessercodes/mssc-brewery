package com.lessercodes.msscbrevery.service.v2;

import com.lessercodes.msscbrevery.web.model.v2.BeerDtoV2;
import com.lessercodes.msscbrevery.web.model.v2.BeerStyle;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceV2Impl implements BeerServiceV2 {

    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return BeerDtoV2.builder()
                .id(beerId)
                .beerName("Mock Beer V2")
                .beerStyle(BeerStyle.IPA)
                .build();
    }

    @Override
    public BeerDtoV2 createNewBeer(BeerDtoV2 beerDto) {
        return null;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {

    }

    @Override
    public void deleteBeer(UUID beerId) {

    }

}
