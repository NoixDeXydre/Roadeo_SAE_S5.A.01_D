package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.Modele.Utilisateur;
import org.iut.roadeo.R;

import static org.iut.roadeo.Modele.Utilitaire.VerificateurChamps.*;

/**
 * Gère la création du compte.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurCreationCompte extends AppCompatActivity {

    private final static int AGE_MINIMUM = 1;
    private final static int AGE_MAXIMUM = 120;

    private EditText champNom;
    private EditText champPrenom;
    private EditText champAdresseMail;
    private EditText champDomicile;
    private EditText champMdp;
    private EditText champConfirmationMdp;
    private EditText champAge;
    private Spinner spinnerNiveauPhysique;
    private Spinner spinnerMorphologie;
    private ArrayAdapter<String> adaptateurNiveauPhysique;
    private ArrayAdapter<String> adaptateurMorphologie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_compte);

        champNom = findViewById(R.id.champNom);
        champPrenom = findViewById(R.id.champPrenom);
        champAdresseMail = findViewById(R.id.champLogin);
        champDomicile = findViewById(R.id.champAdresse);
        champMdp = findViewById(R.id.champMotDePasse);
        champConfirmationMdp = findViewById(R.id.champConfirmationMotDePasse);
        champAge = findViewById(R.id.champAge);

        spinnerNiveauPhysique = findViewById(R.id.spinnerNiveauEntrainement);
        spinnerMorphologie = findViewById(R.id.spinnerMorphologie);

        // Remplir les spinners

        adaptateurNiveauPhysique = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.spinner_niveau_physique));

        adaptateurMorphologie = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.spinner_morphologie));

        spinnerNiveauPhysique.setAdapter(adaptateurNiveauPhysique);
        spinnerMorphologie.setAdapter(adaptateurMorphologie);
    }

    /**
     * Confirme les données,
     * ferme l'activité et mène vers le menu principal.
     * @param view
     */
    public void confirmerCreationCompte(View view) {

        if (!isChampNonVide(champNom)) {
            // TODO message explicite ou highlight
        } else if (!isChampNonVide(champPrenom)) {
            // TODO message explicite ou highlight
        } else if (!isTexteNonVide(champAdresseMail.getText().toString()
                .toLowerCase().trim())) {
            // TODO message explicite ou highlight
        } else if (!isChampNonVide(champDomicile)) {
            // TODO message explicite ou highlight
        } else if (!isChampNonVide(champMdp)) {
            // TODO message explicite ou highlight
        } else if (!isChampNonVide(champConfirmationMdp)) {
            // TODO message explicite ou highlight
        } else if (!isChampsMdpIdentiques(champMdp, champConfirmationMdp)) {
            // TODO message explicite ou highlight
        } else if (!isChampNonVide(champAge)
                || getIntFromChamp(champAge, 0) < AGE_MINIMUM
                || getIntFromChamp(champAge, 0) > AGE_MAXIMUM) {
            // TODO message explicite ou highlight
        } else {

            // TODO enregistrer cet utilisateur
            Utilisateur utilisateur = new Utilisateur
                    (champNom.getText().toString(), champPrenom.getText().toString(),
                    getIntFromChamp(champAge, 0),
                    getNiveauEntrainementWithPosition(spinnerNiveauPhysique.getSelectedItemPosition()),
                    getMorphologieWithPosition(spinnerNiveauPhysique.getSelectedItemPosition()),
                    champMdp.getText().toString(), champAdresseMail.getText().toString(),
                    champDomicile.getText().toString());

            // TODO appel API

            Intent intention = new Intent(this, ControleurDashboard.class);
            startActivity(intention);

            // Comme ça, le bouton retour ne mènera plus à cette activité.
            finish();
        }
    }
}
