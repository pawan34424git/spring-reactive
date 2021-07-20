package com.pawan.reactive.mapper;

import org.springframework.beans.BeanUtils;

import com.pawan.reactive.domain.Customer;
import com.pawan.reactive.dto.CustomerDTO;

public class CustomerMapper {

	public static CustomerDTO toDTO(Customer domain) {
		CustomerDTO dto = new CustomerDTO();
		BeanUtils.copyProperties(domain, dto);
		return dto;
	}
	
	public static Customer toDomain(CustomerDTO dto) {
		
		return toDomain(null, dto);
	}
	
	public static Customer toDomain(Customer domain,CustomerDTO dto) {
		if(domain == null) {
			domain = new Customer();
		}
		domain.setCustomerId(dto.getCustomerId());
		domain.setCustomerName(dto.getCustomerName());
		
		return domain;
	}
}
