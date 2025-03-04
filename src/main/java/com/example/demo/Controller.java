package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class Controller {

    private  CustomerService customerService;

    public Controller(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);  // Return the saved customer
    }



    @GetMapping("/byId")
    public ResponseEntity<Customer>  getCustomerById(@RequestParam Long  customerId){

        Customer customerById = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerById, HttpStatus.OK);

    }

    @DeleteMapping("/byId")
    public ResponseEntity<Void> deleteCustomer(@RequestParam Long customerId){

        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


