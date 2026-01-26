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
     *
     * @param morphologie
     */
    public void setMorphologie(String morphologie) {
        this.morphologie = morphologie;
    }
}
