package com.ensi.smartlocker.controllers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ensi.smartlocker.domain.FicheDemande;
import com.ensi.smartlocker.domain.FicheEmprunt;
import com.ensi.smartlocker.domain.LigneFicheDemande;
import com.ensi.smartlocker.domain.LigneFicheEmprunt;
import com.ensi.smartlocker.domain.LignePanier;
import com.ensi.smartlocker.domain.Materiel;
import com.ensi.smartlocker.domain.Panier;
import com.ensi.smartlocker.domain.User;
import com.ensi.smartlocker.domain.enumeration.EtatDemande;
import com.ensi.smartlocker.domain.enumeration.StatusMateriel;
import com.ensi.smartlocker.services.CategorieService;
import com.ensi.smartlocker.services.FicheDemandeService;
import com.ensi.smartlocker.services.LigneFicheDemandeService;
import com.ensi.smartlocker.services.MaterielService;
import com.ensi.smartlocker.services.UserService;

@Controller
@SessionAttributes({ "email", "nom" })
public class FicheDemandeController {

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	private MaterielService materielService;

	@Autowired
	private UserService userService;

	@Autowired
	private FicheDemandeService ficheDemandeService;

	@Autowired
	private LigneFicheDemandeService ligneFicheDemandeService;

	public void setLigneFicheDemandeService(LigneFicheDemandeService ligneFicheDemandeService) {
		this.ligneFicheDemandeService = ligneFicheDemandeService;
	}

	public void setFicheDemandeService(FicheDemandeService ficheDemandeService) {
		this.ficheDemandeService = ficheDemandeService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setMaterielService(MaterielService materielService) {
		this.materielService = materielService;
	}

	@GetMapping({ "new_request" })
	public String newEmprunt(Model model,@ModelAttribute("email") String email) {
		model.addAttribute("materials", materielService.allMaterialOutOfStock());
		model.addAttribute("nom");
		return "new_request";
	}
	
	@GetMapping({ "new_requestAdmin" })
	public String newEmpruntAdmin(Model model,@ModelAttribute("email") String email) {
		model.addAttribute("materials", materielService.allMaterialOutOfStock());
		model.addAttribute("nom");
		return "new_requestAdmin";
	}

	@RequestMapping(value = "/materiel/quatite", method = RequestMethod.GET)
	public @ResponseBody Materiel quantitéParMateriel(
			@RequestParam(value = "materiel", required = true) String materiel) {
		return materielService.getMaterielByName(materiel);
	}

	// Add new request
	@PostMapping("/saveRequest")
	public String saveEmprunt(/* @RequestParam(value = "email", required = false) String email, */
			@RequestParam(value = "materielp", required = false, defaultValue = "vide") String mp,
			@RequestParam(value = "quantitep", required = false, defaultValue = "0") String qp,
			@RequestParam(value = "materield", required = false, defaultValue = "vide") String md,
			@RequestParam(value = "quantited", required = false, defaultValue = "0") String qd,
			@RequestParam(value = "materielt", required = false, defaultValue = "vide") String mt,
			@RequestParam(value = "quantitet", required = false, defaultValue = "0") String qt,
			@RequestParam(value = "materielq", required = false, defaultValue = "vide") String mq,
			@RequestParam(value = "quantiteq", required = false, defaultValue = "0") String qq) {

		if (((mp.equals("vide")) && (md.equals("vide")) && (mt.equals("vide")) && (mq.equals("vide")))
				|| ((qp.equals("0")) && (qd.equals("0")) && (qt.equals("0")) && (qq.equals("0")))) {
			System.out.println("empty request");
		} else {

			Calendar c = Calendar.getInstance();
			User user = userService.getUserById("rania.boltane@ensi-uma.tn");
			FicheDemande d = new FicheDemande(c, user, EtatDemande.EnAttente);
			HashSet<String> set = new HashSet<String>();
			int q1, q2, q3, q4;

			if (!(mp.equals("vide")) && (Integer.parseInt(qp) > 0)) {
				set.add(mp);
				Materiel m = materielService.getMaterielByName(mp);
				if (Integer.parseInt(qp) > 4) {
					q1 = 4;
				} else {
					q1 = Integer.parseInt(qp);
				}
				LigneFicheDemande ld = new LigneFicheDemande(q1);
				ld.setMateriel(m);
				ld.setFicheDemande(d);
				ld.setStatusMateriel(StatusMateriel.Indisponible);
				ligneFicheDemandeService.saveLigneFicheDemande(ld);
			}
			if (!(md.equals("vide")) && (Integer.parseInt(qd) > 0) && !(set.contains(md))) {
				set.add(md);
				Materiel m = materielService.getMaterielByName(md);
				if (Integer.parseInt(qd) > 4) {
					q2 = 4;
				} else {
					q2 = Integer.parseInt(qd);
				}
				LigneFicheDemande ld = new LigneFicheDemande(q2);
				ld.setMateriel(m);
				ld.setFicheDemande(d);
				ld.setStatusMateriel(StatusMateriel.Indisponible);
				ligneFicheDemandeService.saveLigneFicheDemande(ld);
			}
			if (!(mt.equals("vide")) && (Integer.parseInt(qd) > 0) && !(set.contains(mt))) {
				set.add(mt);
				Materiel m = materielService.getMaterielByName(mt);
				if (Integer.parseInt(qd) > 4) {
					q2 = 4;
				} else {
					q2 = Integer.parseInt(qd);
				}
				LigneFicheDemande ld = new LigneFicheDemande(q2);
				ld.setMateriel(m);
				ld.setFicheDemande(d);
				ld.setStatusMateriel(StatusMateriel.Indisponible);
				ligneFicheDemandeService.saveLigneFicheDemande(ld);
			}
			if (!(mq.equals("vide")) && (Integer.parseInt(qd) > 0) && !(set.contains(mq))) {
				set.add(mq);
				Materiel m = materielService.getMaterielByName(mq);
				if (Integer.parseInt(qd) > 4) {
					q2 = 4;
				} else {
					q2 = Integer.parseInt(qd);
				}
				LigneFicheDemande ld = new LigneFicheDemande(q2);
				ld.setMateriel(m);
				ld.setFicheDemande(d);
				ld.setStatusMateriel(StatusMateriel.Indisponible);
				ligneFicheDemandeService.saveLigneFicheDemande(ld);
			}

		}
		return "redirect:/index.html";
	}
	
	
	@GetMapping({ "demandes_list" })
	public String listeborrowing1(Model model) {
		model.addAttribute("demandes", ligneFicheDemandeService.getAllLigneFicheDemande());
		return "demandes_list2";
	}



}
