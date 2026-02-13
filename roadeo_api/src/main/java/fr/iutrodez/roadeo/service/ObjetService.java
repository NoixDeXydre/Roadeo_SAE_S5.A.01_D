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

    public ObjetService(ObjetInterfaceMongoDB repository) {
        this.objetRepository = repository;
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

    /**
     * Permet de récupérer la liste des objets
     * @return liste des catégories sans doublons;
     */
    public List<String> listeNom() {
        //return this.objetRepository.findDistinctByCategorie();
        return objetRepository.findDistinctNom();
    }

    /**
     * Récupère la liste des produits selon la catégorie demandé
     * @param categorie recherché
     * @return la liste des objets par catégories
     */
    public List<Produits> recupListeCategorie(String categorie) {
        return this.objetRepository.findByCategorie(categorie);
    }

    /**
     * Récupère un produit selon l'id
     * @param idObjet id de l'objet cherché
     * @return l'objet si l'id existe,
     *          null sinon
     */
    public Produits getProduitById(String idObjet) {
        return this.objetRepository.findProduitsById(idObjet);
    }

    /**
     * Ajoute des produits à la base de données
     * @param prod produit à ajouter
     * @return le produit ajouté
     * @throws IllegalArgumentException si le produit à ajouter est null
     */
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
    public boolean deleteProduits(String id) {
        if (!objetRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        objetRepository.deleteById(id);
        return objetRepository.existsById(id);
    }
}
