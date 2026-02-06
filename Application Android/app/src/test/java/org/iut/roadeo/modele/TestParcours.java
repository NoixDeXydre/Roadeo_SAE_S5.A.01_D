package org.iut.roadeo.modele;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.iut.roadeo.Modele.PointInteret;
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
                    (1,"Ma randonnée",1, null, null),
                    null, "test");
            fail("Le parcours ne devrait pas être valide si pas de date.");
        } catch (IllegalArgumentException e) { }
    }

    @Test
    public void testParcoursEchecRandonneeNull() {

        try {
            new Parcours(null, new Date(), "test");
            fail("Le parcours ne devrait pas être valide si pas de randonnée liée.");
        } catch (IllegalArgumentException e) { }
    }

    @Test
    public void testParcoursLibelleInvalide() {
        try {
            new Parcours(new Randonnee(1,"Ma randonnée", 1, null, null),
                    new Date(), null);
            fail("Le parcours ne devrait pas être valide si le libelle est null");
        } catch (IllegalArgumentException e) { }
        try {
            new Parcours(new Randonnee(1,"Ma randonnée", 1, null, null),
                    new Date(), "");
            fail("Le parcours ne devrait pas être valide si le libelle est vide");
        } catch (IllegalArgumentException e) { }
        try {
            new Parcours(new Randonnee(1,"Ma randonnée", 1, null, null),
                    new Date(), "  ");
            fail("Le parcours ne devrait pas être valide si le libelle est blanc");
        } catch (IllegalArgumentException e) { }
    }

    @Test
    public void testParcoursSucces() {

        try {
            new Parcours(new Randonnee(1,"Ma randonnée", 1, null, null),
                    new Date(), "test");
        } catch (IllegalArgumentException e) {
            fail("Le parcours est en théorie valide et devrait se créer.");
        }
    }

    @Test
    public void testAjouterPointInteretNull() {

        Parcours parcours = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "test");

        parcours.ajouterPointInteret(null);
        assertEquals(parcours.getPointsInteret().size(), 0);
    }

    @Test
    public void testAjouterPointInteret() {

        Parcours parcours = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "test");

        ArrayList<PointInteret> points = new ArrayList<>
                (Arrays.asList(new PointInteret("test", new double[]{0.0, 0.1}),
                        new PointInteret("test", new double[]{0.1, 0.0})));

        parcours.ajouterPointInteret(points.get(0));
        parcours.ajouterPointInteret(points.get(1));

        assertEquals(parcours.getPointsInteret().size(), points.size());
        assertArrayEquals(parcours.getPointsInteret().toArray(), points.toArray());
    }

    @Test
    public void testAjouterPointTrajetNull() {

        Parcours parcours = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "test");

        parcours.ajouterPointTrajet(null);
        assertEquals(parcours.getTrajetRealise().size(), 0);
    }

    @Test
    public void testAjouterPointTrajet() {

        Parcours parcours = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "test");

        ArrayList<GeoPoint> points = new ArrayList<>
                (Arrays.asList(new GeoPoint(0.0f, 0.1f),
                        new GeoPoint(0.1f, 0.0f)));

        parcours.ajouterPointTrajet(points.get(0));
        parcours.ajouterPointTrajet(points.get(1));

        assertEquals(parcours.getTrajetRealise().size(), points.size());
        assertArrayEquals(parcours.getTrajetRealise().toArray(), points.toArray());
    }

    @Test
    public void testSupprimerPointInteretNull() {

        Parcours parcours = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "test");

        parcours.ajouterPointInteret(new PointInteret("test",
                                                      new double[]{0.0, 0.0}));
        parcours.supprimerPointInteret(null);
        assertEquals(parcours.getPointsInteret().size(),
                     parcours.getPointsInteret().size());
    }
    @Test
    public void testSupprimerPointInteret() {

        Parcours parcours = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "test");

        PointInteret point = new PointInteret("test", new double[]{0.0, 0.0});
        parcours.ajouterPointInteret(point);
        parcours.supprimerPointInteret(point.getCoordonnees());
        assertEquals(parcours.getPointsInteret().size(), 0);
    }

    @Test
    public void testIsParcoursEnFonctionnement() {

        Parcours parcours = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "test");

        // Le parcours n'est pas en fonctionnement par défaut.
        assertEquals(parcours.isParcoursEnFonctionnement(), false);

        // Mettre en fonctionnement enlève le status arrêt et pause du parcours.

        parcours.setParcoursEnPause(true);
        parcours.setParcoursEnFonctionnement(true);
        assertEquals(parcours.isParcoursEnFonctionnement(), true);
        assertEquals(parcours.isParcoursEnPause(), false);

        parcours.setParcoursEnArret(true);
        parcours.setParcoursEnFonctionnement(true);
        assertEquals(parcours.isParcoursEnFonctionnement(), true);
        assertEquals(parcours.isParcoursEnArret(), false);
    }

    @Test
    public void testIsParcoursEnPause() {

        Parcours parcours = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(), "test");

        // Le parcours n'est pas en pause par défaut.
        assertEquals(parcours.isParcoursEnPause(), false);

        // Mettre en pause enlève le status arrêt et fonctionnement du parcours.

        parcours.setParcoursEnArret(true);
        parcours.setParcoursEnPause(true);
        assertEquals(false, parcours.isParcoursEnArret());
        assertEquals(false, parcours.isParcoursEnFonctionnement());

        parcours.setParcoursEnFonctionnement(true);
        parcours.setParcoursEnPause(true);
        assertEquals(true, parcours.isParcoursEnPause());
        assertEquals(false, parcours.isParcoursEnFonctionnement());
    }

    @Test
    public void testIsParcoursEnArret() {

        Parcours parcours = new Parcours(new Randonnee(1, "Mon parcours", 1,
                null, null), new Date(),"test");

        // Le parcours n'est pas en arrêt par défaut.
        assertEquals(parcours.isParcoursEnArret(), false);

        // Mettre en arrêt enlève le status pause et fonctionnement du parcours.

        parcours.setParcoursEnPause(true);
        parcours.setParcoursEnArret(true);
        assertEquals(parcours.isParcoursEnArret(), true);
        assertEquals(parcours.isParcoursEnPause(), false);

        parcours.setParcoursEnFonctionnement(true);
        parcours.setParcoursEnArret(true);
        assertEquals(parcours.isParcoursEnArret(), true);
        assertEquals(parcours.isParcoursEnFonctionnement(), false);
    }
}