package fr.iutrodez.roadeo.modele;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "utilisateur")
public class Utilisateur {

    @Id
    private String id;
    private String patronyme;
    private String mdp;

    @Field("adresse_mail")
    private String adresseMail;

    private String domicile;

    public Utilisateur(){}

    public Utilisateur(String id, String patronyme, String mdp, String adresseMail, String domicile) {
        this.id = id;
        this.patronyme = patronyme;
        this.mdp = mdp;
        this.adresseMail = adresseMail;
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

    public String getAdresseMail() { return adresseMail; }

    public String getDomicile() {
        return domicile;
    }
}
