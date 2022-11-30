package com.example.wojna.msscbrewery.services;

import com.example.wojna.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerBydId(UUID customerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Arek")
                .build();
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Marek")
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        log.debug("updateCustomer");

        // todo
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.debug("deleteCustomer");
        //todo
    }
}
