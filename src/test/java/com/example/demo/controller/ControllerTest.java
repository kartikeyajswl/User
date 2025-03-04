package com.example.demo.controller;

import com.example.demo.Controller;
import com.example.demo.Customer;
import com.example.demo.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(Controller.class)
class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CustomerService customerService;

    @InjectMocks
    private Controller controller;

    private Customer customer;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(Controller).build();
        objectMapper = new ObjectMapper();
        customer =new Customer();
        customer.setName("kartikeya");
        customer.setEmail("kartikeya@gmail.com");
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void saveCustomer() throws Exception {
        // Create a customer object to be saved
        Customer customer = new Customer();
        customer.setName("kartikeya");
        customer.setEmail("kartikeya@gmail.com");

        // Prepare the saved customer with ID (this is what will be returned by the mock service)
        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);  // Set an ID for the saved customer
        savedCustomer.setName("kartikeya");
        savedCustomer.setEmail("kartikeya@gmail.com");

        // Mock the service to return the saved customer when saveCustomer() is called
        when(customerService.saveCustomer(any(Customer.class))).thenReturn(savedCustomer);

        // Perform the POST request to save the customer
        mockMvc.perform(post("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())  // Expect status 201 Created
                .andExpect(jsonPath("$.id").value(1))  // Assert that the ID is returned
                .andExpect(jsonPath("$.name").value("kartikeya"))  // Assert the name
                .andExpect(jsonPath("$.email").value("kartikeya@gmail.com"));  // Assert the email
    }

    @Test
    void getCustomerById() throws Exception{
        customer.setId(1L);
        customer.setName("kartikeya");
        customer.setEmail("kartikeya@gmail.com");

        // Mock the service to return the customer when requested by ID
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        // Perform GET request to get customer by ID
        mockMvc.perform(get("/api/v1/byId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("customerId", "1"))  // Fix: Param should be "customerId=1"
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("kartikeya"))
                .andExpect(jsonPath("$.email").value("kartikeya@gmail.com"));

    }


    @Test
    void  deleteById() throws Exception {
        customer.setId(1L);
        customer.setName("kartikeya");
        customer.setEmail("kartikeya@gmail.com");
        doNothing().when(customerService).deleteCustomer(1L);
        mockMvc.perform(delete("/api/v1/byId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("customerId", "1"))  // Fix: Param should be "customerId=1"
                .andExpect(status().isNoContent());
            }

}