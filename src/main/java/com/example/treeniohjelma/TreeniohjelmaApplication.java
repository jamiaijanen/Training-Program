package com.example.treeniohjelma;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.treeniohjelma.domain.TreeniRepository;
import com.example.treeniohjelma.domain.Viikonpaiva;
import com.example.treeniohjelma.domain.ViikonpaivaRepository;

@SpringBootApplication
public class TreeniohjelmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreeniohjelmaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TreeniRepository repository, ViikonpaivaRepository vrepository) {
		return (args) -> {

			vrepository.save(new Viikonpaiva("Maanantai"));
			vrepository.save(new Viikonpaiva("Tiistai"));
			vrepository.save(new Viikonpaiva("Keskiviikko"));
			vrepository.save(new Viikonpaiva("Torstai"));
			vrepository.save(new Viikonpaiva("Perjantai"));
			vrepository.save(new Viikonpaiva("Lauantai"));
			vrepository.save(new Viikonpaiva("Sunnuntai"));
		};
	}

}
