package fr.iutrodez.roadeo.controleurTest;

import fr.iutrodez.roadeo.controleur.UtilisateurApiControleur;
import fr.iutrodez.roadeo.modele.Utilisateur;
import fr.iutrodez.roadeo.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UtilisateurApiTest {
    private MockMvc mockMvc;

    @Mock
    private UtilisateurService utilisateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        UtilisateurApiControleur controleur = new UtilisateurApiControleur(utilisateurService);
        mockMvc = MockMvcBuilders.standaloneSetup(controleur).build();
    }

    @Test
    void testGetUtilisateur() throws Exception {
        Utilisateur u1 = new Utilisateur();
        Utilisateur u2 = new Utilisateur();

        when(utilisateurService.getAllUtilisateurs()).thenReturn(List.of(u1, u2));

        mockMvc.perform(get("/api/Utilisateur/liste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
