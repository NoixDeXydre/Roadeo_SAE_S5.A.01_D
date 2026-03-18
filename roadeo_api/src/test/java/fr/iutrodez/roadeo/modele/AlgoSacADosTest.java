package fr.iutrodez.roadeo.modele;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlgoSacADosTest {

    @Test
    @DisplayName("getSacADosRepartis repartit tous les produits quand la capacite est suffisante")
    void testGetSacADosRepartis_ok() {
        ArrayList<Produits> produits = new ArrayList<>(Arrays.asList(
                new Produits("bivouac", "tente", "tente", "tente 2 places", 50.0, 3.0, 2, 0),
                new Produits("nourriture", "pates", "repas", "pates", 3.0, 2.0, 1, 500),
                new Produits("extra", "lampe", "lampe", "lampe frontale", 10.0, 1.0, 0, 0)
        ));

        ArrayList<SacADos> sacs = new ArrayList<>(Arrays.asList(
                new SacADos(10.0, 0),
                new SacADos(10.0, 0)
        ));

        AlgoSacADos algo = new AlgoSacADos();
        ArrayList<SacADos> repartis = algo.getSacADosRepartis(produits, sacs);

        int totalProduits = 0;
        for (SacADos sac : repartis) {
            totalProduits += sac.getContenu().size();
        }

        assertEquals(produits.size(), totalProduits);
    }

    @Test
    @DisplayName("getSacADosRepartis leve une erreur si un produit de type 2 ne passe pas")
    void testGetSacADosRepartis_type2Impossible() {
        ArrayList<Produits> produits = new ArrayList<>(Arrays.asList(
                new Produits("bivouac", "tente", "tente", "tente 4 places", 50.0, 12.0, 2, 0)
        ));

        ArrayList<SacADos> sacs = new ArrayList<>(Arrays.asList(
                new SacADos(8.0, 0),
                new SacADos(8.0, 0)
        ));

        AlgoSacADos algo = new AlgoSacADos();
        assertThrows(IllegalArgumentException.class, () -> algo.getSacADosRepartis(produits, sacs));
    }

    @Test
    @DisplayName("getSacADosRepartis leve une erreur si les calories de type 1 sont insuffisantes")
    void testGetSacADosRepartis_caloriesInsuffisantes() {
        ArrayList<Produits> produits = new ArrayList<>(Arrays.asList(
                new Produits("nourriture", "repas", "repas", "repas 1", 5.0, 5.0, 1, 500),
                new Produits("nourriture", "repas", "repas", "repas 2", 5.0, 6.0, 1, 500)
        ));

        ArrayList<SacADos> sacs = new ArrayList<>(Arrays.asList(
                new SacADos(6.0, 0)
        ));

        AlgoSacADos algo = new AlgoSacADos();
        assertThrows(IllegalArgumentException.class, () -> algo.getSacADosRepartis(produits, sacs));
    }
}
