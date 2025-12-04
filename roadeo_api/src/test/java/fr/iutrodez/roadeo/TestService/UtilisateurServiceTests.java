package fr.iutrodez.roadeo.TestService;

import fr.iutrodez.roadeo.UtilisateurInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class UtilisateurServiceTests {

    @Mock
    private UtilisateurInterfaceMongoDB repository;

    private UtilisateurService service;

    private List<Utilisateur> expectedList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new UtilisateurService(repository);

        Utilisateur u1 = new Utilisateur("1","nom prenom","test","nomprenom@test.com","test");
        Utilisateur u2 = new Utilisateur("2","nom2 prenom2","test2","nom2prenom@test.com","test2");

        expectedList = Arrays.asList(u1, u2);
    }

    @Test
    void testGetAllUtilisateurs() {
        when(repository.findAll()).thenReturn(expectedList);

        List<Utilisateur> result = service.getAllUtilisateurs();

        assertEquals(expectedList, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetUtilisateurByIdCorrect() {
        when(repository.findById("1")).thenReturn(Optional.of(expectedList.get(0)));

        Utilisateur result = service.getUtilisateur("1");
        assertEquals(expectedList.get(0), result);
        verify(repository, times(1)).findById("1");
    }

    @Test
    void testGetUtilisateurByIdIncorrect() {
        when(repository.findById("999")).thenReturn(Optional.empty());

        Utilisateur result = service.getUtilisateur("999");
        assertNull(result);
        verify(repository, times(1)).findById("999");
    }

}
