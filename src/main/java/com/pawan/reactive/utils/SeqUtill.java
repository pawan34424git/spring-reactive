package com.pawan.reactive.utils;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import com.pawan.reactive.config.ApplicationContextHolder;
import com.pawan.reactive.domain.Sequencer;

import reactor.core.publisher.Mono;

public class SeqUtill {

	public static final String CUSTOMER = "CUSTOMER";

	public static synchronized Mono<Integer> getNextSequence(String type) {

		ReactiveMongoOperations ops = ApplicationContextHolder.getContext().getBean(ReactiveMongoOperations.class);

		Mono<Sequencer> counter = ops.findAndModify(query(where("_id").is(type)), new Update().inc("seq", 1),
				options().returnNew(true), Sequencer.class);

		return counter.switchIfEmpty(ops.insert(new Sequencer(type, 1))).map(s -> s.getSeq());
	}
}
