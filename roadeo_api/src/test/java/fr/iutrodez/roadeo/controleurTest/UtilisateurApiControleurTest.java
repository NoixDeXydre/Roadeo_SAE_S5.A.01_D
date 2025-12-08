package fr.iutrodez.roadeo.controleurTest;

import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.controleur.UtilisateurApiControleur;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UtilisateurApiControleurTest {
    @Mock
    private UtilisateurService utilisateurService;

    private UtilisateurApiControleur controleur;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controleur = new UtilisateurApiControleur(utilisateurService);
    }

    @Test
    void testGetUtilisateur() {
        Utilisateur u1 = new Utilisateur();
        Utilisateur u2 = new Utilisateur();
        List<Utilisateur> utilisateurs = Arrays.asList(u1, u2);

        when(utilisateurService.getAllUtilisateurs()).thenReturn(utilisateurs);

        List<Utilisateur> result = controleur.getUtilisateur();

        assertEquals(utilisateurs, result);
        verify(utilisateurService, times(1)).getAllUtilisateurs();
    }
}
