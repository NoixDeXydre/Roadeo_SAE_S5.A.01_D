package org.iut.roadeo.Modele;

import android.content.Context;
import android.widget.Toast;

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

import org.iut.roadeo.Modele.Interfaces.IAPIRandonneursCallback;
import org.iut.roadeo.Modele.Interfaces.IAPIUtilisateurCallback;
import org.iut.roadeo.Modele.TypeDonnees.Morphologie;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

        getFileRequete().add(requeteConnexion);
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

        getFileRequete().add(requeteConnexion);
    }

    public void listerParticipant(String id, IAPIRandonneursCallback callback) {
        String urlAppelAPI = prefixeUrl +  SUFFIXE_API_LISTE_PARTICIPANT + id;
        System.out.print(urlAppelAPI);
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

        getFileRequete().add(requeteConnexion);
    }
/*
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

            String label = aCreer.getString("label");
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

            randonnee = new randonnee(label, prenom, age, niveauEntrainement, morphologie);
            aRetourner.add(randonnee);
        }

        // Création du randonneur
        return aRetourner;
    }
*/
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
