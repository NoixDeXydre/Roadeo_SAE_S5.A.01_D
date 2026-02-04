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

    /**
     * Renvoie la liste des participants
     * @param id
     * @return
     */
    @GetMapping("/listeParticipant/{id}") //Utilisation de GetMapping car seul une variable String est passée en argument
    public List<Participant> getParticipant(@PathVariable String id) {
        return randonneeService.getParticipantRandonnee(id); // Renvoie la liste des participants
    }

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

    @PostMapping("/infoRando")
    public Randonnee getRandonneeParId(@RequestBody String idRando) {
        Randonnee randonnee = randonneeService.getRandonnee(idRando);
        randonnee.setParcours(randonneeService.getParcoursByIdRando(randonnee.getId()));
        return randonnee;
    }

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

    @PostMapping("/ajoutRandonnee/{id}")
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
