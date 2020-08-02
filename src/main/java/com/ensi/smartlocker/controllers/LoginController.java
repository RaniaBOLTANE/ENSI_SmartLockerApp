package com.ensi.smartlocker.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ensi.smartlocker.domain.User;
import com.ensi.smartlocker.services.UserService;

@Controller
@SessionAttributes({ "email", "nom", "type" })
public class LoginController {

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@PostMapping("/loginUser")
	public String saveMateriel(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "pass", required = true) String password, Model model) {

		User user = userService.getUserById(email);
		if (user.getPasswordUser().equals(password)) {
			model.addAttribute("email", email);
			String nom = user.getPrenomUser() + " " + user.getNomUser().toUpperCase();
			String type = user.getTypeUser().name();
			model.addAttribute("nom", nom);
			model.addAttribute("type", type);
			if (type.equals("Admin"))
				return "redirect:/indexAdmin";
			else
				return "redirect:/index";
		} else {
			System.out.println("mch s7i7");
			return "redirect:/index0";
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String invalidate(HttpSession session, Model model) {
		session.invalidate();
		model.asMap().clear();
		return "redirect:/index0";
	}

}
