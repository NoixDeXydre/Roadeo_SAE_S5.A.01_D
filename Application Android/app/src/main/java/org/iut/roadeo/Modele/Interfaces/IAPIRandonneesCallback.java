package org.iut.roadeo.Modele.Interfaces;

import org.iut.roadeo.Modele.Randonnee;
import org.iut.roadeo.Modele.Randonneur;

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
