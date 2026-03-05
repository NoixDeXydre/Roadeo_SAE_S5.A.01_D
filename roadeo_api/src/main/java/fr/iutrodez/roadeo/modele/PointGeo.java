package fr.iutrodez.roadeo.modele;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Représente des coordonnnées quelconques.
 */
public class PointGeo {

    @Field("geo")
    /** coordonnées longitude/ latitude de la randonnée */
    private double[] coordonnees;

    public PointGeo() {
        // vide pour MongoDB
    }

    public PointGeo(double lat, double lon) {
        setCoordonnees(new double[] { lat, lon });
    }

    /** Renvoie les coordonnées du point d'interêt */
    public double[] getCoordonnees() {
        return coordonnees;
    }

    /** Met à jour les coordonnées du point d'intérêt */
    public void setCoordonnees(double[] coordonnees) {
        this.coordonnees = coordonnees;
    }
}
