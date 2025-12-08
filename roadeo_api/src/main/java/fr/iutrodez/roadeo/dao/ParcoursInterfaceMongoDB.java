package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Parcours;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParcoursInterfaceMongoDB extends MongoRepository<Parcours, String> {
}