package org.iut.roadeo.controleurs;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.iut.roadeo.modele.interfaces.IAPIRandonneesCallback;
import org.iut.roadeo.modele.Randonnee;
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

    /*  Listener pour communiquer entre les onglets */
    private EcouteurGeneration activiteQuiMEcoute;

    /**
     * Interface surveillant si une randonnée est selectionnée
     * et s'il y a des données à envoyer
     */
    public interface EcouteurGeneration {
        /* Envoi la randonnée choisie par l'utilisateur */
        void recevoirRandonnee(Randonnee randonnee);
    }

    @Override
    public void onAttach(Context contexte) {
        super.onAttach(contexte);
        // contexte est l'activité parente du fragment, donc l'activité principale
        activiteQuiMEcoute = (EcouteurGeneration) contexte;
    }

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
        registerForContextMenu(listeRandonnee);

        return vue;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ajoutRandonnee) {
            activiteQuiMEcoute.recevoirRandonnee(null);
            ControleurDashboard.tabLayout.getTabAt(1).select();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // on défini les items du menu click droit
        new MenuInflater(getContext()).inflate(R.menu.menu_ajouter_liste, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo information =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // On regarde l'option choisie par l'utilisateur
        if(item.getItemId() == R.id.detail){
            // Envoi des informations de la randonnée dans les paramêtres
            activiteQuiMEcoute.recevoirRandonnee(randonnees.get(information
                                                                .position));
            // On redirige vers l'onglet paramètres de la randonnée
            ControleurDashboard.tabLayout.getTabAt(1).select();
        } else if (item.getItemId() == R.id.supprimer) {
            randonnees.remove(information.position);
            listeRandonnee.setAdapter(adaptateur);
        }
        return super.onOptionsItemSelected(item);
    }
}