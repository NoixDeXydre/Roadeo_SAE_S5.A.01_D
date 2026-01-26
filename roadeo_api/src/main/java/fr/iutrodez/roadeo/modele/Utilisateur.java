package fr.iutrodez.roadeo.modele;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "utilisateur")
public class Utilisateur extends Participant {

    @Id
    private String id;
    private String patronyme;
    private String mdp;

    @Field("adresse_mail")
    private String adresseMail;

    private String domicile;

    public Utilisateur(){super();}

    public Utilisateur(String id, String mdp, String adresseMail, String domicile,
                       String nom, String prenom,  int age, String niveauEntrainement,
                       String morphologie) {
        super(nom, prenom,  age, niveauEntrainement, morphologie);
        this.id = id;
        this.patronyme = prenom + " " + nom;
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

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public void setPatronyme(String patronyme) {
        this.patronyme = patronyme;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setId(String id) {
        this.id = id;
    }
}
