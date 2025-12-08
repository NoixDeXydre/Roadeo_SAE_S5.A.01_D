package fr.iutrodez.roadeo.service;

import fr.iutrodez.roadeo.UtilisateurInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Utilisateur;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
public class UtilisateurService {
    private final UtilisateurInterfaceMongoDB repository;

    public UtilisateurService(UtilisateurInterfaceMongoDB repository) {
        this.repository = repository;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return repository.findAll();
    }

    public Utilisateur getUtilisateur(String id) {

        Optional<Utilisateur> result = repository.findById(id);
        //Objet Utilisateur
        return result.orElse(null);
    }

    // TODO dans une version supérieure
    //      la clé API devrait être fournie.
    /**
     * Valide la connexion de l'utilisateur.
     * @param email
     * @param mdp
     * @return l'utilisateur connecté
     */
    public Utilisateur validerConnexion(String email, String mdp) {

        Utilisateur person = new Utilisateur(null, null, mdp, email, null);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase("adresse_mail")
                .withMatcher("adresse_mail", exact())
                .withMatcher("mdp", exact());

        var resultat = repository.findOne(Example.of(person, matcher));
        return resultat.orElse(null);
    }
}
