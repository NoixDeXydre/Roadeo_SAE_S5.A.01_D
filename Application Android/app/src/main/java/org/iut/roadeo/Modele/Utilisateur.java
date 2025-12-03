package org.iut.roadeo.Modele;

/**
 * Représente un utilisateur.
 * Pour rappel, un utilisateur est un randonneur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class Utilisateur extends Randonneur {

    /** Le mot de passe utilisateur */
    private String motDePasse;

    /** L'email de l'utilisateur */
    private String email;

    /** Le domicile de l'utilisateur */
    private String domicile;

    /**
     * Crée un nouvel utilisateur.
     * @param nom le nom du randonneur
     * @param prenom le prénom du randonneur
     */
    public Utilisateur(String nom, String prenom, String mDP, String email,
                       String domicile) {
        super(nom, prenom);
        this.motDePasse = mDP;
        this.email = email;
        this.domicile = domicile;
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
        this.motDePasse = motDePasse;
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
        this.domicile = domicile;
    }
}
