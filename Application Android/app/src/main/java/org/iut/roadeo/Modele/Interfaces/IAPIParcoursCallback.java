package org.iut.roadeo.Modele.Interfaces;

import org.iut.roadeo.Modele.Parcours;
import org.iut.roadeo.Modele.Randonnee;

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
