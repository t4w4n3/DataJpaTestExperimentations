package com.example.springbootdatajpatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataTransactionIocIT {

	@Autowired
	private OneService oneService;

	@Autowired
	private EntityManager entityManager;

	@Test
	void shouldUpdateTheWrittenOne() {
		UUID uuid = UUID.randomUUID();
		One created = oneService.save(new One(uuid, "label1"));
		oneService.updateLabel(uuid, "changed");
		One modified = entityManager.find(One.class, uuid);
		assertThat(modified).hasFieldOrPropertyWithValue("label", "changed");
	}
}
