package fr.iutrodez.roadeo.modele;

import fr.iutrodez.roadeo.service.ObjetService;

import java.util.*;

import static java.util.Collections.list;

/** Permet de faire la répartition des objets dans des sacs */
public class AlgoSacADos {

    private static ArrayList<Produits> listeObjets = new ArrayList<>();
    private static ArrayList<SacADos> listeSacADos = new ArrayList<>();
    private ObjetService serviceO;

    /**
     * Initialise des données tests
     */
    public static void init() {
        SacADos sportif = new SacADos(16, 0); // moyen
        SacADos entraine = new SacADos(18, 0); // fort
        SacADos debutant = new SacADos(7.5, 0); // léger

        //listeObjets.addAll(Arrays.asList(serviceO.recupListeObjet()));
        //listeSacADos.addAll(Arrays.asList(sportif, entraine, debutant));
    }

    /**
     * Range les objets dans les sacs à dos selon leur capacité maximale
     * @param listeObjets liste des objets sélectionnés par l'utilisateur
     * @param listeSacADos liste des sacs à dos vides
     * @return la liste des sacs avec les objets attribués
     */
    public static ArrayList<SacADos> algoGlouton(ArrayList<Produits> listeObjets, ArrayList<SacADos> listeSacADos) {
        //algoTri();
        Produits objet;
        Boolean ok;
        // Pour chaque objet chosit
        for (int i = 0; i < listeObjets.size(); i++) {
            objet = listeObjets.get(i);

            // On vérifie s'il est possible de placer l'objet dans un sac
            for (int s = 0; s < listeSacADos.size(); s++) {
                if (listeSacADos.get(s).addObjet(objet)) {
                    objet.setPresenceSac(false);
                    break; // dès que l'objet est placé dans un sac, on s'arrête pour changer d'objet
                }
            }
        }
        return listeSacADos;
    }

    /**
     * TODO à corriger
     */
    private static void algoTri() {
        for (int i = 0; i < listeObjets.size(); i++) {
            Produits valeur = listeObjets.get(i);

            for (int j = i - 1; j >= 0 && listeObjets.get(j).getUtilite() < valeur.getUtilite(); j--) {
                listeObjets.set(j+1, listeObjets.get(j));
            }
        }
    }

    /**
     * Lance l'algo
     * @param args
     */
    public static void main(String[] args) {
        init();
        ArrayList<SacADos> listeSacTri = algoGlouton(listeObjets, listeSacADos);
        for (SacADos sac : listeSacTri) {
            System.out.println(sac.toString());
        }
    }

}
