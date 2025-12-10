package org.iut.roadeo.Modele.Interfaces;

/**
 * Callback à effectuer après une tentative de connexion à l'API.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public interface IAPIConnexionCallback {
    public void onSuccess();
    public void onError(String message);
}
