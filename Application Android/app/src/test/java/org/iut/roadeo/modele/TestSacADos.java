package org.iut.roadeo.modele;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe de tests de la classe SacADos
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestSacADos {

    private final Produit P1 = new Produit("", "", "", "",
            0.0f, 15.0f, 0, 10.0f);
    private final Produit P2 = new Produit("", "", "", "",
            0.0f, 6.0f, 0, 10.0f);

    @Test
    public void testSacADosInvalide() {

        try {
            new SacADos(null, 1.0f);
            fail("Le sac doit renvoyer une erreur si son contenu est initialisé avec null.");
        } catch (IllegalArgumentException e) {
            // Corps vide
        }

        try {
            new SacADos(new ArrayList<>(), -1.0f);
            fail("Le poids max du sac ne peut pas être négatif.");
        } catch (IllegalArgumentException e) {
            // Corps vide
        }

        try {
            new SacADos(new ArrayList<>(), -1.0f);
            new SacADos(-1.0f);
            fail("Le poids max du sac ne peut pas être négatif.");
        } catch (IllegalArgumentException e) {
            // Corps vide
        }
    }

    @Test
    public void testSacADosSucces() {

        try {
            new SacADos(new ArrayList<>(), 0.0f);
        } catch (IllegalArgumentException e) {
            fail("Le sac devrait pouvoir s'initialiser.");
        }

        try {
            new SacADos(13203.0f);
        } catch (IllegalArgumentException e) {
            fail("Le sac devrait pouvoir s'initialiser.");
        }
    }

    @Test
    public void testGetPoidsTotalAVide() {

        assertEquals(1.0f, new SacADos(100.0f).getPoidsTotal(), 0.0f);

        SacADos sac = new SacADos(50.0f);
        sac.setPoids(12.0f);
        assertEquals(12.0f, sac.getPoidsTotal(), 0.0f);
    }

    @Test
    public void testGetPoidsTotalAvecProduits() {

        SacADos s1 = new SacADos(new ArrayList<>(Arrays.asList(new Produit[]{P1, P2})),
                50.0f);
        SacADos s2 = new SacADos(new ArrayList<>(Arrays.asList(new Produit[]{P2})),
                50.0f);

        assertEquals(22.0f, s1.getPoidsTotal(), 0.0f);
        assertEquals(7.0f, s2.getPoidsTotal(), 0.0f);

        // Vérification en changeant le poids du sac.
        s1.setPoids(14.0f);
        assertEquals(35.0f, s1.getPoidsTotal(), 0.0f);

        // Et en supprimant un item
        s1.supprimerProduit(P1);
        assertEquals(20.0f, s1.getPoidsTotal(), 0.0f);
    }

    @Test
    public void testAjouterProduitWithSacAmplementGrand() {

        SacADos sac = new SacADos(50.0f);

        assertEquals(true, sac.ajouterProduit(P1));
        assertEquals(true, sac.ajouterProduit(P2));

        assertArrayEquals(new Produit[] { P1, P2 }, sac.getContenu().toArray());
    }

    @Test
    public void testAjouterProduitWithSacPetit() {

        SacADos sac = new SacADos(7.0f);

        assertEquals(false, sac.ajouterProduit(P1));
        assertArrayEquals(new Produit[] {}, sac.getContenu().toArray());
    }

    @Test
    public void testSupprimerProduit() {

        SacADos sac = new SacADos(50.0f);

        // Supprimer un produit non existant.
        assertEquals(false, sac.supprimerProduit(P1));

        // Tests avec des produits existants.

        sac.ajouterProduit(P1);
        sac.ajouterProduit(P2);

        assertEquals(true, sac.supprimerProduit(P1));
        assertEquals(1, sac.getContenu().size());
    }
}
