package fr.iutrodez.roadeo.service;

import fr.iutrodez.roadeo.dao.ParcoursInterfaceMongoDB;
import fr.iutrodez.roadeo.dao.RandoneeInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Parcours;
import fr.iutrodez.roadeo.modele.Participant;
import fr.iutrodez.roadeo.modele.Randonnee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Permet de faire la passerelle entre la base de donnée et le web de l'API
 */
@Service
public class RandonneeService {
    /** répertoire qui permet de communiquer avec la collection randonnée */
    private final RandoneeInterfaceMongoDB repository;

    /** répertoire qui permet de communiquer avec la collection parcours */
    private final ParcoursInterfaceMongoDB parcoursRepository;
    //private final ParticipantInterfaceMongoDB participantRepository;

    /**
     * Crée un service
     * @param repository répertoire randonnées
     * @param parcoursRepository répertoire des parcours
     */
    public RandonneeService(RandoneeInterfaceMongoDB repository,  ParcoursInterfaceMongoDB parcoursRepository) {
        this.repository = repository;
        this.parcoursRepository = parcoursRepository;
        //this.participantRepository = participantRepository;
    }

    /**
     * Liste les randonnées de la base de données
     * @return la liste des randonnées
     */
    public List<Randonnee> getAllRandonnees() {
        return repository.findAll();
    }

    /**
     * Liste les parcours de la base de données
     * @return la liste des parcours
     */
    public List<Parcours> getAllParcours() {
        return parcoursRepository.findAll();
    }

    /**
     * Permet de renvoyer une liste de participant d'un parcours
     * @param id id du parcours
     * @return la liste de participant
     */
    public List<Participant> getParticipantRandonnee(String id) {
        /* Récupère le résultat mongoDB */
        Optional<Randonnee> result = repository.findById(id);
        /* Renvoie le parcours ou null si rien trouvé */
        Randonnee rando = result.orElse(null);
        if (rando == null) {
            return null;
        }
        return rando.getParticipants(); // return liste participants
    }

    /**
     * Liste les parcours de la base de données selon l'id d'une randonnée
     * @param idRando id de la randonnée
     * @return la liste des parcours d'une randonnée
     */
    public ArrayList<Parcours> getParcoursByIdRando(String idRando) {
        return parcoursRepository.findByIdRando(idRando);
    }

    /**
     * Récupère une randonnée selon l'id de la randonnée
     * @param idRandonnee id de la randonnée à retrouver
     * @return la randonnée trouvé ou null si l'id n'existe pas
     */
    public Randonnee getRandonneeByIdRandonnee(String idRandonnee) {
        return repository.findById(idRandonnee).orElse(null);
    }

    /**
     * Récupère des randonnées avec l'id utilisateur.
     * @param idUtilisateur
     * @return les randonnées ou null si l'id n'existe pas.
     */
    public List<Randonnee> getRandonneesByIDUtilisateur(String idUtilisateur) {
        return repository.findByIdUtilisateur(idUtilisateur);
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
        return repository.insert(rando);
    }

    /**
     * Ajoute un parcours à une randonnée
     * @param id id de la randonnée
     * @param parcours parcours à ajouter
     * @return la randonnée ajouté ou null si il n'a pas pu être ajouté
     */
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

    /**
     * Supprime une randonnée
     * @param id id de la randonnée
     * @return true si la randonnée a été supprimée
     * @throws IllegalArgumentException si l'id n'existe pas
     */
    public boolean deleteRandonee(String id) {
        if (!repository.existsById(id)) {
           throw new IllegalArgumentException("La randonnée n'existe pas.");
        }
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    /**
     * Modifie une randonnée
     * @param rando la randonnée à modifier
     * @return la randonnée sauvegardée
     * @throws IllegalArgumentException si la rando est null
     */
    public Randonnee modifRandonnee(Randonnee rando) {
        if (rando == null) {
            throw new IllegalArgumentException();
        }
        return repository.save(rando);
    }

    /**
     * Supprime un parcours
     * @param id id de le parcours
     * @return true si le parcours a été supprimé
     * @throws IllegalArgumentException si l'id n'existe pas
     */
    public boolean deleteParcours(String id) {
        if (!parcoursRepository.existsById(id)) {
            throw new IllegalArgumentException("Le parcours n'existe pas.");
        }
        parcoursRepository.deleteById(id);
        return !parcoursRepository.existsById(id);
    }

    /**
     * Modifie un parcours
     * @param parcours le parcours à modifier
     * @return le parcours sauvegardé
     * @throws IllegalArgumentException si le parcours est null
     */
    public Parcours modifParcours(Parcours parcours) {
        if (parcours == null) {
            throw new IllegalArgumentException();
        }
        return parcoursRepository.save(parcours);
    }

    /**
     * Ajoute un parcours à une randonnée
     * @param parcours parcours à ajouter
     * @return la randonnée ajouté ou null si il n'a pas pu être ajouté
     */
    public Parcours ajoutParcours(Parcours parcours) {
        if (parcours == null || !repository.existsById(parcours.getIdRando())) {
            throw new IllegalArgumentException();
        }
        return parcoursRepository.insert(parcours);
    }
}
