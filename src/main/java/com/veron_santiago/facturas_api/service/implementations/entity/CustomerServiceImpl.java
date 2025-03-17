package com.veron_santiago.facturas_api.service.implementations.entity;

import com.veron_santiago.facturas_api.persistence.entity.Customer;
import com.veron_santiago.facturas_api.persistence.repository.ICustomerRepository;
import com.veron_santiago.facturas_api.presentation.dto.entities.CustomerDTO;
import com.veron_santiago.facturas_api.service.interfaces.ICustomerService;
import com.veron_santiago.facturas_api.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(ICustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("No se ha encontrado el cliente"));
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow( () -> new RuntimeException("No se ha encontrado el usuario con ese mail") );
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Cliente no encontrado") );
        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setEmail(customerDTO.getEmail());
        return customerMapper.customerToCustomerDTO(customerRepository.save(existingCustomer));
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        customerRepository.delete(customer);
    }
}
