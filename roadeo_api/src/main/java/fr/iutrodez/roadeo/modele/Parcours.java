package fr.iutrodez.roadeo.modele;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Document(collection = "parcours")
public class Parcours {

    private String id;

    @Field("id_utilisateur")
    private String idUtilisateur;

    @Field("libelle_randonnee")
    private String libelleRandonnee;
    private ArrayList<Participant> participants;

    public Parcours() {}

    public Parcours(String id, String idUtilisateur, String libelleRandonnee, ArrayList<Participant> participants) {
        if ( id == null || id.isEmpty()
                || idUtilisateur == null || idUtilisateur.isEmpty()
                || libelleRandonnee == null || libelleRandonnee.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.libelleRandonnee = libelleRandonnee;
        this.participants = participants;
    }

    public String getId() {
        return this.id;
    }

    public String getIdUtilisateur() {
        return this.idUtilisateur;
    }

    public String getLibelleRandonnee() {
        return this.libelleRandonnee;
    }

    public ArrayList<Participant> getParticipants() {
        return this.participants;
    }
}
