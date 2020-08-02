package com.ensi.smartlocker.controllers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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

import com.ensi.smartlocker.domain.Categorie;
import com.ensi.smartlocker.domain.FicheEmprunt;
import com.ensi.smartlocker.domain.LigneFicheEmprunt;
import com.ensi.smartlocker.domain.LignePanier;
import com.ensi.smartlocker.domain.Materiel;
import com.ensi.smartlocker.domain.Panier;
import com.ensi.smartlocker.domain.SousCategorie;
import com.ensi.smartlocker.domain.User;
import com.ensi.smartlocker.services.CategorieService;
import com.ensi.smartlocker.services.FicheEmpruntService;
import com.ensi.smartlocker.services.LigneFicheEmpruntService;
import com.ensi.smartlocker.services.LignePanierService;
import com.ensi.smartlocker.services.MaterielService;
import com.ensi.smartlocker.services.PanierService;
import com.ensi.smartlocker.services.SousCategorieService;
import com.ensi.smartlocker.services.UserService;

import ensi.smartlocker.test.Configuration.MyConstants;

@Controller
@SessionAttributes({ "email", "nom", "type" })
public class FicheEmpruntController {

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	private FicheEmpruntService ficheEmpruntService;

	@Autowired
	private LigneFicheEmpruntService ligneFicheEmpruntService;

	@Autowired
	private LignePanierService lignePanierService;

	@Autowired
	private CategorieService categorieService;

	@Autowired
	private SousCategorieService sousCategorieService;

	@Autowired
	private MaterielService materielService;

	@Autowired
	private PanierService panierService;

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setLignePanierService(LignePanierService lignePanierService) {
		this.lignePanierService = lignePanierService;
	}

	public void setPanierService(PanierService panierService) {
		this.panierService = panierService;
	}

	public void setMaterielService(MaterielService materielService) {
		this.materielService = materielService;
	}

	public void setLigneFicheEmpruntService(LigneFicheEmpruntService ligneFicheEmpruntService) {
		this.ligneFicheEmpruntService = ligneFicheEmpruntService;
	}

	public void setSousCategorieService(SousCategorieService sousCategorieService) {
		this.sousCategorieService = sousCategorieService;
	}

	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public void setFicheEmpruntService(FicheEmpruntService ficheEmpruntService) {
		this.ficheEmpruntService = ficheEmpruntService;
	}

	@GetMapping({ "new_borrowing" })
	public String newEmprunt(Model model, @ModelAttribute("type") String type) {
		model.addAttribute("categories", categorieService.getAllCategories());
		model.addAttribute("email");
		model.addAttribute("nom");
		if (type.equals("Admin"))
			return "new_borrowingAdmin";
		else
			return "new_borrowing";
	}

	@GetMapping({ "new_borrowingAdmin" })
	public String newEmpruntAdmin(Model model) {
		model.addAttribute("categories", categorieService.getAllCategories());
		model.addAttribute("email");
		model.addAttribute("nom");
		return "new_borrowingAdmin";
	}

	@RequestMapping(value = "/ajouter_emprunt/sousCategories", method = RequestMethod.GET)
	public @ResponseBody Set<SousCategorie> findAllSouscata(
			@RequestParam(value = "Categorie", required = true) String categorieName) {
		Categorie categorie = categorieService.getCategorieById(categorieName);
		return sousCategorieService.getSousCategoriesByCategorie(categorie);
	}

	@RequestMapping(value = "/ajouter_emprunt/sousCategories/materiels", method = RequestMethod.GET)
	public @ResponseBody Set<Materiel> findAllSouscataMAt(
			@RequestParam(value = "sousCatt", required = true) String souscat) {
		System.out.println(souscat);
		SousCategorie sousCategorie = sousCategorieService.getSousCategorieById(souscat);
		return sousCategorie.getMateriels();
	}

	@RequestMapping(value = "/borrowing_list", method = RequestMethod.GET)
	public String listeborrowing(Model model) {
		model.addAttribute("borrow", ligneFicheEmpruntService.getAllLigneFicheEmprunt());
		return "borrowing_list";
	}


	// Add new Emprunt
	@PostMapping("/saveEmprunt1")
	public String saveEmprunt(@ModelAttribute("type") String type,
			@RequestParam(value = "email", required = false) String email,
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
			System.out.println("pas d'emprunt");
			// ajouter le lien vers la page du materiel
		} else {
			// Creation d'un nouveau emprunt
			// Modifier le panier de l'utilisateur
			Calendar c = Calendar.getInstance();
			HashSet<String> set = new HashSet<String>();
			Map<String, Integer> hm = new HashMap<>();
			Map<Integer, Integer> listTotal = new HashMap<>();
			User user = userService.getUserById(email);
			FicheEmprunt e = new FicheEmprunt(c, user);
			ficheEmpruntService.saveFicheEmprunt(e);
			boolean test = false;

			// get du panier de l'utilisateur
			Panier p = panierService.getRealPanierById(panierService.getPanierByUser(email));
			System.out.println(p.getIdPanier());
			int q1, q2, q3, q4;
			if (!(mp.equals("vide")) && (Integer.parseInt(qp) != 0)) {
				set.add(mp);
				Materiel m = materielService.getMaterielByName(mp);
				if (Integer.parseInt(qp) > m.getQuantiteDisponibleeMateriel()) {
					q1 = m.getQuantiteDisponibleeMateriel();
				} else {
					q1 = Integer.parseInt(qp);
				}

				LigneFicheEmprunt lfe = new LigneFicheEmprunt(q1);
				lfe.setMateriel(m);
				lfe.setFicheEmprunt(e);
				ligneFicheEmpruntService.saveLigneFicheEmprunt(lfe);

				// gestion du panier
				// si l'utilisateur a deja emprunter le mm materiel
				for (LignePanier lp : p.getLignePaniers()) {
					if (lp.getMateriel().getDesignationMateriel().equalsIgnoreCase(m.getDesignationMateriel()))
						test = true;
				}
				if (test == true) {
					System.out.println("test true");
					LignePanier lp = lignePanierService.getLignePanierById(
							lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() + q1);
					lignePanierService.saveLignePanier(lp);
				} else {
					// sinon
					System.out.println("test false");
					LignePanier lp = new LignePanier();
					lp.setMateriel(m);
					lp.setQuantiteMateriel(q1);
					lp.setPanier(p);
					lp.setDateEmprunt(c);
					lignePanierService.saveLignePanier(lp);

				}

				// diminuer la quantite disponible pour ce materiel

				Materiel m1 = materielService.getRealMaterielById(m.getReferenceMateriel());
				m1.setQuantiteDisponibleeMateriel(m1.getQuantiteDisponibleeMateriel() - q1);
				materielService.saveMateriel(m1);

				hm.put(m.getDesignationMateriel(), q1);
				listTotal.put(m.getReferenceMateriel(), q1);

			}
			if (!(md.equals("vide")) && (Integer.parseInt(qd) != 0) && !(set.contains(md))) {
				set.add(md);
				Materiel m = materielService.getMaterielByName(md);
				if (Integer.parseInt(qd) > m.getQuantiteDisponibleeMateriel()) {
					q2 = m.getQuantiteDisponibleeMateriel();
				} else {
					q2 = Integer.parseInt(qd);
				}

				LigneFicheEmprunt lfe = new LigneFicheEmprunt(q2);
				lfe.setMateriel(m);
				lfe.setFicheEmprunt(e);
				ligneFicheEmpruntService.saveLigneFicheEmprunt(lfe);

				// gestion du panier
				// si l'utilisateur a deja emprunter le mm materiel
				test = false;
				for (LignePanier lp : p.getLignePaniers()) {
					if (lp.getMateriel().getDesignationMateriel().equalsIgnoreCase(m.getDesignationMateriel()))
						test = true;
				}
				if (test == true) {
					System.out.println("test true");
					LignePanier lp = lignePanierService.getLignePanierById(
							lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() + q2);
					lignePanierService.saveLignePanier(lp);
				} else {
					// sinon
					System.out.println("test false");
					LignePanier lp = new LignePanier();
					lp.setMateriel(m);
					lp.setQuantiteMateriel(q2);
					lp.setPanier(p);
					lp.setDateEmprunt(c);
					lignePanierService.saveLignePanier(lp);

				}

//diminuer la quantite disponible pour ce materiel

				Materiel m1 = materielService.getRealMaterielById(m.getReferenceMateriel());
				m1.setQuantiteDisponibleeMateriel(m1.getQuantiteDisponibleeMateriel() - q2);
				materielService.saveMateriel(m1);

				hm.put(m.getDesignationMateriel(), q2);
				listTotal.put(m.getReferenceMateriel(), q2);
			}
			if (!(mt.equals("vide")) && (Integer.parseInt(qt) != 0) && !(set.contains(mt))) {
				set.add(mt);
				Materiel m = materielService.getMaterielByName(mt);
				if (Integer.parseInt(qt) > m.getQuantiteDisponibleeMateriel()) {
					q3 = m.getQuantiteDisponibleeMateriel();
				} else {
					q3 = Integer.parseInt(qt);
				}

				LigneFicheEmprunt lfe = new LigneFicheEmprunt(q3);
				lfe.setMateriel(m);
				lfe.setFicheEmprunt(e);
				ligneFicheEmpruntService.saveLigneFicheEmprunt(lfe);

				// gestion du panier
				// si l'utilisateur a deja emprunter le mm materiel
				test = false;
				for (LignePanier lp : p.getLignePaniers()) {
					if (lp.getMateriel().getDesignationMateriel().equalsIgnoreCase(m.getDesignationMateriel()))
						test = true;
				}
				if (test == true) {
					System.out.println("test true");
					LignePanier lp = lignePanierService.getLignePanierById(
							lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() + q3);
					lignePanierService.saveLignePanier(lp);
				} else {
					// sinon
					System.out.println("test false");
					LignePanier lp = new LignePanier();
					lp.setMateriel(m);
					lp.setQuantiteMateriel(q3);
					lp.setPanier(p);
					lp.setDateEmprunt(c);
					lignePanierService.saveLignePanier(lp);

				}
				// diminuer la quantite disponible pour ce materiel

				Materiel m1 = materielService.getRealMaterielById(m.getReferenceMateriel());
				m1.setQuantiteDisponibleeMateriel(m1.getQuantiteDisponibleeMateriel() - q3);
				materielService.saveMateriel(m1);

				hm.put(m.getDesignationMateriel(), q3);
				listTotal.put(m.getReferenceMateriel(), q3);
			}
			if (!(mq.equals("vide")) && (Integer.parseInt(qq) != 0) && !(set.contains(mq))) {
				set.add(mq);
				Materiel m = materielService.getMaterielByName(mq);
				if (Integer.parseInt(qq) > m.getQuantiteDisponibleeMateriel()) {
					q4 = m.getQuantiteDisponibleeMateriel();
				} else {
					q4 = Integer.parseInt(qq);
				}

				LigneFicheEmprunt lfe = new LigneFicheEmprunt(q4);
				lfe.setMateriel(m);
				lfe.setFicheEmprunt(e);
				ligneFicheEmpruntService.saveLigneFicheEmprunt(lfe);

				// gestion du panier
				// si l'utilisateur a deja emprunter le mm materiel
				test = false;
				for (LignePanier lp : p.getLignePaniers()) {
					if (lp.getMateriel().getDesignationMateriel().equalsIgnoreCase(m.getDesignationMateriel()))
						test = true;
				}
				if (test == true) {
					System.out.println("test true");
					LignePanier lp = lignePanierService.getLignePanierById(
							lignePanierService.getRealLignePanierById(m.getReferenceMateriel(), p.getIdPanier()));
					lp.setQuantiteMateriel(lp.getQuantiteMateriel() + q4);
					lignePanierService.saveLignePanier(lp);
				} else {
					// sinon
					System.out.println("test false");
					LignePanier lp = new LignePanier();
					lp.setMateriel(m);
					lp.setQuantiteMateriel(q4);
					lp.setPanier(p);
					lp.setDateEmprunt(c);
					lignePanierService.saveLignePanier(lp);

				}

				// diminuer la quantite disponible pour ce materiel

				Materiel m1 = materielService.getRealMaterielById(m.getReferenceMateriel());
				m1.setQuantiteDisponibleeMateriel(m1.getQuantiteDisponibleeMateriel() - q4);
				materielService.saveMateriel(m1);

				hm.put(m.getDesignationMateriel(), q4);
				listTotal.put(m.getReferenceMateriel(), q4);

			}
//			// Creation du mail
//			String emailBody = "Bonjour Aya Hamdi, \n \n";
//			emailBody += "vous avez emprunté le matériel suivant:  \n";
//
//			// le materiel emprunté maintnant
//			Set<Entry<String, Integer>> setHm = hm.entrySet();
//			Iterator<Entry<String, Integer>> it = setHm.iterator();
//			while (it.hasNext()) {
//				Entry<String, Integer> e1 = it.next();
//				emailBody += "\t **Materiel: " + e1.getKey() + " | Quantité: " + e1.getValue() + ". \n";
//			}
//			emailBody += "voici l'état de votre panier:\n";
//			System.out.println(emailBody);
//
//			// parcour du panier de l'utilisateur
//
//			Iterable<LignePanier> lignespanies = lignePanierService.getAllLignePanier();
//			Map<String, Integer> lh = new HashMap<>();
//			for (LignePanier l : lignespanies) {
//				if (l.getPanier().getUser().getEmailUser().equalsIgnoreCase(email)) {
//					lh.put(l.getMateriel().getDesignationMateriel(), l.getQuantiteMateriel());
//				}
//			}
//
//			Set<Entry<String, Integer>> setlh = lh.entrySet();
//			Iterator<Entry<String, Integer>> itlh = setlh.iterator();
//			while (itlh.hasNext()) {
//				Entry<String, Integer> e2 = itlh.next();
//				emailBody += "\t **Materiel: " + e2.getKey() + " | Quantité: " + e2.getValue() + ". \n";
//			}
//			emailBody += "\nMerci pour votre emprunt.\r\n"
//					+ "En vous souhaitant cordialement une excellente journée.\r\n";
//			emailBody += "\nENSI SmartLocker";
//
//			System.out.println(emailBody);
//			// sendSimpleEmail(email, emailBody);
		}
		if (type.equals("Admin"))
			return "redirect:/indexAdmin";
		else
			return "redirect:/index";
	}

	public void sendSimpleEmail(String userEmail, String text) {

		// Create a Simple MailMessage.
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(userEmail);
		message.setSubject("E-mail d'emprunt");
		message.setText(text);

		// Send Message!
		this.emailSender.send(message);

	}

}
