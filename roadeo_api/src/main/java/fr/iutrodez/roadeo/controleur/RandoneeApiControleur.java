package fr.iutrodez.roadeo.controleur;

import fr.iutrodez.roadeo.modele.Parcours;
import fr.iutrodez.roadeo.modele.Randonnee;
import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.service.RandonneeService;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Randonnee")
public class RandoneeApiControleur {

    public RandonneeService randonneeService;

    public RandoneeApiControleur(RandonneeService randonneeService) {
        this.randonneeService = randonneeService;
    }

    @RequestMapping("/liste")
    public List<Randonnee> getRandonnee() {
        return randonneeService.getAllRandonnees();
    }

    @RequestMapping("/listeParcours")
    public List<Parcours> getParcours() {
        return randonneeService.getAllParcours();
    }
}
