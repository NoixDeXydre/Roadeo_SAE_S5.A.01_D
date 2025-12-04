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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UtilisateurServiceTests {

    @Mock
    private UtilisateurInterfaceMongoDB repository;

    private UtilisateurService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new UtilisateurService(repository);
    }

    @Test
    void testGetAllUtilisateurs() {
        Utilisateur u1 = new Utilisateur("1","nom prenom","test","nomprenom@test.com","test");
        Utilisateur u2 = new Utilisateur("2","nom2 prenom2","test2","nom2prenom@test.com","test2");

        List<Utilisateur> expectedList = Arrays.asList(u1, u2);

        when(repository.findAll()).thenReturn(expectedList);

        List<Utilisateur> result = service.getAllUtilisateurs();

        assertEquals(expectedList, result);
        verify(repository, times(1)).findAll();
    }

}
