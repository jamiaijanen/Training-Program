package com.example.trainingprogram.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Training training) {
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