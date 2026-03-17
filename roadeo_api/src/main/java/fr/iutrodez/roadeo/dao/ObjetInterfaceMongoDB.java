package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Produits;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Communique avec la collection produits de MongoDB
 */
public interface ObjetInterfaceMongoDB extends MongoRepository<Produits, String> {

    /**
     * Liste les catégories distincte des objets
     * @return la liste des catégories
     */
    @Aggregation(pipeline = { "{ '$group': { '_id': '$categorie' } }" })
    ArrayList<String> findDistinctCategorie();

    ArrayList<Produits> findByCategorie(String Categorie);

    Produits findProduitsById(String idObjet);

    ArrayList<Produits> findByIdRandonnee(String idRandonnee);

    @Aggregation(pipeline = { "{ '$group': { '_id': '$nom' } }" })
    ArrayList<String> findDistinctNom();
}
