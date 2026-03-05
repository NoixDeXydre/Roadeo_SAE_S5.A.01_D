package fr.iutrodez.roadeo.modele;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Point d'interêt d'un parcours d'une randonnée
 */
public class PointInteret {

    /** libelle du point d'intérêt */
    private String libelle;

    /** Coordonnées du point d'intérêt */
    private PointGeo coordonnees;

    /** Permet à MongoDB de crée un point d'interêt */
    public PointInteret() {
        // vide pour MongoDB
    }

    /** Permet une création manuelle si besoin */
    public PointInteret(String libelle, PointGeo coordonnees) {
        this.libelle = libelle;
    }

    /** Renvoie le libelle du point d'interêt */
    public String getLibelle() {
        return libelle;
    }

    /** Met à jour le libelle du point d'intérêt */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
