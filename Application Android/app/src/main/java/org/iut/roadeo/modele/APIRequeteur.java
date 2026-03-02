package org.iut.roadeo.modele;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.iut.roadeo.modele.interfaces.IAPIParcoursCallback;
import org.iut.roadeo.modele.interfaces.IAPIRandonneesCallback;
import org.iut.roadeo.modele.interfaces.IAPIRandonneursCallback;
import org.iut.roadeo.modele.interfaces.IAPIUtilisateurCallback;
import org.iut.roadeo.modele.typedonnees.Morphologie;
import org.iut.roadeo.modele.typedonnees.NiveauEntrainement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Gère les appels API.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class APIRequeteur {

    // TODO utiliser une clé API dans les méthodes
    // Il suffit de la mettre dans le header.

    // ===== Messages d'erreur =====

    private final static String MESSAGE_ERREUR_LOGIN_ECHEC
            = "Echec : L'utilisateur n'a pas pu se connecter," +
            " l'adresse mail ou le mot de passe est incorrecte.";

    private final static String MESSAGE_ERREUR_QUELCONQUE
            = "Echec : Une erreur inconnue est survenue.";

    private final static String MESSAGE_ERREUR_SERVEUR_ECHEC
            = "Echec : Le serveur est en dysfonctionnement ou indisponible.";

    private final static String MESSAGE_ERREUR_CONNEXION
            = "Echec : Aucune connexion.";

    private final static String MESSAGE_ERREUR_SERVEUR_INTROUVABLE
            = "Echec : Le serveur est introuvable.";

    // ======= Commandes API - Utilisateur =======
    private final static String SUFFIXE_API_UTILISATEUR = "Utilisateur";
    private final static String SUFFIXE_API_SE_CONNECTER = SUFFIXE_API_UTILISATEUR
            + "/seConnecter";
    private final static String SUFFIXE_API_AJOUT_UTILISATEUR = SUFFIXE_API_UTILISATEUR
            + "/ajoutUtilisateur";

    // ======= Commandes API - Randonnee =======

    private final static String SUFFIXE_API_RANDONNEE = "Randonnee";
    private final static String SUFFIXE_API_LISTE_PARTICIPANT = SUFFIXE_API_RANDONNEE
            + "/listeParticipant/";

    private final static String SUFFIXE_API_LISTE_PARCOURS = SUFFIXE_API_RANDONNEE
            + "/infoRandoUtil";
    private final static String SUFFIXE_API_LISTE_RANDONNEE = SUFFIXE_API_RANDONNEE
            + "/liste";
    private String prefixeUrl;
    private Context contexteApplication;
    private RequestQueue fileRequete;

    /**
     * Crée un nouveau requêteur de l'API.
     * @param contexteApplication le contexte d'une classe.
     *                 Elle devrait provenir d'une classe
     *                 qui ne se détruit pas (point d'entrée.)
     * @param prefixeUrl l'URL de base qui mène vers l'API.
     */
    public APIRequeteur(Context contexteApplication, String prefixeUrl) {
        this.contexteApplication = contexteApplication;
        this.prefixeUrl = prefixeUrl;
    }

    /**
     * Ajoute un utilisateur dans la base de données.
     * @param utilisateur l'utilisateur à ajouter dans la base de données.
     * @param callback les actions à effectuer.
     */
    public void ajouterUtilisateur(Utilisateur utilisateur,
                                   IAPIUtilisateurCallback callback) {

        String urlAppelAPI = prefixeUrl +  SUFFIXE_API_AJOUT_UTILISATEUR;

        HashMap<String, String> entreesJsonRequete = new HashMap<>();

        entreesJsonRequete.put("patronyme",
                utilisateur.getNom() + " " + utilisateur.getPrenom());
        entreesJsonRequete.put("adresseMail", utilisateur.getEmail());
        entreesJsonRequete.put("mdp", utilisateur.getMotDePasse());
        entreesJsonRequete.put("domicile", utilisateur.getDomicile());
        entreesJsonRequete.put("age", Integer.toString(utilisateur.getAge()));
        entreesJsonRequete.put("niveauEntrainement", utilisateur.getNiveauEntrainement().toString());
        entreesJsonRequete.put("morphologie", utilisateur.getMorphologie().toString());

        JsonObjectRequest requeteConnexion = new JsonObjectRequest(Request.Method.POST,
                urlAppelAPI, new JSONObject(entreesJsonRequete),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Utilisateur utilisateur;
                        try {
                            utilisateur = construireUtilisateurWithReponse(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        callback.onSuccess(utilisateur);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {

                        if (error instanceof ServerError)
                            callback.onError(MESSAGE_ERREUR_SERVEUR_ECHEC);
                        else if (error instanceof TimeoutError)
                            callback.onError(MESSAGE_ERREUR_SERVEUR_INTROUVABLE);
                        else if (error instanceof NoConnectionError)
                            callback.onError(MESSAGE_ERREUR_CONNEXION);
                        else
                            callback.onError(MESSAGE_ERREUR_QUELCONQUE);
                    }
                });

        getFileRequete().add(requeteConnexion.setTag(this));
    }

    /**
     * Récupère la clé API, confirmant la connexion de l'utilisateur.
     * @param adresseMail
     * @param mdp
     * @param callback les actions à effectuer.
     */
    public void seConnecter(String adresseMail, String mdp,
                            IAPIUtilisateurCallback callback) {

        // FIXME cette méthode devrait récupérer
        //       une clé API après confirmation

        String urlAppelAPI = prefixeUrl +  SUFFIXE_API_SE_CONNECTER;

        HashMap<String, String> entreesJsonRequete = new HashMap<>();
        entreesJsonRequete.put("adresseMail", adresseMail);
        entreesJsonRequete.put("mdp", mdp);

        JsonObjectRequest requeteConnexion = new JsonObjectRequest(Request.Method.POST,
                urlAppelAPI, new JSONObject(entreesJsonRequete),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Utilisateur utilisateur;
                        try {
                            utilisateur = construireUtilisateurWithReponse(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        callback.onSuccess(utilisateur);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {

                        if (error instanceof AuthFailureError)
                            callback.onError(MESSAGE_ERREUR_LOGIN_ECHEC);
                        else if (error instanceof ServerError)
                            callback.onError(MESSAGE_ERREUR_SERVEUR_ECHEC);
                        else if (error instanceof TimeoutError)
                            callback.onError(MESSAGE_ERREUR_SERVEUR_INTROUVABLE);
                        else if (error instanceof NoConnectionError)
                            callback.onError(MESSAGE_ERREUR_CONNEXION);
                        else
                            callback.onError(MESSAGE_ERREUR_QUELCONQUE);
                    }
                });

        getFileRequete().add(requeteConnexion.setTag(this));
    }

    public void listerParcours(int id, IAPIParcoursCallback callback) {
        String urlAppelAPI = prefixeUrl +  SUFFIXE_API_LISTE_PARCOURS;

        HashMap<String, String> entreesJsonRequete = new HashMap<>();
        entreesJsonRequete.put("id", Integer.toString(1));

        JsonArrayRequest requeteConnexion = new JsonArrayRequest(Request.Method.POST,
                urlAppelAPI, new JSONObject(entreesJsonRequete).names(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<Parcours> parcours = new ArrayList<>();
                        try {
                            parcours = construireParcoursWithReponse(
                                    response, id);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        callback.onSuccess(parcours);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError
                                                        error) {

                        if (error instanceof AuthFailureError)
                            callback.onError(MESSAGE_ERREUR_LOGIN_ECHEC);
                        else if (error instanceof ServerError)
                            callback.onError(MESSAGE_ERREUR_SERVEUR_ECHEC);
                        else if (error instanceof TimeoutError)
                            callback.onError(MESSAGE_ERREUR_SERVEUR_INTROUVABLE);
                        else if (error instanceof NoConnectionError)
                            callback.onError(MESSAGE_ERREUR_CONNEXION);
                        else
                            callback.onError(MESSAGE_ERREUR_QUELCONQUE);
                    }
                });

        getFileRequete().add(requeteConnexion.setTag(this));
    }

    public void listerParticipant(String id, IAPIRandonneursCallback callback) {
        String urlAppelAPI = prefixeUrl +  SUFFIXE_API_LISTE_PARTICIPANT + id;
        JsonArrayRequest requeteConnexion = new JsonArrayRequest(urlAppelAPI,
                                            new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<Randonneur> randonneurs = new ArrayList<>();
                try {
                    randonneurs = construireRandonneursWithReponse(
                                  response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                callback.onSuccess(randonneurs);
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(com.android.volley.VolleyError
                                        error) {

                if (error instanceof AuthFailureError)
                    callback.onError(MESSAGE_ERREUR_LOGIN_ECHEC);
                else if (error instanceof ServerError)
                    callback.onError(MESSAGE_ERREUR_SERVEUR_ECHEC);
                else if (error instanceof TimeoutError)
                    callback.onError(MESSAGE_ERREUR_SERVEUR_INTROUVABLE);
                else if (error instanceof NoConnectionError)
                    callback.onError(MESSAGE_ERREUR_CONNEXION);
                else
                    callback.onError(MESSAGE_ERREUR_QUELCONQUE);
            }
        });

        getFileRequete().add(requeteConnexion.setTag(this));
    }

    public void listerRandonnee(IAPIRandonneesCallback callback) {
        String urlAppelAPI = prefixeUrl + SUFFIXE_API_LISTE_RANDONNEE;
        JsonArrayRequest requeteConnexion = new JsonArrayRequest(urlAppelAPI,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<Randonnee> randonnees = new ArrayList<>();
                        try {
                            randonnees = construireRandonneesWithReponse(
                                    response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        callback.onSuccess(randonnees);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError
                                                        error) {

                        if (error instanceof AuthFailureError)
                            callback.onError(MESSAGE_ERREUR_LOGIN_ECHEC);
                        else if (error instanceof ServerError)
                            callback.onError(MESSAGE_ERREUR_SERVEUR_ECHEC);
                        else if (error instanceof TimeoutError)
                            callback.onError(MESSAGE_ERREUR_SERVEUR_INTROUVABLE);
                        else if (error instanceof NoConnectionError)
                            callback.onError(MESSAGE_ERREUR_CONNEXION);
                        else
                            callback.onError(MESSAGE_ERREUR_QUELCONQUE);
                    }
                });

        getFileRequete().add(requeteConnexion.setTag(this));
    }

    /**
     * Annule toutes les requêtes en cours.
     */
    public void annulerRequetes() {

        if (fileRequete != null) {
            fileRequete.cancelAll(this);
        }
    }

    private ArrayList<Parcours>
            construireParcoursWithReponse(JSONArray reponse, int id)
            throws JSONException {

        ArrayList<Parcours> aRetourner;

        JSONObject aCreer;
        Parcours parcours;
        Randonnee randonnee;

        aRetourner = new ArrayList<>();
        // Récupération des énums
        for(int i=0; i<reponse.length(); i++) {
            aCreer = reponse.getJSONObject(i);

            /* On récupère les différents paramètres de la randonnée */
            int idRando = aCreer.getInt("id");
            if (id == idRando) {
                /* On récupère les différents paramètres de la randonnée */
                String libelleRando = aCreer.getString("libelle");
                int nombreJours = aCreer.getInt("nombreJours");

                /* On récupère le point de départ */
                JSONObject depart = aCreer.getJSONObject("depart");

                /* on récupère les coordonnées du point de départ */
                JSONArray coordonneeDepart = depart.getJSONArray("coordonnees");
                double latitudeDepart = coordonneeDepart.getDouble(0);
                double longitudeDepart = coordonneeDepart.getDouble(1);

                GeoPoint pointDepart = new GeoPoint(latitudeDepart,
                                                    longitudeDepart);

                /* On récupère le point de départ */
                JSONObject arrivee = aCreer.getJSONObject("arrive");

                /* on récupère les coordonnées du point de départ */
                JSONArray coordonneeArrivee = arrivee.getJSONArray("coordonnees");
                double latitudeArrivee = coordonneeArrivee.getDouble(0);
                double longitudeArrivee = coordonneeArrivee.getDouble(1);

                GeoPoint pointArrivee = new GeoPoint(latitudeArrivee,
                                                     longitudeArrivee);

                /* On crée la randonnée et on l'ajoute à la liste */
                randonnee = new Randonnee(idRando, libelleRando, nombreJours,
                        pointDepart, pointArrivee);

                Date date = new Date();

                /* On récupère la liste des parcours */
                JSONArray listeParcours = aCreer.getJSONArray("parcours");

                for(int j=0; j<listeParcours.length(); j++) {

                    /* On récupère le parcours */
                    JSONObject parcoursJSONObject = listeParcours.getJSONObject(j);

                    /* On récupère le libellé du parcours */
                    String libelle = parcoursJSONObject
                                     .getString("libelleRandonnee");

                    /* On crée le parcours */
                    parcours = new Parcours(randonnee, date, libelle);

                    JSONArray listePointInteret;
                    listePointInteret = new JSONArray();

                    /* On vérifie qu'il y a des points d'intérêt */
                    if (!parcoursJSONObject.isNull("pointInterets")){
                        /* On récupère la liste des points d'intérêt */
                        listePointInteret = parcoursJSONObject
                                                .getJSONArray("pointInterets");
                    }

                    /* S'il y a des points d'intérêts, on les ajoutes */
                    if (listePointInteret.length() != 0) {
                        for(int k=0;k<listePointInteret.length();k++) {
                            JSONObject pointInteretJSON;
                            JSONArray coordonneePointInteret;

                            String nom;
                            double[] tabCoord;

                            PointInteret pointInteret;

                            /* Récupère les données du point d'intérêt */
                            pointInteretJSON = listePointInteret.getJSONObject(k);
                            nom = pointInteretJSON.getString("libelle");
                            coordonneePointInteret = pointInteretJSON
                                                     .getJSONArray("coordonnees");
                            tabCoord = new double[]
                                       {coordonneePointInteret.getDouble(0),
                                        coordonneePointInteret.getDouble(1)};

                            /* On crée le point d'itérêt */
                            pointInteret = new PointInteret(nom,tabCoord);

                            /* On l'ajoute à la randonnée */
                            parcours.ajouterPointInteret(pointInteret);
                        }
                    }

                    /* On ajoute la randonnée à la liste */
                    aRetourner.add(parcours);
                }
            }
        }

        // On retroune la liste des randonnées

        return aRetourner;
    }

    private ArrayList<Randonnee>
            construireRandonneesWithReponse(JSONArray reponse)
            throws JSONException {

        ArrayList<Randonnee> aRetourner;

        JSONObject aCreer;
        Randonnee randonnee;

        aRetourner = new ArrayList<>();
        // Récupération des énums
        for(int i=0; i<reponse.length(); i++) {
            aCreer = reponse.getJSONObject(i);

            /* On récupère les différents paramètres de la randonnée */
            int id = aCreer.getInt("id");
            String libelle = aCreer.getString("libelle");
            int nombreJours = aCreer.getInt("nombreJours");

            /* On récupère le point de départ */
            JSONObject depart = aCreer.getJSONObject("depart");

            /* on récupère les coordonnées du point de départ */
            JSONArray coordonneeDepart = depart.getJSONArray("coordonnees");
            double latitudeDepart = coordonneeDepart.getDouble(0);
            double longitudeDepart = coordonneeDepart.getDouble(1);

            GeoPoint pointDepart = new GeoPoint(latitudeDepart, longitudeDepart);

            /* On récupère le point de départ */
            JSONObject arrivee = aCreer.getJSONObject("arrive");

            /* on récupère les coordonnées du point de départ */
            JSONArray coordonneeArrivee = arrivee.getJSONArray("coordonnees");
            double latitudeArrivee = coordonneeArrivee.getDouble(0);
            double longitudeArrivee = coordonneeArrivee.getDouble(1);

            GeoPoint pointArrivee = new GeoPoint(latitudeArrivee, longitudeArrivee);

            /* On crée la randonnée et on l'ajoute à la liste */
            randonnee = new Randonnee(id, libelle, nombreJours,
                                      pointDepart, pointArrivee);
            aRetourner.add(randonnee);
        }

        // On retroune la liste des randonnées
        return aRetourner;
    }

    private ArrayList<Randonneur>
            construireRandonneursWithReponse(JSONArray reponse)
            throws JSONException {

        ArrayList<Randonneur> aRetourner;

        JSONObject aCreer;
        Randonneur randonneur;

        aRetourner = new ArrayList<>();
        // Récupération des énums
        for(int i=0; i<reponse.length(); i++) {
            aCreer = reponse.getJSONObject(i);

            String nom = aCreer.getString("nom");
            String prenom = aCreer.getString("prenom");

            int age = Integer.parseInt(aCreer.getString("age"));
            if (Integer.parseInt(aCreer.getString("age")) == 0) {
                age = 1;
            }

            NiveauEntrainement niveauEntrainement;
            try {
                niveauEntrainement = NiveauEntrainement.valueOf
                        (aCreer.getString("niveauEntrainement").toUpperCase());
            } catch (IllegalArgumentException _) {
                niveauEntrainement = NiveauEntrainement.DEBUTANT;
            }

            Morphologie morphologie;
            try {
                morphologie = Morphologie.valueOf
                        (aCreer.getString("morphologie").toUpperCase());
            } catch (IllegalArgumentException _) {
                morphologie = Morphologie.LEGER;
            }

            randonneur = new Randonneur(nom, prenom, age, niveauEntrainement,
                                        morphologie);
            aRetourner.add(randonneur);
        }

        // Création du randonneur
        return aRetourner;
    }

    private Utilisateur construireUtilisateurWithReponse(JSONObject reponse)
                        throws JSONException {

        // Récupération des énums

        NiveauEntrainement niveauEntrainement;
        try {
            niveauEntrainement = NiveauEntrainement.valueOf
                    (reponse.getString("niveauEntrainement").toUpperCase());
        } catch (IllegalArgumentException _) {
            niveauEntrainement = NiveauEntrainement.DEBUTANT;
        }

        Morphologie morphologie;
        try {
            morphologie = Morphologie.valueOf
                    (reponse.getString("morphologie").toUpperCase());
        } catch (IllegalArgumentException _) {
            morphologie = Morphologie.LEGER;
        }

        int age = Integer.parseInt(reponse.getString("age"));
        if (Integer.parseInt(reponse.getString("age")) == 0) {
            age = 1;
        }

        // Création de l'utilisateur
        return new Utilisateur(
                reponse.getString("patronyme").split(" ")[0],
                reponse.getString("patronyme").split(" ")[1],
                age, niveauEntrainement, morphologie,
                reponse.getString("mdp"),
                reponse.getString("adresseMail"),
                reponse.getString("domicile"));
    }

    private RequestQueue getFileRequete() {

        if (fileRequete == null) {
            fileRequete = Volley.newRequestQueue(contexteApplication);
        }

        return fileRequete;
    }
}
