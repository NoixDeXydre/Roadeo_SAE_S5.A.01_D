package fr.iutrodez.roadeo.controleur;

import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Utilisateur")
public class UtilisateurApiControleur {

    public UtilisateurService utilisateurService;

    public UtilisateurApiControleur(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @RequestMapping("/liste")
    public List<Utilisateur> getUtilisateur() {
        return utilisateurService.getAllUtilisateurs();
    }
}
