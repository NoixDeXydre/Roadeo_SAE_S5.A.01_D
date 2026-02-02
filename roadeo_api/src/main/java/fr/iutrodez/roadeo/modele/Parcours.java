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
    public Parcours(String id, String idRando, String idUtilisateur, String libelleRandonnee,
                    ArrayList<Participant> participants,  ArrayList<PointInteret> pointInterets) {
        if ( idRando == null || idRando.isEmpty()
                || idUtilisateur == null || idUtilisateur.isEmpty()
                || libelleRandonnee == null || libelleRandonnee.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.idRando = idRando;
        this.idUtilisateur = idUtilisateur;
        this.libelleRandonnee = libelleRandonnee;
        this.participants = participants;
        this.pointInterets = pointInterets;
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

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    public void setPointInterets(ArrayList<PointInteret> pointInterets) {
        this.pointInterets = pointInterets;
    }

    /** liste des randonnées */
    public ArrayList<Participant> getParticipants() {
        return this.participants;
    }

    public ArrayList<PointInteret> getPointInterets() {
        return this.pointInterets;
    }

    /**
     * Calcul de Kcal selon la formule de Mifflin–St Jeor
     * MB = 10 × poids(kg) + 6,25 × taille(cm) − 5 × âge + 5
     * -> on prend la formule du calcul de Mifflin-St Jeor pour un homme au repos
     * et on ultiplie par 1,9 pour simuler l'activité sportive
     * @return le nombre de kilo calorie d'une personne
     */
    private double calculKcalParticipant(double poids, double taille, int age) {
        return (10 * poids + 6.25 * taille - 5 * age + 5) * 1.9;
    }

    /**
     * Calcul le nombre Kcal total pour l'ensemble des participants du parcours
     * @param jour durée de la randonnée en jour
     * @return les besoins en Kcal pour tout le parcours
     */
    public double calculKcalTotal(int jour) {
        double result = 0.0;
        for (Participant participant : this.participants) {
            result += calculKcalParticipant(participant.poidsApproximatif(),
                    participant.tailleApproximative(),
                    participant.getAge());
        }
        return result * jour;
    }

    /**
     * Calcul le poids max emportable par les participants du parcours
     * @return le poids maximum
     */
    public double poidsMax() {
        double result = 0.0;
        for (Participant participant : this.participants) {
            result += participant.poidsApproximatif();
        }
        return result;
    }

    public double poidsRando() {
        double result = 0.0;
        for (Participant participant : this.participants) {
            result += participant.poidsRando();
        }
        return result;
    }
}
