package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UtilisateurInterfaceMongoDB extends MongoRepository<Utilisateur, String> {
}
