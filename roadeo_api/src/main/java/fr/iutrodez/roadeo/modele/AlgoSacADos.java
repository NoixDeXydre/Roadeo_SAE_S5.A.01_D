package fr.iutrodez.roadeo.modele;

import java.util.*;

import static java.util.Collections.list;

public class AlgoSacADos {

    private ArrayList<Objet> listeObjets = new ArrayList<>();
    private ArrayList<SacADos> listeSacADos = new ArrayList<>();

    public void init() {
        Objet obj1 = new Objet("gourde", "gourde remplit d'eau d'environ 1litre", 2.2, 5.0, 5);
        Objet obj2 = new Objet("Bouteille isoterme", "Bouteille qui conserve la fraicheur ou la chaleur", 9.2, 10.0, 5);
        Objet obj3 = new Objet("Jumelles", "Permet d'observe la nature de loin. Il serait dommage de faire fuir une licorne par votre proximitée.", 3.0, 1.0, 1);
        Objet obj4 = new Objet("Batôn de marche", "Baton de marche pour faciliter la progression sur des sentiers battues ou pour diminuer les efforts", 10.0, 3.0, 4);
        Objet obj5 = new Objet("Appareil photo", "Appareil permettant de prendre des photos de vos plus beau voyage", 2.2, 5.0, 2);

        SacADos debutant = new SacADos(null, 6, 0);
        SacADos expert = new SacADos(null, 10, 0);

        listeObjets.addAll(Arrays.asList(obj1, obj2, obj3, obj4, obj5));
        listeSacADos.addAll(Arrays.asList(debutant, expert));
    }

    /** Vérifier que le contenu des sacs ne dépassant les volumes maximum **/
    public boolean verifVolume() {
        boolean ok = true;
        double sommeVolume = 0;
        for (SacADos sac: listeSacADos) {
            for (Objet objet : sac.getContenu()) {
                sommeVolume += objet.getVolume();
            }
            ok = sac.getVolumeMax() > sommeVolume;
            if (!ok) { // Si un volume d'un sac ne correspond pas on arrête
                return ok;
            }
        }
        return ok;
    }

    /** Fait la somme des valeurs des objets **/
    public boolean attributValeur() {
        int sommeValeur = 0;
        for (SacADos sac: listeSacADos) {
            for (Objet objet : sac.getContenu()) {
                sommeValeur += objet.getUtilite();
            }
            sac.setValeur(sommeValeur);
        }
        return sommeValeur > 0;
    }

    public void algoGlouton() {
        Objet valeur = null;
        Objet valeurPrecedente = null;
        Objet bouchon;
        for (int i = 0; i < listeObjets.size(); i++) {
            valeur = listeObjets.get(i);
            for (int y = i; y != 0 && valeur.getUtilite() < valeurPrecedente.getUtilite(); y--) {
                bouchon = valeur;
                listeObjets.get(y) = valeurPrecedente;
                if (y > 0) {
                    listeObjets.get(y - 1) = valeur;
                }
                valeur = valeurPrecedente;
            }
        }
    }



}
