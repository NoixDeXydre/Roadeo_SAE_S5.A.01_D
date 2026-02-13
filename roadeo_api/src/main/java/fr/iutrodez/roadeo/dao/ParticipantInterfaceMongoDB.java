package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

/**
 * Communique avec la sous collection participant de la collection randonnée de MongoDB
 */
public interface ParticipantInterfaceMongoDB extends MongoRepository<Participant, String> {
}
