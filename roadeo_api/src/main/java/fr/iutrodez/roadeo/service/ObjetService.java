package fr.iutrodez.roadeo.service;


import fr.iutrodez.roadeo.dao.ObjetInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Produits;
import fr.iutrodez.roadeo.modele.Produits;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Récupère les données de la base de données par l'intermédiaire du repository
 */
@Service
public class ObjetService {
    private final ObjetInterfaceMongoDB objetRepository;
    private MongoTemplate template;

    public ObjetService(ObjetInterfaceMongoDB repository, MongoTemplate template) {
        this.objetRepository = repository;
        this.template = template;
    }

    /**
     * Permet de récupérer la liste de tous les objets
     * @return la liste de tous les objets
     */
    public ArrayList<Produits> recupListeObjet() {
        return (ArrayList<Produits>) this.objetRepository.findAll();
    }

    /**
     * Permet de récupérer la liste des catégories
     * @return liste des catégories sans doublons;
     */
    public List<String> listeCategorie() {
        //return this.objetRepository.findDistinctByCategorie();
        return objetRepository.findDistinctCategorie();
    }

    public List<Produits> recupListeCategorie(String categorie) {
        return this.objetRepository.findByCategorie(categorie);
    }

    public Produits getProduitById(String idObjet) {
        return this.objetRepository.findProduitsById(idObjet);
    }
}
