package fr.iutrodez.roadeo.web.controleur;

import fr.iutrodez.roadeo.dao.ObjetInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Produits;
import fr.iutrodez.roadeo.service.ObjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/produits")
public class ProduitsControleur {


    private ObjetService service;

    private List<String> listeCategorie;
    private List<String> listeNom;


    public ProduitsControleur(ObjetService service) {
        this.service = service;
    }


    @GetMapping
    public String pageProduits(@RequestParam(required = false) String categorie,
                               @RequestParam(required = false) String nom,
                               @RequestParam(required = false) String editId,
                               Model model)  {

        listeCategorie = service.listeCategorie();
        List<Produits> produits;

        if (categorie.equals("Tous")) {
            produits = service.recupListeObjet();
        } else {
            produits = recupListeObjetFiltre(categorie,nom); //service.recupListeCategorie(categorie);
        }

        Produits produit = (editId != null)
                ? service.getProduitById(editId)
                : new Produits();

        model.addAttribute("produits", produits);
        model.addAttribute("produit", produit);

        model.addAttribute("categories", listeCategorie);
        model.addAttribute("noms", listeNom);

        model.addAttribute("selectedCategorie", categorie);
        model.addAttribute("selectedNom", nom);

        model.addAttribute("editMode", editId != null);

        return "produits";
    }

    @PostMapping("/save")
    public String saveProduit(Produits produit) {

        // Validation simple
        if (produit.getMasse() < 0 || produit.getPrix() < 0) {
            return "redirect:/produits";
        }

        if (!"Nourriture".equals(produit.getCategorie())) {
            produit.setNutrition(0.0);
        }

        service.modifProduits(produit);

        return "redirect:/produits";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable String id) {
        service.deleteProduits(id);
        return "redirect:/produits";
    }

    public List<Produits> recupListeObjetFiltre(String categorie, String nom) {

        return service.recupListeObjet().stream()
                .filter(p -> categorie == null || categorie.isEmpty() || p.getCategorie().equals(categorie))
                .filter(p -> nom == null || nom.isEmpty() || p.getNom().equals(nom))
                .toList();
    }

}
