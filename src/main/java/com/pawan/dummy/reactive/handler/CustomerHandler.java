package com.pawan.dummy.reactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.pawan.dummy.reactive.dao.CustomerDao;
import com.pawan.reactive.dto.CustomerDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

	@Autowired
	CustomerDao customerDao;
	
	public Mono<ServerResponse> getCustomers(ServerRequest request){
		
		
		Flux<CustomerDTO> list = customerDao.getCustomersStream();
		
		return ServerResponse.ok().body(list,CustomerDTO.class);
		
	}
	
	public Mono<ServerResponse> findCustomer(ServerRequest request){
		
		Integer customerId = Integer.valueOf(request.pathVariable("customerId"));
		
		Mono<CustomerDTO> c = customerDao.getCustomersStream().filter(cs->cs.getCustomerId() == customerId).next();
		
		return ServerResponse.ok().body(c,CustomerDTO.class);
		
	}
	
	
	public Mono<ServerResponse> saveCustomer(ServerRequest request){
		
		Mono<CustomerDTO> customer = request.bodyToMono(CustomerDTO.class);
		
		Mono<String> res = customer.map(c->c.getCustomerId() + " :: " + c.getCustomerName());
		
		return ServerResponse.ok().body(res,String.class);
		
	}
	
	public Mono<ServerResponse> getCustomersStream(ServerRequest request){
		Flux<CustomerDTO> list = customerDao.getCustomersStreamDelay();
		
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(list,CustomerDTO.class);
		
	}
}
