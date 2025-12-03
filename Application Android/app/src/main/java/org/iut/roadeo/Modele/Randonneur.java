package org.iut.roadeo.Modele;

/**
 * Représente un randonneur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class Randonneur {

    protected String nom;
    protected String prenom;


    /**
     * Crée un nouveau randonneur.
     * @param nom
     * @param prenom
     * @throws IllegalArgumentException si le nom ou prénom est vide ou null.
     */
    public Randonneur(String nom, String prenom) throws IllegalArgumentException {
        setNom(nom);
        setPrenom(prenom);
    }

    public String getNom() {
        return this.nom;
    }

    /**
     * @param nom
     * @throws IllegalArgumentException si le nom ou prénom est vide ou null.
     */
    public void setNom(String nom) {
        if (nom.trim().equals("")) {
            throw new IllegalArgumentException
                    ("Le nom du randonneur ne devrait pas être vide ou null.");
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    /**
     * @param prenom
     * @throws IllegalArgumentException si le nom ou prénom est vide ou null.
     */
    public void setPrenom(String prenom) {
        if (prenom.trim().equals("")) {
            throw new IllegalArgumentException
                    ("Le prénom du randonneur ne devrait pas être vide ou null.");
        }
        this.prenom = prenom;
    }
}
