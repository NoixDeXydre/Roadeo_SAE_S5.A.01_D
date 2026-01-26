package fr.iutrodez.roadeo.modele;

/**
 *
 */
public class Participant {

    private String nom;
    private String prenom;
    private int age;
    private String niveauEntrainement;
    private String morphologie;

    /**
     *
     */
    public Participant() {
        // nécessaire pour MongoDB
    }

    /**
     *
     * @param nom
     * @param prenom
     * @param age
     * @param niveauEntrainement
     * @param morphologie
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
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @return
     */
    public String getNiveauEntrainement() {
        return niveauEntrainement;
    }

    /**
     *
     * @param niveauEntrainement
     */
    public void setNiveauEntrainement(String niveauEntrainement) {
        this.niveauEntrainement = niveauEntrainement;
    }

    /**
     *
     * @return
     */
    public String getMorphologie() {
        return morphologie;
    }

    /**
     *
     * @param morphologie
     */
    public void setMorphologie(String morphologie) {
        this.morphologie = morphologie;
    }

    /**
     * Renvoie une estimation de la taille moyenne selon l'âge
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
     * @return la taille
     */
    public double poidsApproximative() {
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
