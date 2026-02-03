package fr.iutrodez.roadeo.web.controleur;

import fr.iutrodez.roadeo.modele.*;
import fr.iutrodez.roadeo.service.ObjetService;
import fr.iutrodez.roadeo.service.RandonneeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class AjoutObjetSacControleur {

    private final ObjetService objetService;
    private final RandonneeService randonneeService;

    // Attention : ces variables d'instance devraient idéalement être en Session
    // ou gérées via une base de données pour éviter les conflits entre utilisateurs.
    private Randonnee rando;
    private List<Produits> objetSac;
    private List<Produits> produitsCategorie = new ArrayList<>();
    private SacADos sacReserve;
    private String categorieSelectionne;
    private Produits detailProduit;
    private List<String> categories;
    private double poidsMax;
    private double kcalMax;
    private double poidsTotal;
    private double kcalTotal;


    public AjoutObjetSacControleur(ObjetService serviceO, RandonneeService serviceR) {
        this.objetService = serviceO;
        this.randonneeService = serviceR;

        // Initialisation (Simulation d'un ID "2")
        this.rando = randonneeService.getRandonnee("1");
        this.poidsMax = rando.poidsMax()*1000;
        this.sacReserve = new SacADos(poidsMax*1.20, 0);

        this.objetSac = objetService.recupListeObjet();
        this.categories = objetService.listeCategorie();

        if (!categories.isEmpty()) {
            this.categorieSelectionne = categories.get(0);
            updateProduitsCategorie(); // Met à jour la liste filtrée
        }


        this.kcalMax =  500;//parcours.calculKcalTotal(1);
    }

    @GetMapping("/ajoutObjet")
    public String afficherSac(Model model) {
        // Mise à jour des totaux avant affichage
        double prixTotal = sacReserve.getContenu().stream().mapToDouble(Produits::getPrix).sum();
        kcalTotal = sacReserve.getContenu().stream().mapToDouble(Produits::getNutrition).sum();
        poidsTotal = sacReserve.poidsTotal();

        model.addAttribute("objetsSac", sacReserve.getContenu());
        model.addAttribute("poidsTotal", poidsTotal);
        model.addAttribute("poidsMax", poidsMax);
        model.addAttribute("caloriesTotal", kcalTotal);
        model.addAttribute("caloriesRequise",kcalMax);
        model.addAttribute("prixTotal", prixTotal);

        model.addAttribute("typesObjets", categories);
        model.addAttribute("categorieSelectionne", categorieSelectionne);
        model.addAttribute("objetsListe", produitsCategorie);
        model.addAttribute("objetsDetail", detailProduit);

        return "ajoutObjet";
    }

    @PostMapping("/sac/ajouter")
    public String ajouterObjet(@RequestParam String idObjet) {
        Produits aAjouter = objetService.getProduitById(idObjet);
        if (aAjouter != null) {
            sacReserve.addObjet(aAjouter);
        }
        return "redirect:/ajoutObjet";
    }

    @PostMapping("/sac/supprimer")
    public String supprimerObjet(@RequestParam String idObjet) {
        sacReserve.supprimeProduitsById(idObjet);
        return "redirect:/ajoutObjet";
    }

    @GetMapping("/sac/selectionnerType")
    public String selectionnerType(@RequestParam(required = false) String categorie) {
        if (categorie != null) {
            this.categorieSelectionne = categorie;
            updateProduitsCategorie();
            // On réinitialise le détail si on change de catégorie
            this.detailProduit = produitsCategorie.isEmpty() ? null : produitsCategorie.get(0);
        }
        return "redirect:/ajoutObjet";
    }

    @GetMapping("/sac/selectionnerObjet")
    public String selectionnerObjet(@RequestParam(required = false) String idProduit) {
        if (idProduit != null) {
            this.detailProduit = objetSac.stream()
                    .filter(p -> p.getId().equals(idProduit))
                    .findFirst()
                    .orElse(null);
        }
        return "redirect:/ajoutObjet";
    }

    private void updateProduitsCategorie() {
        this.produitsCategorie = objetSac.stream()
                .filter(p -> p.getCategorie().equals(this.categorieSelectionne))
                .toList();
    }

    @PostMapping("/sac/valider")
    public String valider(RedirectAttributes attribut) {
        if ((poidsMax * 1.10 < poidsTotal) || (kcalTotal < kcalMax)) {
            attribut.addFlashAttribute("messageErreur",
                    "Validation impossible : Le total des objets sélectionnées est trop lourd ou les calories sont insuffisantes.");
        }

        ArrayList<SacADos> sacADosParticipants = new ArrayList<>();
        List<Participant> participantsEligibles = new ArrayList<>();

        for (Participant participant : rando.getParticipants()) {
            if (participant.getAge() > 8) {
                sacADosParticipants.add(new SacADos((participant.poidsApproximatif() + 1.0)*1000, 0));
                participantsEligibles.add(participant);
            }
        }

        ArrayList<SacADos> sacsRemplis = AlgoSacADos.algoGlouton(sacReserve.getContenu(), sacADosParticipants);

        HashMap<String, SacADos> sacsParParticipant = new HashMap<>();
        for (int i = 0; i < participantsEligibles.size(); i++) {
            // On associe le nom du participant au sac correspondant retourné par l'algo
            sacsParParticipant.put(participantsEligibles.get(i).getNom(), sacsRemplis.get(i));
        }

        attribut.addFlashAttribute("sacsDistribues", sacsParParticipant);
        return "redirect:/recapSac";
    }
}