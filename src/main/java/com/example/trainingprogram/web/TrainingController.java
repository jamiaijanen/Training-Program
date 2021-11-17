package com.example.trainingprogram.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.trainingprogram.domain.DayOfWeekRepository;
import com.example.trainingprogram.domain.Training;
import com.example.trainingprogram.domain.TrainingRepository;

@Controller
public class TrainingController {

	@Autowired
	private TrainingRepository repository;

	@Autowired
	private DayOfWeekRepository vrepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = { "/", "/trainings" })
	public String trainings(Model model) {
		model.addAttribute("trainings", repository.findAll());
		return "trainings";
	}

	@RequestMapping(value = "/add")
	public String addTraining(Model model) {
		model.addAttribute("training", new Training());
		model.addAttribute("dayOfWeek", vrepository.findAll());
		return "addTraining";
	}

	@RequestMapping(value = "/save")
	public String saveTraining(Training training) {
		repository.save(training);
		return "redirect:trainings";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}")
	public String deleteTraining(@PathVariable("id") Long trainingId, Model model) {
		repository.deleteById(trainingId);
		return "redirect:../trainings";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editTraining(@PathVariable("id") Long trainingId, Model model) {
		model.addAttribute("training", repository.findById(trainingId));
		model.addAttribute("dayOfWeek", vrepository.findAll());
		return "editTraining";
	}
}