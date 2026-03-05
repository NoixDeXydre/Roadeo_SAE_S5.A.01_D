package fr.iutrodez.roadeo.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandonneeTest {

    private Randonnee randoTest;

    @BeforeEach
    void setUp() {
        randoTest = new Randonnee("1","La montagne Noire", 3, new PointInteret("Depart",
                new PointGeo(0.0f, 0.0f)), new PointInteret("Arrive", new PointGeo(0.0f, 0.0f)),
                new ArrayList<Participant>());
    }

    @Test
    @DisplayName("Test du constructeur avec valeur incorrecte")
    public void randonneeControleurIncorrectTest(){
        assertThrows(IllegalArgumentException.class,
                    () -> new Randonnee("1","",
                             3,
                             new PointInteret("Depart", new PointGeo(0.0f, 0.0f)),
                             new PointInteret("Arrive", new PointGeo(0.0f, 0.0f)),
                            new ArrayList<Participant>()));
        assertThrows(IllegalArgumentException.class,
                    () -> new Randonnee("1","    ",
                            3,
                            new PointInteret("Depart", new PointGeo(0.0f, 0.0f)),
                            new PointInteret("Arrive", new PointGeo(0.0f, 0.0f))
                            , new ArrayList<Participant>()));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test",
                        -20,
                        new PointInteret("Depart", new PointGeo(0.0f, 0.0f)),
                        new PointInteret("Arrive", new PointGeo(0.0f, 0.0f)),
                        new ArrayList<Participant>()));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test",
                        0,
                        new PointInteret("Depart", new PointGeo(0.0f, 0.0f)),
                        new PointInteret("Arrive", new PointGeo(0.0f, 0.0f)),
                        new ArrayList<Participant>()));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test", 4,
                        new PointInteret("Depart", new PointGeo(0.0f, 0.0f)),
                        new PointInteret("Arrive", new PointGeo(0.0f, 0.0f)),
                        new ArrayList<Participant>()));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test", 45,
                        new PointInteret("Depart", new PointGeo(0.0f, 0.0f)),
                        new PointInteret("Arrive", new PointGeo(0.0f, 0.0f)),
                        new ArrayList<Participant>()));
    }

    @Test
    @DisplayName("Test du getter de id")
    public void getIdTest(){
        assertEquals("1", randoTest.getId());
    }

    @Test
    @DisplayName("Test du getter de libelle")
    public void getLibelleTest(){
        assertEquals("La montagne Noire", randoTest.getLibelle());
    }
}
