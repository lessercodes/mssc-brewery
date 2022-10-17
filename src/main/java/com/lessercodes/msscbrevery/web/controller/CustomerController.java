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

import com.lessercodes.msscbrevery.service.CustomerService;
import com.lessercodes.msscbrevery.web.model.CustomerDto;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId) {
        return ResponseEntity.ok().body(customerService.getCustomerById(customerId));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDto customerDto) {
        val savedCustomerDto = customerService.createCustomer(customerDto);
        val location = String.format("/api/v1/customer/%s", savedCustomerDto.getId());
        val locationUri = new URI(location);
        return ResponseEntity.created(locationUri).build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomer(
            @PathVariable UUID customerId,
            @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerId, customerDto);
        return ResponseEntity.noContent().build();
    }

}
