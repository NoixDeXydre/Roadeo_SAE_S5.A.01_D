package org.iut.roadeo.Modele;

import org.osmdroid.util.GeoPoint;

/**
 * Représente une randonnée pouvant être sélectionnée.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class Randonnee {

    /** Le nombre maximum de paticipants possible à une randonnée */
    public static final int NB_MAX_PARTICIPANT = 3;

    /** La durée minimale d'une randonnée en jour */
    public static final int NB_JOURS_MIN = 1;

    /** La durée maximale d'une randonnée en jour */
    public static final int NB_JOURS_MAX = 3;

    /** Titre de la randonnée */
    private String libelle;

    /** Nombre maximum de participants à la randonnée */
    private int nbParticpantsMax;

    /** Durée en jours de la randonnée */
    private int nbJours;

    /** Point de départ de la randonnée */
    private GeoPoint pointDepart;

    /** Point d'arrivée de la randonnée */
    private GeoPoint pointArrive;

    /**
     * Initialise une nouvelle randonnée.
     *
     * @param libelle titre de la randonnée
     * @param pointDepart le point de départ de la randonnée
     * @param pointArrive le point d'arrivé de la randonnée
     * @throws IllegalArgumentException si le labelle est vide ou null.
     */
    public Randonnee(String libelle, int nbJours, GeoPoint pointDepart, GeoPoint pointArrive)
            throws IllegalArgumentException {
        setLibelle(libelle);
        this.nbParticpantsMax = NB_MAX_PARTICIPANT;
        setNbJours(nbJours);
        setPointDepart(pointDepart);
        setPointArrive(pointArrive);
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        if (libelle == null || libelle.isBlank()) {
            throw new IllegalArgumentException("Le libelle de la randonnée" +
                    " ne devrait pas être vide ou null.");
        }
        this.libelle = libelle;
    }

    /** @return Le point de départ, ou null s'il n'a pas été paramétré. */
    public GeoPoint getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(GeoPoint pointDepart) {
        this.pointDepart = pointDepart;
    }

    /** @return Le point d'arrivée, ou null s'il n'a pas été paramétré. */
    public GeoPoint getPointArrive() {
        return pointArrive;
    }

    public void setPointArrive(GeoPoint pointArrive) {
        this.pointArrive = pointArrive;
    }

    public int getNbParticpantsMax() {
        return nbParticpantsMax;
    }

    public int getNbJours() {
        return nbJours;
    }

    public void setNbJours(int nbJours) {
        if (nbJours<NB_JOURS_MIN) {
            throw new IllegalArgumentException("La durée de la randonnée doit" +
                    " être d'au moins " + NB_JOURS_MIN +
                    " jour");
        }
        if (nbJours>NB_JOURS_MAX) {
            throw new IllegalArgumentException("La durée de la randonnée ne peut" +
                    " pas dépasser " + NB_JOURS_MAX +
                    " jours");
        }
        this.nbJours = nbJours;
    }

    @Override
    public String toString() {
        return "Randonnée : " + libelle;
    }
}
