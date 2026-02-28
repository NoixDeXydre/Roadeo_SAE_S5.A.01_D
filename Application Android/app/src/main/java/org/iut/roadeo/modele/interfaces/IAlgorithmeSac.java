package org.iut.roadeo.modele.interfaces;

import org.iut.roadeo.modele.Produit;
import org.iut.roadeo.modele.SacADos;

import java.util.ArrayList;

/**
 * Contrat sur les algorithmes KP.
 *
 * Réparti les objets dans les sacs
 * selon le poids et l'utilité de ces objets.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public interface IAlgorithmeSac {

    /**
     * Résout la répartition d'objets avec un poids et une utilité
     * dans les sacs donnés.
     * TLDR range les produits équitablement entre les sacs.
     *
     * @param produits les produits à mettre dans les sacs.
     * @param sacADos les sacs dans lesquels les produits vont être répartis.
     * @return les sacs à dos répartis
     */
    public ArrayList<SacADos> getSacADosRepartis
            (ArrayList<Produit> produits, ArrayList<SacADos> sacADos);
}
