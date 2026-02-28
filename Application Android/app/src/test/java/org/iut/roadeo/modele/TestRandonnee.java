package org.iut.roadeo.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.iut.roadeo.modele.typedonnees.Morphologie;
import org.iut.roadeo.modele.typedonnees.NiveauEntrainement;
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

    @Test
    public void testAjouterRandonneur() {

        Randonnee maRandonnee = new Randonnee(1, "Ma randonnée", 1,
                null, null);

        // Un randonneur null ne peut pas être ajouté.
        maRandonnee.ajouterRandonneur(null);
        assertEquals(0, maRandonnee.getRandonneurs().size());

        maRandonnee.ajouterRandonneur(new Randonneur("test", "test", 10,
                NiveauEntrainement.SPORTIF,  Morphologie.MOYEN));
        assertEquals(1, maRandonnee.getRandonneurs().size());
    }

    @Test
    public void testSupprimerRandonneur() {

        Randonnee maRandonnee = new Randonnee(1, "Ma randonnée", 1,
                null, null);
        Randonneur randonneur = new Randonneur("test", "test", 10,
                NiveauEntrainement.SPORTIF,  Morphologie.MOYEN);

        maRandonnee.ajouterRandonneur(randonneur);

        // Aucun randonneur supprimé
        maRandonnee.supprimerRandonneur(null);
        assertEquals(1, maRandonnee.getRandonneurs().size());

        // Rien de supprimé. Ce n'est pas le même randonneur.
        maRandonnee.supprimerRandonneur(new Randonneur("test", "test", 10,
                NiveauEntrainement.SPORTIF,  Morphologie.MOYEN));
        assertEquals(1, maRandonnee.getRandonneurs().size());

        // Cas nominal
        maRandonnee.supprimerRandonneur(randonneur);
        assertEquals(0, maRandonnee.getRandonneurs().size());
    }
}
