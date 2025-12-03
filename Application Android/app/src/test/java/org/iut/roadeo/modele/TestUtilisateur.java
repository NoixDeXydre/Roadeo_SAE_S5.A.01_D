package org.iut.roadeo.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.iut.roadeo.Modele.Utilisateur;
import org.junit.Test;

/**
 * Classe de tests de la classe Utilisateur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestUtilisateur {

    @Test
    public void constructeurInfoCorrect() {
        // Given un utilisateur
        Utilisateur util;

        // WHEN toutes les informations sont correctes
        util = new Utilisateur("Marsenac", "Marcel", "motDePasse", "email@mail.com", "ici");

        // THEN les données sont crées et disponibles
        assertEquals("Marsenac", util.getNom());
    }

    @Test
    public void constructeurInfoInCorrectNull() {
        // Given un utilisateur
        Utilisateur util;

        // WHEN une information est incorrectes les informations sont null
        String infosNull = "";
        String infosVide = " ";

        // THEN Une erreur IllegalArgumentException est levée
        // pour le nom
        assertThrows(IllegalArgumentException.class,
                 () -> new Utilisateur(infosNull, "Marcel",
                            "motDePasse", "email@mail.com", "ici"));

        // pour le prenom
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("Marcenac", infosNull,
                        "motDePasse", "email@mail.com", "ici"));

        // pour le mot de passe
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("Marcenac", "Marcenac",
                        infosNull, "email@mail.com", "ici"));

        // pour l'email
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("Marcenac", "Marcenac",
                       "motDePasse", infosNull, "ici"));

        // pour le lieu
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("Marcenac", "Marcenac",
                        "motDePasse", "email@mail.com", infosNull));
    }

    @Test
    public void constructeurInfoInCorrectVide() {
        // Given un utilisateur
        Utilisateur util;

        // WHEN une information est incorrectes les informations sont vides
        String infosVide = " ";

        // THEN Une erreur IllegalArgumentException est levée
        // pour le nom
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur(infosVide, "Marcel",
                        "motDePasse", "email@mail.com", "ici"));

        // pour le prenom
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("Marcenac", infosVide,
                        "motDePasse", "email@mail.com", "ici"));


        // pour le mot de passe
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("Marcenac", "Marcenac",
                        infosVide, "email@mail.com", "ici"));

        // pour l'email
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("Marcenac", "Marcenac",
                        "motDePasse", infosVide, "ici"));

        // pour le lieu
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("Marcenac", "Marcenac",
                        "motDePasse", "email@mail.com", infosVide));
    }

    @Test
    public void TestGetMDP() {
        Utilisateur util = new Utilisateur("Marsenac", "Marcel", "motDePasse", "email@mail.com", "ici");
    }

    @Test
    public void TestGetEmail() {

    }

    @Test
    public void TestGetDomicile() {

    }

    @Test
    public void TestSetMDP() {

    }

    @Test
    public void TestSetEmail() {

    }

    @Test
    public void TestSetDomicile() {

    }

}