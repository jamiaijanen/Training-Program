package com.example.trainingprogram.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<Training, Long> {

	Training findByUser(User user);

	List<Training> findByTrainingName(String trainingName);

}