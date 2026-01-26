package fr.iutrodez.roadeo.modele;

/**
 * Objet à sélectionner par l'utilisateur
 */
public class Objet {

    /** nom de l'objet **/
    private String nom;

    /** Categorie **/
    private String categorie;

    /** détail de l'objet **/
    private String detail;

    /** volume de l'objet **/
    private double volume;

    /** poids de l'objet **/
    private double poids;

    /** utilite de l'objet **/
    private int utilite;

    /** Indique si l'objet est dans un sac ou non **/
    private boolean presenceSac;

    public Objet() {}

    public Objet(String nom, String detail, double volume, double poids, int utilite) {
        this.nom = nom;
        this.detail = detail;
        this.volume = volume;
        this.poids = poids;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public int getUtilite() {
        return utilite;
    }

    public void setUtilite(int utilite) {
        this.utilite = utilite;
    }

    @Override
    public String toString() {
        return "Objet{" +
                "nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", detail='" + detail + '\'' +
                ", volume=" + volume +
                ", poids=" + poids +
                ", utilite=" + utilite +
                '}';
    }

    public boolean isPresenceSac() {
        return presenceSac;
    }

    public void setPresenceSac(boolean presenceSac) {
        this.presenceSac = presenceSac;
    }
}
