package com.example.trainingprogram.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.trainingprogram.domain.NewUser;
import com.example.trainingprogram.domain.User;
import com.example.trainingprogram.domain.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository krepository;

	@RequestMapping(value = "registeration")
	public String addUser(Model model) {
		model.addAttribute("registerUser", new NewUser());
		return "registeration";
	}

	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("registerUser") NewUser registerUser,
			BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (registerUser.getPassword().equals(registerUser.getPasswordCheck())) {
				String password = registerUser.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPassword = bc.encode(password);

				User newUser = new User();
				newUser.setPasswordHash(hashPassword);
				newUser.setUsername(registerUser.getUsername());
				newUser.setRole("USER");
				if (krepository.findByUsername(registerUser.getUsername()) == null) {
					krepository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username",
							"Username already exists");
					return "registeration";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passwordCheck", "Passwords does not match");
			}
		} else {
			return "registeration";
		}
		return "redirect:/login";
	}
}
