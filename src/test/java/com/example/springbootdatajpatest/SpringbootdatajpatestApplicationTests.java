package com.example.springbootdatajpatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootdatajpatestApplicationTests {

	@Autowired
	private OneService oneService;

	@Test
	void contextLoads() {
		assert oneService != null;
	}

}
