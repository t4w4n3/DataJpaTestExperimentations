package com.example.springbootdatajpatest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "one")
public class One {

	@Id private UUID id;
	private String label;

	public One() {
	}

	public One(UUID id, String label) {
		this.id = id;
		this.label = label;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
