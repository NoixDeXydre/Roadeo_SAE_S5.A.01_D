package org.iut.roadeo.modele;

import java.util.ArrayList;

/**
 * Classe concrète du sac à dos représentant les données qu'il contient.
 * Il comporte également un algorithme de résolution.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class SacADos {

    /** Ensemble d'objet contenu dans le sac */
    private ArrayList<Produit> contenu;

    /** Poids du sac à vide */
    private double poids = 1.0;

    /** Poids maximal du sac */
    private double poidsMax = 0;

    /**
     *  Crée un sac à dos
     *  @throws IllegalArgumentException si le poidsMax est négatif
     *  ou si le contenu est null.
     */
    public SacADos(ArrayList<Produit> contenu, double poidsMax) {
        setContenu(contenu);
        setPoidsMax(poidsMax);
    }

    /**
     * Crée un sac à dos avec un contenu vide
     * @throws IllegalArgumentException si le poidsMax est négatif.
     */
    public SacADos(double poidsMax) throws IllegalArgumentException {
        this.contenu = new ArrayList<>();
        setPoidsMax(poidsMax);
    }

    /** Calcule le poids total du sac */
    public double getPoidsTotal() {

        double poids = 0.0f;
        for (Produit o : this.contenu) {
            poids += o.getPoids();
        }

        return this.poids + poids;
    }

    /** Renvoie la liste des objets du sac */
    public ArrayList<Produit> getContenu() {
        return contenu;
    }

    /**
     * Modifie toute la liste des objets du sac
     * @throws IllegalArgumentException si le contenu est null.
     */
    public void setContenu(ArrayList<Produit> contenu) {

        if (contenu == null)
            throw new IllegalArgumentException("La liste des produits du sac " +
                    "ne peuvent pas être null.");

        this.contenu = contenu;
    }

    /** Renvoie le poids du sac */
    public double getPoids() {
        return poids;
    }

    /** Modifie le poids du sac */
    public void setPoids(double poids) {

        if (poids < 0.0f)
            throw new IllegalArgumentException("Le poids du sac ne peut pas être négatif.");

        this.poids = poids;
    }

    @Override
    public String toString() {

        StringBuilder contenuObjet = new StringBuilder();
        for (Produit objet : this.contenu) {
            contenuObjet.append("    ").append(objet.toString()).append("\n");
        }
        return "SacADos{" +
                "\n poids=" + this.getPoidsTotal() + " / " + this.poidsMax +
                "\ncontenu :\n" + contenuObjet +
                '}';
    }

    /** Ajoute un objet au sac à dos */
    public boolean ajouterProduit(Produit objet) {

        if (this.getPoidsTotal() + objet.getPoids() > this.poidsMax) {
            return false;
        }

        return this.contenu.add(objet);
    }

    public boolean supprimerProduit(Produit produits) {
        return this.contenu.remove(produits);
    }

    // TODO voir si la cette méthode est inutile
    public void supprimeProduitsById(String idObjet) {
        for (Produit produit : this.contenu) {
            if (produit.getId().equals(idObjet)) {
                this.contenu.remove(produit);
                break;
            }
        }
    }

    public double getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(double poidsMax) {

        if (poidsMax < 0.0f)
            throw new IllegalArgumentException("Le poids max du sac ne peut pas être négatif.");

        this.poidsMax = poidsMax;
    }
}
