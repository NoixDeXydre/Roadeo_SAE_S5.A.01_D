package org.iut.roadeo.modele.algorithmes_sac;

import org.iut.roadeo.modele.Produit;
import org.iut.roadeo.modele.SacADos;
import org.iut.roadeo.modele.interfaces.IAlgorithmeSac;

import java.util.ArrayList;
import java.util.Comparator;


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
public class AlgorithmeSacGlouton implements IAlgorithmeSac {

    @Override
    public ArrayList<SacADos> getSacADosRepartis(ArrayList<Produit> produits,
                                                 ArrayList<SacADos> sacADos) {

        ArrayList<Produit> produitsTraitement = new ArrayList<>(produits);
        ArrayList<SacADos> sacADosTraitement = new ArrayList<>(sacADos);
        produitsTraitement.sort(Comparator.comparingInt(Produit::getUtilite).reversed());

        Produit produit;
        for (int i = 0; i < produitsTraitement.size(); i++) { // Pour chaque objet sélectionné.

            produit = produitsTraitement.get(i);
            int indexSac = 0;

            // Tant qu'on peut ajouter un produit dans un sac, on continue.
            while (indexSac < sacADosTraitement.size()
                    && sacADosTraitement.get(indexSac++).ajouterProduit(produit)) {
                // Corps vide
            }
        }

        return sacADosTraitement;
    }
}
