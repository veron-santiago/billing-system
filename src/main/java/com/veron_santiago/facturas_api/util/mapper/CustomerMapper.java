package com.veron_santiago.facturas_api.util.mapper;

import com.veron_santiago.facturas_api.persistence.entity.Customer;
import com.veron_santiago.facturas_api.presentation.dto.entities.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}