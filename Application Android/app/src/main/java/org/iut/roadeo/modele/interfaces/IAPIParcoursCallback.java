package org.iut.roadeo.modele.interfaces;

import org.iut.roadeo.modele.Parcours;

import java.util.ArrayList;

/**
 * Callback à effectuer après une requête API
 * demandant la liste des randonnées.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public interface IAPIParcoursCallback {

    /**
     * Sur un succès, renvoi les randonnées
     * @param parcours
     */
    public void onSuccess(ArrayList<Parcours> parcours);

    public void onError(String message);
}
