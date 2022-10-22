package com.lessercodes.msscbrevery.web.controller.v2;

import com.lessercodes.msscbrevery.service.v2.BeerServiceV2;
import com.lessercodes.msscbrevery.web.model.v2.BeerDtoV2;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/beer")
@RequiredArgsConstructor
public class BeerControllerV2 {

    private final BeerServiceV2 beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable UUID beerId) {
        return ResponseEntity.ok().body(beerService.getBeerById(beerId));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Void> createBeer(@Valid @RequestBody BeerDtoV2 beerDto) {
        val savedBeerDto = beerService.createNewBeer(beerDto);
        val location = String.format("/api/v1/beer/%s", savedBeerDto.getId());
        val locationUri = new URI(location);
        return ResponseEntity.created(locationUri).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeer(
            @PathVariable UUID beerId,
            @Valid @RequestBody BeerDtoV2 beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<Void> deleteBeer(@PathVariable UUID beerId) {
        beerService.deleteBeer(beerId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException e) {
        val errors = e.getConstraintViolations().stream()
                .map(violation -> String.format("%s: %s", violation.getPropertyPath(), violation.getMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

}
