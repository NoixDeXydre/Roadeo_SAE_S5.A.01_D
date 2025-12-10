package org.iut.roadeo.Modele;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.iut.roadeo.Modele.Interfaces.IAPIConnexionCallback;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Gère les appels API.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class APIRequeteur {

    private final static String SUFFIXE_API_UTILISATEUR = "Utilisateur";
    private final static String SUFFIXE_API_SE_CONNECTER = SUFFIXE_API_UTILISATEUR
            + "/seConnecter";
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
     * Récupère la clé API, confirmant la connexion de l'utilisateur.
     * @param adresseMail
     * @param mdp
     * @param callback les actions à effectuer.
     */
    public void seConnecter(String adresseMail, String mdp,
                            IAPIConnexionCallback callback) {

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
                        callback.onSuccess("API good !");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {

                        // Erreur 401
                        if (error instanceof AuthFailureError) {
                            callback.onError("Utilisateur non autorisé");

                        } else {
                            callback.onError("Erreur de connexion à l'API");
                        }
                    }
                });

        getFileRequete().add(requeteConnexion);
    }

    private RequestQueue getFileRequete() {

        if (fileRequete == null) {
            fileRequete = Volley.newRequestQueue(contexteApplication);
        }

        return fileRequete;
    }
}
