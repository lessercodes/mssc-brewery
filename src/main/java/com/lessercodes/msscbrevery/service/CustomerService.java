package com.lessercodes.msscbrevery.service;

import java.util.UUID;

import com.lessercodes.msscbrevery.web.model.CustomerDto;

public interface CustomerService {

    CustomerDto getCustomerById(UUID customerId);

}