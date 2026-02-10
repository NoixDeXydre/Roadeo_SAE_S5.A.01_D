package fr.iutrodez.roadeo.controleur;

import fr.iutrodez.roadeo.modele.Parcours;
import fr.iutrodez.roadeo.modele.Participant;
import fr.iutrodez.roadeo.modele.Randonnee;
import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.service.RandonneeService;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur REST permettant de gérer les objets/produits.
 */
@RestController
@RequestMapping("/api/Randonnee")
public class RandoneeApiControleur {

    /**
     * Service métier permettant la gestion des randonnées.
     */
    public RandonneeService randonneeService;

    /**
     * Constructeur du contrôleur de l'api des randonnées
     * @param randonneeService service utilisé pour accéder aux données des randonnées
     */
    public RandoneeApiControleur(RandonneeService randonneeService) {
        this.randonneeService = randonneeService;
    }

    /**
     * Récupère la liste complète des randonnées.
     * @return liste des randonnées disponibles
     */
    @RequestMapping("/liste")
    public List<Randonnee> getRandonnee() {
        return randonneeService.getAllRandonnees();
    }

    /**
     * Récupère la liste complète des parcours.
     * @return liste des parcours disponibles
     */
    @RequestMapping("/listeParcours")
    public List<Parcours> getParcours() {
        return randonneeService.getAllParcours();
    }

    /**
     * Renvoie la liste des participants
     * @param id id de la randonnées
     * @return la liste des participants de la randonnée
     */
    @GetMapping("/listeParticipant/{id}") //Utilisation de GetMapping car seule une variable String est passée en argument
    public List<Participant> getParticipant(@PathVariable String id) {
        return randonneeService.getParticipantRandonnee(id); // Renvoie la liste des participants
    }

    /**
     * Renvoie la liste de toutes les randonnées d'un participant
     * @param idUtilisateur id de l'utilisateur
     * @return la liste des randonnées
     */
    @PostMapping("/infoRandoUtil")
    public List<Randonnee> getRandonneeParIdUtil(@RequestBody String idUtilisateur) {
        List<Randonnee> listeRandonnee = randonneeService.getAllRandonnees();
        for (Randonnee randonnee : listeRandonnee) {
            randonnee.setParcours(
                    randonneeService.getParcoursByIdRando(randonnee.getId())
            );
        }
        return listeRandonnee;
    }

    /**
     * Renvoie une randonnée
     * @param idRando id de la randonnée
     * @return la randonnée ou null si id inconnu
     */
    @PostMapping("/infoRando")
    public Randonnee getRandonneeParId(@RequestBody String idRando) {
        Randonnee randonnee = randonneeService.getRandonnee(idRando);
        randonnee.setParcours(randonneeService.getParcoursByIdRando(randonnee.getId()));
        return randonnee;
    }

    /**
     * Ajoute une randonnée
     * @param rando la randonnée à modifier
     * @return la randonnée sauvegardée et un code
     * code 200 -> si la randonnée est modifiée
     *      403 -> si une erreur est détectée
     *      404 -> si la randonnée est null
     */
    @PostMapping("/ajoutRandonnee")
    public ResponseEntity<Randonnee>  ajoutRandonnee(@RequestBody Randonnee rando) {
        try {
            Randonnee randonnee = randonneeService.addRandonnee(rando);
            if (randonnee != null) {
                return ResponseEntity.ok(randonnee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Ajoute un parcours à une randonnée
     * @param id de la randonnée à modifier
     * @param parcours le parcours à ajouter
     * @return la randonnée sauvegardée et un code
     * code 200 -> si le parcours est ajouté
     *      403 -> si une erreur est détectée
     *      404 -> si la randonnée est null
     */
    @PostMapping("/ajoutParcours/{id}")
    public ResponseEntity<Randonnee> ajoutParcoursRando(@PathVariable String id, @RequestBody Parcours parcours) {
        try {
            Randonnee randonnee = randonneeService.addParcours(id,parcours);
            if (randonnee != null) {
                return ResponseEntity.ok(randonnee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Modifie une randonnée
     * @param id de la randonnée à supprimer
     * @return un code 200 -> si la randonnée est supprimée
     *                 403 -> si une erreur est détectée
     *                 404 -> si la randonnée n'est pas supprimé
     */
    @DeleteMapping("/supprime/{id}")
    public ResponseEntity<Randonnee> deleteRandonee(@PathVariable String id) {
        try {
            if(randonneeService.deleteRandonee(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Modifie une randonnée
     * @param rando la randonnée à modifier
     * @return la randonnée sauvegardée et un code
     * code 200 -> si la randonnée est modifiée
     *      403 -> si une erreur est détectée
     *      404 -> si la randonnée est null
     */
    @PutMapping("/modif")
    public ResponseEntity<Randonnee> modifRandonnee(@RequestBody Randonnee rando) {
        try {
            Randonnee randonnee = randonneeService.modifRandonnee(rando);
            if (randonnee == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(randonnee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
