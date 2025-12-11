package org.iut.roadeo.Modele.Interfaces;

import org.iut.roadeo.Modele.Utilisateur;

/**
 * Callback à effectuer après une tentative de connexion à l'API.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public interface IAPIConnexionCallback {

    /**
     * Sur un succès, renvoi l'utilisateur
     * @param utilisateur
     */
    public void onSuccess(Utilisateur utilisateur);

    public void onError(String message);
}
