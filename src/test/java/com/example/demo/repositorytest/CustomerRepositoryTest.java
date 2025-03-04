package com.example.demo.repositorytest;

import com.example.demo.Customer;
import com.example.demo.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
  private CustomerRepository customerRepository;

   @Transactional
    @Test
    void existsByEmail() {
       Customer customer=new Customer();
        customer.setEmail("kartikeya@gmail.com");
        customerRepository.save(customer);

        boolean actualResult= customerRepository.existsByEmail("kartikeya@gmail.com");

        assertTrue(actualResult,"email exists");
    }


}