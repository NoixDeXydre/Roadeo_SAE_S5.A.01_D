package org.iut.roadeo.modele;

import org.iut.roadeo.modele.typedonnees.Morphologie;
import org.iut.roadeo.modele.typedonnees.NiveauEntrainement;

/**
 * Représente un randonneur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class Randonneur {

    private final int AGE_PETIT_SEUIL_TAILLE_PREDEFINI = 10;
    private final int AGE_MOYEN_SEUIL_TAILLE_PREDEFINI = 15;
    private final int AGE_GRAND_SEUIL_TAILLE_PREDEFINI = 75;

    private final double METRIQUE_CORPULENCE_FORTE = 31.8402f;
    private final double METRIQUE_CORPULENCE_LEGERE = 47.5695f;
    private final double METRIQUE_CORPULENCE_MOYEN = 0.9f;

    private final double VALEUR_PETIT_SEUIL_TAILLE_PREDEFINI = 133.0f;
    private final double VALEUR_MOYEN_SEUIL_TAILLE_PREDEFINI = 155.5f;
    private final double VALEUR_GRAND_SEUIL_TAILLE_PREDEFINI = 170.0f;
    private final double VALEUR_VIEUX_SEUIL_TAILLE_PREDEFINI = 165.0f;

    private String nom;
    private String prenom;

    private int age;
    private NiveauEntrainement niveauEntrainement;
    private Morphologie morphologie;
    private SacADos sacADos;

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

        sacADos = new SacADos(0.0f);
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

    public SacADos getSacADos() {
        return sacADos;
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

    /**
     * Renvoie une estimation de la taille moyenne selon l'âge.
     * 8 - 10 = 133
     * 11 - 15 = 155,5
     * 16 - 75 = 170
     * 75+ = 165
     * @return la taille
     */
    public double getTailleApproximative() {

        if (this.age < AGE_PETIT_SEUIL_TAILLE_PREDEFINI)
            return VALEUR_PETIT_SEUIL_TAILLE_PREDEFINI;
        else if(this.age < AGE_MOYEN_SEUIL_TAILLE_PREDEFINI)
            return VALEUR_MOYEN_SEUIL_TAILLE_PREDEFINI;
        else if(this.age < AGE_GRAND_SEUIL_TAILLE_PREDEFINI)
            return VALEUR_GRAND_SEUIL_TAILLE_PREDEFINI;
        return VALEUR_VIEUX_SEUIL_TAILLE_PREDEFINI;
    }

    /**
     * Renvoie une estimation du poids que peut porter
     * le randonneur selon son âge et son poids.
     * @return le poids
     */
    public double getPoidsApproximatif() {

        double corpulence = METRIQUE_CORPULENCE_FORTE;
        if (this.morphologie.equals(Morphologie.LEGER))
            corpulence = METRIQUE_CORPULENCE_LEGERE;
        else if (this.morphologie.equals(Morphologie.MOYEN))
            corpulence = METRIQUE_CORPULENCE_MOYEN;

        // Valeurs calculées à la main

        if (this.age < AGE_PETIT_SEUIL_TAILLE_PREDEFINI)
            return corpulence;
        else if(this.age < AGE_MOYEN_SEUIL_TAILLE_PREDEFINI)
            return 43.245f * corpulence;
        else if(this.age < AGE_GRAND_SEUIL_TAILLE_PREDEFINI)
            return 61.71f * corpulence;
        return 59.895f * corpulence;
    }

    /**
     * Calcul de Kcal selon la formule de Mifflin–St Jeor
     * MB = 10 × poids(kg) + 6,25 × taille(cm) − 5 × âge + 5
     * -> on prend la formule du calcul de Mifflin-St Jeor pour un homme au repos
     * et on ultiplie par 1,9 pour simuler l'activité sportive
     *
     * @return le nombre de kilo calorie d'une personne
     */
    private double calculKcalParticipant() {
        return (10 * getPoidsApproximatif() + 6.25 * getTailleApproximative()
                - 5 * getAge() + 5) * 1.9;
    }

    @Override
    public String toString() {
        return "Patronyme : "+getPrenom()+" "+getNom()+", age : "+getAge()
                +" ans, niveau : "
                +getNiveauEntrainement().toString().toLowerCase()
                +", morphologie : "+getMorphologie().toString().toLowerCase();
    }
}
