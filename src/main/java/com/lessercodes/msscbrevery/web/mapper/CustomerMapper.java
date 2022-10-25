package com.lessercodes.msscbrevery.web.mapper;

import com.lessercodes.msscbrevery.domain.Customer;
import com.lessercodes.msscbrevery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customer);
    
}
