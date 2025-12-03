package org.iut.roadeo.modele;

import org.iut.roadeo.Modele.Randonneur;
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
    public void testIdentifiantRandonneurCorrect() {

        try {
            new Randonneur("Marcel", "Marcenac");
        } catch (IllegalArgumentException e) {
            // Pas de comportement par défaut.
        }
    }

    @Test
    public void testNomVideInvalide() {

        try {
            new Randonneur(null, "Marcenac");
            fail("Le nom d'un randonneur ne devrait pas être null.");
        } catch (IllegalArgumentException e) {
            // Pas de comportement par défaut.
        }

        try {
            new Randonneur("", "Marcenac");
            fail("Le nom d'un randonneur ne devrait pas être vide.");
        } catch (IllegalArgumentException e) {
            // Pas de comportement par défaut.
        }
    }

    @Test
    public void testPrenomVideInvalide() throws IllegalArgumentException {

        try {
            new Randonneur("Marcel", null);
            fail("Le prénom d'un randonneur ne devrait pas être null.");
        } catch (IllegalArgumentException e) {
            // Pas de comportement par défaut.
        }

        try {
            new Randonneur("Marcel", "");
            fail("Le prénom d'un randonneur ne devrait pas être vide.");
        } catch (IllegalArgumentException e) {
            // Pas de comportement par défaut.
        }
    }

    @Test
    public void testGetters() {

        Randonneur r = new Randonneur("Marcel", "Marcenac");

        assertEquals("Marcel", r.getNom());
        assertEquals("Marcenac", r.getPrenom());
    }

    @Test
    public void testSetterNomValide() {

        Randonneur r = new Randonneur("Marcel", "Marcenac");

        r.setNom("Dupont");
        assertEquals("Dupont", r.getNom());
    }

    @Test
    public void testSetterNomInvalide() {

        Randonneur r = new Randonneur("Marcel", "Marcenac");

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

        Randonneur r = new Randonneur("Marcel", "Marcenac");

        r.setPrenom("Paul");
        assertEquals("Paul", r.getPrenom());
    }

    @Test
    public void testSetterPrenomInvalide() {

        Randonneur r = new Randonneur("Marcel", "Marcenac");

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
}