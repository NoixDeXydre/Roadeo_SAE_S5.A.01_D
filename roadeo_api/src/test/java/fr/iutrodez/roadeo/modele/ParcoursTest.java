package fr.iutrodez.roadeo.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParcoursTest {

    private Parcours randoTest;

    @BeforeEach
    void setUp() {
        randoTest = new Parcours("1", "1","3", "Balade insolite à l'IUT de Rodez", new ArrayList<PointInteret>());
    }

    @Test
    @DisplayName("Test du constructeur avec valeur incorrecte")
    public void parcoursControleurVideTest(){
        assertThrows(IllegalArgumentException.class, () -> new Parcours("1", "1","", "Balade insolite à l'IUT de Rodez", new ArrayList<PointInteret>()));
        assertThrows(IllegalArgumentException.class, () -> new Parcours("1", "1","3", "", new ArrayList<PointInteret>()));
    }

    @Test
    @DisplayName("Test du getter de id")
    public void getIdRandoTest() {
        assertEquals("1", randoTest.getIdRando());
    }

    @Test
    @DisplayName("Test du getter de libelle")
    public void getLibelleTest() {
        assertEquals("Balade insolite à l'IUT de Rodez", randoTest.getLibelleRandonnee());
    }

    @Test
    @DisplayName("Test du getter de l'id utilisateur")
    public void getIdRandoUtilisateurTest() {
        assertEquals("3", randoTest.getIdUtilisateur());
    }
}
