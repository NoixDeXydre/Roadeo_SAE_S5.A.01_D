package org.iut.roadeo.modele;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.iut.roadeo.Modele.CaracteristiquesRandonneur;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;
import org.iut.roadeo.Modele.TypeDonnees.Morphologie;
import org.junit.Test;

/**
 * Classe de tests de la classe CaracteristiquesRandonneur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestCaracteristiquesRandonneur {

    @Test
    public void testCaracteristiquesCorrectes() {
        try {
            new CaracteristiquesRandonneur(
                    30,
                    NiveauEntrainement.ENTRAINE,
                    Morphologie.MOYEN
            );
        } catch (IllegalArgumentException e) {
            fail("Les valeurs fournies devraient être valides.");
        }
    }

    @Test
    public void testAgeInvalide() {

        try {
            new CaracteristiquesRandonneur(0, NiveauEntrainement.DEBUTANT, Morphologie.LEGER);
            fail("L'âge ne devrait pas être inférieur à 1.");
        } catch (IllegalArgumentException e) {}

        try {
            new CaracteristiquesRandonneur(121, NiveauEntrainement.DEBUTANT, Morphologie.LEGER);
            fail("L'âge ne devrait pas être supérieur à 120.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testNiveauEntrainementInvalide() {

        try {
            new CaracteristiquesRandonneur(25, null, Morphologie.FORT);
            fail("Le niveau d'entraînement ne devrait pas être null.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testMorphologieInvalide() {

        try {
            new CaracteristiquesRandonneur(25, NiveauEntrainement.DEBUTANT, null);
            fail("La morphologie ne devrait pas être null.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testGetters() {

        CaracteristiquesRandonneur c =
                new CaracteristiquesRandonneur(40, NiveauEntrainement.SPORTIF, Morphologie.MOYEN);

        assertEquals(40, c.getAge());
        assertEquals(NiveauEntrainement.SPORTIF, c.getNiveauEntrainement());
        assertEquals(Morphologie.MOYEN, c.getMorphologie());
    }

    @Test
    public void testSettersAge() {

        CaracteristiquesRandonneur c =
                new CaracteristiquesRandonneur(40, NiveauEntrainement.ENTRAINE, Morphologie.MOYEN);

        c.setAge(20);
        assertEquals(20, c.getAge());

        try {
            c.setAge(0);
            fail("L'âge ne doit jamais être inférieur à 1.");
        } catch (IllegalArgumentException e) {}

        try {
            c.setAge(150);
            fail("L'âge ne doit jamais être supérieur à 120.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testSettersNiveau() {

        CaracteristiquesRandonneur c =
                new CaracteristiquesRandonneur(30, NiveauEntrainement.ENTRAINE, Morphologie.LEGER);

        c.setNiveauEntrainement(NiveauEntrainement.SPORTIF);
        assertEquals(NiveauEntrainement.SPORTIF, c.getNiveauEntrainement());

        try {
            c.setNiveauEntrainement(null);
            fail("Le niveau d'entraînement ne doit pas être null.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testSettersMorphologie() {

        CaracteristiquesRandonneur c =
                new CaracteristiquesRandonneur(30, NiveauEntrainement.ENTRAINE, Morphologie.LEGER);

        c.setMorphologie(Morphologie.FORT);
        assertEquals(Morphologie.FORT, c.getMorphologie());

        try {
            c.setMorphologie(null);
            fail("La morphologie ne doit pas être null.");
        } catch (IllegalArgumentException e) {}
    }
}
