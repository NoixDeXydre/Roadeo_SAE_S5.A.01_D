package org.iut.roadeo.modele;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Classe de tests de la classe Parcours
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestPointInteret {

    @Test
    public void testPointInteretLibelleInvalide() {
        // si libellé null
        assertThrows(IllegalArgumentException.class,
                     ()->new PointInteret(null, new double[]{0.0, 0.0}));

        // si libellé vide
        assertThrows(IllegalArgumentException.class,
                     ()->new PointInteret("", new double[]{0.0, 0.0}));

        // si libellé blanc
        assertThrows(IllegalArgumentException.class,
                     ()->new PointInteret("  ", new double[]{0.0, 0.0}));
    }

    @Test
    public void testPointInteretCoordoneesInvalide() {
        // si coordonnées null
        assertThrows(IllegalArgumentException.class,
                     ()->new PointInteret("test", null));

        // si nb coordonnées > 2
        assertThrows(IllegalArgumentException.class,
                     ()->new PointInteret("test", new double[]{}));
        assertThrows(IllegalArgumentException.class,
                     ()->new PointInteret("test", new double[]{0.0}));

        // si nb coordonnées < 2
        assertThrows(IllegalArgumentException.class,
                     ()->new PointInteret("test", new double[]{0.0, 0.0, 0.0}));
        assertThrows(IllegalArgumentException.class,
                     ()->new PointInteret("test",
                                          new double[]{0.0, 0.0, 0.0, 0.0, 0.0}));
    }

    @Test
    public void testPointInteretvalide() {
        try {
            new PointInteret("test", new double[]{0.0, 0.0});
        } catch (IllegalArgumentException e) {
            fail("Le point d'intérêt est en théorie valide et devrait se créer.");
        }
    }
}