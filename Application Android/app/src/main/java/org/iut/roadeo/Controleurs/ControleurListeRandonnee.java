package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.iut.roadeo.R;
import org.osmdroid.config.Configuration;

/**
 * Affiche les différentes randonnées d'un utilisateur
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurListeRandonnee extends Fragment implements View.OnClickListener {

    public static ControleurListeRandonnee newInstance() {
        return new ControleurListeRandonnee();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vue = inflater.inflate(R.layout.liste_randonnees, container, false);

        Configuration.getInstance().load(
                vue.getContext(),
                PreferenceManager.getDefaultSharedPreferences(vue.getContext()));

        vue.findViewById(R.id.listeRandonnee);
        vue.findViewById(R.id.ajoutRandonnee).setOnClickListener(this);

        return vue;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ajoutRandonnee) {
            // TODO lien vers l'ajout d'une randonnée
        }
    }
}
