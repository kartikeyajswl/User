package com.example.demo;


import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer saveCustomer(Customer customer) {

        if(customerRepository.existsByEmail(customer.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);}

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
