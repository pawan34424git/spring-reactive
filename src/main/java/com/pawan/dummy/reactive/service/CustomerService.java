package com.pawan.dummy.reactive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.dummy.reactive.dao.CustomerDao;
import com.pawan.reactive.dto.CustomerDTO;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao dao;
	
	public List<CustomerDTO> getCustomers(){
		long start =System.currentTimeMillis();
		
		List<CustomerDTO> list = dao.getCustomers();
		System.out.println("Total time::: "+(System.currentTimeMillis()-start));
		return list;
	}
	
	public Flux<CustomerDTO> getCustomersStream(){
		long start =System.currentTimeMillis();
		
		Flux<CustomerDTO> list = dao.getCustomersStreamDelay();
		System.out.println("Total time::: "+(System.currentTimeMillis()-start));
		return list;
	}
}
