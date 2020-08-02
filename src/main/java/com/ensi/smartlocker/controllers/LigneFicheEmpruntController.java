package com.ensi.smartlocker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensi.smartlocker.domain.Materiel;
import com.ensi.smartlocker.services.MaterielService;

@Controller
public class LigneFicheEmpruntController {

	@Autowired
	private MaterielService materielService;

	public void setMaterielService(MaterielService materielService) {
		this.materielService = materielService;
	}

	@RequestMapping(value = "/ajouter_emprunt/add", method = RequestMethod.GET)
	public String quantitéParMateriel(@RequestParam(value = "materiel", required = true) String materielName) {

		Materiel materiel = materielService.getMaterielByName(materielName);
		return materielName;

	}

}
