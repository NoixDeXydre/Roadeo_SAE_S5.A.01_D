package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.BuildConfig;
import org.iut.roadeo.Modele.APIRequeteur;
import org.iut.roadeo.Modele.Interfaces.IAPIConnexionCallback;
import org.iut.roadeo.Modele.Utilisateur;
import org.iut.roadeo.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_connexion);

        champIdentifiant = findViewById(R.id.champLogin);
        champMotDePasse = findViewById(R.id.champMotDePasse);
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

        if (!isChampCorrect(champIdentifiant.getText().toString())) {
            // TODO afficher erreur identifiant
        } else if (!isChampCorrect(champMotDePasse.getText().toString())) {
            // TODO afficher erreur mdp
        } else {

            // TODO appels API

            // Test API
            apiRequeteur = new APIRequeteur(this.getApplicationContext(), BuildConfig.API_URL);
            apiRequeteur.seConnecter(champIdentifiant.getText().toString(), champMotDePasse.getText().toString(),
                    new IAPIConnexionCallback() {
                @Override
                public void onSuccess(Utilisateur utilisateur) {

                    // TODO enregistrer l'utilisateur dans le cache
                    System.out.println(utilisateur.getNom() + " " + utilisateur.getPrenom());
                    System.out.println("TODO écriture dans le cache de l'utilisateur.");

                    // Note :
                    // Après connexion, l'utilisateur pourra appuyer sur BACK
                    // pour revenir à cette page.

                    Intent intention = new Intent(ControleurPageConnexion.this,
                            ControleurMenuPrincipal.class);
                    startActivity(intention);
                }

                @Override
                public void onError(String message) {
                    Toast.makeText(ControleurPageConnexion.this,
                            message.subSequence(0, message.length()), Toast.LENGTH_LONG).show();
                }
            });
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

    private boolean isChampCorrect(String texteChamp) {
        return texteChamp != null && !texteChamp.isBlank();
    }
}