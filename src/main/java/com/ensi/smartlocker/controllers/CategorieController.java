package com.ensi.smartlocker.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ensi.smartlocker.domain.Categorie;
import com.ensi.smartlocker.domain.SousCategorie;
import com.ensi.smartlocker.repositories.CategorieRepository;
import com.ensi.smartlocker.services.CategorieService;
import com.ensi.smartlocker.services.SousCategorieService;

@Controller
public class CategorieController {

	@Autowired
	private CategorieService categorieService;
	@Autowired
	private SousCategorieService sousCategorieService;
	@Autowired
	private CategorieRepository categorieRepository;

	public void setCategorieRepository(CategorieRepository categorieRepository) {
		this.categorieRepository = categorieRepository;
	}

	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public void setSousCategorieService(SousCategorieService sousCategorieService) {
		this.sousCategorieService = sousCategorieService;
	}

	@GetMapping(value = "/Categories.html")
	public void AllCategories(Model model) {
		model.addAttribute("categories", categorieService.getAllCategories());
	}

	@RequestMapping(value = "/ajouter_materiel.html", method = RequestMethod.GET)
	public String createAppointmentPost(Model model, @ModelAttribute("categorie") Categorie categorie,
			@ModelAttribute("sousCategorie") SousCategorie sousCategorie) {
		Categorie categorie2 = new Categorie();
		model.addAttribute("Categorie", categorie2);
		model.addAttribute("categories", categorieService.getAllCategories());
		return "ajouter_materiel";
	}

	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody List<Categorie> findAllcata() {
		System.out.println("bech ne5ou les categories");
		return categorieRepository.findAll();
	}
	



}
