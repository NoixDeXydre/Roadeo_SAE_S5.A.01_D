package org.iut.roadeo.Modele;

import org.iut.roadeo.Modele.TypeDonnees.Morphologie;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;

/**
 * Représente un randonneur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class Randonneur {

    private String nom;
    private String prenom;

    private int age;
    private NiveauEntrainement niveauEntrainement;
    private Morphologie morphologie;


    /**
     * Crée un nouveau randonneur.
     * @param nom le nom du randonneur
     * @param prenom le prenom du randonneur
     * @throws IllegalArgumentException si le nom ou prénom est vide ou null.
     */
    public Randonneur(String nom, String prenom, int age,
                      NiveauEntrainement niveauEntrainement,
                      Morphologie morphologie) throws IllegalArgumentException {
        setNom(nom);
        setPrenom(prenom);
        this.setAge(age);
        this.setNiveauEntrainement(niveauEntrainement);
        this.setMorphologie(morphologie);
    }

    public String getNom() {
        return this.nom;
    }

    /**
     * @param nom
     * @throws IllegalArgumentException si le nom ou prénom est vide ou null.
     */
    public void setNom(String nom) {
        if (nom == null || nom.trim().equals("")) {
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
        if (prenom == null || prenom.trim().equals("")) {
            throw new IllegalArgumentException
                    ("Le prénom du randonneur ne devrait pas être vide ou null.");
        }
        this.prenom = prenom;
    }

    /**
     * Retourne l'âge du randonneur.
     * @return âge
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Définit l'âge du randonneur.
     * @param age âge entre 1 et 120
     * @throws IllegalArgumentException si l'âge est hors limites
     */
    public void setAge(int age) {
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("L'âge doit être compris entre 1 et 120.");
        }
        this.age = age;
    }

    /**
     * Retourne le niveau d'entraînement.
     * @return niveau d'entraînement
     */
    public NiveauEntrainement getNiveauEntrainement() {
        return this.niveauEntrainement;
    }

    /**
     * Définit le niveau d'entraînement.
     * @param niveauEntrainement enum obligatoire
     * @throws IllegalArgumentException si null
     */
    public void setNiveauEntrainement(NiveauEntrainement niveauEntrainement) {
        if (niveauEntrainement == null) {
            throw new IllegalArgumentException("Le niveau d'entraînement ne doit pas être null.");
        }
        this.niveauEntrainement = niveauEntrainement;
    }

    /**
     * Retourne la morphologie.
     * @return morphologie
     */
    public Morphologie getMorphologie() {
        return this.morphologie;
    }

    /**
     * Définit la morphologie du randonneur.
     * @param morphologie enum obligatoire
     * @throws IllegalArgumentException si null
     */
    public void setMorphologie(Morphologie morphologie) {
        if (morphologie == null) {
            throw new IllegalArgumentException("La morphologie ne doit pas être null.");
        }
        this.morphologie = morphologie;
    }
}
