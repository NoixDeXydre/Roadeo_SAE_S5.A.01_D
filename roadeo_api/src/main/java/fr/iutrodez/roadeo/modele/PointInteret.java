package fr.iutrodez.roadeo.modele;

import org.springframework.data.mongodb.core.mapping.Field;

public class PointInteret {

    private String libelle;

    @Field("geo")
    private double[] coordonnees;

    public PointInteret() {
        // vide pour MongoDB
    }

    public PointInteret(String libelle, double[] coordonnees) {
        this.libelle = libelle;
        this.coordonnees = coordonnees;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double[] getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(double[] coordonnees) {
        this.coordonnees = coordonnees;
    }

}
