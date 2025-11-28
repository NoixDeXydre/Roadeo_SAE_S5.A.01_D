package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compte_utilisateur);
    }
}
