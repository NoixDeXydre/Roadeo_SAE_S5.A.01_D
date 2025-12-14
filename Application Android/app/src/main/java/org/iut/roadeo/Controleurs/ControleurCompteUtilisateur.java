package org.iut.roadeo.Controleurs;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.CacheApplication;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;
import org.iut.roadeo.Modele.Utilisateur;
import org.iut.roadeo.Modele.Utilitaire.ChangeVue;
import org.iut.roadeo.R;

import java.util.ArrayList;

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

    private EditText nomUtilisateur;

    private EditText motDePasseUtilisateur;

    private EditText emailUtilisateur;

    private EditText domicileUtilisateur;

    private Spinner niveauUtilisateur;
    private Spinner morphologie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.compte_utilisateur);

        /* Récupération des identifiants */
        nomUtilisateur = findViewById(R.id.nomUtilisateur);
        motDePasseUtilisateur = findViewById(R.id.motDePasseUtilisateur);
        emailUtilisateur = findViewById(R.id.emailUtilisateur);
        domicileUtilisateur = findViewById(R.id.domicileUtilisateur);
        niveauUtilisateur = findViewById(R.id.niveauUtilisateur);
        morphologie = findViewById(R.id.spinnerMorphologie);

        // Remplissage spinner

        niveauUtilisateur.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.spinner_niveau_physique)));

        morphologie.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.spinner_morphologie)));

        CacheApplication cacheApplication = CacheApplication.getInstance();
        Utilisateur utilisateur = cacheApplication.getUtilisateurConnecte();

        /* Ecriture des données du compte */
        if (utilisateur != null) {
            nomUtilisateur.setText(utilisateur.getNom() + " " + utilisateur.getPrenom());
            motDePasseUtilisateur.setText(utilisateur.getMotDePasse());
            emailUtilisateur.setText(utilisateur.getEmail());
            domicileUtilisateur.setText(utilisateur.getDomicile());
            niveauUtilisateur.setSelection(utilisateur.getNiveauEntrainement().ordinal());
            morphologie.setSelection(utilisateur.getMorphologie().ordinal());
        }

        /* On rend les données non modifiables */
        nomUtilisateur.setEnabled(false);
        motDePasseUtilisateur.setEnabled(false);
        emailUtilisateur.setEnabled(false);
        domicileUtilisateur.setEnabled(false);
        niveauUtilisateur.setEnabled(false);
        morphologie.setEnabled(false);
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
}
