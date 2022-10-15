package com.lessercodes.msscbrevery.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lessercodes.msscbrevery.web.model.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(customerId)
                .name("John Doe")
                .build();
    }

}