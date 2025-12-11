package fr.iutrodez.roadeo.modele;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

/**
 * Classe qui recevra les données d'un document de la collection parcours de mongoDB
 */
@Document(collection = "parcours")
public class Parcours {

    /** id de la randonnée auquelle le parcours est associé */
    private String idRando;

    /**
     * id de l'utilisateur auquelle le parcours est associé
     * @serialField id_utilisateur : champ mongo associé à la variable
     *  car nom différent
     */
    @Field("id_utilisateur")
    private String idUtilisateur;

    /**
     * id de l'utilisateur auquelle le parcours est associé
     * @serialField libelle_randonnee : champ mongo associé à la variable
     *  car nom différent
     */
    @Field("libelle_randonnee")
    private String libelleRandonnee;

    /** Liste des participants du parcours */
    private ArrayList<Participant> participants;

    private ArrayList<PointInteret> pointInterets;

    /** Constructeur vide pour laisser mongoDB gérer avec les getters */
    public Parcours() {}

    /** Constructeur manuel */
    public Parcours(String idRando, String idUtilisateur, String libelleRandonnee,
                    ArrayList<Participant> participants,  ArrayList<PointInteret> pointInterets) {
        if ( idRando == null || idRando.isEmpty()
                || idUtilisateur == null || idUtilisateur.isEmpty()
                || libelleRandonnee == null || libelleRandonnee.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.idRando = idRando;
        this.idUtilisateur = idUtilisateur;
        this.libelleRandonnee = libelleRandonnee;
        this.participants = participants;
        this.pointInterets = pointInterets;
    }

    /** Renvoie l'id de la randonnée du parcours */
    public String getId() {
        return this.idRando;
    }

    /** Renvoie l'id de l'utilisateur du parcours */
    public String getIdUtilisateur() {
        return this.idUtilisateur;
    }

    /** Renvoie le libelle de la randonnee */
    public String getLibelleRandonnee() {
        return this.libelleRandonnee;
    }

    /** liste des randonnées */
    public ArrayList<Participant> getParticipants() {
        return this.participants;
    }

    public ArrayList<PointInteret> getPointInterets() {
        return this.pointInterets;
    }
}
