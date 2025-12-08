package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Randonnee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RandoneeInterfaceMongoDB extends MongoRepository<Randonnee, String> {
}