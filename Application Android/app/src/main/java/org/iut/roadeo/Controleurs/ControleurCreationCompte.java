package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

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

    private EditText champNom;
    private EditText champPrenom;
    private EditText champAdresseMail;
    private EditText champDomicile;
    private EditText champMdp;
    private EditText champConfirmationMdp;
    private EditText champAge;
    private Spinner spinnerNiveauPhysique;
    private Spinner spinnerMorphologie;

    // TODO remplir les spinners.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_compte);

        champNom = findViewById(R.id.champNom);
        champPrenom = findViewById(R.id.champPrenom);
        champAdresseMail = findViewById(R.id.champAdresse);
        champMdp = findViewById(R.id.champMotDePasse);
        champConfirmationMdp = findViewById(R.id.champConfirmationMotDePasse);
        champAge = findViewById(R.id.champAge);

        spinnerNiveauPhysique = findViewById(R.id.spinnerNiveauEntrainement);
        spinnerMorphologie = findViewById(R.id.spinnerMorphologie);
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
