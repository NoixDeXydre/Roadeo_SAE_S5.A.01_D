package org.iut.roadeo.Modele;

import org.iut.roadeo.Modele.TypeDonnees.Morphologie;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;

/**
 * Représente les caractéristiques d'un randonneur.
 * D'après les spécifications du projet, un randonneur à forcément :
 * <ul>
 *     <li>Un âge</li>
 *     <li>Un niveau d'entraînement</li>
 *     <li>Une morphologie</li>
 * </ul>
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class CaracteristiquesRandonneur {

    protected int age;
    protected NiveauEntrainement niveauEntrainement;
    protected Morphologie morphologie;

    /**
     * Crée les caractéristiques d'un randonneur.
     * @param age âge du randonneur (1 à 120)
     * @param niveauEntrainement niveau d'entraînement
     * @param morphologie morphologie du randonneur
     * @throws IllegalArgumentException si un paramètre est invalide
     */
    public CaracteristiquesRandonneur(int age,
                                      NiveauEntrainement niveauEntrainement,
                                      Morphologie morphologie) throws  IllegalArgumentException {

        this.setAge(age);
        this.setNiveauEntrainement(niveauEntrainement);
        this.setMorphologie(morphologie);
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
