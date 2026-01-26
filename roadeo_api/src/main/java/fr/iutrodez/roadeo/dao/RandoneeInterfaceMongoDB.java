package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Randonnee;
import fr.iutrodez.roadeo.modele.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RandoneeInterfaceMongoDB extends MongoRepository<Randonnee, String> {
    Optional<Randonnee> findById(String id);
}