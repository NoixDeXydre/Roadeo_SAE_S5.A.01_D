package fr.iutrodez.roadeo.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandonneeTest {

    private Randonnee randoTest;

    @BeforeEach
    void setUp() {
        randoTest = new Randonnee("1","La montagne Noire", 3, 1,
                                  new PointInteret("Depart", new double[]{0.0,0.0}),
                                  new PointInteret("Arrive", new double[]{0.0,0.0}));
    }

    @Test
    @DisplayName("Test du constructeur avec valeur incorrecte")
    public void randonneeControleurIncorrectTest(){
        assertThrows(IllegalArgumentException.class,
                    () -> new Randonnee("1","",
                             3, 1,
                             new PointInteret("Depart", new double[]{0.0,0.0}),
                             new PointInteret("Arrive", new double[]{0.0,0.0})));
        assertThrows(IllegalArgumentException.class,
                    () -> new Randonnee("1","    ",
                            3, 1,
                            new PointInteret("Depart", new double[]{0.0,0.0}),
                            new PointInteret("Arrive", new double[]{0.0,0.0})));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test",
                        -20, 1,
                        new PointInteret("Depart", new double[]{0.0,0.0}),
                        new PointInteret("Arrive", new double[]{0.0,0.0})));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test",
                        0, 1,
                        new PointInteret("Depart", new double[]{0.0,0.0}),
                        new PointInteret("Arrive", new double[]{0.0,0.0})));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test",
                        4, 1,
                        new PointInteret("Depart", new double[]{0.0,0.0}),
                        new PointInteret("Arrive", new double[]{0.0,0.0})));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test",
                        45, 1,
                        new PointInteret("Depart", new double[]{0.0,0.0}),
                        new PointInteret("Arrive", new double[]{0.0,0.0})));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test",
                        3, -5,
                        new PointInteret("Depart", new double[]{0.0,0.0}),
                        new PointInteret("Arrive", new double[]{0.0,0.0})));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test",
                        3, 0,
                        new PointInteret("Depart", new double[]{0.0,0.0}),
                        new PointInteret("Arrive", new double[]{0.0,0.0})));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test",
                        0, 4,
                        new PointInteret("Depart", new double[]{0.0,0.0}),
                        new PointInteret("Arrive", new double[]{0.0,0.0})));
        assertThrows(IllegalArgumentException.class,
                () -> new Randonnee("1","Randonnée test",
                        0, 10,
                        new PointInteret("Depart", new double[]{0.0,0.0}),
                        new PointInteret("Arrive", new double[]{0.0,0.0})));
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
