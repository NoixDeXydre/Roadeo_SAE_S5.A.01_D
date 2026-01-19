package fr.iutrodez.roadeo.modele;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "randonnee")
public class Randonnee {

    @Id
    private String id;

    private String libelle;

    private ArrayList<Parcours> parcours;

    public Randonnee(String id, String libelle) {
        if ( libelle.isEmpty() ) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.libelle = libelle;
        this.parcours = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {}

    public String getLibelle() {
        return libelle;
    }

    public ArrayList<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(ArrayList<Parcours> parcours) {
        this.parcours = parcours;
    }
}
