package fr.iutrodez.roadeo.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ObjetTest {

    private Objet ojetTest;

    @BeforeEach
    void setup() {

    }

    @Test
    @DisplayName("Test du constructeur avec valeur incorrecte")
    public void constructeurTest() {
        assertThrows(IllegalArgumentException.class, () -> new Objet("nom", "", 5.0, 2.0, 1));
    }

}
