package fr.iutrodez.roadeo.modele;

import java.util.ArrayList;

public class SacADos {

    private ArrayList<Objet> contenu;

    private double volumeMax;

    private int valeur;

    private double poids = 1.0;
    private double poidsMax = 50;

    public SacADos(){}

    public SacADos(ArrayList<Objet> contenu, double poidsMax, int valeur) {
        this.contenu = contenu;
        this.poidsMax = poidsMax;
        this.valeur = valeur;
    }

    public SacADos(double poidsMax, int valeur) {
        this.contenu = new ArrayList<>();
        this.poidsMax = poidsMax;
        this.valeur = valeur;
    }

    public double volumeOccuppe() {
        double volume = 0.0;
        for (Objet o : this.contenu) {
            volume += o.getVolume();
        }
        return volume;
    }

    public double poidsTotal() {
        double poids = 0.0;
        for (Objet o : this.contenu) {
            poids += o.getPoids();
        }
        return this.poids + poids;
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
        StringBuilder contenuObjet = new StringBuilder();
        for (Objet objet : this.contenu) {
            contenuObjet.append("    ").append(objet.toString()).append("\n");
        }
    return "SacADos{" +
                "\n volumeMax=" + this.volumeOccuppe() +
                "\n valeur=" + valeur +
                "\n poids=" + this.poidsTotal() + " / " + this.poidsMax +
                "\ncontenu :\n" + contenuObjet +
                '}';
    }

    public boolean addObjet(Objet objet) {
        if (this.poidsTotal() + objet.getPoids() > this.poidsMax) {
            return false;
        }
        return this.contenu.add(objet);
    }
}
