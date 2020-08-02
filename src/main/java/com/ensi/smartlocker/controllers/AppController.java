package com.ensi.smartlocker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({ "email", "nom" })
public class AppController {


	@GetMapping({ "/index0" })
	public String index0() {
		return "index0";
	}

	@GetMapping({ "/index" })
	public String index(Model model, @ModelAttribute("email") String email) {
		model.addAttribute("nom");
		return "index";
	}

	@GetMapping({ "/indexAdmin" })
	public String indexAdmin() {
		return "indexAdmin";
	}

}
