package fr.iutrodez.roadeo.controleur;

import fr.iutrodez.roadeo.modele.Produits;
import fr.iutrodez.roadeo.modele.Randonnee;
import fr.iutrodez.roadeo.service.ObjetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
