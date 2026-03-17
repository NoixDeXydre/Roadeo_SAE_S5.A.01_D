package fr.iutrodez.roadeo.modele;

import fr.iutrodez.roadeo.modele.IAlgorithmeSac;
import fr.iutrodez.roadeo.modele.Produits;
import fr.iutrodez.roadeo.modele.SacADos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Implémentation d'un algorithme de résolution KP
 * par méthode gloutonne.
 *
 * Dans le pire des cas, il est de complexité O(n²)
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class AlgoSacADos implements IAlgorithmeSac {

    public static void triRapide(List<Produits> tab, int premier, int dernier, char option) {
        int pivot;
        if (premier < dernier) {
            pivot = choix_pivot(tab, premier, dernier);
            pivot = partitionnement(tab, premier, dernier, pivot, option);
            triRapide(tab, premier, pivot-1, option);
            triRapide(tab, pivot+1, dernier, option);
        }
    }

    private static int choix_pivot(List<Produits> tab, int premier, int dernier) {
        return dernier;
    }

    public static int partitionnement(List<Produits> tab, int premier, int dernier, int pivot, char option) {
        Collections.swap(tab, pivot, dernier);
        int j = premier;

        for (int i = premier; i < dernier; i++) {
            if (option == 'n') {
                if (tab.get(i).getNutrition() <= tab.get(dernier).getNutrition()) {
                    Collections.swap(tab, i, j);
                    j++;
                }
            } else {
                if (tab.get(i).getMasse() <= tab.get(dernier).getMasse()) {
                    Collections.swap(tab, i, j);
                    j++;
                }
            }
        }
        Collections.swap(tab, dernier, j);
        return j;
    }

    public ArrayList<SacADos> getSacADosRepartis(ArrayList<Produits> produits,
                                                 ArrayList<SacADos> sacADos) {
        final int UTILITE_NOURRITURE = 1;
        final int UTILITE_INDISPENSABLE = 2;

        ArrayList<Produits> produitsTraitement = new ArrayList<>(produits);
        ArrayList<SacADos> sacADosTraitement = new ArrayList<>(sacADos);

        double caloriesDemandes = 0.0;
        double caloriesAjoutees = 0.0;

        for (Produits produit : produitsTraitement) { // Pour chaque objet sélectionné.
            if (produit.getUtilite() == UTILITE_NOURRITURE) {
                caloriesDemandes += produit.getNutrition();
            }

            boolean ajoute = false;
            for (SacADos sac : sacADosTraitement) {
                if (sac.addObjet(produit)) {
                    ajoute = true;
                    if (produit.getUtilite() == UTILITE_NOURRITURE) {
                        caloriesAjoutees += produit.getNutrition();
                    }
                    break;
                }
            }

            if (!ajoute && produit.getUtilite() == UTILITE_INDISPENSABLE) {
                throw new IllegalArgumentException();
            }
        }

        if (caloriesAjoutees < caloriesDemandes) {
            throw new IllegalArgumentException();
        }

        return sacADosTraitement;
    }

    public ArrayList<SacADos> getSacADosRepartisAmeliorer(ArrayList<Produits> produits,
                                                          ArrayList<SacADos> sacADos) {
        final char TRI_NUTRITION = 'n';
        final char TRI_SUPPLEMENT = 's';

        ArrayList<Produits> produitsTraitement = new ArrayList<>(produits);
        ArrayList<SacADos> sacADosTraitement = new ArrayList<>(sacADos);
        produitsTraitement.sort(Comparator.comparingInt(Produits::getUtilite).reversed());


        Map<Integer, List<Produits>> groupes =
                produitsTraitement.stream().collect(Collectors.groupingBy(Produits::getUtilite));
        for (int i = 2; i >= 0; i--) {
            List<Produits> groupe = groupes.get(i);
            if (groupe == null || groupe.isEmpty()) {
                continue;
            }
            if (i == 1) {
                triRapide(groupe, 0, groupe.size() - 1, TRI_NUTRITION);
            } else {
                triRapide(groupe, 0, groupe.size() - 1, TRI_SUPPLEMENT);
            }
            triSacADos(groupe, sacADosTraitement);
        }
        return sacADosTraitement;
    }

    public static void triSacADos(List<Produits> produits,
                                  ArrayList<SacADos> sacADos) {
        ArrayList<Produits> produitsTraitement = new ArrayList<>(produits);
        ArrayList<SacADos> sacADosTraitement = new ArrayList<>(sacADos);
        produitsTraitement.sort(Comparator.comparingInt(Produits::getUtilite).reversed());

        for (Produits produit : produitsTraitement) {
            boolean ajoute = false;
            for (SacADos sac : sacADosTraitement) {
                if (sac.addObjet(produit)) {
                    ajoute = true;
                    break;
                }
            }
            if (!ajoute) {
                throw new IllegalArgumentException();
            }
        }
    }
}



