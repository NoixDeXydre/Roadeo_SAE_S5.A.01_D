package org.iut.roadeo.controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import org.iut.roadeo.modele.Randonnee;
import org.iut.roadeo.R;
import org.osmdroid.config.Configuration;

/**
 * Affiche les différents paramètres d'une randonnée.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurParamRandonnee extends Fragment implements View.OnClickListener {

    private Randonnee randonnee;

    private EditText saisieLibelle;

    private EditText saisieJours;

    public static ControleurParamRandonnee newInstance() {
        return new ControleurParamRandonnee();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // On initialise les variables
        View vue = inflater.inflate(R.layout.param_randonnee, container,
                false);
        Configuration.getInstance().load(
                vue.getContext(),
                PreferenceManager.getDefaultSharedPreferences(vue.getContext()));

        saisieLibelle = vue.findViewById(R.id.labelLibelle);
        saisieJours = vue.findViewById(R.id.labelDureeJours);

        vue.findViewById(R.id.voirListeParcours).setOnClickListener(this);
        vue.findViewById(R.id.voirListeParticipants).setOnClickListener(this);
        vue.findViewById(R.id.confirmerRandonnee).setOnClickListener(this);

        randonnee = ((ControleurDashboard) getActivity()).getRandonneeCommunique();

        mettreAJourLabels(randonnee);

        return vue;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.voirListeParcours) {
            Intent intention = new Intent(getContext(),
                    ControleurListeParcours.class);
            if (randonnee != null) {
                intention.putExtra("RANDONNEE", randonnee.getId());
            }

            startActivity(intention);
        } else if (view.getId() == R.id.confirmerRandonnee) {
            // TODO appel API pour ajouter la randonnée
            ControleurDashboard.tabLayout.getTabAt(0).select();
        } else if (view.getId() == R.id.voirListeParticipants) {
            Intent intention = new Intent(getContext(),
                                          ControleurListeParticipant.class);
            if (randonnee != null) {
                intention.putExtra("RANDONNEE", randonnee.getId());
            }

            startActivity(intention);
        }
    }

    /**
     * Permet de mettre à jours les champs de la randonnée
     * @param randonneeAAfficher La randonnée selectionnée
     *                           null si on crée une nouvelle randonnée
     */
    public void mettreAJourLabels(Randonnee randonneeAAfficher) {
        randonnee = randonneeAAfficher;
        if (randonnee != null) {
            saisieLibelle.setText(randonnee.getLibelle());
            saisieJours.setText(Integer.toString(randonnee.getNbJours()));
        } else {
            saisieLibelle.setText(null);
            saisieJours.setText(null);
        }
    }
}