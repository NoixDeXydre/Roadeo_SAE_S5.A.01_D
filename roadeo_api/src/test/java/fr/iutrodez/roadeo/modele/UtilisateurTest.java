package fr.iutrodez.roadeo.modele;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
public class UtilisateurTest {

    @Test
    @DisplayName("Test du constructeur avec valeur correcte")
    public void utilisateurTest(){
        Utilisateur util = new Utilisateur(1,"nom prenom","test","nomprenom@test.com","test");
        assertEquals(1, util.getId());
    }
}
