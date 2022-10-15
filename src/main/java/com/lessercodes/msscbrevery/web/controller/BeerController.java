package com.lessercodes.msscbrevery.web.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lessercodes.msscbrevery.service.BeerService;
import com.lessercodes.msscbrevery.web.model.BeerDto;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {
        return ResponseEntity.ok().body(beerService.getBeerById(beerId));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Void> createBeer(@RequestBody BeerDto beerDto) {
        val savedBeerDto = beerService.createNewBeer(beerDto);
        val location = String.format("/api/v1/beer/%s", savedBeerDto.getId());
        val locationUri = new URI(location);
        return ResponseEntity.created(locationUri).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeer(
            @PathVariable UUID beerId,
            @RequestBody BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return ResponseEntity.noContent().build();
    }

}
