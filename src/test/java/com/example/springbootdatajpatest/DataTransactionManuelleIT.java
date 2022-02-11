package com.example.springbootdatajpatest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@DataJpaTest(showSql = false)
@Transactional(propagation = NOT_SUPPORTED)
class DataTransactionManuelleIT {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private OneRepository oneRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	private TransactionTemplate transactionTemplate;

	@BeforeEach
	public void setUp() {
		transactionTemplate = new TransactionTemplate(transactionManager);
	}

	@Test
	void shouldUpdateTheWrittenOne() {
		OneService oneService = new OneService(oneRepository);
		UUID uuid = UUID.randomUUID();
		One created = transactionTemplate.execute(status -> {
			return oneService.save(new One(uuid, "label1"));
		});
		transactionTemplate.execute(status -> {
			oneService.updateLabel(uuid, "changed");
			return true;
		});
		One modified = transactionTemplate.execute(status -> entityManager.find(One.class, uuid));
		assertThat(modified).hasFieldOrPropertyWithValue("label", "changed");
	}
}
