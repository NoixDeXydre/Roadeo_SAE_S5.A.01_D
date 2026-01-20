package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UtilisateurInterfaceMongoDB extends MongoRepository<Utilisateur, String> {

    /**
     * Cherche un utilisateur avec son adresse mail et son mot de passe.
     * @param adresseMail
     * @param mdp
     * @return l'utilisateur ou null
     */
    Optional<Utilisateur> findByAdresseMailAndMdp(String adresseMail, String mdp);

}
