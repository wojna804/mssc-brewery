package com.example.wojna.msscbrewery.services;

import com.example.wojna.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustomerBydId(UUID customerId);
}
