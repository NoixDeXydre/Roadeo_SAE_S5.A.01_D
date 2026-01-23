package org.iut.roadeo.modele;

import static org.junit.Assert.fail;

import org.iut.roadeo.Modele.Randonnee;
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
            new Randonnee(null, null, null);
            fail("Le libellé ne peut pas être null.");
        } catch (IllegalArgumentException e) { }

        try {
            new Randonnee("", null, null);
            fail("Le libellé ne peut pas être vide.");
        } catch (IllegalArgumentException e) { }

        try {
            new Randonnee(" ", null, null);
            fail("Le libellé ne peut pas être vide (même avec des espaces.)");
        } catch (IllegalArgumentException e) { }
    }

    @Test
    public void testRandonneeSucces() {

        try {
            new Randonnee("Ma randonnée", null, null);
        } catch (IllegalArgumentException e) {
            fail("La randonnée devrait être construite à ce point.");
        }
    }
}
