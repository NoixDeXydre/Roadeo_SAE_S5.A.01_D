package fr.iutrodez.roadeo.service;

import fr.iutrodez.roadeo.dao.ParcoursInterfaceMongoDB;
import fr.iutrodez.roadeo.dao.ParticipantInterfaceMongoDB;
import fr.iutrodez.roadeo.dao.RandoneeInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Parcours;
import fr.iutrodez.roadeo.modele.Participant;
import fr.iutrodez.roadeo.modele.Randonnee;
import fr.iutrodez.roadeo.modele.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RandonneeService {
    private final RandoneeInterfaceMongoDB repository;
    private final ParcoursInterfaceMongoDB parcoursRepository;
    private final ParticipantInterfaceMongoDB participantRepository;

    public RandonneeService(RandoneeInterfaceMongoDB repository,  ParcoursInterfaceMongoDB parcoursRepository,
                            ParticipantInterfaceMongoDB participantRepository) {
        this.repository = repository;
        this.parcoursRepository = parcoursRepository;
        this.participantRepository = participantRepository;
    }

    public List<Randonnee> getAllRandonnees() {
        return repository.findAll();
    }

    public List<Parcours> getAllParcours() {
        return parcoursRepository.findAll();
    }

    /**
     * Permet de renvoyer une liste de participant d'un parcours
     * @param id id du parcours
     * @return la liste de participant
     */
    public List<Participant> getParticipantParcours(String id) {
        /* Récupère le résultat mongoDB */
        Optional<Parcours> result = parcoursRepository.findById(id);
        /* Renvoie le parcours ou null si rien trouvé */
        Parcours parcours = result.orElse(null);
        if (parcours == null) {
            return null;
        }
        return parcours.getParticipants(); // return liste participants
    }

    public ArrayList<Parcours> getParcoursByIdRando(String idRando) {
        return parcoursRepository.findByIdRando(idRando);
    }

    public Randonnee getRandonnee(String id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Ajoute une randonnee dans la bd mango
     * @param rando la randonnée à ajouter
     * @return la randonnee ajoutée
     * @throws IllegalArgumentException si le paramètre est null
     */
    public Randonnee addRandonnee(Randonnee rando) {
        if (rando == null) {
            throw new IllegalArgumentException();
        }
        return repository.save(rando);
    }

    public Randonnee addParcours(String id, Parcours parcours) {
        Randonnee randonnee;
        if (!repository.existsById(id)) {
            return null;
        } else {
            Optional<Randonnee> rando = repository.findById(id);
            randonnee = rando.orElse(null);
        }

        if (randonnee == null) {
            throw new IllegalArgumentException();
        }
        randonnee.addParcours(parcours);
        return repository.save(randonnee);
    }

}
