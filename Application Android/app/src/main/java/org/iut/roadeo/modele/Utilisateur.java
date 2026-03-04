package org.iut.roadeo.modele;

import org.iut.roadeo.modele.typedonnees.Morphologie;
import org.iut.roadeo.modele.typedonnees.NiveauEntrainement;

import java.util.ArrayList;

/**
 * Représente un utilisateur.
 * Pour rappel, un utilisateur est un randonneur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class Utilisateur extends Randonneur {

    private String id;

    /** Le mot de passe utilisateur */
    private String motDePasse;

    /** L'email de l'utilisateur */
    private String email;

    /** Le domicile de l'utilisateur */
    private String domicile;

    /** Parcours enregistrés par l'utilisateur. */
    private ArrayList<Parcours> parcours;

    /**
     * Crée un nouvel utilisateur.
     * @param nom le nom de l'utilisateur
     * @param prenom le prénom de l'utilisateur
     * @param domicile le domicile de l'utilisateur
     * @param email l'email de l'utiliteur
     * @param mDP le mot de passe de l'utilisateur
     */
    public Utilisateur(String id, String nom, String prenom, int age,
                       NiveauEntrainement niveauEntrainement,
                       Morphologie morphologie, String mDP, String email,
                       String domicile) {

        super(nom, prenom, age, niveauEntrainement, morphologie);

        setMotDePasse(mDP);
        setEmail(email);
        setDomicile(domicile);

        parcours = new ArrayList<>();
    }

    /**
     * Renvoi le mot de passe utilisateur
     * @return le mot de passe
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Change le mot de passe utilisateur
     * @param motDePasse le nouveau mot de passe
     */
    public void setMotDePasse(String motDePasse) {
        if (motDePasse == null || motDePasse.trim().equals("")) {
            throw new IllegalArgumentException("Le mot de passe incorrect");
        }
        this.motDePasse = motDePasse;
    }

    /** @return l'ID */
    public String getId() {
        return id;
    }

    /**
     * Renvoi l'email utilisateur
     * @return l'email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Change l'email utilisateur
     * @param email le nouvel email utilisateur
     */
    public void setEmail(String email) {
        if (email == null || email.trim().equals("")) {
            throw new IllegalArgumentException("L'email est incorrect");
        }
        this.email = email;
    }

    /**
     * Renvoi le domicile de l'utilisateur
     * @return l'adresse
     */
    public String getDomicile() {
        return domicile;
    }

    /**
     * Change l'adresse de l'utilisateur
     * @param domicile le domicile de l'utilisateur
     */
    public void setDomicile(String domicile) {
        if (domicile == null || domicile.trim().equals("")) {
            throw new IllegalArgumentException("Le domicile incorrect");
        }
        this.domicile = domicile;
    }

    /**
     * Ajoute un parcours dans la liste des parcours utilisateur.
     * @param parcours
     */
    public void ajouterParcours(Parcours parcours) {

        if (parcours != null) {
            this.parcours.add(parcours);
        }
    }

    public ArrayList<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(ArrayList<Parcours> parcours) {
        this.parcours = parcours;
    }
}
