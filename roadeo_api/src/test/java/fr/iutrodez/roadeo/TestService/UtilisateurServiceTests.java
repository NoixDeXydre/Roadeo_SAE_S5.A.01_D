package fr.iutrodez.roadeo.TestService;

import fr.iutrodez.roadeo.dao.UtilisateurInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

        Utilisateur u1 = new Utilisateur("1", "1234", "jean-miche@gmail.com",
                "IUT Rodez", "Jean", "Michel", 40,
                "Sportif", "Fort");
        Utilisateur u2 = new Utilisateur("2", "1234", "jean-jacques@gmail.com",
                                         "IUT Rodez", "Jean", "Jacques", 40,
                                         "Sportif", "Fort");

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
    void testValiderConnexionSucces() {

        // Simuler le comportement du repository
        when(repository.findOne(any(Example.class))).thenReturn(Optional.of(expectedList.get(0)));

        Utilisateur result = service.validerConnexion("test@example.com", "mdp123");

        assertNotNull(result);
        assertEquals(expectedList.get(0).getAdresseMail(), result.getAdresseMail());
        assertEquals(expectedList.get(0).getMdp(), result.getMdp());

        verify(repository, times(1)).findOne(any(Example.class));
    }

    @Test
    void testValiderConnexionEchec() {

        // Simuler un login incorrect
        when(repository.findOne(any(Example.class))).thenReturn(Optional.empty());

        Utilisateur result = service.validerConnexion("wrong@example.com", "badpass");

        assertNull(result);
        verify(repository, times(1)).findOne(any(Example.class));
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
