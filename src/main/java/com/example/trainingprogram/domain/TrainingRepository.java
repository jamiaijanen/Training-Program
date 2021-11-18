package com.example.trainingprogram.domain;

import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<Training, Long> {

	Training findByUser(String user);

}