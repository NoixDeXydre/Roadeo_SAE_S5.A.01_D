package fr.iutrodez.roadeo.service;


import fr.iutrodez.roadeo.dao.ObjetInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.AlgoSacADos;
import fr.iutrodez.roadeo.modele.Participant;
import fr.iutrodez.roadeo.modele.Produits;
import fr.iutrodez.roadeo.modele.Randonnee;
import fr.iutrodez.roadeo.modele.SacADos;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Récupère les données de la base de données par l'intermédiaire du repository
 */
@Service
public class ObjetService {
    private final ObjetInterfaceMongoDB objetRepository;
    private final RandonneeService randonneeService;

    public ObjetService(ObjetInterfaceMongoDB repository,
                        RandonneeService randonneeService,
                        MeterRegistry meterRegistry) {
        this.objetRepository = repository;
        this.randonneeService = randonneeService;
    }

    /**
     * Permet de récupérer la liste de tous les objets
     * @return la liste de tous les objets
     */
    @Timed(value = "roadeo.objet.recupListeObjet", description = "Temps de lecture des produits")
    public ArrayList<Produits> recupListeObjet() {
        return (ArrayList<Produits>) this.objetRepository.findAll();
    }

    /**
     * Permet de récupérer la liste des catégories
     * @return liste des catégories sans doublons;
     */
    @Timed(value = "roadeo.objet.listeCategorie", description = "Temps de lecture des categories distinctes")
    public List<String> listeCategorie() {
        //return this.objetRepository.findDistinctByCategorie();
        return objetRepository.findDistinctCategorie();
    }

    /**
     * Permet de récupérer la liste des objets
     * @return liste des catégories sans doublons;
     */
    @Timed(value = "roadeo.objet.listeNom", description = "Temps de lecture des noms distincts")
    public List<String> listeNom() {
        //return this.objetRepository.findDistinctByCategorie();
        return objetRepository.findDistinctNom();
    }

    /**
     * Récupère la liste des produits selon la catégorie demandé
     * @param categorie recherché
     * @return la liste des objets par catégories
     */
    @Timed(value = "roadeo.objet.recupListeCategorie", description = "Temps de lecture par categorie")
    public List<Produits> recupListeCategorie(String categorie) {
        return this.objetRepository.findByCategorie(categorie);
    }

    /**
     * Récupére un produit selon l'id
     * @param idObjet id de l'objet cherché
     * @return l'objet si l'id existe,
     *          null sinon
     */
    @Timed(value = "roadeo.objet.getProduitById", description = "Temps de lecture par id produit")
    public Produits getProduitById(String idObjet) {
        return this.objetRepository.findProduitsById(idObjet);
    }

    /**
     * Ajoute des produits à la base de données
     * @param prod produit à ajouter
     * @return le produit ajouté
     * @throws IllegalArgumentException si le produit à ajouter est null
     */
    @Timed(value = "roadeo.objet.ajoutProduits", description = "Temps d'ajout d'un produit")
    public Produits ajoutProduits(Produits prod) {
        if (prod == null) {
            throw new IllegalArgumentException();
        }
        return objetRepository.insert(prod);
    }

    /**
     * Modifie un produit de la base de données
     * @param prod produit à modifier
     * @return le produit à modifier
     * @throws IllegalArgumentException si le produit à modifier et null
     *                        ou si le produit n'existe pas
     */
    @Timed(value = "roadeo.objet.modifProduits", description = "Temps de modification d'un produit")
    public Produits modifProduits(Produits prod) {
        if (prod == null || !objetRepository.existsById(prod.getId())) {
            throw new IllegalArgumentException();
        }
        return objetRepository.save(prod);
    }

    /**
     * Supprime un produit de la base de données
     * @param id id du produit à supprimer
     * @return false
     * @throws IllegalArgumentException si le produit à supprimer n'existe pas
     */
    @Timed(value = "roadeo.objet.deleteProduits", description = "Temps de suppression d'un produit")
    public boolean deleteProduits(String id) {
        if (!objetRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        objetRepository.deleteById(id);
        return objetRepository.existsById(id);
    }

    @Timed(value = "roadeo.objet.getParticipantsAvecSacs", description = "Temps de calcul des sacs par randonnee")
    public List<Participant> getParticipantsAvecSacs(String idRandonnee) {
        final int UTILITE_NOURRITURE = 1;
        Randonnee randonnee = randonneeService.getRandonnee(idRandonnee);
        if (randonnee == null) {
            return null;
        }

        List<Participant> participants = randonnee.getParticipants();
        if (participants == null || participants.isEmpty()) {
            throw new IllegalArgumentException();
        }

        ArrayList<SacADos> sacs = new ArrayList<>();
        for (Participant participant : participants) {
            double poidsMax = participant.poidsRando();
            sacs.add(new SacADos(poidsMax, 0));
        }

        List<Produits> produits = objetRepository.findByIdRandonnee(idRandonnee);
        if (produits == null || produits.isEmpty()) {
            throw new IllegalArgumentException();
        }


        ArrayList<SacADos> sacsRemplis = new AlgoSacADos()
                .getSacADosRepartis(new ArrayList<>(produits), sacs);

        double caloriesRequises = randonnee.calculKcalTotal(randonnee.getNombreJours());
        double caloriesDansSacs = 0.0;
        for (SacADos sac : sacsRemplis) {
            for (Produits produit : sac.getContenu()) {
                if (produit.getUtilite() == UTILITE_NOURRITURE) {
                    caloriesDansSacs += produit.getNutrition();
                }
            }
        }

        if (caloriesDansSacs < caloriesRequises) {
            throw new IllegalArgumentException();
        }

        if (sacsRemplis.size() != participants.size()) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < participants.size(); i++) {
            participants.get(i).setSacADos(sacsRemplis.get(i));
        }

        return participants;
    }
}
