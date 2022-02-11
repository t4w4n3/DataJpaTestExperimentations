package com.example.springbootdatajpatest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OneService {

	private final OneRepository oneRepository;

	public OneService(OneRepository oneRepository) {
		this.oneRepository = oneRepository;
	}

	@Transactional
	public One save(One one) {
		return oneRepository.save(one);
	}

	@Transactional
	public One get(UUID uuid) {
		return oneRepository.getById(uuid);
	}

	@Transactional
	public void updateLabel(UUID uuid, String newLabel) {
		oneRepository.findById(uuid).ifPresent(found -> found.setLabel(newLabel));
	}
}
