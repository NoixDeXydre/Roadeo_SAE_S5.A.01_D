package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.Modele.Utilitaire.ChangeVue;
import org.iut.roadeo.R;

/**
 * Affiche les informations principales de l'application.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        // FIXME
        // on aura dashboard avec les parcours et les randonnées.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // On affiche le menu burger
        new MenuInflater(this).inflate(R.menu.menu_activite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* On envoi la vue choisie et le contexte à la méthode
         * permettant de changer de vue
         */
        ChangeVue.changeurVue(item, ControleurDashboard.this);

        return super.onOptionsItemSelected(item);
    }

    public void ouvrirCarte(View view) {
        startActivity(new Intent(ControleurDashboard.this,
                ControleurCarte.class));
    }
}
