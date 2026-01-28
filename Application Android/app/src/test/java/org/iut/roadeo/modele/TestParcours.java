package org.iut.roadeo.modele;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.iut.roadeo.Modele.Randonnee;
import org.iut.roadeo.Modele.Parcours;
import org.junit.Test;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Classe de tests de la classe Parcours
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestParcours {

    @Test
    public void testParcoursEchecDateNull()
    {

        try {
            new Parcours(new Randonnee
                    ("Ma randonnée",1, null, null), null);
            fail("Le parcours ne devrait pas être valide si pas de date.");
        } catch (IllegalArgumentException e) { }
    }

    @Test
    public void testParcoursEchecRandonneeNull() {

        try {
            new Parcours(null, new Date());
            fail("Le parcours ne devrait pas être valide si pas de randonnée liée.");
        } catch (IllegalArgumentException e) { }
    }

    @Test
    public void testParcoursSucces() {

        try {
            new Parcours(new Randonnee("Ma randonnée", 1, null, null),
                    new Date());
        } catch (IllegalArgumentException e) {
            fail("Le parcours est en théorie valide et devrait se créer.");
        }
    }

    @Test
    public void testAjouterPointInteretNull() {

        Parcours parcours = new Parcours(new Randonnee("Mon parcours", 1,
                null, null), new Date());

        parcours.ajouterPointInteret(null);
        assertEquals(parcours.getPointsInteret().size(), 0);
    }

    @Test
    public void testAjouterPointInteret() {

        Parcours parcours = new Parcours(new Randonnee("Mon parcours", 1,
                null, null), new Date());

        ArrayList<GeoPoint> points = new ArrayList<>
                (Arrays.asList(new GeoPoint(0.0f, 0.1f),
                        new GeoPoint(0.1f, 0.0f)));

        parcours.ajouterPointInteret(points.get(0));
        parcours.ajouterPointInteret(points.get(1));

        assertEquals(parcours.getPointsInteret().size(), points.size());
        assertArrayEquals(parcours.getPointsInteret().toArray(), points.toArray());
    }
}