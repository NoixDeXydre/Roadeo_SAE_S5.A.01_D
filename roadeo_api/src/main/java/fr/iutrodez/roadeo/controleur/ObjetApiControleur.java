package fr.iutrodez.roadeo.controleur;

import fr.iutrodez.roadeo.modele.Produits;
import fr.iutrodez.roadeo.modele.Randonnee;
import fr.iutrodez.roadeo.service.ObjetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Objet")
public class ObjetApiControleur {

    public ObjetService objetService;

    public ObjetApiControleur(ObjetService serviceO) {
        this.objetService = serviceO;
    }

    @RequestMapping("/liste")
    public List<Produits> getRandonnee() {
        return objetService.recupListeObjet();
    }

    @RequestMapping("/categorie")
    public List<String> getCategorie() {
        return objetService.listeCategorie();
    }
}
