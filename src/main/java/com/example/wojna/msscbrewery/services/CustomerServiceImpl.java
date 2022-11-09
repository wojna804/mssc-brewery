package com.example.wojna.msscbrewery.services;

import com.example.wojna.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerBydId(UUID customerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Arek")
                .build();
    }
}
