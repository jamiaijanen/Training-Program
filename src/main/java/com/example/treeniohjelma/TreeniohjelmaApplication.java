package com.example.treeniohjelma;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.treeniohjelma.domain.TreeniRepository;
import com.example.treeniohjelma.domain.User;
import com.example.treeniohjelma.domain.UserRepository;
import com.example.treeniohjelma.domain.Viikonpaiva;
import com.example.treeniohjelma.domain.ViikonpaivaRepository;

@SpringBootApplication
public class TreeniohjelmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreeniohjelmaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TreeniRepository repository, ViikonpaivaRepository vrepository,
			UserRepository krepository) {
		return (args) -> {

			vrepository.save(new Viikonpaiva("Maanantai"));
			vrepository.save(new Viikonpaiva("Tiistai"));
			vrepository.save(new Viikonpaiva("Keskiviikko"));
			vrepository.save(new Viikonpaiva("Torstai"));
			vrepository.save(new Viikonpaiva("Perjantai"));
			vrepository.save(new Viikonpaiva("Lauantai"));
			vrepository.save(new Viikonpaiva("Sunnuntai"));

			User user = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User admin = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");

			krepository.save(user);
			krepository.save(admin);

		};
	}

}
