package org.iut.roadeo.controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.R;

/**
 * Affiche les différents paramètres d'un parcours.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurParamParcours extends AppCompatActivity {

    private EditText labelParcours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.param_parcours);

        labelParcours = findViewById(R.id.labelLibelleParcours);

        Intent intention = getIntent();

        String libelle;

        libelle = intention.getStringExtra("NOM");

        labelParcours.setText(libelle);
    }

    /**
     * Renvoi vers la carte avec le parcours associé
     * @param view non utilisé
     */
    public void voirCarteParcours(View view) {
        // TODO renvoyer vers la carte pour ajouter des points clés
        Intent intention = new Intent(ControleurParamParcours.this,
                ControleurCarte.class);
        startActivity(intention);
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
