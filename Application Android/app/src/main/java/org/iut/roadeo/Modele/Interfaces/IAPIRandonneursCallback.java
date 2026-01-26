package org.iut.roadeo.Modele.Interfaces;

import org.iut.roadeo.Modele.Randonneur;

import java.util.ArrayList;

/**
 * Callback à effectuer après une requête API
 * demandant la liste des randonneurs.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public interface IAPIRandonneursCallback {

    /**
     * Sur un succès, renvoi les randonneurs
     * @param randonneurs
     */
    public void onSuccess(ArrayList<Randonneur> randonneurs);

    public void onError(String message);
}