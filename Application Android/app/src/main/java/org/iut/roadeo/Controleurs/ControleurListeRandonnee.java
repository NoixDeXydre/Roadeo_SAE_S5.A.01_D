package org.iut.roadeo.Controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.iut.roadeo.Modele.Interfaces.IAPIRandonneesCallback;
import org.iut.roadeo.Modele.Randonnee;
import org.iut.roadeo.R;
import org.osmdroid.config.Configuration;

import java.util.ArrayList;

/**
 * Affiche les différentes randonnées d'un utilisateur
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurListeRandonnee extends Fragment implements View.OnClickListener {

    /* Contient les différentes randonnées */
    private ArrayList<Randonnee> randonnees;

    private ArrayAdapter<Randonnee> adaptateur;

    /* Liste les différentes randonnées */
    private ListView listeRandonnee;

    public static ControleurListeRandonnee newInstance() {
        return new ControleurListeRandonnee();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // On initialise les variables
        View vue = inflater.inflate(R.layout.liste_randonnees, container,
                         false);
        Configuration.getInstance().load(
                vue.getContext(),
                PreferenceManager.getDefaultSharedPreferences(vue.getContext()));
        randonnees = new ArrayList<>();
        listeRandonnee = vue.findViewById(R.id.listeRandonnee);
        vue.findViewById(R.id.ajoutRandonnee).setOnClickListener(this);

        /* On rempli la liste des randonnées */
        ControleurPageConnexion.apiRequeteur.listerRandonnee(new
                                        IAPIRandonneesCallback() {
        @Override
        public void onSuccess(ArrayList<Randonnee> randos) {
            randonnees.addAll(randos);
            adaptateur = new ArrayAdapter<>(getContext(),
                                            androidx.appcompat.R.layout.
                                            support_simple_spinner_dropdown_item,
                                            randonnees);
            listeRandonnee.setAdapter(adaptateur);
        }
        @Override
        public void onError(String message) {
            Toast.makeText(getContext(),
                "Les randonneurs n'ont pas pu être affichés\n"+
                        message,
                Toast.LENGTH_LONG).show();
        }
        });

        return vue;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ajoutRandonnee) {
            // TODO lien vers l'ajout d'une randonnée
        }
    }
}
