package org.iut.roadeo.Modele.Utilitaire.Champ;

import android.widget.EditText;

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
     * @param autoriserEspaces
     * @return true si le texte du champ n'est pas vide, sinon false.
     */
    public static boolean isChampNonVide(EditText champ, boolean autoriserEspaces) {

        if (champ == null)
            return false;

        if (autoriserEspaces)
            return isTexteNonVideEmpty(champ.getText().toString());
        return isTexteNonVideBlank(champ.getText().toString());
    }

    /**
     * @param texteChamp
     * @return true si le texte n'est pas vide (en ne comptant pas les espaces),
     * sinon false.
     */
    public static boolean isTexteNonVideBlank(String texteChamp) {
        return texteChamp != null && !texteChamp.isBlank();
    }

    /**
     * @param texteChamp
     * @return true si le texte n'est pas vide (en comptant les espaces),
     * sinon false.
     */
    public static boolean isTexteNonVideEmpty(String texteChamp) {
        return texteChamp != null && !texteChamp.isEmpty();
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
