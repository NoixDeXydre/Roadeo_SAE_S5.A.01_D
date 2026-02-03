package fr.iutrodez.roadeo.modele;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgoSacADosTest {

    private static ArrayList<Produits> listeObjets = new ArrayList<>();
    private static ArrayList<SacADos> listeSacADos = new ArrayList<>();

    @BeforeEach
    void setup() {
        Produits obj1 = new Produits("extra", "gourde d'eau", "gourde", "gourde remplit d'eau d'environ 1 litre", 2.2, 2.0,1, 0);
        Produits obj2 = new Produits("extra", "bouteille", "Bouteille isoterme", "Bouteille qui conserve la fraicheur de la glace ou la chaleur d'un volcan", 9.2, 1.1, 1, 0);
        Produits obj3 = new Produits("extra", "jeumelle", "Jumelles", "Permet d'observer la nature de loin. Il serait dommage de faire fuir une licorne par votre proximité.", 3.0, 1.0,1,  0);
        Produits obj4 = new Produits("extra", "baton", "Batôn de marche", "Baton de marche pour faciliter la progression sur des sentiers battues ou pour diminuer les efforts", 8.0, 3.0,1,  0);
        Produits obj5 = new Produits("extra", "photo", "Appareil photo", "Appareil permettant de prendre des photos de vos plus beaux voyages. Immortalisez tous les rossignols qui éternuent", 2.2, 5.0,1,  0);
        Produits obj6 = new Produits("bivouac", "couchage", "sac de couchage", "Pour se reposer dans la c;haleur d'un cocon", 2.2, 2.2, 2, 0);
        Produits obj7 = new Produits("bivouac", "tente", "tente pour 3", "Pour se protéger des moustiques et s'assurer une nuit protégée du froid.", 2.2, 5.0, 2, 0);
        Produits obj8 = new Produits("bivouac", "couchage", "sac de couchage", "Pour se reposer dans la chaleur d'un cocon", 2.2, 2.2,2, 0);
        Produits obj9 = new Produits("bivouac", "couchage", "sac de couchage", "Pour se reposer dans la chaleur d'un cocon", 2.2, 2.2, 2, 0);
        Produits obj10 = new Produits("extra", "gourde", "gourde", "gourde remplit d'eau d'environ 1 litre", 2.2, 2.0, 5, 0);
        Produits obj11 = new Produits("extra", "bouteille", "Bouteille isoterme", "Bouteille qui conserve la fraicheur de la glace ou la chaleur d'un volcan", 9.2, 1.1, 5, 0);
        Produits obj12 = new Produits("extra", "Jumelles", "jumelle", "Permet d'observer la nature de loin. Il serait dommage de faire fuir une licorne par votre proximité.", 3.0, 2.0, 1, 0);
        Produits obj13 = new Produits("extra", "baton", "Batôn de marche", "Baton de marche pour faciliter la progression sur des sentiers battues ou pour diminuer les efforts", 8.0, 3.0, 4, 0);

        SacADos sportif = new SacADos(16, 0); // moyen
        SacADos entraine = new SacADos(18, 0); // fort
        SacADos debutant = new SacADos(7.5, 0); // leger

        listeObjets.addAll(Arrays.asList(obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13));
        listeSacADos.addAll(Arrays.asList(sportif, entraine, debutant));
    }


}
