package org.iut.roadeo.benchmark;

import org.iut.roadeo.modele.FabriqueSacADos;
import org.iut.roadeo.modele.Produit;
import org.iut.roadeo.modele.algorithmes_sac.AlgorithmeSacGlouton;
import org.iut.roadeo.modele.interfaces.IAlgorithmeSac;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe permettant d'analyser
 * le temps d'exécution des algorithmes KP existants.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class BenchmarkAlgorithmesSac {

    private static ArrayList<IAlgorithmeSac> algorithmes;

    private static void instancierAlgorithmes() {
        algorithmes = new ArrayList<>();
        algorithmes.add(new AlgorithmeSacGlouton() {

            @Override
            public String toString() {
                return "Glouton";
            }
        });
    }

    private static double[] genererPoidsSac(int nombreSacs) {

        Random rd = new Random(); // creating Random object
        return rd.doubles(nombreSacs, 12000, 12001).toArray();
    }

    private static ArrayList<Produit> genererProduits(int nombreProduits) {

        Random rd = new Random();

        ArrayList<Produit> produits = new ArrayList<>();
        for (int i = 0 ; i < nombreProduits ; i++) {
            produits.add(new Produit("", "", "", "", 0,
                    4, rd.ints(1, 1, 100).toArray()[0], 1));
        }

        return produits;
    }

    /**
     * Exécute l'analyse du temps.
     * @param args
     */
    public static void main(String[] args) {

        instancierAlgorithmes();

        System.out.println("Début de l'analyse de temps d'exécution des algorithmes.");
        for (int i = 1; i < 1000; i++) {

            // Simulation par algorithmes

            ArrayList<Long> tempsAlgorithmes = new ArrayList<>();
            for (IAlgorithmeSac algorithmeSac : algorithmes) {

                double[] poidsSacs = genererPoidsSac(i);
                ArrayList<Produit> produits = genererProduits(100000);

                long startTime = System.currentTimeMillis();
                FabriqueSacADos.creerSacADosRepartis(poidsSacs, produits, algorithmeSac);
                long stopTime = System.currentTimeMillis();

                tempsAlgorithmes.add(stopTime - startTime);
            }

            // Affichage du résultat pour les algortithmes.
            for (int indexAlgorithme = 0 ; indexAlgorithme < algorithmes.size()
                    ; indexAlgorithme++) {
                System.out.print("Algo : " + algorithmes.get(indexAlgorithme)
                        + ", Temps exec : " + tempsAlgorithmes.get(indexAlgorithme) + "ms"
                        + ", Nombre sacs : " + i);
            }

            System.out.println();
        }
    }
}
