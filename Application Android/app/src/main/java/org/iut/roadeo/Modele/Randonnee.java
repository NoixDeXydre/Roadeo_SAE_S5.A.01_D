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
     */
    public Randonnee(String libelle, GeoPoint pointDepart, GeoPoint pointArrive) {
        // TODO constructeur
    }

    // TODO vérification des setters

    public String getLibelle() {
        return libelle;
    }

    public GeoPoint getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(GeoPoint pointDepart) {
        this.pointDepart = pointDepart;
    }

    public GeoPoint getPointArrive() {
        return pointArrive;
    }

    public void setPointArrive(GeoPoint pointArrive) {
        this.pointArrive = pointArrive;
    }
}
