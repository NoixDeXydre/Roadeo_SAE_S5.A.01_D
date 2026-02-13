package org.iut.roadeo.modele.utilitaire.champ;

import static org.iut.roadeo.modele.utilitaire.champ.VerificateurChamps.*;

import android.widget.EditText;

import org.iut.roadeo.modele.typedonnees.Morphologie;
import org.iut.roadeo.modele.typedonnees.NiveauEntrainement;

/**
 * Offre des méthodes utilitaires
 * pour la récupération de données dans les champs utilisateur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class RecuperateurChamps {

    /**
     * @param champ
     * @param valeurDefaut la valeur par défaut
     *                     si la valeur n'a pas pu être récupérée.
     * @return la valeur extraite du champ.
     */
    public static int getIntFromChamp(EditText champ, int valeurDefaut) {

        try {
            if (isChampNonVide(champ, false)) {
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
