package com.example.demo.Service;

import com.example.demo.Customer;
import com.example.demo.CustomerRepository;
import com.example.demo.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;


    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        this.customerService=new CustomerService(this.customerRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveCustomer() {
        Customer customer=new Customer();
        customer.setEmail("test@example.com");
        customer.setName("test");
        customerService.saveCustomer(customer);
        verify(customerRepository).save(customer);
    }
}