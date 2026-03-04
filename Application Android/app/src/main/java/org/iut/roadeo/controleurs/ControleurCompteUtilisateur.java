package org.iut.roadeo.controleurs;

import static org.iut.roadeo.modele.utilitaire.ViewUtils.*;
import static org.iut.roadeo.modele.utilitaire.champ.DecorateurChamps.setBarreCouleurChamp;
import static org.iut.roadeo.modele.utilitaire.champ.RecuperateurChamps.*;
import static org.iut.roadeo.modele.utilitaire.champ.VerificateurChamps.*;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.CacheApplication;
import org.iut.roadeo.modele.Utilisateur;
import org.iut.roadeo.modele.utilitaire.ChangeVue;
import org.iut.roadeo.R;

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

    // FIXME code dupliqué provenant de ControleurCreationCompte
    private final static int AGE_MINIMUM = 1;
    private final static int AGE_MAXIMUM = 120;

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

    private View ecranChargement;

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

        ecranChargement = findViewById(R.id.ecran_chargement);

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

        resetCouleursChamps();
    }

    public void modifierCompte(View view) {
        rendreChampsModifiables(true);
    }

    // C'est ici qu'on va vérifier
    // et écrire les changements dans le cache et dans l'API.
    public void validerChangements(View view) {

        // Le code provient de ControleurCreationCompte.
        //
        // Si l'on devait faire une future version,
        // il faudrait empêcher cette duplication de code.

        // Champ patronyme vide ?
        if (!isChampNonVide(nomUtilisateur, false)) {
            setBarreCouleurChamp(nomUtilisateur, R.color.red);

            Toast.makeText(ControleurCompteUtilisateur.this,
                    R.string.message_erreur_nom_prenom,
                    Toast.LENGTH_SHORT).show();

        // Champ mail vide ?
        } else if (!isTexteNonVideBlank(emailUtilisateur.getText().toString()
                .toLowerCase().trim())) {
            setBarreCouleurChamp(nomUtilisateur, R.color.black);
            setBarreCouleurChamp(emailUtilisateur, R.color.red);

            Toast.makeText(ControleurCompteUtilisateur.this,
                    R.string.message_erreur_mail_vide,
                    Toast.LENGTH_SHORT).show();

        // Champ mail valide ?
        } else if (!isEmailValide(emailUtilisateur.getText().toString()
                .toLowerCase().trim())) {
            setBarreCouleurChamp(emailUtilisateur, R.color.red);
            setBarreCouleurChamp(domicileUtilisateur, R.color.black);

            Toast.makeText(ControleurCompteUtilisateur.this,
                    R.string.message_erreur_mail_incorrecte,
                    Toast.LENGTH_SHORT).show();

        // Champ domicile vide ?
        } else if (!isChampNonVide(domicileUtilisateur, false)) {
            setBarreCouleurChamp(emailUtilisateur, R.color.black);
            setBarreCouleurChamp(domicileUtilisateur, R.color.red);

            Toast.makeText(ControleurCompteUtilisateur.this,
                    R.string.message_erreur_adresse,
                    Toast.LENGTH_SHORT).show();

        // Champ mdp vide ?
        } else if (!isChampNonVide(motDePasseUtilisateur, true)) {
            setBarreCouleurChamp(domicileUtilisateur, R.color.black);
            setBarreCouleurChamp(motDePasseUtilisateur, R.color.red);

            Toast.makeText(ControleurCompteUtilisateur.this,
                    R.string.message_erreur_mdp,
                    Toast.LENGTH_SHORT).show();

        } else if (!isChampNonVide(motDePasseUtilisateurConfirmation, true)) {
            setBarreCouleurChamp(motDePasseUtilisateur, R.color.black);
            setBarreCouleurChamp(motDePasseUtilisateurConfirmation, R.color.red);

            Toast.makeText(ControleurCompteUtilisateur.this,
                    R.string.message_erreur_confirmation_mdp,
                    Toast.LENGTH_SHORT).show();

        // Les deux mots de passe concordent ?
        } else if (!isChampsMdpIdentiques(motDePasseUtilisateur, motDePasseUtilisateurConfirmation)) {
            setBarreCouleurChamp(motDePasseUtilisateur, R.color.red);

            Toast.makeText(ControleurCompteUtilisateur.this,
                    R.string.message_erreur_mdp_conf_echec,
                    Toast.LENGTH_SHORT).show();

        // Champ de l'âge correct et dans la tranche ?
        } else if (!isChampNonVide(ageUtilisateur, false)
                || getIntFromChamp(ageUtilisateur, 0) < AGE_MINIMUM
                || getIntFromChamp(ageUtilisateur, 0) > AGE_MAXIMUM) {
            setBarreCouleurChamp(motDePasseUtilisateurConfirmation, R.color.black);
            setBarreCouleurChamp(ageUtilisateur, R.color.red);

            Toast.makeText(ControleurCompteUtilisateur.this,
                    R.string.message_erreur_age,
                    Toast.LENGTH_LONG).show();

        } else {

            activerVisuellementView(ecranChargement);

            String[] patronyme = nomUtilisateur.getText().toString().split(" ");
            Utilisateur utilisateurACreer = new Utilisateur
                    (cacheApplication.getUtilisateurConnecte().getId(),
                    patronyme[0], patronyme[1],
                    getIntFromChamp(ageUtilisateur, 1),
                    getNiveauEntrainementWithPosition(niveauUtilisateur.getSelectedItemPosition()),
                    getMorphologieWithPosition(morphologie.getSelectedItemPosition()),
                    motDePasseUtilisateur.getText().toString(),
                    emailUtilisateur.getText().toString(),
                    domicileUtilisateur.getText().toString());

            // TODO API
            // TODO écrire dans le cache
            // TODO redonner la main

            // desactiverVisuellementView(ecranChargement);
        }
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

    private void resetCouleursChamps() {
        setBarreCouleurChamp(nomUtilisateur, R.color.black);
        setBarreCouleurChamp(motDePasseUtilisateur, R.color.black);
        setBarreCouleurChamp(motDePasseUtilisateurConfirmation, R.color.black);
        setBarreCouleurChamp(emailUtilisateur, R.color.black);
        setBarreCouleurChamp(domicileUtilisateur, R.color.black);
        setBarreCouleurChamp(ageUtilisateur, R.color.black);
    }
}
