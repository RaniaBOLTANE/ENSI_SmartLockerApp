package com.ensi.smartlocker.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ensi.smartlocker.domain.SousCategorie;
import com.ensi.smartlocker.services.CategorieService;
import com.ensi.smartlocker.services.SousCategorieService;

@Controller
public class SousCategorieController {

	@Autowired
	private SousCategorieService sousCategorieService;

	@Autowired
	private CategorieService categorieService;

	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public void setSousCategorieService(SousCategorieService sousCategorieService) {
		this.sousCategorieService = sousCategorieService;
	}

	@RequestMapping(value ="/fromCatToSousCat", method = RequestMethod.GET)
	public String findAllSousCatByCategorie(@RequestParam(value = "categorie", required = true) String categorieName,
			Model model) {
		model.addAttribute("categorie", categorieName);
		return "liste_souscategorie";
	}

	@RequestMapping(path = "/consulterSousCategorieParCategorie/{name}", method = RequestMethod.GET)
	public @ResponseBody Set<SousCategorie> AllSousCatByCategorie(@PathVariable("name") String name) {
		return sousCategorieService.getSousCategoriesByCategorie(categorieService.getCategorieById(name));
	}

}
