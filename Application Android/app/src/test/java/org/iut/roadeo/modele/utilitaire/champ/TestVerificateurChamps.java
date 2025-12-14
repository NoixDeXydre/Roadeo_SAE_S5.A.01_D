package org.iut.roadeo.modele.utilitaire.champ;

import static org.iut.roadeo.Modele.Utilitaire.Champ.VerificateurChamps.*;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Classe de tests de la classe VerificateurChamps.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestVerificateurChamps {

    private final static String[] DONNEES_VIDES_EN_BLANK = {
        "   ", "", null
    };

    private final static String[] DONNEES_VIDES_EN_EMPTY = {
        "", null
    };

    private final static String[] DONNEES_NON_VIDES_EN_BLANK = {
            "test ", " mon_mdp "
    };

    private final static String[] DONNEES_NON_VIDES_EN_EMPTY = {
        "  ", "test ", " mdp "
    };

    @Test
    public void testIsTexteNonVideBlankWithDonneesVides() {

        for (String chaineVide : DONNEES_VIDES_EN_BLANK) {
            assertFalse(isTexteNonVideBlank(chaineVide));
        }
    }

    @Test
    public void testIsTexteNonVideBlankWithDonneesNonVides() {

        for (String chaineVide : DONNEES_NON_VIDES_EN_BLANK) {
            assertTrue(isTexteNonVideBlank(chaineVide));
        }
    }

    @Test
    public void testIsTexteNonVideEmptyWithDonneesVides() {

        for (String chaineVide : DONNEES_VIDES_EN_EMPTY) {
            assertFalse(isTexteNonVideEmpty(chaineVide));
        }
    }

    @Test
    public void testIsTexteNonVideEmptyWithDonneesNonVides() {

        for (String chaineVide : DONNEES_NON_VIDES_EN_EMPTY) {
            assertTrue(isTexteNonVideEmpty(chaineVide));
        }
    }
}
