package com.pawan.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.pawan.reactive.domain.Customer;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, Integer>{


}
