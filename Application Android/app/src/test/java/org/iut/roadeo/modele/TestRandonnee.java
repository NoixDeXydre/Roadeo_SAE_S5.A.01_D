package org.iut.roadeo.modele;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Classe de tests de la classe Randonnee
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestRandonnee {

    @Test
    public void testRandonneeErreurSiLibelleNull() {

        try {
            new Randonnee(1, null, 1, null, null);
            fail("Le libellé ne peut pas être null.");
        } catch (IllegalArgumentException e) { }

        try {
            new Randonnee(1, "", 1,null, null);
            fail("Le libellé ne peut pas être vide.");
        } catch (IllegalArgumentException e) { }

        try {
            new Randonnee(1, " ", 1, null, null);
            fail("Le libellé ne peut pas être vide (même avec des espaces.)");
        } catch (IllegalArgumentException e) { }
    }

    @Test
    public void testRandonneeErreurNbJours() {

        try {
            new Randonnee(1, "Ma randonnée", Randonnee.NB_JOURS_MIN-1, null,
                          null);
            fail("Le nombre de participant max est invalide");
        } catch (IllegalArgumentException e) { }

        try {
            new Randonnee(1, "Ma randonnée", Randonnee.NB_JOURS_MAX+1, null,
                          null);
            fail("Le nombre de participant max est trop grand pour être valide");
        } catch (IllegalArgumentException e) { }

    }

    @Test
    public void testRandonneeSucces() {

        try {
            new Randonnee(1, "Ma randonnée", 1, null, null);
        } catch (IllegalArgumentException e) {
            fail("La randonnée devrait être construite à ce point.");
        }
    }
}
