package com.example.trainingprogram.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DayOfWeekRepository extends CrudRepository<DayOfWeek, Long> {

	List<DayOfWeek> findByDayOfWeek(String dayOfWeek);
}