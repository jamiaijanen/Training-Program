package com.example.treeniohjelma.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ViikonpaivaRepository extends CrudRepository<Viikonpaiva, Long> {

	List<Viikonpaiva> findByViikonpaiva(String viikonpaiva);
}