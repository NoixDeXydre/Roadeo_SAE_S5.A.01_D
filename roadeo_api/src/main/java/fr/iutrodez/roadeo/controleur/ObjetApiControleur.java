package fr.iutrodez.roadeo.controleur;

import fr.iutrodez.roadeo.modele.Participant;
import fr.iutrodez.roadeo.modele.Produits;
import fr.iutrodez.roadeo.service.ObjetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST permettant de gérer les objets/produits.
 */
@RestController
@RequestMapping("/api/Objet")
public class ObjetApiControleur {

    /**
     * Service métier permettant la gestion des objets/produits.
     */
    public ObjetService objetService;

    /**
     * Constructeur du contrôleur de l'api des produits
     * @param serviceO service utilisé pour accéder aux données des objets/produits
     */
    public ObjetApiControleur(ObjetService serviceO) {
        this.objetService = serviceO;
    }

    /**
     * Récupère la liste complète des produits.
     * @return liste des produits disponibles
     */
    @GetMapping("/liste")
    public List<Produits> getProduits() {
        return objetService.recupListeObjet();
    }

    /**
     * Récupère la liste des catégories de produits.
     * @return liste des catégories existantes
     */
    @GetMapping("/categorie")
    public List<String> getCategorie() {
        return objetService.listeCategorie();
    }

    @GetMapping("/sac/{idRandonnee}")
    public ResponseEntity<List<Participant>> getSacs(@PathVariable String idRandonnee) {
        try {
            List<Participant> participants = objetService.getParticipantsAvecSacs(idRandonnee);
            if (participants == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(participants);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
