package fr.iutrodez.roadeo.controleur;

import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // TODO dans les versions supérieures, devrait renvoyer la clé API.
    @PostMapping("seConnecter")
    public ResponseEntity<Utilisateur> seConnecter(@RequestBody Utilisateur request) {

        var utilisateur = utilisateurService.validerConnexion
                (request.getAdresseMail(), request.getMdp());
        if (utilisateur != null) {
            return ResponseEntity.ok(utilisateur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/liste")
    public List<Utilisateur> getUtilisateur() {
        return utilisateurService.getAllUtilisateurs();
    }

    @PostMapping("/Id")
    public ResponseEntity<Utilisateur> getUtilisateurById(@RequestBody Utilisateur request) {
        Utilisateur utilisateur = utilisateurService.getUtilisateur(request.getId());

        if (utilisateur != null) {
            return ResponseEntity.ok(utilisateur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
