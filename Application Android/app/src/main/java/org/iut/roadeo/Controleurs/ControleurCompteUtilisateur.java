package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.Modele.Utilitaire.ChangeVue;
import org.iut.roadeo.R;

/**
 * Affiche les informations du compte utilisateur
 * ainsi que son historique.
 * Il y a également des commandes pour modifier ces informations.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurCompteUtilisateur extends AppCompatActivity {

    private EditText nomUtilisateur;

    private EditText motDePasseUtilisateur;

    private EditText emailUtilisateur;

    private EditText domicileUtilisateur;

    private Spinner niveauUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compte_utilisateur);

        /* Récupération des identifiants */
        nomUtilisateur = findViewById(R.id.nomUtilisateur);
        motDePasseUtilisateur = findViewById(R.id.motDePasseUtilisateur);
        emailUtilisateur = findViewById(R.id.emailUtilisateur);
        domicileUtilisateur = findViewById(R.id.domicileUtilisateur);
        niveauUtilisateur = findViewById(R.id.niveauUtilisateur);

        /* Ecriture des données du compte */
        nomUtilisateur.setText(R.string.description_nom);
        motDePasseUtilisateur.setText(R.string.description_motdepasse);
        emailUtilisateur.setText(R.string.description_motdepasse);
        domicileUtilisateur.setText(R.string.description_domicile);

        /* On rend les données non modifiables */
        nomUtilisateur.setEnabled(false);
        motDePasseUtilisateur.setEnabled(false);
        emailUtilisateur.setEnabled(false);
        domicileUtilisateur.setEnabled(false);
        niveauUtilisateur.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_activite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ChangeVue.changeurVue(item, ControleurCompteUtilisateur.this);

        return super.onOptionsItemSelected(item);
    }
}
