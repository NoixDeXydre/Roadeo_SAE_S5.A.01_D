package org.iut.roadeo.Modele;

/**
 * Représente un utilisateur.
 * Pour rappel, un utilisateur est un randonneur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class Utilisateur extends Randonneur {

    /**
     * Crée un nouvel utilisateur.
     * @param nom
     * @param prenom
     */
    public Utilisateur(String nom, String prenom) {
        super(nom, prenom);
    }
}
