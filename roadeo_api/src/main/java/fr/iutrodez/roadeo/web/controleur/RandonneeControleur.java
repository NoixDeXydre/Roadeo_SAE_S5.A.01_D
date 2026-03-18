package fr.iutrodez.roadeo.web.controleur;
import fr.iutrodez.roadeo.modele.*;
import fr.iutrodez.roadeo.service.ObjetService;
import fr.iutrodez.roadeo.service.RandonneeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/randonnee")
public class RandonneeControleur {

    private final RandonneeService randonneeService;
    private final ObjetService objetService;
    private final AlgoSacADos algoSacADos;

    public RandonneeControleur(RandonneeService randonneeService, ObjetService objetService) {
        this.randonneeService = randonneeService;
        this.objetService = objetService;
        this.algoSacADos = new AlgoSacADos();
    }

    // Affiche la liste des participants et les produits disponibles
    @GetMapping("/{idRandonnee}/participants")
    public String afficherParticipants(
            @PathVariable String idRandonnee,
            Model model) {
        Randonnee randonnee = randonneeService.getRandonnee(idRandonnee);
        List<Participant> participants = randonnee.getParticipants();
        System.out.println(participants.size());
        //List<Produits> produitsDisponibles = objetService.recupListeObjet();

        model.addAttribute("randonnee", randonnee);
        model.addAttribute("participants", participants);
        //model.addAttribute("produitsDisponibles", produitsDisponibles);
        return "randonnee-participants";
    }

    // Affiche le formulaire d'ajout de produits
    @GetMapping("/{idRandonnee}/ajout-produits")
    public String afficherFormulaireAjoutProduits(
            @PathVariable String idRandonnee,
            Model model) {
        List<Produits> produitsDisponibles = objetService.recupListeObjet();
        model.addAttribute("produitsDisponibles", produitsDisponibles);
        model.addAttribute("idRandonnee", idRandonnee);
        return "ajout-produits";
    }

    // Traite l'ajout de produits à la randonnée
    @PostMapping("/{idRandonnee}/ajouter-produits")
    public String ajouterProduits(
            @PathVariable String idRandonnee,
            @RequestParam String idProduit,
            @RequestParam int quantite,
            RedirectAttributes redirectAttributes) {

        Randonnee rando = randonneeService.getRandonnee(idRandonnee);
        Produits produit = objetService.getProduitById(idProduit);

        if (produit == null) {
            redirectAttributes.addFlashAttribute("erreur", "Produit introuvable");
            return "redirect:/randonnee/" + idRandonnee + "/ajout-produits";
        }

        if (rando.getProduits() == null) {
            rando.setProduits(new ArrayList<>());
        }

        // Crée une copie du produit avec la quantité
        for (int i = quantite; i > 0; i--) {
            rando.addProduits(produit);
        }


        randonneeService.modifRandonnee(rando);

        redirectAttributes.addFlashAttribute("succes", "Produit ajouté avec succès !");
        return "redirect:/randonnee/" + idRandonnee + "/participants";
    }

    // Lance l'algorithme de répartition et affiche les sacs
    @PostMapping("/{idRandonnee}/distribuer-produits")
    public String distribuerProduits(
            @PathVariable String idRandonnee,
            Model model) {

        // 1. Récupère les participants avec leurs sacs
        List<Participant> participantsAvecSacs = objetService.getParticipantsAvecSacs(idRandonnee);

        // 2. Récupère les produits de la randonnée
        List<Produits> produitsRandonnee = objetService.recupListeObjet();

        // 3. Crée les sacs pour chaque participant
        ArrayList<SacADos> sacs = new ArrayList<>();
        for (Participant participant : participantsAvecSacs) {
            sacs.add(participant.getSacADos());
        }

        // 4. Lance l'algorithme de répartition
        ArrayList<SacADos> sacsOptimises = algoSacADos.getSacADosRepartisAmeliorer(
                new ArrayList<>(produitsRandonnee),
                sacs);

        // 5. Met à jour les sacs des participants
        for (int i = 0; i < participantsAvecSacs.size(); i++) {
            participantsAvecSacs.get(i).setSacADos(sacsOptimises.get(i));
        }

        // 6. Prépare les données pour la vue
        Map<String, SacADos> sacsDistribues = new HashMap<>();
        for (Participant participant : participantsAvecSacs) {
            sacsDistribues.put(participant.getNom() + " " + participant.getPrenom(), participant.getSacADos());
        }

        model.addAttribute("sacsDistribues", sacsDistribues);
        return "sacs-optimises";
    }
}
