package fr.iutrodez.roadeo.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilisateurTest {

    private Utilisateur utilTest;

    @BeforeEach
    void setUp() {
        utilTest = new Utilisateur("1", "1234", "jean-miche@gmail.com",
                                   "IUT Rodez", "Jean", "Michel", 40,
                                   "Sportif", "Fort");
    }

    @Test
    @DisplayName("Test du constructeur avec valeur correcte")
    public void utilisateurTest(){
        Utilisateur util = new Utilisateur("1", "1234", "jean-miche@gmail.com",
                                           "IUT Rodez", "Jean", "Michel", 40,
                                           "Sportif", "Fort");
        assertEquals("1", util.getId());
    }

    @Test
    @DisplayName("Test du getter de id")
    public void getIdTest(){
        assertEquals("1", utilTest.getId());
    }

    @Test
    @DisplayName("Test du getter de patronyme")
    public void getNomTest(){
        assertEquals("nom prenom", utilTest.getPatronyme());
    }

    @Test
    @DisplayName("Test du getter de mdp")
    public void getMdpTest(){
        assertEquals("test", utilTest.getMdp());
    }

    @Test
    @DisplayName("Test du getter de adresse mail")
    public void getMailTest(){
        assertEquals("nomprenom@test.com", utilTest.getAdresseMail());
    }

    @Test
    @DisplayName("Test du getter de domicile")
    public void getDomicileTest(){
        assertEquals("test", utilTest.getDomicile());
    }
}
