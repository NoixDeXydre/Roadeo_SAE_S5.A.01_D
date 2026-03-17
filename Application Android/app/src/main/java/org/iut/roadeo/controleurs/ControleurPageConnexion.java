package org.iut.roadeo.controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.BuildConfig;
import org.iut.roadeo.CacheApplication;
import org.iut.roadeo.modele.APIRequeteur;
import org.iut.roadeo.modele.Randonnee;
import org.iut.roadeo.modele.interfaces.IAPIRandonneesCallback;
import org.iut.roadeo.modele.interfaces.IAPIUtilisateurCallback;
import org.iut.roadeo.modele.Utilisateur;
import org.iut.roadeo.R;

import static org.iut.roadeo.modele.utilitaire.champ.DecorateurChamps.*;
import static org.iut.roadeo.modele.utilitaire.champ.VerificateurChamps.*;
import static org.iut.roadeo.modele.utilitaire.ViewUtils.*;

import java.util.ArrayList;

/**
 * Point d'entrée de l'application.
 *
 * S'occupe de la page de connexion,
 * de la vérification des champs et de la liaison
 * entre la page principale et la création de compte.
 *
 * Vous retrouverez en plus dans ce contrôleur
 * une instance de APIRequeteur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurPageConnexion extends AppCompatActivity {

    /**
     * Instance globale permettant de faire des requêtes.
     */
    public static APIRequeteur apiRequeteur;

    private EditText champIdentifiant;
    private EditText champMotDePasse;

    private View ecranChargement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_connexion);

        champIdentifiant = setChampListenerResetErreurOnEcriture(findViewById(R.id.champLogin));
        champMotDePasse = setChampListenerResetErreurOnEcriture(findViewById(R.id.champMotDePasse));

        ecranChargement = findViewById(R.id.ecran_chargement);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        apiRequeteur.annulerRequetes();
    }

    /**
     * (Déclenché au clic du bouton se connecter.)
     *
     * Vérifie les champs utilisateur, fait un appel à la base de données,
     * puis mène l'utilisateur vers la page principale.
     * @param view
     */
    public void seConnecter(View view) {

        // On remet à zéro le requêteur
        apiRequeteur = null;

        // Vérification des champs

        if (!isTexteNonVideBlank(champIdentifiant.getText().toString()
                .toLowerCase().trim())) {

            setBarreCouleurChamp(champIdentifiant, R.color.red);
            setBarreCouleurChamp(champMotDePasse, R.color.black);
            Toast.makeText(ControleurPageConnexion.this,
                    R.string.message_erreur_mail_vide,
                    Toast.LENGTH_SHORT).show();

        } else if (!isChampNonVide(champMotDePasse, true)) {

            setBarreCouleurChamp(champIdentifiant, R.color.black);
            setBarreCouleurChamp(champMotDePasse, R.color.red);
            Toast.makeText(ControleurPageConnexion.this,
                    R.string.message_erreur_mdp,
                    Toast.LENGTH_SHORT).show();

        } else {

            seConnecter();

            setBarreCouleurChamp(champIdentifiant, R.color.black);
            champMotDePasse.setText("");
        }
    }

    /**
     * Mène l'utilisateur vers la page de connexion d'un compte.
     * @param view
     */
    public void creerCompte(View view) {

        Intent intention = new Intent(ControleurPageConnexion.this,
                ControleurCreationCompte.class);
        startActivity(intention);
    }

    /**
     * Tente une connexion de l'utilisateur.
     */
    public void seConnecter() {

        activerVisuellementView(ecranChargement);

        apiRequeteur = new APIRequeteur(this.getApplicationContext(), BuildConfig.API_URL);
        apiRequeteur.seConnecter(champIdentifiant.getText().toString(),
                champMotDePasse.getText().toString(),
                new IAPIUtilisateurCallback() {
                    @Override
                    public void onSuccess(Utilisateur utilisateur) {

                        // Enregistre l'utilisateur dans le cache.
                        CacheApplication.getInstance().setUtilisateurConnecte(utilisateur);
                        recupererRandonneesUtilisateur();
                    }

                    @Override
                    public void onError(String message) {

                        desactiverVisuellementView(ecranChargement);
                        Toast.makeText(ControleurPageConnexion.this,
                                message.subSequence(0, message.length()), Toast.LENGTH_LONG).show();
                    }
                });
    }

    /**
     * Récupère les randonnées et les met
     * dans le cache une fois l'utilisateur connecté.
     */
    public void recupererRandonneesUtilisateur() {

        apiRequeteur = new APIRequeteur(this.getApplicationContext(), BuildConfig.API_URL);
        apiRequeteur.listerRandonnees(CacheApplication.getInstance().getUtilisateurConnecte().getId(),
                new IAPIRandonneesCallback() {

                    @Override
                    public void onSuccess(ArrayList<Randonnee> randonnees) {

                        for (Randonnee r : randonnees) {
                            CacheApplication.getInstance()
                                    .getUtilisateurConnecte().ajouterRandonnee(r);
                        }

                        envoyerUtilisateurDashboard();
                    }

                    @Override
                    public void onError(String message) {
                        desactiverVisuellementView(ecranChargement);
                        Toast.makeText(ControleurPageConnexion.this,
                                message.subSequence(0, message.length()), Toast.LENGTH_LONG).show();
                    }
                });
    }

    /**
     * Envoi les utilisateurs
     * dans le dashboard quand tout est terminé.
     */
    public void envoyerUtilisateurDashboard() {

        // Note :
        // Après connexion, l'utilisateur pourra appuyer sur BACK
        // pour revenir à cette page.

        desactiverVisuellementView(ecranChargement);
        Intent intention = new Intent(ControleurPageConnexion.this,
                ControleurDashboard.class);
        startActivity(intention);
    }
}