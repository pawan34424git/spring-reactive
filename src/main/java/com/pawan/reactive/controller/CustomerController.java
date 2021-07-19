package com.pawan.reactive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.reactive.dto.CustomerDTO;
import com.pawan.reactive.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public List<CustomerDTO> getCustomers(){
		return customerService.getCustomers();
	}
	
	@GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<CustomerDTO> getCustomersStream(){
		return customerService.getCustomersStream();
	}
}
