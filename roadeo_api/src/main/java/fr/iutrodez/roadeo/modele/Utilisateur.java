package fr.iutrodez.roadeo.modele;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "utilisateur")
public class Utilisateur {

    @Id
    private String id;
    private String patronime;
    private String mdp;
    private String adresse_mail;
    private String domicile;

    public Utilisateur(){}

    public Utilisateur(String id, String patronime, String mdp, String adresse_mail, String domicile) {
        this.id = id;
        this.patronime = patronime;
        this.mdp = mdp;
        this.adresse_mail = adresse_mail;
        this.domicile = domicile;
    }

    public String getId() {
        return id;
    }
}
