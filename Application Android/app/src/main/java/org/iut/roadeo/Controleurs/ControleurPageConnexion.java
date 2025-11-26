package org.iut.roadeo.Controleurs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_connexion);
    }
}