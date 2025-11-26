package fr.iutrodez.roadeo.modele;


public class Utilisateur {
    private int id;
    private String patronyme;
    private String mdp;
    private String adresse_mail;
    private String domicile;

    public Utilisateur(int id, String patronyme, String mdp, String adresse_mail, String domicile) {

    }

    public int getId() {
        return id;
    }
}
