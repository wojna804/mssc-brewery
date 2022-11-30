package com.example.wojna.msscbrewery.web.controller;

import com.example.wojna.msscbrewery.services.CustomerService;
import com.example.wojna.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId){
        return new ResponseEntity<>(customerService.getCustomerBydId(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody final CustomerDto customerDto){
        CustomerDto savedCustomer = customerService.saveCustomer(customerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handeUpdate(@PathVariable("customerId") UUID customerId, @RequestBody final CustomerDto customerDto){
        customerService.updateCustomer(customerId, customerDto);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handeDelete(@PathVariable("customerId") UUID customerId){
        customerService.deleteCustomer(customerId);
    }
}
