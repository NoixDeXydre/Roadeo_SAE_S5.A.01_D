package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Produits;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

public interface ObjetInterfaceMongoDB extends MongoRepository<Produits, String> {

    /**
     * Liste les catégories distincte des objets
     * @return la liste des catégories
     */
    @Aggregation(pipeline = { "{ '$group': { '_id': '$categorie' } }" })
    ArrayList<String> findDistinctCategorie();

    ArrayList<Produits> findByCategorie(String Categorie);

}
