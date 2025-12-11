package fr.iutrodez.roadeo.dao;

import fr.iutrodez.roadeo.modele.Parcours;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface ParcoursInterfaceMongoDB extends MongoRepository<Parcours, String> {

    ArrayList<Parcours> findByIdRando(String idRando);
}