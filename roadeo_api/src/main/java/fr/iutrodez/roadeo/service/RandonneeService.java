package fr.iutrodez.roadeo.service;

import fr.iutrodez.roadeo.dao.ParcoursInterfaceMongoDB;
import fr.iutrodez.roadeo.dao.RandoneeInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Parcours;
import fr.iutrodez.roadeo.modele.Randonnee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandonneeService {
    private final RandoneeInterfaceMongoDB repository;
    private final ParcoursInterfaceMongoDB parcoursRepository;

    public RandonneeService(RandoneeInterfaceMongoDB repository,  ParcoursInterfaceMongoDB parcoursRepository) {
        this.repository = repository;
        this.parcoursRepository = parcoursRepository;
    }

    public List<Randonnee> getAllRandonnees() {
        return repository.findAll();
    }

    public List<Parcours> getAllParcours() {
        return parcoursRepository.findAll();
    }
}
