package com.pawan.reactive.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.pawan.reactive.handler.CustomerHandler;

@Configuration
public class RouterConfig {

	@Autowired
	CustomerHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction(){
		return RouterFunctions
				.route()
				.GET("/router/customer",handler::getCustomers)
				.GET("/router/customer/{customerId}",handler::findCustomer)
				.GET("/router/customer-stream",handler::getCustomersStream)
				.POST("/router/customer",handler::saveCustomer)
				.build();
	}
}
