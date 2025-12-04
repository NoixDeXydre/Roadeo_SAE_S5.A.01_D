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
}
