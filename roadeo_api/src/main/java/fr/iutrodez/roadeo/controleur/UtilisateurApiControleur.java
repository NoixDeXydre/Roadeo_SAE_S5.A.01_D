package fr.iutrodez.roadeo.controleur;

import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
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

    /**
     * Ajoute un utilisateur
     * @param util l'utilisateur à ajouter
     * @return l'utilisateur sauvegardée et un code
     * code 200 -> si l'utilisateur est ajouté
     *      403 -> si une erreur est détectée
     *      404 -> si l'utilisateur est null
     */
    @PostMapping("/ajoutUtilisateur")
    public ResponseEntity<Utilisateur>  ajoutUtilisateur(@RequestBody Utilisateur util) {
        try {
            Utilisateur utilisateur = utilisateurService.addUtilisateur(util);
            if (utilisateur != null) {
                return ResponseEntity.ok(utilisateur);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Modifie un utilisateur
     * @param util l'utilisateur à modifier
     * @return l'utilisateur sauvegardée et un code
     * code 200 -> si l'utilisateur est modifiée
     *      403 -> si une erreur est détectée
     *      404 -> si l'utilisateur est null
     */
    @PutMapping("/modifUtilisateur")
    public ResponseEntity<Utilisateur> modifUtilisateur(@RequestBody Utilisateur util) {
        try{
            Utilisateur utilisateur = utilisateurService.updateUtilisateur(util);
            if (utilisateur != null) {
                return ResponseEntity.ok(utilisateur);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Modifie un utilisateur
     * @param id de l'utilisateur à supprimer
     * @return un code 200 -> si l'utilisateur est supprimée
     *                 403 -> si une erreur est détectée
     *                 404 -> si l'utilisateur n'est pas supprimé
     */
    @DeleteMapping("/supprimeUtilisateur/{id}")
    public ResponseEntity<Void>  supprimeUtilisateur(@PathVariable String id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        } else if (utilisateurService.supprimerUtilisateur(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
