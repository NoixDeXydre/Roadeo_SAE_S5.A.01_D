package fr.iutrodez.roadeo.TestService;

import fr.iutrodez.roadeo.dao.ParcoursInterfaceMongoDB;
import fr.iutrodez.roadeo.dao.RandoneeInterfaceMongoDB;
import fr.iutrodez.roadeo.dao.UtilisateurInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.*;
import fr.iutrodez.roadeo.service.RandonneeService;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RandonneeServiceTest {

    @Mock
    private RandoneeInterfaceMongoDB repository;

    @Mock
    private ParcoursInterfaceMongoDB parcoursRepository;

    private RandonneeService service;

    private ArrayList<Parcours> listeParcours;
    private List<Randonnee> expectedList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ArrayList<PointInteret> interet = new ArrayList<>();
        listeParcours = new ArrayList<>();
        Parcours parcours1 = new Parcours("1", "2", "1", "Parcours champètre", interet);
        Parcours parcours2 = new Parcours("2", "2", "1", "Parcours foret", interet);


        service = new RandonneeService(repository, parcoursRepository);
        listeParcours.add(parcours1);
        listeParcours.add(parcours2);
        interet.add(new PointInteret("prairie laitière", new double[]{15.0, 45.0}));
        interet.add(new PointInteret("prairie caverneuse", new double[]{20.5, 45.0}));

        Randonnee randonnee1 = new Randonnee("1", "randonnée", 2,
                new PointInteret("départ", new double[]{12.0,45.0}),
                new PointInteret("arrivee", new double[]{54.12,95.3}),
                new ArrayList<>());

        Randonnee randonnee2 = new Randonnee("2", "randonnée", 1,
                new PointInteret("départ", new double[]{12.0,45.0}),
                new PointInteret("arrivee", new double[]{54.12,95.3}),
                new ArrayList<>());

        expectedList = Arrays.asList(randonnee1, randonnee2);
    }

    @Test
    void testGetAllRandonnees() {
        when(repository.findAll()).thenReturn(expectedList);

        List<Randonnee> result = service.getAllRandonnees();

        assertEquals(expectedList, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetAllParcours () {
        //todo getAllParcours()
        when(parcoursRepository.findByIdRando("2")).thenReturn(listeParcours);

        List<Parcours> result = service.getParcoursByIdRando("2");

        assertEquals(listeParcours, result);
        verify(parcoursRepository, times(1)).findByIdRando("2");
    }

    @Test
    void testGetParticipantRandonnee() {
        //todo getParticipantRandonnee()
        Randonnee rando = null;
        when(repository.findById("2")).thenReturn(Optional.ofNullable(rando));

        List<Participant> result = service.getParticipantRandonnee("2");

        assertEquals(rando.getParticipants(), result);
        //verify(repository, times(1)).findAll();
        fail();
    }

    @Test
    void testGetParcoursByIdRando() {
        //todo getParcoursByIdRando(idRando)
        fail();
    }

    @Test
    void testgetRandonnee() {
        //todo getRandonnee(id)
        fail();
    }

    @Test
    void testAddRandonnee() {
        //todo addRandonnee(rando)
        fail();
    }

    @Test
    void testaddParcours() {
        //todo addParcours(id, parcours)
        fail();
    }

}
