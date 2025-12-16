package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.R;

import static org.iut.roadeo.Modele.Utilitaire.Champ.DecorateurChamps.*;
import static org.iut.roadeo.Modele.Utilitaire.Champ.VerificateurChamps.*;

/**
 * Point d'entrée de l'application.
 *
 * S'occupe de la page de connexion,
 * de la vérification des champs et de la liaison
 * entre la page principale et la création de compte.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurPageConnexion extends AppCompatActivity {
    private EditText champIdentifiant;
    private EditText champMotDePasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_connexion);

        champIdentifiant = setChampListenerResetErreurOnEcriture(findViewById(R.id.champLogin));
        champMotDePasse = setChampListenerResetErreurOnEcriture(findViewById(R.id.champMotDePasse));
    }

    /**
     * (Déclenché au clic du bouton se connecter.)
     *
     * Vérifie les champs utilisateur, fait un appel à la base de données,
     * puis mène l'utilisateur vers la page principale.
     * @param view
     */
    public void seConnecter(View view) {

        // Vérification des champs

        if (!isTexteNonVideBlank(champIdentifiant.getText().toString()
                .toLowerCase().trim())) {

            setBarreCouleurChamp(champIdentifiant, R.color.red);
            setBarreCouleurChamp(champMotDePasse, R.color.black);
            Toast.makeText(ControleurPageConnexion.this,
                    R.string.message_erreur_mail,
                    Toast.LENGTH_SHORT).show();

        } else if (!isChampNonVide(champMotDePasse, true)) {

            setBarreCouleurChamp(champIdentifiant, R.color.black);
            setBarreCouleurChamp(champMotDePasse, R.color.red);
            Toast.makeText(ControleurPageConnexion.this,
                    R.string.message_erreur_mdp,
                    Toast.LENGTH_SHORT).show();

        } else {

            setBarreCouleurChamp(champIdentifiant, R.color.black);

            // TODO appels API

            Intent intention = new Intent(ControleurPageConnexion.this,
                    ControleurDashboard.class);
            startActivity(intention);

            finish();
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
}