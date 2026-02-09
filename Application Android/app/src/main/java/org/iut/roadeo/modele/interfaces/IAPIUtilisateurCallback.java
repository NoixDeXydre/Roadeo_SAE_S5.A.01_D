package org.iut.roadeo.modele.interfaces;

import org.iut.roadeo.modele.Utilisateur;

/**
 * Callback à effectuer après une requête API
 * impactant un utilisateur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public interface IAPIUtilisateurCallback {

    /**
     * Sur un succès, renvoi l'utilisateur
     * @param utilisateur
     */
    public void onSuccess(Utilisateur utilisateur);

    public void onError(String message);
}
