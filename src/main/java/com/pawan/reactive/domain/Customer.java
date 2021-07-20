package com.pawan.reactive.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	private Integer customerId;
	private String CustomerName;
}
