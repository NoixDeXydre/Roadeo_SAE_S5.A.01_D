package org.iut.roadeo.controleurs;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.CacheApplication;
import org.iut.roadeo.modele.Utilisateur;
import org.iut.roadeo.modele.utilitaire.ChangeVue;
import org.iut.roadeo.R;

import java.util.ArrayList;

/**
 * Affiche les informations du compte utilisateur
 * ainsi que son historique.
 *
 * Il y a également des commandes pour modifier ces informations.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurCompteUtilisateur extends AppCompatActivity {

    private ArrayList<String> niveauPhysique;

    private ArrayAdapter<String> adaptateur;

    private TextView titrePatronyme;
    private EditText nomUtilisateur;
    private EditText motDePasseUtilisateur;
    private EditText motDePasseUtilisateurConfirmation;
    private EditText emailUtilisateur;
    private EditText domicileUtilisateur;
    private EditText ageUtilisateur;

    private Spinner niveauUtilisateur;
    private Spinner morphologie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.compte_utilisateur);

        titrePatronyme = findViewById(R.id.patronyme_utilisateur_titre);

        /* Récupération des identifiants */
        nomUtilisateur = findViewById(R.id.nomUtilisateur);
        motDePasseUtilisateur = findViewById(R.id.motDePasseUtilisateur);
        motDePasseUtilisateurConfirmation = findViewById(R.id.champConfirmationMotDePasse);
        emailUtilisateur = findViewById(R.id.emailUtilisateur);
        domicileUtilisateur = findViewById(R.id.domicileUtilisateur);
        niveauUtilisateur = findViewById(R.id.niveauUtilisateur);
        morphologie = findViewById(R.id.spinnerMorphologie);
        ageUtilisateur = findViewById(R.id.champAge);

        // Remplissage spinner

        niveauUtilisateur.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.spinner_niveau_physique)));

        morphologie.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.spinner_morphologie)));

        CacheApplication cacheApplication = CacheApplication.getInstance();
        updateChampsUtilisateur(cacheApplication.getUtilisateurConnecte());

        // On rend les données non modifiables
        rendreChampsModifiables(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // On affiche le menu burger
        new MenuInflater(this).inflate(R.menu.menu_activite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /* On envoi la vue choisie et le contexte à la méthode
         * permettant de changer de vue
         */
        ChangeVue.changeurVue(item, ControleurCompteUtilisateur.this);

        return super.onOptionsItemSelected(item);
    }

    /**
     * @param isChampModifiable true = modifiable, sinon false.
     */
    private void rendreChampsModifiables(boolean isChampModifiable) {
        nomUtilisateur.setEnabled(isChampModifiable);
        motDePasseUtilisateur.setEnabled(isChampModifiable);
        motDePasseUtilisateurConfirmation.setEnabled(isChampModifiable);
        emailUtilisateur.setEnabled(isChampModifiable);
        domicileUtilisateur.setEnabled(isChampModifiable);
        ageUtilisateur.setEnabled(isChampModifiable);
        niveauUtilisateur.setEnabled(isChampModifiable);
        morphologie.setEnabled(isChampModifiable);
    }

    private void updateChampsUtilisateur(Utilisateur utilisateur) {

        if (utilisateur != null) {
            titrePatronyme.setText(utilisateur.getNom() + " " + utilisateur.getPrenom());
            nomUtilisateur.setText(titrePatronyme.getText());
            motDePasseUtilisateur.setText(utilisateur.getMotDePasse());
            motDePasseUtilisateurConfirmation.setText(utilisateur.getMotDePasse());
            emailUtilisateur.setText(utilisateur.getEmail());
            domicileUtilisateur.setText(utilisateur.getDomicile());
            ageUtilisateur.setText(utilisateur.getAge() + "");
            niveauUtilisateur.setSelection(utilisateur.getNiveauEntrainement().ordinal());
            morphologie.setSelection(utilisateur.getMorphologie().ordinal());
        }
    }
}
