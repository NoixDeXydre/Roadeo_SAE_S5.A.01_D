package fr.iutrodez.roadeo.modele;

import java.util.ArrayList;

/**
 * Sac à dos d'un randonneur
 */
public class SacADos {

    /** Ensemble d'objet contenu dans le sac */
    private ArrayList<Produits> contenu;

    /** Volume maximal à ne pas dépasser (TODO voir pour suppression)  */
    private double volumeMax;

    /** Valeur total des objets selon l'importance des objets */
    private int valeur;

    /** Poids du sac à vide */
    private double poids = 1.0;

    /** Poids maximal du sac */
    private double poidsMax = 0;

    /** Crée un sac, utilisé par MongoDB pour le transfère des documents */
    public SacADos(){}

    /** Crée un sac à dos */
    public SacADos(ArrayList<Produits> contenu, double poidsMax, int valeur) {
        this.contenu = contenu;
        this.poidsMax = poidsMax;
        this.valeur = valeur;
    }

    /** Crée un sac à dos avec un contenu vide */
    public SacADos(double poidsMax, int valeur) {
        this.contenu = new ArrayList<>();
        this.poidsMax = poidsMax;
        this.valeur = valeur;
    }

    /** Calcule le volume occuppe par les objets (TODO voir pour suppression) */
    public double volumeOccuppe() {
        double volume = 0.0;
        /*for (Objet o : this.contenu) {
            volume += o.getVolume();
        }*/
        return volume;
    }

    /** Calcule le poids total du sac */
    public double poidsTotal() {
        double poids = 0.0;
        for (Produits o : this.contenu) {
            poids += o.getMasse();
        }
        return this.poids + poids;
    }

    /** Renvoie la liste des objets du sac */
    public ArrayList<Produits> getContenu() {
        return contenu;
    }

    /** Modifie toute la liste des objets du sac */
    public void setContenu(ArrayList<Produits> contenu) {
        this.contenu = contenu;
    }

    /** Renvoie le volume max du sac (TODO voir pour suppression) */
    public double getVolumeMax() {
        return volumeMax;
    }

    /** Modifie le volume max du sac (TODO voir pour suppression) */
    public void setVolumeMax(double volumeMax) {
        this.volumeMax = volumeMax;
    }

    /** Renvoie la valeur des objets du sac */
    public int getValeur() {
        return valeur;
    }

    /** Modifie la valeur des objets du sac */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /** Renvoie le poids du sac */
    public double getPoids() {
        return poids;
    }

    /** Modifie le poids du sac */
    public void setPoids(double poids) {
        this.poids = poids;
    }

    @Override
    public String toString() {
        StringBuilder contenuObjet = new StringBuilder();
        for (Produits objet : this.contenu) {
            contenuObjet.append("    ").append(objet.toString()).append("\n");
        }
    return "SacADos{" +
                "\n volumeMax=" + this.volumeOccuppe() +
                "\n valeur=" + valeur +
                "\n poids=" + this.poidsTotal() + " / " + this.poidsMax +
                "\ncontenu :\n" + contenuObjet +
                '}';
    }

    /** Ajoute un objet au sac à dos */
    public boolean addObjet(Produits objet) {
        if (this.poidsTotal() + objet.getMasse() > this.poidsMax) {
            return false;
        }
        return this.contenu.add(objet);
    }

    public boolean supprimeProduits(Produits produits) {
        return this.contenu.remove(produits);
    }

    public void supprimeProduitsById(String idObjet) {
        for (Produits produit : this.contenu) {
            if (produit.getId().equals(idObjet)) {
                this.contenu.remove(produit);
                break;
            }
        }
    }
}
