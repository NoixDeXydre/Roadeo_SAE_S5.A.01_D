package fr.iutrodez.roadeo.modele;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

/**
 * Classe qui recevra les données d'un document de la collection parcours de mongoDB
 */
@Document(collection = "parcours")
public class Parcours {

    @Id
    private String id;

    /** id de la randonnée auquelle le parcours est associé */
    private String idRando;

    /** Permet de savoir si le parcours est sélectionné pour la randonnée */
    private boolean selection;

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
    //private ArrayList<Participant> participants;

    private ArrayList<PointInteret> pointInterets;

    /** Constructeur vide pour laisser mongoDB gérer avec les getters */
    public Parcours() {}

    /** Constructeur manuel */
    public Parcours(String id, String idRando, String idUtilisateur, String libelleRandonnee,
                    ArrayList<PointInteret> pointInterets) {
        if ( idRando == null || idRando.isEmpty()
                || idUtilisateur == null || idUtilisateur.isEmpty()
                || libelleRandonnee == null || libelleRandonnee.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.idRando = idRando;
        this.idUtilisateur = idUtilisateur;
        this.libelleRandonnee = libelleRandonnee;
        this.pointInterets = pointInterets;
        this.selection = false;
    }

    /** Renvoi l'id du parcours */
    public String getId() {
        return this.id;
    }

    /** Renvoie l'id de la randonnée du parcours */
    public String getIdRando() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setIdRando(String idRando) {
        this.idRando = idRando;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setLibelleRandonnee(String libelleRandonnee) {
        this.libelleRandonnee = libelleRandonnee;
    }

    public void setPointInterets(ArrayList<PointInteret> pointInterets) {
        this.pointInterets = pointInterets;
    }

    public ArrayList<PointInteret> getPointInterets() {
        return this.pointInterets;
    }

    public boolean getSelection() {
        return this.selection;
    }

    public void setSelection(boolean selection) {
        this.selection = selection;
    }
}
