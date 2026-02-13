package org.iut.roadeo.modele.interfaces;

import org.iut.roadeo.modele.Randonnee;

import java.util.ArrayList;

/**
 * Callback à effectuer après une requête API
 * demandant la liste des randonnées.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public interface IAPIRandonneesCallback {

    /**
     * Sur un succès, renvoi les randonnées
     * @param randonnees
     */
    public void onSuccess(ArrayList<Randonnee> randonnees);

    public void onError(String message);
}
