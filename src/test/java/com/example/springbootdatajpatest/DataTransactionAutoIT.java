package com.example.springbootdatajpatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
class DataTransactionAutoIT {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private OneRepository oneRepository;

	@Test
	void shouldUpdateTheWrittenOne() {
		OneService oneService = new OneService(oneRepository);
		UUID uuid = UUID.randomUUID();
		One created = oneService.save(new One(uuid, "label1"));
		oneService.updateLabel(uuid, "changed");
		assertThat(created).hasFieldOrPropertyWithValue("label", "changed");
	}
}
