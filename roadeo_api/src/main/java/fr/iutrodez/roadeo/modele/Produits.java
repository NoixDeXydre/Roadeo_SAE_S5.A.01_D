package fr.iutrodez.roadeo.modele;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Objet à sélectionner par l'utilisateur
 */
@Document(collection = "produits")
public class Produits {

    @Id
    private String id;

    /** nom de l'objet **/
    private String denomination;

    /** nom de l'objet **/
    private String nom;

    /** Categorie **/
    private String categorie;

    /** détail de l'objet **/
    private String description;

    /** poids de l'objet **/
    private double masse;

    /** poids de l'objet **/
    private double nutrition;

    /** poids de l'objet **/
    private double prix;

    /** utilite de l'objet **/
    private int utilite;

    /** Indique si l'objet est dans un sac ou non **/
    private boolean presenceSac = false;

    /** Id de la randonnee associee **/
    private String idRandonnee;


    /**
     * Permet à MongoDB de créer un produit
     */
    public Produits() {}

    /**
     * Crée un produit
     * @param nom nom du produits
     * @param description description du produit
     * @param categorie catégorie du produit
     * @param denomination nom du produits permettant de le différencier
     * @param prix prix du produit
     * @param poids poids du produit en gramme
     * @param utilite utilite du produit
     * @param nutrition niveau nutritionnel en kcal
     */
    public Produits(String nom, String description, String categorie, String denomination,
                 double prix, double poids, int utilite, double nutrition) {
        this.nom = nom;
        this.categorie = categorie;
        this.denomination = denomination;
        this.presenceSac = false;
        this.masse = poids;
        this.description = description;
        this.nutrition = nutrition;
        this.prix = prix;
        this.utilite = utilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getUtilite() {
        return utilite;
    }

    public void setUtilite(int utilite) {
        this.utilite = utilite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMasse() {
        return masse;
    }

    public void setMasse(double masse) {
        this.masse = masse;
    }

    public double getNutrition() {
        return nutrition;
    }

    public void setNutrition(double nutrition) {
        this.nutrition = nutrition;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean getPresenceSac() {
        return presenceSac;
    }

    public void setPresenceSac(boolean presenceSac) {
        this.presenceSac = presenceSac;
    }

    public String getIdRandonnee() {
        return idRandonnee;
    }

    public void setIdRandonnee(String idRandonnee) {
        this.idRandonnee = idRandonnee;
    }

    @Override
    public String toString() {
        return "Produits{" +
                "id='" + id + '\'' +
                ", denomination='" + denomination + '\'' +
                ", nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", description='" + description + '\'' +
                ", masse=" + masse +
                ", nutrition=" + nutrition +
                ", prix=" + prix +
                ", utilite=" + utilite +
                ", presenceSac=" + presenceSac +
                ", idRandonnee='" + idRandonnee + '\'' +
                '}';
    }
}
