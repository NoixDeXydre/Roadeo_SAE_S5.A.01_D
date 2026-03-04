package org.iut.roadeo.controleurs;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private Button annulerChangements;
    private Button modifierCompte;
    private Button validerChangements;

    CacheApplication cacheApplication;

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

        // Récupération des boutons
        annulerChangements = findViewById(R.id.annuler_changements_compte);
        modifierCompte = findViewById(R.id.modifier_compte);
        validerChangements = findViewById(R.id.valider_changements_compte);

        cacheApplication = CacheApplication.getInstance();
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

    public void annulerChangements(View view) {
        rendreChampsModifiables(false);
        updateChampsUtilisateur(cacheApplication.getUtilisateurConnecte());
    }

    public void modifierCompte(View view) {
        rendreChampsModifiables(true);
    }

    public void validerChangements(View view) {
        // TODO API
        // TODO écrire dans le cache
        // TODO redonner la main
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

        // À partir d'ici, on change l'apparence
        // et le status des boutons selon l'état de modification.

        annulerChangements.setEnabled(isChampModifiable);
        validerChangements.setEnabled(isChampModifiable);

        modifierCompte.setEnabled(!isChampModifiable);

        if (isChampModifiable) {
            annulerChangements.setBackgroundColor(getResources().getColor(R.color.vert_pomme));
            validerChangements.setBackgroundColor(getResources().getColor(R.color.vert_pomme));
            modifierCompte.setBackgroundColor(getResources().getColor(R.color.ecran_chargement));
        } else {
            annulerChangements.setBackgroundColor(getResources().getColor(R.color.ecran_chargement));
            validerChangements.setBackgroundColor(getResources().getColor(R.color.ecran_chargement));
            modifierCompte.setBackgroundColor(getResources().getColor(R.color.vert_pomme));
        }
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
