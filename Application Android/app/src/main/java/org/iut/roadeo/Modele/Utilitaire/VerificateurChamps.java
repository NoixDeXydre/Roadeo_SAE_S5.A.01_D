package org.iut.roadeo.Modele.Utilitaire;

import android.widget.EditText;

import org.iut.roadeo.Modele.TypeDonnees.Morphologie;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;

/**
 * Offre des méthodes utilitaires
 * pour la vérification et la gestion des champs utilisateur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class VerificateurChamps {

    /**
     * @param champ
     * @return true si le texte du champ n'est pas vide, sinon false.
     */
    public static boolean isChampNonVide(EditText champ) {
        return champ != null && isTexteNonVide(champ.getText().toString());
    }

    /**
     * @param texteChamp
     * @return true si le texte n'est pas vide, sinon false.
     */
    public static boolean isTexteNonVide(String texteChamp) {
        return texteChamp != null && !texteChamp.isBlank();
    }

    /**
     * @param champMdp1
     * @param champMdp2
     * @return true si les deux sont identiques, sinon false.
     */
    public static boolean isChampsMdpIdentiques(EditText champMdp1, EditText champMdp2) {

        return (champMdp1 != null && champMdp2 != null && champMdp1.getText().toString()
                .equals(champMdp2.getText().toString()));
    }

    /**
     * @param champ
     * @param valeurDefaut la valeur par défaut
     *                     si la valeur n'a pas pu être récupérée.
     * @return la valeur extraite du champ.
     */
    public static int getIntFromChamp(EditText champ, int valeurDefaut) {

        try {
            if (isChampNonVide(champ)) {
                return Integer.parseInt(champ.getText().toString());
            }
        } catch (NumberFormatException e) {
            return valeurDefaut;
        }

        return valeurDefaut;
    }

    /**
     * @param position
     * @return le niveau d'entrainement
     */
    public static NiveauEntrainement getNiveauEntrainementWithPosition(int position) {
        return NiveauEntrainement.values()[position];
    }

    /**
     * @param position
     * @return la morphologie
     */
    public static Morphologie getMorphologieWithPosition(int position) {
        return Morphologie.values()[position];
    }
}
