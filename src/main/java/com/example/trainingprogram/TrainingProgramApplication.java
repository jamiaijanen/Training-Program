package com.example.trainingprogram;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.trainingprogram.domain.DayOfWeek;
import com.example.trainingprogram.domain.DayOfWeekRepository;
import com.example.trainingprogram.domain.TrainingRepository;
import com.example.trainingprogram.domain.User;
import com.example.trainingprogram.domain.UserRepository;

@SpringBootApplication
public class TrainingProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingProgramApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TrainingRepository repository, DayOfWeekRepository vrepository,
			UserRepository urepository) {
		return (args) -> {

			// check if there is already saved DayOfWeek objects
			if (vrepository.count() == 7) {

			} else {

				// saving 7 DayOfWeek objects and 2 users (user and admin)
				vrepository.save(new DayOfWeek("Monday"));
				vrepository.save(new DayOfWeek("Tuesday"));
				vrepository.save(new DayOfWeek("Wednesday"));
				vrepository.save(new DayOfWeek("Thursday"));
				vrepository.save(new DayOfWeek("Friday"));
				vrepository.save(new DayOfWeek("Saturday"));
				vrepository.save(new DayOfWeek("Sunday"));
				urepository
						.save(new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER"));
				urepository.save(
						new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN"));
			}
		};
	}
}
