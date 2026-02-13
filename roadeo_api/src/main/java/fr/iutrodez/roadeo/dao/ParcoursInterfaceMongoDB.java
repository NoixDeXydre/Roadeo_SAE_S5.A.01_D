package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Parcours;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Communique avec la collection parcours de MongoDB
 */
public interface ParcoursInterfaceMongoDB extends MongoRepository<Parcours, String> {

    ArrayList<Parcours> findByIdRando(String idRando);

    /**
     * Recherche du parcours selon l'id
     * @param id du parcours
     * @return un optional parcours (résultat mongoDB)
     */
    Optional<Parcours> findById(String id);
}