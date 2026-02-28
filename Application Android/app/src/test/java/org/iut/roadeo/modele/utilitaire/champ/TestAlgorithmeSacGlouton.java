package org.iut.roadeo.modele.utilitaire.champ;


import org.iut.roadeo.modele.Produit;
import org.iut.roadeo.modele.Randonneur;
import org.iut.roadeo.modele.SacADos;
import org.iut.roadeo.modele.algorithmes_sac.AlgorithmeSacGlouton;

import org.iut.roadeo.modele.typedonnees.Morphologie;
import org.iut.roadeo.modele.typedonnees.NiveauEntrainement;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.Arrays;

public class TestAlgorithmeSacGlouton {
    private static ArrayList<Produit> listeObjets = new ArrayList<>();
    private static ArrayList<SacADos> listeSacADos = new ArrayList<>();
    private static ArrayList<Randonneur> participants = new ArrayList<>();

    @Before
    public void setup() {
        Produit obj1 = new Produit("extra", "gourde d'eau", "gourde", "gourde remplit d'eau d'environ 1 litre", 2.2, 2.0,1, 0);
        Produit obj2 = new Produit("extra", "bouteille", "Bouteille isoterme", "Bouteille qui conserve la fraicheur de la glace ou la chaleur d'un volcan", 9.2, 1.1, 1, 0);
        Produit obj3 = new Produit("extra", "jeumelle", "Jumelles", "Permet d'observer la nature de loin. Il serait dommage de faire fuir une licorne par votre proximité.", 3.0, 1.0,1,  0);
        Produit obj4 = new Produit("extra", "baton", "Batôn de marche", "Baton de marche pour faciliter la progression sur des sentiers battues ou pour diminuer les efforts", 8.0, 3.0,1,  0);
        Produit obj5 = new Produit("extra", "photo", "Appareil photo", "Appareil permettant de prendre des photos de vos plus beaux voyages. Immortalisez tous les rossignols qui éternuent", 2.2, 5.0,1,  0);
        Produit obj6 = new Produit("bivouac", "couchage", "sac de couchage", "Pour se reposer dans la c;haleur d'un cocon", 2.2, 2.2, 2, 0);
        Produit obj7 = new Produit("bivouac", "tente", "tente pour 3", "Pour se protéger des moustiques et s'assurer une nuit protégée du froid.", 2.2, 5.0, 2, 0);
        Produit obj8 = new Produit("bivouac", "couchage", "sac de couchage", "Pour se reposer dans la chaleur d'un cocon", 2.2, 2.2,2, 0);
        Produit obj9 = new Produit("bivouac", "couchage", "sac de couchage", "Pour se reposer dans la chaleur d'un cocon", 2.2, 2.2, 2, 0);
        Produit obj10 = new Produit("extra", "gourde", "gourde", "gourde remplit d'eau d'environ 1 litre", 2.2, 2.0, 5, 0);
        Produit obj11 = new Produit("extra", "bouteille", "Bouteille isoterme", "Bouteille qui conserve la fraicheur de la glace ou la chaleur d'un volcan", 9.2, 1.1, 5, 0);
        Produit obj12 = new Produit("extra", "Jumelles", "jumelle", "Permet d'observer la nature de loin. Il serait dommage de faire fuir une licorne par votre proximité.", 3.0, 2.0, 1, 0);
        Produit obj13 = new Produit("extra", "baton", "Batôn de marche", "Baton de marche pour faciliter la progression sur des sentiers battues ou pour diminuer les efforts", 8.0, 3.0, 4, 0);
        Randonneur rando = new Randonneur("machin", "machine", 15, NiveauEntrainement.SPORTIF, Morphologie.LEGER);
        Randonneur rando2 = new Randonneur("machin", "mashine", 20, NiveauEntrainement.SPORTIF, Morphologie.MOYEN);
        SacADos sportif = new SacADos(16); // moyen
        SacADos entraine = new SacADos(18); // fort
        SacADos debutant = new SacADos(7.5); // leger

        listeObjets.addAll(Arrays.asList(obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13));
        listeSacADos.addAll(Arrays.asList(sportif, entraine, debutant));
    }

    @Test
    @DisplayName("Test la répartition du sac à dos")
    public void testGetSacADosRepartis() {
        Produit obj1 = new Produit("extra", "gourde d'eau", "gourde", "gourde remplit d'eau d'environ 1 litre", 2.2, 2.0,1, 0);
        Produit obj2 = new Produit("extra", "bouteille", "Bouteille isoterme", "Bouteille qui conserve la fraicheur de la glace ou la chaleur d'un volcan", 9.2, 1.0, 1, 0);
        Produit obj3 = new Produit("extra", "jeumelle", "Jumelles", "Permet d'observer la nature de loin. Il serait dommage de faire fuir une licorne par votre proximité.", 3.0, 1.0,1,  0);
        listeObjets.addAll(Arrays.asList(obj1, obj2, obj3));
        SacADos sportif = new SacADos(2); // moyen
        SacADos entraine = new SacADos(2); // fort
        AlgorithmeSacGlouton algo = new AlgorithmeSacGlouton();
        sportif.ajouterProduit(obj1);
        sportif.ajouterProduit(obj2);
        entraine.ajouterProduit(obj3);
        listeSacADos.addAll(Arrays.asList(sportif, entraine));
        assertEquals(listeSacADos, algo.getSacADosRepartis(listeObjets, listeSacADos));
    }

}
