package fr.iutrodez.roadeo.controleurTest;

import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.controleur.UtilisateurApiControleur;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilisateurApiControleurTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
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

    @Test
    void testSeConnecterUtilisateurSucces() {

        Utilisateur u1 = new Utilisateur("1", "1234", "jean-miche@gmail.com",
                                         "IUT Rodez", "Jean", "Michel", 40,
                                         "Sportif", "Fort");

        when(utilisateurService.validerConnexion(u1.getAdresseMail(), u1.getMdp())).thenReturn(u1);

        Utilisateur resultat = controleur.utilisateurService.validerConnexion
                (u1.getAdresseMail(), u1.getMdp());

        assertEquals(u1, resultat);
        verify(utilisateurService, times(1))
                .validerConnexion(u1.getAdresseMail(), u1.getMdp());
    }

    @Test
    void testSeConnecterUtilisateurEchec() throws Exception {

        Utilisateur u1 = new Utilisateur("1", "1234", "jean-miche@gmail.com",
                                         "IUT Rodez", "Jean", "Michel", 40,
                                         "Sportif", "Fort");

        when(utilisateurService.validerConnexion("mauvais-email@g.com","non"))
                .thenReturn(null);

        mockMvc.perform(post("/api/Utilisateur/seConnecter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "adresseMail": "mauvais-email@g.com",
                            "mdp": "non"
                        }
                        """))
                .andExpect(status().isUnauthorized());
    }
}