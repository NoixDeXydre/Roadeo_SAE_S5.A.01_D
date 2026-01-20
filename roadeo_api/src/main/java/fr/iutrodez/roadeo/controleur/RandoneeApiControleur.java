package fr.iutrodez.roadeo.controleur;

import fr.iutrodez.roadeo.modele.Parcours;
import fr.iutrodez.roadeo.modele.Randonnee;
import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.service.RandonneeService;
import fr.iutrodez.roadeo.service.UtilisateurService;
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
    public ResponseEntity<Randonnee>  ajoutParcoursRando(@PathVariable String id, @RequestBody Parcours parcours) {
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
}
