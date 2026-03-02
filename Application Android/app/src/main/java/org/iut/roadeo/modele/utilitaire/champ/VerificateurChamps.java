package org.iut.roadeo.modele.utilitaire.champ;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Offre des méthodes utilitaires
 * pour la vérification et la gestion des champs utilisateur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class VerificateurChamps {

    // Regex email (https://emailregex.com)
    private static final Pattern EMAIL_REGEX
    = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|" +
            "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09" +
            "\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]" +
            "(?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)" +
            "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b" +
            "\\x0c\\x0e-\\x7f])+)\\])", Pattern.CASE_INSENSITIVE);

    /**
     * Valide une adresse mail
     * @param email
     * @return true si l'adresse mail est correcte, sinon false.
     */
    public static boolean isEmailValide(String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.matches();
    }

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
