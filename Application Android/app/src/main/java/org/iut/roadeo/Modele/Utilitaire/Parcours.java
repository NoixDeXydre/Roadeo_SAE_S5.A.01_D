package org.iut.roadeo.Modele.Utilitaire;

import org.iut.roadeo.Modele.Randonnee;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * Représente un parcours crée à partir
 * d'une randonnée par un Utilisateur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class Parcours {

    /** Défini si le parcours est à l'arrêt. */
    private boolean isParcoursEnArret;

    /** Défini si le parcours est en pause. */
    private boolean isParcoursEnPause;

    /** Défini la complétion du parcours. */
    private boolean isParcoursTermine;

    /** La randonnée liée au parcours. */
    private Randonnee randonneeParcours;

    /** La date de début du parcours. */
    private String date;

    /** Points d'intérêts enregistrés par l'utilisateur. */
    private ArrayList<GeoPoint> pointsInteret;

    /** Trajet composé de plusieurs coordonnées. */
    private ArrayList<GeoPoint> trajetRealise;

    /**
     * Crée un nouveau parcours.
     * @param randonnee la randonnée liée au parcours.
     * @param date la date de début du parcours.
     */
    public Parcours(Randonnee randonnee, String date) {
        // TODO constructeur
    }

    /**
     * Ajoute un point d'intérêt dans le parcours.
     * @param pointInteret
     */
    public void ajouterPointInteret(GeoPoint pointInteret) {
        // TODO méthode
    }

    /**
     * Ajoute un point dans le trajet actuel réalisé.
     * @param nouveauPoint
     */
    public void ajouterPointTrajet(GeoPoint nouveauPoint) {
        // TODO méthode
    }

    public ArrayList<GeoPoint> getPointsInteret() {
        return new ArrayList<>(pointsInteret);
    }

    public ArrayList<GeoPoint> getTrajetRealise() {
        return new ArrayList<>(trajetRealise);
    }

    public boolean isParcoursEnArret() {
        return isParcoursEnArret;
    }

    public boolean isParcoursEnPause() {
        return isParcoursEnPause;
    }

    public boolean isParcoursTermine() {
        return isParcoursTermine;
    }

    public void setParcoursEnArret(boolean parcoursEnArret) {
        isParcoursEnArret = parcoursEnArret;
    }

    public void setParcoursEnPause(boolean parcoursEnPause) {
        isParcoursEnPause = parcoursEnPause;
    }

    public void setParcoursTermine(boolean parcoursTermine) {
        isParcoursTermine = parcoursTermine;
    }

    /**
     * Supprime un point d'intérêt du parcours.
     * Il ne se passera rien si le point d'intérêt n'existe pas.
     *
     * @param pointInteret le point d'intérêt à supprimer.
     */
    public void supprimerPointInteret(GeoPoint pointInteret) {
        // TODO méthode
    }
}
