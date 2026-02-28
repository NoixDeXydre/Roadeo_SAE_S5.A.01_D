package org.iut.roadeo.modele;

import org.iut.roadeo.modele.interfaces.IAlgorithmeSac;

import java.util.ArrayList;

/**
 * Fabrique de sac à dos.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class FabriqueSacADos {

    /**
     * Créer plusieurs sacs vides.
     *
     * @param poidsMaxSacs le poids max pour chaque sac.
     * @return des sacs vides ou null si poidsMaxSacs == null
     */
    public static ArrayList<SacADos> creerSacADos(double[] poidsMaxSacs) {

        if (poidsMaxSacs == null)
            return null;

        ArrayList<SacADos> sacADos = new ArrayList<>();
        for (double poidsMaxSac : poidsMaxSacs) {
            sacADos.add(new SacADos(poidsMaxSac));
        }

        return sacADos;
    }

    /**
     * Donne une liste des sacs répartis.
     *
     * @param poidsMaxSacs le poids maximum pour chaque sac.
     * @param produits les produits à répartir.
     * @param algorithmeResolution l'algorithme de résolution
     *                             permettant de répartir les sacs à dos.
     * @return les sacs répartis ou null si poidsMaxSacs est null.
     * @throws IllegalArgumentException si la liste des produits
     * ou l'algorithme est null.
     */
    public static ArrayList<SacADos> creerSacADosRepartis
            (double[] poidsMaxSacs, ArrayList<Produit> produits,
             IAlgorithmeSac algorithmeResolution) throws IllegalArgumentException {

        if (produits == null || algorithmeResolution == null)
           throw new IllegalArgumentException("La liste des produits," +
                   " ou l'algorithme de résolution ne peut être null.");
        if (poidsMaxSacs == null)
            return null;

        return algorithmeResolution.getSacADosRepartis(produits, creerSacADos(poidsMaxSacs));
    }
}
