package org.iut.roadeo.modele;

import org.iut.roadeo.modele.interfaces.IAlgorithmeSac;
import org.junit.Test;
import static org.iut.roadeo.modele.FabriqueSacADos.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Classe de tests de la classe FabriqueSacADos
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestFabriqueSacADos {

    @Test
    public void testCreerSacADos() {

        // Création avec tableau null
        assertEquals(null, creerSacADos(null));

        // Tableau vide
        assertEquals(0, creerSacADos(new double[]{}).size());

        // Vérification du nombre de sacs
        assertEquals(2, creerSacADos(new double[]{1.0, 2.0}).size());
        assertEquals(5, creerSacADos(new double[]{1.0, 2.0, 4.0, 3.0, 5.0}).size());
    }

    @Test
    public void testCreerSacADosRepartisEchec() {

        try {
            creerSacADosRepartis(null, null, null);
            fail("Les produits ou l'algorithme ne peut pas être null.");
        } catch (IllegalArgumentException e) {
            // Corps vide
        }

        try {
            creerSacADosRepartis(null, new ArrayList<>(), null);
            fail("L'algorithme ne peut pas être null.");
        } catch (IllegalArgumentException e) {
            // Corps vide
        }

        try {
            creerSacADosRepartis(null, null, (produits, sacADos) -> null);
            fail("Les produits ne peuvent pas être null.");
        } catch (IllegalArgumentException e) {
            // Corps vide
        }
    }

    @Test
    public void testCreerSacADosRepartisSucces() {

        // Tableau des poids null.
        assertEquals(null, creerSacADosRepartis(null, new ArrayList<>(),
                (produits, sacADos) -> null));

        // Cette fois c'est l'algorithme qui doit renvoyer null.
        assertEquals(null, creerSacADosRepartis(new double[] { 1.0f, 2.0f },
                new ArrayList<>(), (produits, sacADos) -> null));

        // On vérifie une deuxième fois si l'algorithme est bien exécuté.
        assertEquals(0, creerSacADosRepartis(new double[] { 1.0f, 2.0f },
                new ArrayList<>(), (produits, sacADos) -> new ArrayList<>()).size());
    }
}