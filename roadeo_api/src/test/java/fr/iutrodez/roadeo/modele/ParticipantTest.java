package fr.iutrodez.roadeo.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParticipantTest {
    private Participant participantTest;

    @BeforeEach
    void setUp() {
        participantTest = new Participant("Marcel Jr","Marcenelle", 12, "Debutant", "Legere");
    }

    @Test
    @DisplayName("Test du constructeur avec valeur incorrecte")
    public void participantControleurVideTest(){
        //assertThrows(IllegalArgumentException.class, () -> new Participant("Marcel Jr","Marcenelle", 12, "Debutant", "Legere"););
        assertThrows(IllegalArgumentException.class, () -> new Participant("","Marcenelle", 12, "Debutant", "Legere"));
        assertThrows(IllegalArgumentException.class, () -> new Participant("Marcel Jr","", 12, "Debutant", "Legere"));
        assertThrows(IllegalArgumentException.class, () -> new Participant("Marcel Jr","Marcenelle", -1, "Debutant", "Legere"));
        assertThrows(IllegalArgumentException.class, () -> new Participant("Marcel Jr","Marcenelle", 12, "", "Legere"));
        assertThrows(IllegalArgumentException.class, () -> new Participant("Marcel Jr","Marcenelle", 12, "Debutant", ""));
    }

    @Test
    @DisplayName("Test du getter de id")
    public void getIdTest(){
        assertEquals("Marcel Jr", participantTest.getNom());
    }

    @Test
    @DisplayName("Test du getter du prenom")
    public void getNomTest(){
        assertEquals("Marcenelle", participantTest.getPrenom());
    }

    @Test
    @DisplayName("Test du getter de l'age")
    public void getAgeTest() {
        assertEquals(12, participantTest.getAge());
    }

    @Test
    @DisplayName("Test du getter du niveau d'entrainement")
    public void getNiveauEntrainementTest() {
        assertEquals("Debutant", participantTest.getNiveauEntrainement());
    }

    @Test
    @DisplayName("Test du getter de la morphologie")
    public void getMorphologieTest() {
        assertEquals("Legere", participantTest.getMorphologie());
    }
}
