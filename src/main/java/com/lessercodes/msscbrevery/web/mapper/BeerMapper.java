package com.lessercodes.msscbrevery.web.mapper;

import com.lessercodes.msscbrevery.domain.Beer;
import com.lessercodes.msscbrevery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {DateMapper.class}
)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beer);

}
