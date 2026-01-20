package fr.iutrodez.roadeo.modele;

import java.util.ArrayList;

public class SacADos {

    private ArrayList<Objet> contenu;

    private double volumeMax;

    private int valeur;

    private double poids = 1.0;

    public SacADos(){}

    public SacADos(ArrayList<Objet> contenu, double volumeMax, int valeur) {
        this.contenu = contenu;
        this.volumeMax = volumeMax;
        this.valeur = valeur;
    }

    public double volumeOccuppe() {
        return 0.0;
    }

    public double poidsTotal() {
        return this.poids;
    }

    public int valeur() {
        return 0;
    }

    public ArrayList<Objet> getContenu() {
        return contenu;
    }

    public void setContenu(ArrayList<Objet> contenu) {
        this.contenu = contenu;
    }

    public double getVolumeMax() {
        return volumeMax;
    }

    public void setVolumeMax(double volumeMax) {
        this.volumeMax = volumeMax;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    @Override
    public String toString() {
        return "SacADos{" +
                "contenu=" + contenu +
                ", volumeMax=" + volumeMax +
                ", valeur=" + valeur +
                ", poids=" + poids +
                '}';
    }
}
