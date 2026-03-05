package org.iut.roadeo.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.iut.roadeo.modele.typedonnees.Morphologie;
import org.iut.roadeo.modele.typedonnees.NiveauEntrainement;
import org.junit.Test;

import java.util.Date;

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
        util = new Utilisateur("", "Marsenac", "Marcel", 20,
                NiveauEntrainement.SPORTIF,
                Morphologie.FORT, "motDePasse",
                          "email@mail.com", "ici");

        // THEN les données sont crées et disponibles
        assertEquals("Marsenac", util.getNom());
    }

    @Test
    public void constructeurInfoIncorrectNull() {
        // Given un utilisateur
        Utilisateur util;

        // WHEN une information est incorrectes les informations sont null
        String infoNull = null;

        // THEN Une erreur IllegalArgumentException est levée

        // pour le mot de passe
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("", "Marcenac", "Marcel", 20,
                        NiveauEntrainement.SPORTIF,
                        Morphologie.FORT,
                        infoNull, "email@mail.com", "ici"));

        // pour l'email
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("", "Marcenac", "Marcel", 20,
                        NiveauEntrainement.SPORTIF,
                        Morphologie.FORT,
                       "motDePasse", infoNull, "ici"));

        // pour le lieu
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("", "Marcenac", "Marcel", 20,
                        NiveauEntrainement.SPORTIF,
                        Morphologie.FORT,
                        "motDePasse", "email@mail.com", infoNull));
    }

    @Test
    public void constructeurInfoInCorrectVide() {
        // Given un utilisateur
        Utilisateur util;

        // WHEN une information est incorrectes les informations sont vides
        String infosVide = "";

        // THEN Une erreur IllegalArgumentException est levée

        // pour le mot de passe
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("", "Marcenac", "Marcel", 20,
                        NiveauEntrainement.SPORTIF,
                        Morphologie.FORT,
                        infosVide, "email@mail.com", "ici"));

        // pour l'email
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("", "Marcenac", "Marcel", 20,
                        NiveauEntrainement.SPORTIF,
                        Morphologie.FORT,
                        "motDePasse", infosVide, "ici"));

        // pour le lieu
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("", "Marcenac", "Marcel", 20,
                        NiveauEntrainement.SPORTIF,
                        Morphologie.FORT,
                        "motDePasse", "email@mail.com", infosVide));
    }

    @Test
    public void constructeurInfoInCorrectBlanc() {
        // Given un utilisateur
        Utilisateur util;

        // WHEN une information est incorrectes les informations sont vides
        String infosBlanc = " ";

        // THEN Une erreur IllegalArgumentException est levée

        // pour le mot de passe
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("", "Marcenac", "Marcel", 20,
                        NiveauEntrainement.SPORTIF,
                        Morphologie.FORT,
                        infosBlanc, "email@mail.com", "ici"));

        // pour l'email
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("", "Marcenac", "Marcel", 20,
                        NiveauEntrainement.SPORTIF,
                        Morphologie.FORT,
                        "motDePasse", infosBlanc, "ici"));

        // pour le lieu
        assertThrows(IllegalArgumentException.class,
                () -> new Utilisateur("", "Marcenac", "Marcel", 20,
                        NiveauEntrainement.SPORTIF,
                        Morphologie.FORT,
                        "motDePasse", "email@mail.com", infosBlanc));
    }
    @Test
    public void testGetMDP() {
        Utilisateur util = new Utilisateur("", "Marsenac", "Marcel", 20,
                                           NiveauEntrainement.SPORTIF,
                                           Morphologie.FORT,
                                      "motDePasse", "email@mail.com",
                                    "ici");

        assertEquals("motDePasse", util.getMotDePasse());
    }

    @Test
    public void testGetEmail() {
        Utilisateur util = new Utilisateur("", "Marsenac", "Marcel", 20,
                                           NiveauEntrainement.SPORTIF,
                                           Morphologie.FORT,
                                      "motDePasse", "email@mail.com",
                                    "ici");

        assertEquals("email@mail.com", util.getEmail());
    }

    @Test
    public void testGetDomicile() {
        Utilisateur util = new Utilisateur("", "Marsenac", "Marcel", 20,
                                           NiveauEntrainement.SPORTIF,
                                           Morphologie.FORT,
                                      "motDePasse", "email@mail.com",
                                    "ici");

        assertEquals("ici", util.getDomicile());
    }

    @Test
    public void testSetMDP() {
        Utilisateur util = new Utilisateur("", "Marsenac", "Marcel", 20,
                                           NiveauEntrainement.SPORTIF,
                                           Morphologie.FORT,
                                           "motDePasse", "email@mail.com",
                                           "ici");

        assertThrows(IllegalArgumentException.class,
                     ()->util.setMotDePasse(null));
        assertThrows(IllegalArgumentException.class,
                     ()->util.setMotDePasse(""));
        assertThrows(IllegalArgumentException.class,
                     ()->util.setMotDePasse(" "));
    }

    @Test
    public void testSetEmail() {
        Utilisateur util = new Utilisateur("", "Marsenac", "Marcel", 20,
                                            NiveauEntrainement.SPORTIF,
                                            Morphologie.FORT,
                                            "motDePasse", "email@mail.com",
                                            "ici");

        assertThrows(IllegalArgumentException.class,
                ()->util.setEmail(null));
        assertThrows(IllegalArgumentException.class,
                ()->util.setEmail(""));
        assertThrows(IllegalArgumentException.class,
                ()->util.setEmail(" "));

    }

    @Test
    public void testSetDomicile() {
        Utilisateur util = new Utilisateur("", "Marsenac", "Marcel", 20,
                                            NiveauEntrainement.SPORTIF,
                                            Morphologie.FORT,
                                            "motDePasse", "email@mail.com",
                                            "ici");

        assertThrows(IllegalArgumentException.class,
                ()->util.setDomicile(null));
        assertThrows(IllegalArgumentException.class,
                ()->util.setDomicile(""));
        assertThrows(IllegalArgumentException.class,
                ()->util.setDomicile(" "));
    }

    @Test
    public void testAjouterParcoursNull() {

        Parcours parcours = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "parcours");

        Utilisateur ut = new Utilisateur("", "a", "a", 1,
                NiveauEntrainement.DEBUTANT, Morphologie.LEGER, "a", "a", "a");

        ut.ajouterParcours(null);
        assertEquals(parcours.getPointsInteret().size(), 0);
    }

    @Test
    public void testAjouterParcoursSucces() {

        Parcours parcours1 = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "parcours");
        Parcours parcours2 = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "parcours");

        Utilisateur ut = new Utilisateur("", "a", "a", 1,
                NiveauEntrainement.DEBUTANT, Morphologie.LEGER, "a", "a", "a");

        ut.ajouterParcours(parcours1);
        ut.ajouterParcours(parcours2);

        assertEquals(ut.getParcours().size(), 2);
    }
}