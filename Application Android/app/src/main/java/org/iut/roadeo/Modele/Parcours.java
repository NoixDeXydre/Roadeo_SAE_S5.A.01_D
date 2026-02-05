package org.iut.roadeo.Modele;

import org.iut.roadeo.Modele.Randonnee;
import org.iut.roadeo.Modele.Randonneur;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Date;

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

    /** Défini si le parcours est en fonctionnement. */
    private boolean isParcoursEnFonctionnement;

    /** La randonnée liée au parcours. */
    private Randonnee randonneeParcours;

    /** La date de début du parcours. */
    private Date date;

    /** Points d'intérêts enregistrés par l'utilisateur. */
    private ArrayList<GeoPoint> pointsInteret;

    /** Trajet composé de plusieurs coordonnées. */
    private ArrayList<GeoPoint> trajetRealise;

    /**
     * Crée un nouveau parcours.
     * @param randonnee la randonnée liée au parcours.
     * @param date la date de début du parcours.
     * @throws IllegalArgumentException Si la randonnée liée ou la date est null.
     */
    public Parcours(Randonnee randonnee, Date date) throws IllegalArgumentException {

        if (randonnee == null || date == null) {
            throw new IllegalArgumentException("Erreur : la randonnée" +
                    " ou la date de la randonnée est null.");
        }

        randonneeParcours = randonnee;
        this.date = date;

        pointsInteret = new ArrayList<>();
        trajetRealise = new ArrayList<>();
    }

    /**
     * Ajoute un point d'intérêt dans le parcours.
     * @param pointInteret
     */
    public void ajouterPointInteret(GeoPoint pointInteret) {

        if (pointInteret != null)
            pointsInteret.add(pointInteret);
    }

    /**
     * Ajoute un point dans le trajet actuel réalisé.
     * @param nouveauPoint
     */
    public void ajouterPointTrajet(GeoPoint nouveauPoint) {

        if (nouveauPoint != null)
            trajetRealise.add(nouveauPoint);
    }

    public Randonnee getRandonneeParcours() {
        return randonneeParcours;
    }

    public ArrayList<GeoPoint> getPointsInteret() {
        return new ArrayList<>(pointsInteret);
    }

    public ArrayList<GeoPoint> getTrajetRealise() {
        return new ArrayList<>(trajetRealise);
    }

    public Date getDate() {
        return date;
    }

    public boolean isParcoursEnArret() {
        return isParcoursEnArret;
    }

    public boolean isParcoursEnFonctionnement() {
        return isParcoursEnFonctionnement;
    }

    public boolean isParcoursEnPause() {
        return isParcoursEnPause;
    }

    public boolean isParcoursTermine() {
        return isParcoursTermine;
    }

    /**
     * Met le parcours en arret.
     * Cela enlève par ailleurs le status pause et fonctionnement du parcours.
     * @param parcoursEnArret
     */
    public void setParcoursEnArret(boolean parcoursEnArret) {

        isParcoursEnArret = parcoursEnArret;
        if (isParcoursEnArret) {
            setParcoursEnPause(false);
            setParcoursEnFonctionnement(false);
        }
    }

    /**
     * Démarre le parcours
     * Cela enlève par ailleurs le status arrêt et pause du parcours.
     * @param parcoursEnFonctionnement
     */
    public void setParcoursEnFonctionnement(boolean parcoursEnFonctionnement) {

        isParcoursEnFonctionnement = parcoursEnFonctionnement;
        if (parcoursEnFonctionnement) {
            setParcoursEnArret(false);
            setParcoursEnPause(false);
        }
    }

    /**
     * Met le parcours en pause.
     * Cela enlève par ailleurs le status arrêt et fonctionnement du parcours.
     * @param parcoursEnPause
     */
    public void setParcoursEnPause(boolean parcoursEnPause) {

        isParcoursEnPause = parcoursEnPause;
        if (isParcoursEnPause) {
            setParcoursEnArret(false);
            setParcoursEnFonctionnement(false);
        }
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

        if (pointInteret != null)
            pointsInteret.remove(pointInteret);
    }
}
