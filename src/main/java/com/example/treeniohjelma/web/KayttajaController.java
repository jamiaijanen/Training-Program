package com.example.treeniohjelma.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.treeniohjelma.domain.NewUser;
import com.example.treeniohjelma.domain.User;
import com.example.treeniohjelma.domain.UserRepository;

@Controller
public class KayttajaController {

	@Autowired
	private UserRepository krepository;

	@RequestMapping(value = "rekisteroituminen")
	public String lisaaKayttaja(Model model) {
		model.addAttribute("rekisteroikayttaja", new NewUser());
		return "rekisteroituminen";
	}

	@RequestMapping(value = "tallennakayttaja", method = RequestMethod.POST)
	public String tallenna(@Valid @ModelAttribute("rekisteroikayttaja") NewUser rekisteroiKayttaja,
			BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (rekisteroiKayttaja.getPassword().equals(rekisteroiKayttaja.getPasswordCheck())) {
				String salasana = rekisteroiKayttaja.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashSalasana = bc.encode(salasana);

				User uusiKayttaja = new User();
				uusiKayttaja.setPasswordHash(hashSalasana);
				uusiKayttaja.setUsername(rekisteroiKayttaja.getUsername());
				uusiKayttaja.setRole("USER");
				if (krepository.findByUsername(rekisteroiKayttaja.getUsername()) == null) {
					krepository.save(uusiKayttaja);
				} else {
					bindingResult.rejectValue("kayttajatunnus", "err.kayttajatunnus",
							"Kyseinen kayttajatunnus on jo olemassa");
					return "rekisteroituminen";
				}
			} else {
				bindingResult.rejectValue("salasanaTarkistus", "err.salasanaTarkistus", "Salasanat eivät täsmää");
			}
		} else {
			return "rekisteroityminen";
		}
		return "redirect:/login";
	}
}
