package org.iut.roadeo.Modele.Interfaces;

import org.iut.roadeo.Modele.Utilisateur;

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
