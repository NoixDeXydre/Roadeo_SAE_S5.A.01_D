package org.iut.roadeo.controleurs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.R;
import org.iut.roadeo.modele.PointInteret;

import java.util.ArrayList;

/**
 * Affiche les différents paramètres d'un parcours.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurParamParcours extends AppCompatActivity {
    private String libelle;

    private ArrayList<PointInteret> pointInterets;

    private double[] depart;
    private double[] arrivee;

    private EditText labelParcours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.param_parcours);

        labelParcours = findViewById(R.id.labelLibelleParcours);

        Intent intention = getIntent();

        libelle = intention.getStringExtra("NOM");

        depart = new double[2];
        arrivee = new double[2];

        depart[0] = intention.getDoubleExtra("DEPART_LATITUDE", 0.0);
        depart[1] = intention.getDoubleExtra("DEPART_LONGITUDE", 0.0);
        arrivee[0] = intention.getDoubleExtra("ARRIVEE_LATITUDE", 0.0);
        arrivee[1] = intention.getDoubleExtra("ARRIVEE_LONGITUDE", 0.0);

        labelParcours.setText(libelle);
    }

    /**
     * Renvoi vers la carte avec le parcours associé
     * @param view non utilisé
     */
    public void voirCarteParcours(View view) {
        if ((depart[0] == 0.0 && depart[1] == 0.0)
             || (arrivee[0] == 0.0 && arrivee[1] == 0.0)) {
            Toast.makeText(this, "Veuillez d'abord saisir un point de départ" +
                                        " et d'arrivée de la randonée",
                                        Toast.LENGTH_LONG).show();
        } else {
            Intent intention = new Intent(ControleurParamParcours.this,
                    ControleurCarte.class);
            intention.putExtra("DEPART", depart);
            intention.putExtra("ARRIVEE", arrivee);

            startActivity(intention);
        }
    }

    /**
     * Verifie si le parcours est ok puis termine l'activitée
     * @param view non utilisé
     */
    public void confirmerParcours(View view) {
        // TODO transmettre les données
        finish();
    }
}
