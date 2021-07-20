package com.pawan.reactive.service;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.pawan.reactive.domain.Customer;
import com.pawan.reactive.dto.CustomerDTO;
import com.pawan.reactive.mapper.CustomerMapper;
import com.pawan.reactive.repository.CustomerRepository;
import com.pawan.reactive.utils.SeqUtill;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private ReactiveMongoOperations mongoOperations;

	public Flux<CustomerDTO> getCustomers() {
		
		return repository.findAll().map(CustomerMapper::toDTO);
	}
	
	public Flux<?> getCustomersStream() {
		
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		Query query=new Query();
		query.with(Sort.by(Sort.Direction.DESC, "customerId"));
		
	 
		Mono<CustomerDTO> stream = mongoOperations.findOne(query, Customer.class).map(CustomerMapper::toDTO);
	
		
		return interval.flatMap(i->stream).doOnNext(System.out::println);
	}

	
	/*
 public Flux<?> getCustomersStream() {
		
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		Query query=new Query();
		query.with(Sort.by(Sort.Direction.DESC, "customerId"));
		
		Mono<CustomerDTO> stream = mongoOperations.findOne(query, Customer.class).map(CustomerMapper::toDTO);
	
		
		return Flux.zip(Flux.fromStream(stream.flux().toStream()),interval,(key, value) -> key);
	}*/
 
 
	public Mono<CustomerDTO> getCustomer(Integer customerId) {
		return repository.findById(customerId).map(CustomerMapper::toDTO);
	}

	public Mono<CustomerDTO> saveCustomers(Mono<CustomerDTO> dto) {

		return dto.map(CustomerMapper::toDomain).zipWith(SeqUtill.getNextSequence(SeqUtill.CUSTOMER), (domain, id) -> {
			domain.setCustomerId(id);
			return domain;
		}).flatMap(repository::insert).map(CustomerMapper::toDTO);
	}

	public Mono<CustomerDTO> updateCustomers(Mono<CustomerDTO> dto, Integer customerId) {
		return repository.findById(customerId).map(domain -> CustomerMapper.toDomain(domain, dto.block()))
				.doOnNext(domain -> domain.setCustomerId(customerId)).flatMap(repository::save)
				.map(CustomerMapper::toDTO);
	}
}
