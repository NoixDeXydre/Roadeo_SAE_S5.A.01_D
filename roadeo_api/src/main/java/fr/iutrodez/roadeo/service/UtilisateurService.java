package fr.iutrodez.roadeo.service;

import fr.iutrodez.roadeo.dao.UtilisateurInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Utilisateur;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    /** répertoire qui permet de communiquer avec la collection utilisateur */
    private final UtilisateurInterfaceMongoDB repository;

    /**
     * Crée un service
     * @param repository répertoire utilisateur
     */
    public UtilisateurService(UtilisateurInterfaceMongoDB repository) {
        this.repository = repository;
    }

    /**
     * Liste les utilisateurs de la base de données
     * @return la liste des utilisateurs
     */
    @Timed(value = "roadeo.utilisateur.getAllUtilisateurs", description = "Temps de lecture des utilisateurs")
    public List<Utilisateur> getAllUtilisateurs() {
        return repository.findAll();
    }

    /**
     * Récupère un utilisateur selon un id
     * @param id id de l'utilisateur à retrouver
     * @return l'utilisateur trouvé ou null si l'id n'existe pas
     */
    @Timed(value = "roadeo.utilisateur.getUtilisateurById", description = "Temps de lecture d'un utilisateur par id")
    public Utilisateur getUtilisateurById(String id) {

        Optional<Utilisateur> result = repository.findById(id);
        return result.orElse(null);
    }

    /**
     * Récupère un utilisateur selon son adresse mail.
     * @param adresseMail l'adresse mail de l'utilisateur à retrouver.
     * @return l'utilisateur trouvé ou null si l'adresse mail n'existe pas.
     */
    @Timed(value = "roadeo.utilisateur.getUtilisateurByAdresseMail", description = "Temps de lecture d'un utilisateur par mail")
    public Utilisateur getUtilisateurByAdresseMail(String adresseMail) {

        Optional<Utilisateur> result
        = repository.findByAdresseMail(adresseMail.trim().toLowerCase());

        return result.orElse(null);
    }

    // TODO dans une version supérieure
    //      la clé API devrait être fournie.
    /**
     * Valide la connexion de l'utilisateur.
     * @param email adresse mail de l'utilisateur
     * @param mdp mot de passe de l'utilisateur
     * @return l'utilisateur connecté
     */
    @Timed(value = "roadeo.utilisateur.validerConnexion", description = "Temps de validation de connexion")
    public Utilisateur validerConnexion(String email, String mdp) {

        var resultat = repository.findByAdresseMailAndMdp
                (email.toLowerCase().trim(), mdp);
        return resultat.orElse(null);
    }

    /**
     * Supprime un utilisateur si il existe
     * @param id  id de l'utilisateur à supprimer
     * @return true si l'utilisateur est supprimé
     *          false sinon
     */
    @Timed(value = "roadeo.utilisateur.supprimerUtilisateur", description = "Temps de suppression d'un utilisateur")
    public boolean supprimerUtilisateur(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    /**
     * Modifie un utilisateur existant
     * @param utilisateur, l'utilisateur à modifier avec les données modifiées
     * @return la nouvelle valeur de l'utilisateur si modifié
     * @throws IllegalArgumentException si l'utilisateur n'existe pas
     *      ou si le parametre est null
     */
    @Timed(value = "roadeo.utilisateur.updateUtilisateur", description = "Temps de modification d'un utilisateur")
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        if  (utilisateur == null || !repository.existsById(utilisateur.getId())) {
            throw new IllegalArgumentException();
        }
        return repository.save(utilisateur);
    }

    /**
     * Ajoute un utilisateur dans la bd mango
     * @param utilisateur  les données utilisateur à ajouter
     * @return l'utilisateur ajouté
     * @throws IllegalArgumentException si le paramètre est null
     */
    @Timed(value = "roadeo.utilisateur.addUtilisateur", description = "Temps d'ajout d'un utilisateur")
    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null) {
            throw new IllegalArgumentException();
        }
        return repository.save(utilisateur);
    }
}
