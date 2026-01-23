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

    /** Titre de la randonnée */
    private String libelle;

    /** Point de départ de la randonnée */
    private GeoPoint pointDepart;

    /** Point d'arrivée de la randonnée */
    private GeoPoint pointArrive;

    /**
     * Initialise une nouvelle randonnée.
     *
     * @param libelle titre de la randonnée
     * @param pointDepart
     * @param pointArrive
     * @throws IllegalArgumentException si le labelle est vide ou null.
     */
    public Randonnee(String libelle, GeoPoint pointDepart, GeoPoint pointArrive)
            throws IllegalArgumentException {

        if (libelle == null || libelle.isBlank()) {
            throw new IllegalArgumentException("Le libelle de la randonnée" +
                    " ne devrait pas être vide ou null.");
        }

        setPointDepart(pointDepart);
        setPointArrive(pointArrive);
    }

    public String getLibelle() {
        return libelle;
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
}
