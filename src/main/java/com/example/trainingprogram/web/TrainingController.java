package com.example.trainingprogram.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.trainingprogram.domain.DayOfWeekRepository;
import com.example.trainingprogram.domain.Training;
import com.example.trainingprogram.domain.TrainingRepository;

@Service
@Controller
public class TrainingController {

	@Autowired
	private TrainingRepository repository;

	@Autowired
	private DayOfWeekRepository vrepository;

//	@Autowired
//	private UserRepository urepository;

	// returns login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// display all trainings saved to database
	@RequestMapping(value = { "/", "/trainings" })
	public String trainings(Model model) {
		model.addAttribute("trainings", repository.findAll());
		return "trainings";
	}

	// add new training to database
	@RequestMapping(value = "/add")
	public String addTraining(Model model) {
		model.addAttribute("training", new Training());
		model.addAttribute("dayOfWeek", vrepository.findAll());
		return "addTraining";
	}

	// save new training to database
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Training training) {
		repository.save(training);
		return "redirect:trainings";
	}

	// delete training from database
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}")
	public String deleteTraining(@PathVariable("id") Long trainingId, Model model) {
		repository.deleteById(trainingId);
		return "redirect:../trainings";
	}

	// edit saved training from database
	@RequestMapping(value = "/edit/{id}")
	public String editTraining(@PathVariable("id") Long trainingId, Model model) {
		model.addAttribute("training", repository.findById(trainingId));
		model.addAttribute("dayOfWeek", vrepository.findAll());
		return "editTraining";
	}

	// REST returns all trainings
	@RequestMapping(value = "/resttrainings")
	public @ResponseBody List<Training> traininglistREST() {
		return (List<Training>) repository.findAll();
	}

	// REST returns 1 training by id
	@RequestMapping(value = "/resttrainings/{id}")
	public @ResponseBody Optional<Training> findTrainingsREST(@PathVariable("id") Long trainingId) {
		return repository.findById(trainingId);
	}

//	@RequestMapping(value = "/username")
//	@ResponseBody
//	public String currentUsername(Principal principal) {
//		return principal.getName();
//	}

//	@RequestMapping(value = "/trainings")
//	public String user(Model model, Principal principal) {
//		model.addAttribute("trainings", repository.findByUser(urepository.findByUsername(principal.getName())));
//		return "train";
//	}
}