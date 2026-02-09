package org.iut.roadeo.modele;

import org.osmdroid.util.GeoPoint;

public class PointInteret {
    /** libelle du point d'intérêt */
    private String libelle;

    /** coordonnées longitude/ latitude de la randonnée */
    private GeoPoint coordonnees;

    /**
     * Crée un point d'intérêt pour le parcours
     * @param libelle le nom du point d'intérêt
     * @param coordonnees la longitude et la latitude du point d'intérêt
     */
    public PointInteret(String libelle, double[] coordonnees) {
        setLibelle(libelle);
        setCoordonnees(coordonnees);
    }

    /**
     * Renvoie le libelle du point d'intérêt
     * @return le nom du point d'intérêt
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Met à jour le libelle du point d'intérêt
     * @param libelle le nouveau nom
     * @throws IllegalArgumentException levé si libellé vide ou null
     */
    public void setLibelle(String libelle) {
        if (libelle == null || libelle.isBlank()) {
            throw new IllegalArgumentException("Le point d'intérêt doit avoir" +
                                               " un libellé");
        }
        this.libelle = libelle;
    }

    /**
     * Renvoie les coordonnées du point d'interêt
     * @return la longitude et la latitude
     */
    public GeoPoint getCoordonnees() {
        return coordonnees;
    }

    /**
     * Met à jour les coordonnées du point d'intérêt
     * @param coordonnees les nouvelles coordonnées
     * @throws IllegalArgumentException si les coordonnées sont nulles
     *                                  si le tableau ne contient pas
     *                                      strictement 2 cordonnées
     */
    public void setCoordonnees(double[] coordonnees) {
        if (coordonnees == null) {
            throw new IllegalArgumentException("Les coordonnées ne peuvent" +
                                               " pas être nulles");
        } else if (coordonnees.length != 2) {
            throw new IllegalArgumentException("Les coordonnées ne peuvent" +
                                               " contenir que 2 données");
        }
        this.coordonnees = new GeoPoint(coordonnees[0], coordonnees[1]);
    }
}
