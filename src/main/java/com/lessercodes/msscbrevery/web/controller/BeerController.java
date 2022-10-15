package com.lessercodes.msscbrevery.web.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lessercodes.msscbrevery.service.BeerService;
import com.lessercodes.msscbrevery.web.model.BeerDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {
        return ResponseEntity.ok().body(beerService.getBeerById(beerId));
    }

}
