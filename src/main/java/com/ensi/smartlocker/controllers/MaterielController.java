package com.ensi.smartlocker.controllers;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ensi.smartlocker.domain.LignePanier;
import com.ensi.smartlocker.domain.Materiel;
import com.ensi.smartlocker.domain.SousCategorie;
import com.ensi.smartlocker.services.CategorieService;
import com.ensi.smartlocker.services.LignePanierService;
import com.ensi.smartlocker.services.MaterielService;
import com.ensi.smartlocker.services.SousCategorieService;

@Controller
public class MaterielController {

	private MaterielService materielService;
	private CategorieService categorieService;
	private SousCategorieService sousCategorieService;
	@Autowired
	private LignePanierService lignePanierService;

	@Autowired
	public void setSousCategorieService(SousCategorieService sousCategorieService) {
		this.sousCategorieService = sousCategorieService;
	}

	@Autowired
	public void setMaterielService(MaterielService materielService) {
		this.materielService = materielService;
	}

	@Autowired
	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public void setLignePanierService(LignePanierService lignePanierService) {
		this.lignePanierService = lignePanierService;
	}

	// listing all materials
	@RequestMapping(value = "/materials_list", method = RequestMethod.GET)
	public String listeMateriels(Model model) {
		model.addAttribute("Materiels", materielService.getAllMateriels());
		model.addAttribute("categories", categorieService.getAllCategories());
		return "materials_list";
	}

	// Add new Materiel
	@PostMapping("/saveMateriel")
	public String saveMateriel(Materiel materiel,
			@RequestParam(value = "sousCategorie", required = true) String sousCategorieName) {
		Calendar calendar = Calendar.getInstance();
		materiel.setDateCreationMateriel(calendar);
		materiel.setQuantiteDisponibleeMateriel(materiel.getQuantiteGeneraleMateriel());
		materielService.saveMateriel(materiel);
		SousCategorie sousCategorie = sousCategorieService.getSousCategorieById(sousCategorieName);
		Set<Materiel> materiels = sousCategorie.getMateriels();
		materiels.add(materiel);
		sousCategorieService.saveSousCategorie(sousCategorie);
		return "redirect:/materials_list.html";
	}

	// Edit material
	@PostMapping("/editMateriel")
	public String editMateriel(Materiel materiel, @RequestParam(value = "id", required = true) String id) {
		materiel.setReferenceMateriel(materielService.getMaterielById(Integer.parseInt(id)).getReferenceMateriel());
		materiel.setDateCreationMateriel(
				materielService.getMaterielById(Integer.parseInt(id)).getDateCreationMateriel());
		materielService.saveMateriel(materiel);

		return "redirect:/materials_list.html";
	}

	@RequestMapping(value = "/materials", method = RequestMethod.GET)
	public @ResponseBody List<Materiel> findAllMat() {
		return (List<Materiel>) materielService.getAllMateriels();
	}

	@RequestMapping(value = "/ajouter_emprunt/sousCategories/materiel/quatite", method = RequestMethod.GET)
	public @ResponseBody Materiel quantitéParMateriel(
			@RequestParam(value = "materiel", required = true) String materiel) {
		return materielService.getMaterielByName(materiel);
	}

	// Edit material
	@PostMapping("/deleteMateriel")
	public String deleteMateriel(Materiel materiel, @RequestParam(value = "id", required = true) String id) {
		materiel.setReferenceMateriel(materielService.getMaterielById(Integer.parseInt(id)).getReferenceMateriel());
		materiel.setDateCreationMateriel(
				materielService.getMaterielById(Integer.parseInt(id)).getDateCreationMateriel());
		materielService.saveMateriel(materiel);

		return "redirect:/materials_list.html";
	}

	// delete Material
	@GetMapping("/deleteMaterial")
	public String deleteUser(@RequestParam(value = "ref", required = true) String ref, Model model) {
		Set<LignePanier> lps = lignePanierService
				.getLPByMaterial(materielService.getMaterielById(Integer.parseInt(ref)));

		if (lps.isEmpty()) {
			return "redirect:/materials_list";
		} else {
			return "redirect:/materials_list1";
		}

	}

	@RequestMapping(value = "/materials_list1", method = RequestMethod.GET)
	public String listeMateriels1(Model model) {
		model.addAttribute("Materiels", materielService.getAllMateriels());
		model.addAttribute("categories", categorieService.getAllCategories());
		model.addAttribute("pp", "This material is being borrowed, so we can't delete it !!! ");
		return "materials_list";
	}

}
