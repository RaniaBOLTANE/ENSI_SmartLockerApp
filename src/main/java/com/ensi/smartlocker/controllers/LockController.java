package com.ensi.smartlocker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ensi.smartlocker.domain.Casier;
import com.ensi.smartlocker.domain.Materiel;
import com.ensi.smartlocker.services.CasierService;
import com.ensi.smartlocker.services.MaterielService;

@Controller
@SessionAttributes({ "email", "nom" })
public class LockController {

	@Autowired
	private CasierService casierService;

	@Autowired
	private MaterielService materielService;

	public void setCasierService(CasierService casierService) {
		this.casierService = casierService;
	}

	public void setMaterielService(MaterielService materielService) {
		this.materielService = materielService;
	}

	@RequestMapping(value = "/lock", method = RequestMethod.GET)
	public String lock(Model model) {
		model.addAttribute("lock", casierService.getAllCasiers());
		return "lock";
	}

	@PostMapping({ "returnlock" })
	public String newReturnSave(
			@RequestParam(value = "materiel1", required = false, defaultValue = "vide") String materiel1,
			@RequestParam(value = "materiel2", required = false, defaultValue = "vide") String materiel2,
			@RequestParam(value = "materiel3", required = false, defaultValue = "vide") String materiel3,
			@RequestParam(value = "materiel4", required = false, defaultValue = "vide") String materiel4,
			@RequestParam(value = "materiel5", required = false, defaultValue = "vide") String materiel5,
			@RequestParam(value = "materiel6", required = false, defaultValue = "vide") String materiel6,
			@RequestParam(value = "materiel7", required = false, defaultValue = "vide") String materiel7,
			@RequestParam(value = "materiel8", required = false, defaultValue = "vide") String materiel8,
			@RequestParam(value = "q1", required = false, defaultValue = "0") String q1,
			@RequestParam(value = "q2", required = false, defaultValue = "0") String q2,
			@RequestParam(value = "q3", required = false, defaultValue = "0") String q3,
			@RequestParam(value = "q4", required = false, defaultValue = "0") String q4,
			@RequestParam(value = "q5", required = false, defaultValue = "0") String q5,
			@RequestParam(value = "q6", required = false, defaultValue = "0") String q6,
			@RequestParam(value = "q7", required = false, defaultValue = "0") String q7,
			@RequestParam(value = "q8", required = false, defaultValue = "0") String q8) {

		if ((q1.equals("0")) && (q2.equals("0")) && (q3.equals("0")) && (q4.equals("0")) && (q5.equals("0"))
				&& (q6.equals("0")) && (q7.equals("0")) && (q8.equals("0"))) {
			System.out.println("mafamech materiel a rendre");
		} else {

			if (!(q1.equals("0")) && !(q1.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel1);
				System.out.println(m.getDesignationMateriel());
				int q = Integer.parseInt(q1);
				m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
				Casier c = casierService.getRealCasierByRef(m.getReferenceMateriel());
				casierService.deleteCasierById(c.getIdLock());

			}
			if (!(q2.equals("0")) && !(q2.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel2);
				int q = Integer.parseInt(q2);
				m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
			}
			if (!(q3.equals("0")) && !(q3.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel3);
				int q = Integer.parseInt(q3);
				m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
			}
			if (!(q4.equals("0")) && !(q4.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel4);
				int q = Integer.parseInt(q4);
				m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
			}
			if (!(q5.equals("0")) && !(q5.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel5);
				int q = Integer.parseInt(q5);
				m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
			}
			if (!(q6.equals("0")) && !(q6.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel6);
				int q = Integer.parseInt(q6);
				m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
			}
			if (!(q7.equals("0")) && !(q7.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel7);
				int q = Integer.parseInt(q7);
				m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
			}
			if (!(q8.equals("0")) && !(q8.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel8);
				int q = Integer.parseInt(q8);
				m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
			}

		}

		return "redirect:/indexAdmin";

	}

}
