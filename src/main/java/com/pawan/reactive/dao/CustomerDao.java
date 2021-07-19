package com.pawan.reactive.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.pawan.reactive.dto.CustomerDTO;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {
	
	private static void sleep(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}
	
	public List<CustomerDTO> getCustomers(){
		return IntStream.range(1, 50)
				   .peek(CustomerDao::sleep)
				    .peek(i-> System.out.println("processing ---> "+i))
					.mapToObj(i->new CustomerDTO(i,"Customer-"+i))
					.collect(Collectors.toList());
	}
	
	
	
	public Flux<CustomerDTO> getCustomersStreamDelay(){
		
		return Flux.range(1, 50)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i-> System.out.println("processing flux ---> "+i))
				.map(i->new CustomerDTO(i,"Customer-"+i));
				
	
	}
	
	public Flux<CustomerDTO> getCustomersStream(){
		
		return Flux.range(1, 50)
				.doOnNext(i-> System.out.println("processing flux ---> "+i))
				.map(i->new CustomerDTO(i,"Customer-"+i));
				
	
	}

}
