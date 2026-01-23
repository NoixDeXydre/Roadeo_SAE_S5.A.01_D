package fr.iutrodez.roadeo.modele;

import java.util.*;

import static java.util.Collections.list;

public class AlgoSacADos {

    private static ArrayList<Objet> listeObjets = new ArrayList<>();
    private static ArrayList<SacADos> listeSacADos = new ArrayList<>();

    public static void init() {
        Objet obj1 = new Objet("gourde", "gourde remplit d'eau d'environ 1 litre", 2.2, 2.0, 5);
        Objet obj2 = new Objet("Bouteille isoterme", "Bouteille qui conserve la fraicheur de la glace ou la chaleur d'un volcan", 9.2, 1.1, 5);
        Objet obj3 = new Objet("Jumelles", "Permet d'observer la nature de loin. Il serait dommage de faire fuir une licorne par votre proximité.", 3.0, 1.0, 1);
        Objet obj4 = new Objet("Batôn de marche", "Baton de marche pour faciliter la progression sur des sentiers battues ou pour diminuer les efforts", 8.0, 3.0, 4);
        Objet obj5 = new Objet("Appareil photo", "Appareil permettant de prendre des photos de vos plus beaux voyages. Immortalisez tous les rossignols qui éternuent", 2.2, 5.0, 2);
        Objet obj6 = new Objet("sac de couchage", "Pour se reposer dans la chaleur d'un cocon", 2.2, 2.2, 2);
        Objet obj7 = new Objet("tante pour 3", "Pour se protéger des moustiques et s'assurer une nuit protégée du froid.", 2.2, 5.0, 2);
        Objet obj8 = new Objet("sac de couchage", "Pour se reposer dans la chaleur d'un cocon", 2.2, 2.2,2);
        Objet obj9 = new Objet("sac de couchage", "Pour se reposer dans la chaleur d'un cocon", 2.2, 2.2, 2);
        Objet obj10 = new Objet("gourde", "gourde remplit d'eau d'environ 1 litre", 2.2, 2.0, 5);
        Objet obj11 = new Objet("Bouteille isoterme", "Bouteille qui conserve la fraicheur de la glace ou la chaleur d'un volcan", 9.2, 1.1, 5);
        Objet obj12 = new Objet("Jumelles", "Permet d'observer la nature de loin. Il serait dommage de faire fuir une licorne par votre proximité.", 3.0, 2.0, 1);
        Objet obj13 = new Objet("Batôn de marche", "Baton de marche pour faciliter la progression sur des sentiers battues ou pour diminuer les efforts", 8.0, 3.0, 4);

        SacADos sportif = new SacADos(16, 0); // moyen
        SacADos entraine = new SacADos(18, 0); // fort
        SacADos debutant = new SacADos(7.5, 0); // leger

        listeObjets.addAll(Arrays.asList(obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13));
        listeSacADos.addAll(Arrays.asList(sportif, entraine, debutant));
    }

    /** Vérifier que le contenu des sacs ne dépasse les volumes maximum **/
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

    public static void algoGlouton() {
        //algoTri();
        Objet objet;
        Boolean ok;
        for (int i = 0; i < listeObjets.size(); i++) {
            objet = listeObjets.get(i);

            for (int s = 0; s < listeSacADos.size(); s++) {
                if (listeSacADos.get(s).addObjet(objet)) {
                    objet.setPresenceSac(false);
                    break;
                }
            }
        }
    }

    private static void algoTri() {
        for (int i = 0; i < listeObjets.size(); i++) {
            Objet valeur = listeObjets.get(i);

            for (int j = i - 1; j >= 0 && listeObjets.get(j).getUtilite() < valeur.getUtilite(); j--) {
                listeObjets.set(j+1, listeObjets.get(j));
            }
        }
    }

    public static void main(String[] args) {
        init();
        algoGlouton();
        for (SacADos sac : listeSacADos) {
            System.out.println(sac.toString());
        }
    }

}
