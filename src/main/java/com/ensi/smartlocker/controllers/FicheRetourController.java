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

import com.ensi.smartlocker.domain.Casier;
import com.ensi.smartlocker.domain.FicheRetour;
import com.ensi.smartlocker.domain.LigneFicheRetour;
import com.ensi.smartlocker.domain.LignePanier;
import com.ensi.smartlocker.domain.Materiel;
import com.ensi.smartlocker.domain.Panier;
import com.ensi.smartlocker.domain.User;
import com.ensi.smartlocker.services.CasierService;
import com.ensi.smartlocker.services.FicheRetourService;
import com.ensi.smartlocker.services.LigneFicheRetourService;
import com.ensi.smartlocker.services.LignePanierService;
import com.ensi.smartlocker.services.MaterielService;
import com.ensi.smartlocker.services.PanierService;
import com.ensi.smartlocker.services.UserService;

@Controller
@SessionAttributes({ "email", "nom", "type" })
public class FicheRetourController {

	@Autowired
	private FicheRetourService ficheRetourService;

	@Autowired
	private CasierService casierService;

	@Autowired
	private MaterielService materielService;

	@Autowired
	private PanierService panierService;

	@Autowired
	private LignePanierService lignePanierService;

	@Autowired
	private LigneFicheRetourService ligneFicheRetourService;

	@Autowired
	private UserService userService;

	public void setCasierService(CasierService casierService) {
		this.casierService = casierService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setLignePanierService(LignePanierService lignePanierService) {
		this.lignePanierService = lignePanierService;
	}

	public void setMaterielService(MaterielService materielService) {
		this.materielService = materielService;
	}

	public void setFicheRetourService(FicheRetourService ficheRetourService) {
		this.ficheRetourService = ficheRetourService;
	}

	public void setPanierService(PanierService panierService) {
		this.panierService = panierService;
	}

	public void setLigneFicheRetourService(LigneFicheRetourService ligneFicheRetourService) {
		this.ligneFicheRetourService = ligneFicheRetourService;
	}

	@GetMapping({ "return_material" })
	public String newReturn(Model model, @ModelAttribute("email") String email) {
		Panier p = panierService.getPanierById(panierService.getPanierByUser(email));
		model.addAttribute("lps", p.getLignePaniers());
		model.addAttribute("nom");
		return "return_material";
	}

	@GetMapping({ "return_materialAdmin" })
	public String newReturnAdmin(Model model, @ModelAttribute("email") String email) {
		Panier p = panierService.getPanierById(panierService.getPanierByUser(email));
		model.addAttribute("lps", p.getLignePaniers());
		model.addAttribute("nom");
		return "return_materialAdmin";
	}

	@RequestMapping(value = "/returned_materials", method = RequestMethod.GET)
	public String listeReturnedMAterial(Model model) {
		model.addAttribute("return", ligneFicheRetourService.getAllLigneFicheRetour());
		return "returned_materials";
	}

	@PostMapping({ "saveRetrun" })
	public String newReturnSave(@ModelAttribute("type") String type,
			@ModelAttribute("email") String email,
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
			// creation of a new return object
			Calendar c = Calendar.getInstance();
			User user = userService.getUserById(email);
			FicheRetour fr = new FicheRetour(c, user);
			ficheRetourService.saveFicheRetour(fr);
			
			

			Panier p = panierService.getPanierById(panierService.getPanierByUser(email));
			Set<LignePanier> lps = p.getLignePaniers();

			if (!(q1.equals("0")) && !(q1.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel1);
				LignePanier lp = lignePanierService.getLignePanierById(
						lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
				int q = Integer.parseInt(q1);
				if (q >= lp.getQuantiteMateriel()) {
					System.out.println("sup ou egale");
					q = lp.getQuantiteMateriel();
					lignePanierService.deleteLignePanierById(lp.getIdLigne());
				}else {
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() - q);
					lignePanierService.saveLignePanier(lp);
				}
				//m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
				LigneFicheRetour lf = new LigneFicheRetour(q);
				lf.setMateriel(m);
				lf.setFicheRetour(fr);
				ligneFicheRetourService.saveLigneFicheRetour(lf);
				
				// creation d'un nouveau objet de type casier
				Casier cs = new Casier(m, q, c);
				casierService.saveCasier(cs);
				
			}  if (!(q2.equals("0")) && !(q2.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel2);
				LignePanier lp = lignePanierService.getLignePanierById(
						lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
				int q = Integer.parseInt(q2);
				if (q >= lp.getQuantiteMateriel()) {
					System.out.println("sup ou egale");
					q = lp.getQuantiteMateriel();
					lignePanierService.deleteLignePanierById(lp.getIdLigne());
				}else {
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() - q);
					lignePanierService.saveLignePanier(lp);
				}
				//m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
				LigneFicheRetour lf = new LigneFicheRetour(q);
				lf.setMateriel(m);
				lf.setFicheRetour(fr);
				ligneFicheRetourService.saveLigneFicheRetour(lf);
				
				Casier cs = new Casier(m, q, c);
				casierService.saveCasier(cs);
				
			}  if (!(q3.equals("0")) && !(q3.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel3);
				LignePanier lp = lignePanierService.getLignePanierById(
						lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
				int q = Integer.parseInt(q3);
				if (q >= lp.getQuantiteMateriel()) {
					System.out.println("sup ou egale");
					q = lp.getQuantiteMateriel();
					lignePanierService.deleteLignePanierById(lp.getIdLigne());
				}else {
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() - q);
					lignePanierService.saveLignePanier(lp);
				}
				//m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
				LigneFicheRetour lf = new LigneFicheRetour(q);
				lf.setMateriel(m);
				lf.setFicheRetour(fr);
				ligneFicheRetourService.saveLigneFicheRetour(lf);
				
				Casier cs = new Casier(m, q, c);
				casierService.saveCasier(cs);
				
			}  if (!(q4.equals("0")) && !(q4.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel4);
				LignePanier lp = lignePanierService.getLignePanierById(
						lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
				int q = Integer.parseInt(q4);
				if (q >= lp.getQuantiteMateriel()) {
					System.out.println("sup ou egale");
					q = lp.getQuantiteMateriel();
					lignePanierService.deleteLignePanierById(lp.getIdLigne());
				}else {
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() - q);
					lignePanierService.saveLignePanier(lp);
				}
				//m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
				LigneFicheRetour lf = new LigneFicheRetour(q);
				lf.setMateriel(m);
				lf.setFicheRetour(fr);
				ligneFicheRetourService.saveLigneFicheRetour(lf);
				
				Casier cs = new Casier(m, q, c);
				casierService.saveCasier(cs);
				
			}  if (!(q5.equals("0")) && !(q5.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel5);
				LignePanier lp = lignePanierService.getLignePanierById(
						lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
				int q = Integer.parseInt(q5);
				if (q >= lp.getQuantiteMateriel()) {
					System.out.println("sup ou egale");
					q = lp.getQuantiteMateriel();
					lignePanierService.deleteLignePanierById(lp.getIdLigne());
				}else {
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() - q);
					lignePanierService.saveLignePanier(lp);
				}
				//m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
				LigneFicheRetour lf = new LigneFicheRetour(q);
				lf.setMateriel(m);
				lf.setFicheRetour(fr);
				ligneFicheRetourService.saveLigneFicheRetour(lf);
				
				Casier cs = new Casier(m, q, c);
				casierService.saveCasier(cs);
				
			}  if (!(q6.equals("0")) && !(q6.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel6);
				LignePanier lp = lignePanierService.getLignePanierById(
						lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
				int q = Integer.parseInt(q6);
				if (q >= lp.getQuantiteMateriel()) {
					System.out.println("sup ou egale");
					q = lp.getQuantiteMateriel();
					System.out.println(lp.getIdLigne());
					lignePanierService.deleteLignePanierById(lp.getIdLigne());
				}else {
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() - q);
					lignePanierService.saveLignePanier(lp);
				}
				//m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
				LigneFicheRetour lf = new LigneFicheRetour(q);
				lf.setMateriel(m);
				lf.setFicheRetour(fr);
				ligneFicheRetourService.saveLigneFicheRetour(lf);
				
				Casier cs = new Casier(m, q, c);
				casierService.saveCasier(cs);
				
			}  if (!(q7.equals("0")) && !(q7.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel7);
				LignePanier lp = lignePanierService.getLignePanierById(
						lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
				int q = Integer.parseInt(q7);
				if (q >= lp.getQuantiteMateriel()) {
					System.out.println("sup ou egale");
					q = lp.getQuantiteMateriel();
					lignePanierService.deleteLignePanierById(lp.getIdLigne());
				}else {
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() - q);
					lignePanierService.saveLignePanier(lp);
				}
				//m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
				LigneFicheRetour lf = new LigneFicheRetour(q);
				lf.setMateriel(m);
				lf.setFicheRetour(fr);
				ligneFicheRetourService.saveLigneFicheRetour(lf);
				
				Casier cs = new Casier(m, q, c);
				casierService.saveCasier(cs);
				
			}  if (!(q8.equals("0")) && !(q8.contains("-"))) {
				Materiel m = materielService.getMaterielByName(materiel8);
				LignePanier lp = lignePanierService.getLignePanierById(
						lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
				int q = Integer.parseInt(q8);
				if (q >= lp.getQuantiteMateriel()) {
					System.out.println("sup ou egale");
					q = lp.getQuantiteMateriel();
					lignePanierService.deleteLignePanierById(lp.getIdLigne());
				}else {
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() - q);
					lignePanierService.saveLignePanier(lp);
				}
				//m.setQuantiteDisponibleeMateriel(m.getQuantiteDisponibleeMateriel() + q);
				LigneFicheRetour lf = new LigneFicheRetour(q);
				lf.setMateriel(m);
				lf.setFicheRetour(fr);
				ligneFicheRetourService.saveLigneFicheRetour(lf);
				
				Casier cs = new Casier(m, q, c);
				casierService.saveCasier(cs);
			}

		}
		if (type.equals("Admin"))
			return "redirect:/indexAdmin";
		else
			return "redirect:/index";
		
	}

}
