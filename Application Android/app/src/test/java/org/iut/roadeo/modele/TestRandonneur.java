package org.iut.roadeo.modele;

import org.iut.roadeo.Modele.Randonneur;
import org.iut.roadeo.Modele.TypeDonnees.Morphologie;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de tests de la classe Randonneur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestRandonneur {

    @Test
    public void testRandonneurCorrect() {

        try {
            new Randonneur("Marcel", "Marcenac", 20, NiveauEntrainement.SPORTIF,
                            Morphologie.FORT);
        } catch (IllegalArgumentException e) {
            // Pas de comportement par défaut.
        }
    }

    @Test
    public void testNomVideInvalide() {

        try {
            new Randonneur(null, "Marcenac", 20, NiveauEntrainement.SPORTIF,
                            Morphologie.FORT);
            fail("Le nom d'un randonneur ne devrait pas être null.");
        } catch (IllegalArgumentException e) {
            // Pas de comportement par défaut.
        }

        try {
            new Randonneur("", "Marcenac", 20, NiveauEntrainement.SPORTIF,
                            Morphologie.FORT);
            fail("Le nom d'un randonneur ne devrait pas être vide.");
        } catch (IllegalArgumentException e) {
            // Pas de comportement par défaut.
        }
    }

    @Test
    public void testPrenomVideInvalide() throws IllegalArgumentException {

        try {
            new Randonneur("Marcel", null, 20, NiveauEntrainement.SPORTIF,
                            Morphologie.FORT);
            fail("Le prénom d'un randonneur ne devrait pas être null.");
        } catch (IllegalArgumentException e) {
            // Pas de comportement par défaut.
        }

        try {
            new Randonneur("Marcel", "", 20, NiveauEntrainement.SPORTIF,
                            Morphologie.FORT);
            fail("Le prénom d'un randonneur ne devrait pas être vide.");
        } catch (IllegalArgumentException e) {
            // Pas de comportement par défaut.
        }
    }

    @Test
    public void testGetters() {

        Randonneur r = new Randonneur("Marcel", "Marcenac", 20,
                                      NiveauEntrainement.SPORTIF,
                                      Morphologie.FORT);

        assertEquals("Marcel", r.getNom());
        assertEquals("Marcenac", r.getPrenom());
        assertEquals(20, r.getAge());
        assertEquals(NiveauEntrainement.SPORTIF, r.getNiveauEntrainement());
        assertEquals(Morphologie.FORT, r.getMorphologie());
    }

    @Test
    public void testSetterNomValide() {

        Randonneur r = new Randonneur("Marcel", "Marcenac", 20,
                                      NiveauEntrainement.SPORTIF,
                                      Morphologie.FORT);

        r.setNom("Dupont");
        assertEquals("Dupont", r.getNom());
    }

    @Test
    public void testSetterNomInvalide() {

        Randonneur r = new Randonneur("Marcel", "Marcenac", 20,
                                      NiveauEntrainement.SPORTIF,
                                      Morphologie.FORT);

        try {
            r.setNom(null);
            fail("Le nom d'un randonneur ne doit pas être null.");
        } catch (IllegalArgumentException e) {
            // Comportement attendu
        }

        try {
            r.setNom("");
            fail("Le nom d'un randonneur ne doit pas être vide.");
        } catch (IllegalArgumentException e) {
            // Comportement attendu
        }
    }

    @Test
    public void testSetterPrenomValide() {

        Randonneur r = new Randonneur("Marcel", "Marcenac", 20,
                                      NiveauEntrainement.SPORTIF,
                                      Morphologie.FORT);

        r.setPrenom("Paul");
        assertEquals("Paul", r.getPrenom());
    }

    @Test
    public void testSetterPrenomInvalide() {

        Randonneur r = new Randonneur("Marcel", "Marcenac", 20,
                                      NiveauEntrainement.SPORTIF,
                                      Morphologie.FORT);

        try {
            r.setPrenom(null);
            fail("Le prénom d'un randonneur ne doit pas être null.");
        } catch (IllegalArgumentException e) {
            // Comportement attendu
        }

        try {
            r.setPrenom("");
            fail("Le prénom d'un randonneur ne doit pas être vide.");
        } catch (IllegalArgumentException e) {
            // Comportement attendu
        }
    }

    @Test
    public void testAgeInvalide() {

        try {
            new Randonneur("Marcel", "Marcenac", 0, NiveauEntrainement.DEBUTANT,
                            Morphologie.LEGER);
            fail("L'âge ne devrait pas être inférieur à 1.");
        } catch (IllegalArgumentException e) {}

        try {
            new Randonneur("Marcel", "Marcenac", 121, NiveauEntrainement.DEBUTANT,
                            Morphologie.LEGER);
            fail("L'âge ne devrait pas être supérieur à 120.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testNiveauEntrainementInvalide() {

        try {
            new Randonneur("Marcel", "Marcenac", 25, null, Morphologie.FORT);
            fail("Le niveau d'entraînement ne devrait pas être null.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testMorphologieInvalide() {

        try {
            new Randonneur("Marcel", "Marcenac", 25, NiveauEntrainement.DEBUTANT,
                        null);
            fail("La morphologie ne devrait pas être null.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testSettersAge() {

        Randonneur r =
                new Randonneur("Marcel", "Marcenac", 40,
                               NiveauEntrainement.ENTRAINE, Morphologie.MOYEN);

        r.setAge(20);
        assertEquals(20, r.getAge());

        try {
            r.setAge(0);
            fail("L'âge ne doit jamais être inférieur à 1.");
        } catch (IllegalArgumentException e) {}

        try {
            r.setAge(150);
            fail("L'âge ne doit jamais être supérieur à 120.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testSettersNiveau() {

        Randonneur r =
                new Randonneur("Marcel", "Marcenac", 30,
                               NiveauEntrainement.ENTRAINE, Morphologie.LEGER);

        r.setNiveauEntrainement(NiveauEntrainement.SPORTIF);
        assertEquals(NiveauEntrainement.SPORTIF, r.getNiveauEntrainement());

        try {
            r.setNiveauEntrainement(null);
            fail("Le niveau d'entraînement ne doit pas être null.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testSettersMorphologie() {

        Randonneur r =
                new Randonneur("Marcel", "Marcenac", 30,
                               NiveauEntrainement.ENTRAINE, Morphologie.LEGER);

        r.setMorphologie(Morphologie.FORT);
        assertEquals(Morphologie.FORT, r.getMorphologie());

        try {
            r.setMorphologie(null);
            fail("La morphologie ne doit pas être null.");
        } catch (IllegalArgumentException e) {}
    }
}