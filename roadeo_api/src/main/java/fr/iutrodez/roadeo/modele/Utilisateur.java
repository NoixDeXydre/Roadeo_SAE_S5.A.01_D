package fr.iutrodez.roadeo.modele;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "utilisateur")
public class Utilisateur {

    @Id
    private String id;
    private String patronyme;
    private String mdp;
    private String adresse_mail;
    private String domicile;

    public Utilisateur(){}

    public Utilisateur(String id, String patronyme, String mdp, String adresse_mail, String domicile) {
        this.id = id;
        this.patronyme = patronyme;
        this.mdp = mdp;
        this.adresse_mail = adresse_mail;
        this.domicile = domicile;
    }

    public String getId() {
        return id;
    }

    public String getPatronyme() {
        return patronyme;
    }

    public String getMdp() {
        return mdp;
    }

    public String getAdresse_mail() {
        return adresse_mail;
    }

    public String getDomicile() {
        return domicile;
    }
}
