package fr.iutrodez.roadeo.modele;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Document(collection = "randonnee")
public class Randonnee {

    /** Le nombre maximum de paticipants possible à une randonnée */
    private final int NB_MAX_PARTICIPANT = 3;

    /** La durée minimale d'une randonnée en jour */
    private final int NB_JOURS_MIN = 1;

    /** La durée maximale d'une randonnée en jour */
    private final int NB_JOURS_MAX = 3;

    @Id
    private String id;

    private String libelle;

    @Field("participants_max")
    private int participantsMax = 3;

    @Field("point_depart")
    private PointInteret depart;

    @Field("point_arrive")
    private PointInteret arrive;

    @Field("nombre_jours")
    private int nombreJours;

    private ArrayList<Parcours> parcours;

    private ArrayList<Participant> participants;

    /**
     * Controleur vide pour mongo DB
     */
    public Randonnee() {

    }

    /**
     * Crée une nouvelle randonnée avec les paramètres saisis
     * @param id L'id de la randonnée
     * @param libelle Le nom de la randonnée
     * @param nombreJours La durée en jours de la randonnée
     * @param depart Le point de départ de la randonnée
     * @param arrive Le point d'arrivée de la randonnée
     * @throws IllegalArgumentException si :
     * <ul>
     *     <li>libelle est blanc</li>
     *     <li>participantsMax<=0 || participantsMax>NB_MAX_PARTICIPANT</li>
     *     <li>nombreJours<1 || nombreJours>3</li>
     * </ul>
     */
    public Randonnee(String id, String libelle,
                     int nombreJours, PointInteret depart,
                     PointInteret arrive, ArrayList<Participant> participants) {
        if (libelle.isBlank()) {
            throw new IllegalArgumentException("Le libellé est vide");
        }
        if (participants.size()>NB_MAX_PARTICIPANT) {
            throw new IllegalArgumentException("Il y a trop de participants"
                                               + " dans la randonnée");
        }

        if (nombreJours<NB_JOURS_MIN) {
            throw new IllegalArgumentException("La durée de la randonnée doit"
                                               + "être d'au moins 1 jour");
        }
        if (nombreJours>NB_JOURS_MAX) {
            throw new IllegalArgumentException("La durée de la randonnée ne peut"
                                               + "pas dépasser 3 jours");
        }
        this.id = id;
        this.libelle = libelle;
        this.nombreJours = nombreJours;
        this.depart = depart;
        this.arrive = arrive;
        this.parcours = new ArrayList<>();
        this.participants = participants;
    }

    /**
     * Renvoi l'id de la randonnée
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Inutilisé
     * @param id Inutilisé
     */
    public void setId(String id) {}

    /**
     * Récupère le nom de la randonnée
     * @return libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Permet de mettre à jours le libellé de la rando
     * @param libelle Le nouveau libellés
     * @throws IllegalArgumentException Si le libellé est blanc
     */
    public void setLibelle(String libelle) {
        if (libelle.isBlank()) {
            throw new IllegalArgumentException("Le libellé est vide");
        }
        this.libelle = libelle;
    }

    /**
     * Renvoi le nombre maximum de participant
     * @return participantsMax
     */
    public int getParticipantsMax() {
        return participantsMax;
    }

    /**
     * Modifie le nombre maximum de participant à la randonnée
     * @param participantsMax le nouveau nombre max de participant
     */
    public void setParticipantsMax(int participantsMax) {
        if (participantsMax>NB_MAX_PARTICIPANT) {
            throw new IllegalArgumentException("Il y a trop de participants"
                                               + " dans la randonnée");
        }
        if (participantsMax<=0) {
            throw new IllegalArgumentException("Il doit y avoir au moins 1"
                                               + " participant à la raandonée");
        }
        this.participantsMax = participantsMax;
    }

    /**
     * Récupère le nombre de jours durant lequel va durer la randonnée
     * @return nombreJours
     */
    public int getNombreJours() {
        return nombreJours;
    }

    /**
     * Modifie la durée en jours d'une randonnée
     * @param nombreJours la nouvelle durée en jours
     */
    public void setNombreJours(int nombreJours) {
        if (nombreJours<NB_JOURS_MIN) {
            throw new IllegalArgumentException("La durée de la randonnée doit"
                    + "être d'au moins 1 jour");
        }
        if (nombreJours>NB_JOURS_MAX) {
            throw new IllegalArgumentException("La durée de la randonnée ne peut"
                    + "pas dépasser 3 jours");
        }
        this.nombreJours = nombreJours;
    }

    /**
     * Renvoi le point de départ de la randonnée
     * @return depart
     */
    public PointInteret getDepart() {
        return depart;
    }

    /**
     * Modifie le point de départ de la randonnée
     * @param coordonnee le nouveau point de départ de la randonnée
     */
    public void setDepart(double[] coordonnee) {
        depart.setCoordonnees(coordonnee);
    }

    /**
     * Renvoi le point d'arrivée de la randonnée
     * @return arrive
     */
    public PointInteret getArrive() {
        return arrive;
    }

    /**
     * Modifie le point d'arrivé de la randonnée
     * @param coordonnee les nouveau point d'arrivé de la randonée
     */
    public void setArrive(double[] coordonnee) {
        arrive.setCoordonnees(coordonnee);
    }

    /**
     * renvoi les parcours associés à la randonnée
     * @return parcours
     */
    public ArrayList<Parcours> getParcours() {
        return parcours;
    }

    /**
     * Modifie la liste des parcours liés à la randonnée
     * @param parcours les nouveaux parcours de la randonnée
     */
    public void setParcours(ArrayList<Parcours> parcours) {
        this.parcours = parcours;
    }

    /**
     * Permet d'ajouter un parcour à la randonnée
     * @param parcours le parcours à ajouter
     */
    public void addParcours(Parcours parcours) {
        this.parcours.add(parcours);
    }

    /**
     * Permet de supprimer un parcours de la liste des parcours
     * @param parcours le parcours à supprimer
     */
    public void removeParcours(Parcours parcours) {
        this.parcours.remove(parcours);
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    /** liste des randonnées */
    public ArrayList<Participant> getParticipants() {
        return this.participants;
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

    @Override
    public String toString() {
        return "Randonnee{" +
                "NB_MAX_PARTICIPANT=" + NB_MAX_PARTICIPANT +
                ", NB_JOURS_MIN=" + NB_JOURS_MIN +
                ", NB_JOURS_MAX=" + NB_JOURS_MAX +
                ", id='" + id + '\'' +
                ", libelle='" + libelle + '\'' +
                ", participantsMax=" + participantsMax +
                ", depart=" + depart +
                ", arrive=" + arrive +
                ", nombreJours=" + nombreJours +
                ", parcours=" + parcours +
                ", participants=" + participants +
                '}';
    }

    public void setDepart(PointInteret depart) {
        this.depart = depart;
    }

    public void setArrive(PointInteret arrive) {
        this.arrive = arrive;
    }
}
