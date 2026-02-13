package fr.iutrodez.roadeo.web.controleur;

import fr.iutrodez.roadeo.dao.ObjetInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Produits;
import fr.iutrodez.roadeo.service.ObjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/produits")
/**
 * Permet de faire la passerelle entre la base de données et le site web d'ajout des produits
 */
public class ProduitsControleur {


    /** Service des produits */
    private ObjetService service;

    /** liste des catégories */
    private List<String> listeCategorie;

    /** Liste des noms */
    private List<String> listeNom;


    /**
     * Crée le controleur pour la page web
     * @param service fait l'intermédiaire avec la base de données
     */
    public ProduitsControleur(ObjetService service) {
        this.service = service;
    }


    @GetMapping
    /** Initialise les données de la page */
    public String pageProduits(@RequestParam(required = false) String categorie,
                               @RequestParam(required = false) String nom,
                               @RequestParam(required = false) String editId,
                               Model model)  {

        listeCategorie = service.listeCategorie();
        listeNom = service.listeNom();

        List<Produits> produits = recupListeObjetFiltre(categorie, nom);

        Produits produit = (editId != null)
                ? service.getProduitById(editId)
                : new Produits();

        model.addAttribute("produits", produits);
        model.addAttribute("produit", produit);

        model.addAttribute("categories", listeCategorie);
        model.addAttribute("noms", listeNom); //todo listeNom);

        model.addAttribute("selectedCategorie", categorie);
        model.addAttribute("selectedNom", nom);

        model.addAttribute("editMode", editId != null);

        return "produits";
    }

    /**
     * Ajoute ou modifie un produit
     * @param produit le produit à sauvegarder
     * @return la page index
     */
    @PostMapping("/save")
    public String saveProduit(Produits produit) {

        if (produit.getId() != null && produit.getId().isBlank()) {
            produit.setId(null);
        }

        // Validation simple
        if (produit.getMasse() < 0 || produit.getPrix() < 0) {
            return "redirect:/produits";
        }

        if (!"nourriture".equals(produit.getCategorie())) {
            produit.setNutrition(0.0);
        }
        System.out.println(produit);
        if (produit.getId() != null) {
            service.modifProduits(produit);
        } else {
            service.ajoutProduits(produit);
        }

        return "redirect:/produits";
    }

    /**
     * Supprime un produit
     * @param id l'id du produit à supprimer
     * @return la page index
     */
    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable String id) {
        service.deleteProduits(id);
        return "redirect:/produits";
    }

    /**
     * Permet de filtrer l'affichage des objets selon la catégorie de l'objet et son nom
     * @param categorie du produit demandé
     * @param nom nom du produit demandé
     * @return la liste des produits filtrés
     */
    public List<Produits> recupListeObjetFiltre(String categorie, String nom) {

        return service.recupListeObjet().stream()
                .filter(p -> categorie == null || categorie.isEmpty() || p.getCategorie().equals(categorie))
                .filter(p -> nom == null || nom.isEmpty() || p.getNom().equals(nom))
                .toList();
    }

}
