package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
public class ControleurMenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

       /*
        // FIXME
        // C'est temporaire, dans une version avancée on aura dashboard
        // avec les parcours et les randonnées.
        Intent intention = new Intent(this,
                ControleurCompteUtilisateur.class);
        startActivity(intention);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_activite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ChangeVue.changeurVue(item, ControleurMenuPrincipal.this);

        return super.onOptionsItemSelected(item);
    }
}
