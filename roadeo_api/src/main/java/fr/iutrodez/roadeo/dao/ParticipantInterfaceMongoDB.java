package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface ParticipantInterfaceMongoDB extends MongoRepository<Participant, String> {
    // unused for now
}
