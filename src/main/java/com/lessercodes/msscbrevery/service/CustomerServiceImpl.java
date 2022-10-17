package com.lessercodes.msscbrevery.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lessercodes.msscbrevery.web.model.CustomerDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(customerId)
                .name("John Doe")
                .build();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name(customerDto.getName())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        // TODO: Update customer data
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.debug("Deleting customer with id: " + customerId);
    }

}
