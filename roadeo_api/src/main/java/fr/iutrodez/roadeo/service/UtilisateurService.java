package fr.iutrodez.roadeo.service;

import fr.iutrodez.roadeo.UtilisateurInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        var resultat = repository.findByAdresseMailAndMdp
                (email.toLowerCase().trim(), mdp);
        return resultat.orElse(null);
    }
}
