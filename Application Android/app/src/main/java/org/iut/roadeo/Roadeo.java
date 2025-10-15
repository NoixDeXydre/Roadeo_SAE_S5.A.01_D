/*
 * 07 octobre 2025
 * IUT de Rodez, pas de droits réservés
 */

package org.iut.roadeo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * Point d'entrée de notre application.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class Roadeo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_connexion);
    }
}