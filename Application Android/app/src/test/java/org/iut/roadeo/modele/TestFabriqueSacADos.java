package org.iut.roadeo.modele;

import org.junit.Test;
import static org.iut.roadeo.modele.FabriqueSacADos.*;
import static org.junit.Assert.*;

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
}
