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
		model.addAttribute("newuser", new NewUser());
		return "registeration";
	}

	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("newuser") NewUser newUser, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (newUser.getPassword().equals(newUser.getPasswordCheck())) {
				String password = newUser.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPassword = bc.encode(password);

				User user = new User();
				user.setPasswordHash(hashPassword);
				user.setUsername(newUser.getUsername());
				user.setRole("USER");
				if (krepository.findByUsername(newUser.getUsername()) == null) {
					krepository.save(user);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "registeration";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "registeration";
			}
		} else {
			return "registeration";
		}
		return "redirect:/login";
	}
}
