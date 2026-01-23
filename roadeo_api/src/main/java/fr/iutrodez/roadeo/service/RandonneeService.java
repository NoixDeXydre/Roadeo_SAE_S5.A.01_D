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

    public List<Participant> getParticipantParcours(String idParcours) {
        ArrayList<Parcours> parcoursRandonnee = parcoursRepository.findParcoursById(idParcours);
        Parcours parcours = parcoursRandonnee.get(0);
        return parcours.getParticipants();
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
