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
}
