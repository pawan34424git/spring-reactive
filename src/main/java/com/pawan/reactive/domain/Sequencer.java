package com.pawan.reactive.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequencer")
public class Sequencer {
	@Id
	private String id;
	private Integer seq;

	public Sequencer() {
	}

	public Sequencer(String id) {
		super();
		this.id = id;
	}

	public Sequencer(String id, Integer seq) {
		super();
		this.id = id;
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

}
