package com.pawan.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Flux<CustomerDTO> getCustomers() {
		return repository.findAll().map(CustomerMapper::toDTO);
	}

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
