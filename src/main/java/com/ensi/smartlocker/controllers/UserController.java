package com.ensi.smartlocker.controllers;

import java.util.Calendar;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ensi.smartlocker.domain.LignePanier;
import com.ensi.smartlocker.domain.Panier;
import com.ensi.smartlocker.domain.User;
import com.ensi.smartlocker.domain.enumeration.EtatPanier;
import com.ensi.smartlocker.domain.enumeration.NiveauUser;
import com.ensi.smartlocker.domain.enumeration.TypeUser;
import com.ensi.smartlocker.services.LignePanierService;
import com.ensi.smartlocker.services.PanierService;
import com.ensi.smartlocker.services.UserService;

@Controller
@SessionAttributes({ "email", "nom", "type" })
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PanierService panierService;

	@Autowired
	private LignePanierService lignePanierService;

	public void setLignePanierService(LignePanierService lignePanierService) {
		this.lignePanierService = lignePanierService;
	}

	public void setPanierService(PanierService panierService) {
		this.panierService = panierService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// listing all users
	@RequestMapping(value = "/users_list", method = RequestMethod.GET)
	public String listeUsers(Model model, @ModelAttribute("pp") String pp) {
		model.addAttribute("users", userService.getAllUsers());
		model.addAttribute("types", TypeUser.values());
		model.addAttribute("niveaux", NiveauUser.values());
		model.addAttribute("pp", pp);
		return "users_list.html";
	}

	// listing all users
	@RequestMapping(value = "/users_list1", method = RequestMethod.GET)
	public String listeUsers1(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		model.addAttribute("types", TypeUser.values());
		model.addAttribute("niveaux", NiveauUser.values());
		model.addAttribute("pp", "this user's Card is not empty");
		return "users_list.html";
	}

	// Add new user
	@PostMapping("/saveUser")
	public String saveUser(User user) {

		Calendar calendar = Calendar.getInstance();
		user.setDateCreationUser(calendar);
		// userService.saveUser(user);
		Panier p = new Panier();
		p.setDateCreationPnier(calendar);
		p.setDateDerniereModification(calendar);
		p.setEtatpanier(EtatPanier.Actif);
		p.setUser(user);
		panierService.savePanier(p);

		return "redirect:/users_list";
	}

	@PostMapping("/editUser")
	public String editUser(User user, @RequestParam(value = "emailUser", required = true) String emailUser) {
		user.setDateCreationUser(userService.getUserById(emailUser).getDateCreationUser());
		userService.saveUser(user);
		return "redirect:/users_list";
	}

	@RequestMapping(value = "/paniers_users", method = RequestMethod.GET)
	public String listeborrowing(Model model) {
		model.addAttribute("paniers", lignePanierService.getAllLignePanier());
		return "paniers_users";
	}

	// delete user
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam(value = "emailUser", required = true) String emailUser) {
		Panier p = panierService.getPanierById(panierService.getPanierByUser(emailUser));
		Set<LignePanier> lps = p.getLignePaniers();
		if (lps.isEmpty()) {
			return "redirect:/users_list";
		} else {
			return "redirect:/users_list1";
		}

	}

	@RequestMapping(value = "/user_profile", method = RequestMethod.GET)
	public String userProfile(Model model, @ModelAttribute("email") String email, @ModelAttribute("type") String type) {
		model.addAttribute("user", userService.getRealUserById(email));
		model.addAttribute("types", TypeUser.values());
		model.addAttribute("niveaux", NiveauUser.values());
		if (type.equals("Admin"))
			return "user_profileAdmin";
		else
			return "user_profile";
	}

	@PostMapping("/editprofile")
	public String editProfile(@ModelAttribute("email") String emailUser, @ModelAttribute("type") String type,
			@RequestParam(value = "pass", required = true) String password,
			@RequestParam(value = "lastname", required = false) String nom,
			@RequestParam(value = "firstname", required = false) String prenom,
			@RequestParam(value = "tel", required = false) String tel,
			@RequestParam(value = "niveauUser", required = false) String niveau) {
		User user = userService.getRealUserById(emailUser);
		user.setNomUser(nom);
		user.setPrenomUser(prenom);
		user.setNumTelUser(tel);
		user.setPasswordUser(password);
		switch (niveau) {
		case "PreIng":
			user.setNiveauUser(NiveauUser.PreIng);
			break;
		case "DeuxIng":
			user.setNiveauUser(NiveauUser.DeuxIng);
			break;
		case "TroisIng":
			user.setNiveauUser(NiveauUser.TroisIng);
			break;
		case "PreMastere":
			user.setNiveauUser(NiveauUser.PreMastere);
			break;
		case "DeuxMastere":
			user.setNiveauUser(NiveauUser.DeuxMastere);
			break;
		case "Professeur":
			user.setNiveauUser(NiveauUser.Professeur);
			break;
		}
		user.setDateCreationUser(userService.getRealUserById(emailUser).getDateCreationUser());
		userService.saveUser(user);
		if (type.equals("Admin"))
			return "redirect:/indexAdmin";
		else
			return "redirect:/index";

	}

	@RequestMapping(value = "/user_card", method = RequestMethod.GET)
	public String userCard(Model model, @ModelAttribute("email") String email, @ModelAttribute("type") String type) {
		Panier p = panierService.getPanierById(panierService.getPanierByUser(email));
		Set<LignePanier> lps = p.getLignePaniers();
		model.addAttribute("lps", lps);
		if (type.equals("Admin"))
			return "user_cardAdmin";
		else
			return "user_card";
	}

}
