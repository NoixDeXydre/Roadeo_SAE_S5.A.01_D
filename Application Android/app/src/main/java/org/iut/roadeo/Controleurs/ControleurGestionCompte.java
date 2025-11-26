package org.iut.roadeo.Controleurs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.R;

/**
 * Gère la création / modification du compte.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurGestionCompte extends AppCompatActivity {

    //
    // TODO
    // Enum pour spécifier si c'est une création ou une modification.
    // Le contrôleur devrait fonctionner de deux manières :
    // - En mode modifs, les informations du compte doivent être communiqués par Intention.
    // - En mode création, rien à communiquer.
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_compte);
    }
}
