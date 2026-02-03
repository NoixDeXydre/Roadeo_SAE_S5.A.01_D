package fr.iutrodez.roadeo.modele;

/**
 * Participant d'une randonnée
 */
public class Participant {

    private String nom;
    private String prenom;
    private int age;
    private String niveauEntrainement;
    private String morphologie;

    /**
     * Crée un participant à partir de MongoDB
     */
    public Participant() {
        // nécessaire pour MongoDB
    }

    /**
     * Crée un participant
     * @param nom nom du participant
     * @param prenom prenom du participant
     * @param age age du participant
     * @param niveauEntrainement niveau d'entraînement du participant
     * @param morphologie morphologie du participant
     */
    public Participant(String nom, String prenom,  int age, String niveauEntrainement, String morphologie) {
        if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty()
                || age < 0 || age > 100 ||  niveauEntrainement == null || niveauEntrainement.isEmpty()
                || morphologie == null || morphologie.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.niveauEntrainement = niveauEntrainement;
        this.morphologie = morphologie;
    }

    /**
     * Donne le nom du participant
     * @return le nom du participant
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom du participant
     * @param nom nom du participant
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Donne le prenom du participant
     * @return le prenom du participant
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie le prenom du participant
     * @param prenom le prenom d'un participant
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Donne l'âge du participant
     * @return l'âge du participant
     */
    public int getAge() {
        return age;
    }

    /**
     * Modifie l'âge du participant
     * @param age âge du participant
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Donne le niveau d'entraînement du participant
     * @return le niveau d'entrainement
     */
    public String getNiveauEntrainement() {
        return niveauEntrainement;
    }

    /**
     * Modifie le niveau d'entraînement du participant
     * @param niveauEntrainement le niveau d'entrainement
     */
    public void setNiveauEntrainement(String niveauEntrainement) {
        this.niveauEntrainement = niveauEntrainement;
    }

    /**
     * Donne la morphologie du participant
     * @return la morphologie du participant
     */
    public String getMorphologie() {
        return morphologie;
    }

    /**
     * Mise à jour de la morphologie du participant
     * @param morphologie
     */
    public void setMorphologie(String morphologie) {
        this.morphologie = morphologie;
    }

    /**
     * Renvoie une estimation de la taille moyenne selon l'âge
     * TODO Simplifier
     * 8 - 10 = 133
     * 11 - 15 = 155,5
     * 16 - 75 = 170
     * 75+ = 165
     * @return la taille
     */
    public double tailleApproximative() {
        if (this.age < 10) {
            return 133.0;
        } else if(this.age < 15) {
            return 155.5;
        } else if(this.age < 75) {
            return 170;
        } else {
            return 165;
        }
    }

    /**
     * Renvoie une estimation de la taille moyenne selon l'âge
     * TODO Simplifier
     * @return la taille
     */
    public double poidsApproximatif() {
        double corpulence = 1.0;

        if (this.morphologie.equals("Legere")) {
            corpulence = 1.10;
        } else if (this.morphologie.equals("Moyenne")) {
            corpulence = 0.90;
        }

        if (this.age < 10) {
            return 18 * Math.pow(1.33,2) * corpulence;
        } else if(this.age < 15) {
            return 18 * Math.pow(1.55,2) * corpulence;
        } else if(this.age < 75) {
            return 22 * Math.pow(1.70,2) * corpulence;
        } else {
            return 22 * Math.pow(1.65,2) * corpulence;
        }
    }


}
