package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Randonnee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Communique avec la collection randonnée de MongoDB
 */
public interface RandoneeInterfaceMongoDB extends MongoRepository<Randonnee, String> {

    /**
     * Renvoie une randonnée selon l'id en paramètre
     * @param id id de la randonnée
     * */
    Optional<Randonnee> findById(String id);
}