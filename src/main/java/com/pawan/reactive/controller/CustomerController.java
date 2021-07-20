package com.pawan.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.reactive.dto.CustomerDTO;
import com.pawan.reactive.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public Flux<CustomerDTO> getCustomers(){
		return customerService.getCustomers();
	}
	
	@GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<CustomerDTO> getCustomersStream(){
		return customerService.getCustomers();
	}
	
	@PostMapping
	public Mono<CustomerDTO> saveCustomers(@RequestBody Mono<CustomerDTO> dto){
		return customerService.saveCustomers(dto);
	}
	
	@GetMapping("/{customerId}")
	public Mono<CustomerDTO> getCustomer(@PathVariable Integer customerId){
		return customerService.getCustomer(customerId);
	}
	
	@PutMapping("/{customerId}")
	public Mono<CustomerDTO> updateCustomers(@RequestBody Mono<CustomerDTO> dto,@PathVariable Integer customerId){
		return customerService.updateCustomers(dto,customerId);
	}
}
