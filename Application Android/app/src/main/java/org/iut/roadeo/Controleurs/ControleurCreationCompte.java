package org.iut.roadeo.Controleurs;

import android.os.Bundle;
import android.widget.EditText;

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

    private EditText nomUtilisateur;

    private EditText motDePasseUtilisateur;

    private EditText emailUtilisateur;

    private EditText domicileUtilisateur;

    private EditText niveauUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_compte);

        /** Récupération des identifiants */
        nomUtilisateur.findViewById(R.id.nomUtilisateur);
        motDePasseUtilisateur.findViewById(R.id.motDePasseUtilisateur);
        emailUtilisateur.findViewById(R.id.emailUtilisateur);
        domicileUtilisateur.findViewById(R.id.domicileUtilisateur);
        niveauUtilisateur.findViewById(R.id.niveauUtilisateur);

        /** Ecriture des données du compte */
        nomUtilisateur.setText("Marcel Marcenac");
        motDePasseUtilisateur.setText(R.string.description_motdepasse);
        emailUtilisateur.setText(R.string.description_motdepasse);
        domicileUtilisateur.setText(R.string.description_domicile);
        niveauUtilisateur.setText("moyen");
    }
}
