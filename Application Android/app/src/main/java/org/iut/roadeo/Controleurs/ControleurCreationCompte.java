package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.R;

/**
 * Gère la création du compte.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurCreationCompte extends AppCompatActivity {

    // TODO faire classe
    // TODO remplir les spinners.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_compte);
    }

    /**
     * Confirme les données,
     * ferme l'activité et mène vers le menu principal.
     * @param view
     */
    public void confirmerCreationCompte(View view) {

        // TODO vérification données
        // TODO appel API

        Intent intention = new Intent(this, ControleurDashboard.class);
        startActivity(intention);

        // Comme ça, le bouton retour ne mènera plus à cette activité.
        finish();
    }
}
