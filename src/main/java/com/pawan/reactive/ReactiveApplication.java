package com.pawan.reactive;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.pawan.reactive.utils.JSR310DateConverters.DateToLocalDateConverter;
import com.pawan.reactive.utils.JSR310DateConverters.DateToLocalDateTimeConverter;
import com.pawan.reactive.utils.JSR310DateConverters.DateToZonedDateTimeConverter;
import com.pawan.reactive.utils.JSR310DateConverters.LocalDateTimeToDateConverter;
import com.pawan.reactive.utils.JSR310DateConverters.LocalDateToDateConverter;
import com.pawan.reactive.utils.JSR310DateConverters.ZonedDateTimeToDateConverter;

@SpringBootApplication
public class ReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	@Bean
	public MongoCustomConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(DateToZonedDateTimeConverter.INSTANCE);
		converters.add(ZonedDateTimeToDateConverter.INSTANCE);
		converters.add(DateToLocalDateConverter.INSTANCE);
		converters.add(LocalDateToDateConverter.INSTANCE);
		converters.add(DateToLocalDateTimeConverter.INSTANCE);
		converters.add(LocalDateTimeToDateConverter.INSTANCE);
		return new MongoCustomConversions(converters);
	}

	/*
	@Bean
	public MongoOperations mongoDatabase(MongoCustomConversions mappingMongoConverter) {
		 
		 
		
		String databaseName="";
		
		MongoTemplate ss=new ReactiveMongoTemplate(null, databaseName)
	
		//MongoTemplate mongoTemplate = new MongoTemplate
//		MappingMongoConverter mmc = (MappingMongoConverter) mongoTemplate.getConverter();
//		mmc.setCustomConversions(mappingMongoConverter);
//		mmc.afterPropertiesSet();
		
		return null;
	}  */

}
