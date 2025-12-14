package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.CacheApplication;
import org.iut.roadeo.Modele.Utilisateur;
import org.iut.roadeo.R;

import static org.iut.roadeo.Modele.Utilitaire.Champ.DecorateurChamps.*;
import static org.iut.roadeo.Modele.Utilitaire.Champ.RecuperateurChamps.*;
import static org.iut.roadeo.Modele.Utilitaire.Champ.VerificateurChamps.*;

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

        champNom = setChampListenerResetErreurOnEcriture(findViewById(R.id.champNom));
        champPrenom = setChampListenerResetErreurOnEcriture(findViewById(R.id.champPrenom));
        champAdresseMail = setChampListenerResetErreurOnEcriture(findViewById(R.id.champLogin));
        champDomicile = setChampListenerResetErreurOnEcriture(findViewById(R.id.champAdresse));
        champMdp = setChampListenerResetErreurOnEcriture(findViewById(R.id.champMotDePasse));
        champConfirmationMdp = setChampListenerResetErreurOnEcriture
                (findViewById(R.id.champConfirmationMotDePasse));
        champAge = setChampListenerResetErreurOnEcriture(findViewById(R.id.champAge));

        // Avec cet écouteur, on peut bloquer
        // la confirmation du mot de passe si aucun mdp n'a été inséré.
        champMdp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // TODO mettre une constante pour les valeurs alpha
                if (charSequence.length() != 0) {
                    champConfirmationMdp.setEnabled(true);
                    champConfirmationMdp.setAlpha(1.0f);
                } else {
                    champConfirmationMdp.setEnabled(false);
                    champConfirmationMdp.setAlpha(0.3f);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

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

        if (!isChampNonVide(champNom, false)) {
            setBarreCouleurChamp(champNom, R.color.red);

            Toast.makeText(ControleurCreationCompte.this,
                    R.string.message_erreur_nom,
                    Toast.LENGTH_SHORT).show();

        } else if (!isChampNonVide(champPrenom, false)) {
            setBarreCouleurChamp(champNom, R.color.black);
            setBarreCouleurChamp(champPrenom, R.color.red);

            Toast.makeText(ControleurCreationCompte.this,
                    R.string.message_erreur_prenom,
                    Toast.LENGTH_SHORT).show();

        } else if (!isTexteNonVideBlank(champAdresseMail.getText().toString()
                .toLowerCase().trim())) {
            setBarreCouleurChamp(champPrenom, R.color.black);
            setBarreCouleurChamp(champAdresseMail, R.color.red);

            Toast.makeText(ControleurCreationCompte.this,
                    R.string.message_erreur_mail,
                    Toast.LENGTH_SHORT).show();

        } else if (!isChampNonVide(champDomicile, false)) {
            setBarreCouleurChamp(champAdresseMail, R.color.black);
            setBarreCouleurChamp(champDomicile, R.color.red);

            Toast.makeText(ControleurCreationCompte.this,
                    R.string.message_erreur_adresse,
                    Toast.LENGTH_SHORT).show();

        } else if (!isChampNonVide(champMdp, true)) {
            setBarreCouleurChamp(champDomicile, R.color.black);
            setBarreCouleurChamp(champMdp, R.color.red);

            Toast.makeText(ControleurCreationCompte.this,
                    R.string.message_erreur_mdp,
                    Toast.LENGTH_SHORT).show();

        } else if (!isChampNonVide(champConfirmationMdp, true)) {
            setBarreCouleurChamp(champMdp, R.color.black);
            setBarreCouleurChamp(champConfirmationMdp, R.color.red);

            Toast.makeText(ControleurCreationCompte.this,
                    R.string.message_erreur_confirmation_mdp,
                    Toast.LENGTH_SHORT).show();

        } else if (!isChampsMdpIdentiques(champMdp, champConfirmationMdp)) {
            setBarreCouleurChamp(champConfirmationMdp, R.color.red);

            Toast.makeText(ControleurCreationCompte.this,
                    R.string.message_erreur_mdp_conf_echec,
                    Toast.LENGTH_SHORT).show();

        } else if (!isChampNonVide(champAge, false)
                || getIntFromChamp(champAge, 0) < AGE_MINIMUM
                || getIntFromChamp(champAge, 0) > AGE_MAXIMUM) {
            setBarreCouleurChamp(champConfirmationMdp, R.color.black);
            setBarreCouleurChamp(champAge, R.color.red);

            Toast.makeText(ControleurCreationCompte.this,
                    R.string.message_erreur_age,
                    Toast.LENGTH_LONG).show();

        } else {

            Utilisateur utilisateur = new Utilisateur
                    (champNom.getText().toString(), champPrenom.getText().toString(),
                    getIntFromChamp(champAge, 0),
                    getNiveauEntrainementWithPosition(spinnerNiveauPhysique.getSelectedItemPosition()),
                    getMorphologieWithPosition(spinnerNiveauPhysique.getSelectedItemPosition()),
                    champMdp.getText().toString(), champAdresseMail.getText().toString(),
                    champDomicile.getText().toString());

            CacheApplication.getInstance().setUtilisateurConnecte(utilisateur);

            Toast.makeText(ControleurCreationCompte.this,
                    getString(R.string.message_succes_creation_compte, utilisateur.getNom()),
                    Toast.LENGTH_LONG).show();

            // TODO appel API

            Intent intention = new Intent(this, ControleurDashboard.class);
            startActivity(intention);

            // Comme ça, le bouton retour ne mènera plus à cette activité.
            finish();
        }
    }
}
