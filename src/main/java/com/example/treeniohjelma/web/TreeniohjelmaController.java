package com.example.treeniohjelma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.treeniohjelma.domain.Treeni;
import com.example.treeniohjelma.domain.TreeniRepository;
import com.example.treeniohjelma.domain.ViikonpaivaRepository;

@Controller
public class TreeniohjelmaController {

	@Autowired
	private TreeniRepository repository;

	@Autowired
	private ViikonpaivaRepository vrepository;

	@RequestMapping(value = "/kirjautuminen")
	public String kirjautuminen() {
		return "kirjautuminen";
	}

	@RequestMapping(value = { "/", "/treenit" })
	public String treenit(Model model) {
		model.addAttribute("treenit", repository.findAll());
		return "treenit";
	}

	@RequestMapping(value = "/lisaa")
	public String lisaaTreeni(Model model) {
		model.addAttribute("treeni", new Treeni());
		model.addAttribute("viikonpaivat", vrepository.findAll());
		return "lisaaTreeni";
	}

	@RequestMapping(value = "/save")
	public String tallennaTiedot(Treeni treeni) {
		repository.save(treeni);
		return "redirect:treenit";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}")
	public String poistaTreeni(@PathVariable("id") Long treeniId, Model model) {
		repository.deleteById(treeniId);
		return "redirect:../treenit";
	}

	@RequestMapping(value = "/edit/{id}")
	public String muokkaaTreenia(@PathVariable("id") Long treeniId, Model model) {
		model.addAttribute("treeni", repository.findById(treeniId));
		model.addAttribute("viikonpaivat", vrepository.findAll());
		return "muokkaaTreenia";
	}
}