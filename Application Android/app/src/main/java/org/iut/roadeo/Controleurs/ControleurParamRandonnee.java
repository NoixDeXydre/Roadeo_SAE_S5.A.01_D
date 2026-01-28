package org.iut.roadeo.Controleurs;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.iut.roadeo.R;
import org.osmdroid.config.Configuration;

public class ControleurParamRandonnee extends Fragment implements View.OnClickListener {

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

        return vue;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.voirListeParcours) {
            // TODO lien vers la liste des parcours
        } else if (view.getId() == R.id.confirmerRandonnee) {
            // TODO retour vers la liste des randonnées
        }
    }
}
