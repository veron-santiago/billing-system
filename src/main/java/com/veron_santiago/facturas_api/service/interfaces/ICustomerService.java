package com.veron_santiago.facturas_api.service.interfaces;

import com.veron_santiago.facturas_api.persistance.entity.Customer;

import java.util.List;

public interface ICustomerService {

    Customer createCustomer(Customer customer);
    Customer getCustomerById(Long id);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);

}
