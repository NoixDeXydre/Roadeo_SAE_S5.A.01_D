package org.iut.roadeo.Controleurs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.Modele.Interfaces.IAPIRandonneursCallback;
import org.iut.roadeo.Modele.Randonneur;
import org.iut.roadeo.Modele.TypeDonnees.Morphologie;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;
import org.iut.roadeo.Modele.Utilisateur;
import org.iut.roadeo.Modele.Utilitaire.ChangeVue;
import org.iut.roadeo.R;

import java.util.ArrayList;

/**
 * Affiche les différents participants d'une randonnée.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurListeParticipant extends AppCompatActivity {

    /* Contient les différents participants */
    private ArrayList<Randonneur> participants;

    private ArrayAdapter<Randonneur> adaptateur;

    /* Liste les différents participants */
    private ListView listeParticipant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_participants);

        participants = new ArrayList<>();
        // on initialise les differents elements
        listeParticipant = findViewById(R.id.listeParticipant);

        // TODO adapter en fonction de la rando choisie
        ControleurPageConnexion.apiRequeteur.listerParticipant("1", new
                                                IAPIRandonneursCallback() {
            @Override
            public void onSuccess(ArrayList<Randonneur> randonneurs) {
                participants.addAll(randonneurs);
                adaptateur = new ArrayAdapter<>(getApplicationContext(),
                                                androidx.appcompat.R.layout.
                                                support_simple_spinner_dropdown_item,
                                                participants);
                listeParticipant.setAdapter(adaptateur);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(),
                          "Les randonneurs n'ont pas pu être affichés\n"+
                                message,
                               Toast.LENGTH_LONG).show();
            }
        });

        // on associe le menu contextuel
        registerForContextMenu(listeParticipant);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // on défini les items du menu click droit
        new MenuInflater(this).inflate(R.menu.menu_ajouter_liste, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo information =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // On regarde l'option choisie par l'utilisateur
        if(item.getItemId() == R.id.detail){
            // S'il a choisi les détails sur le participant, on ouvre
            // l'activité permettant de consulter les détails
            Intent intention = new Intent(this,
                                          ControleurAjoutRandonneur.class);
            intention.putExtra("NOM_RANDONNEUR",
                               participants.get(information.position).getNom());
            intention.putExtra("PRENOM_RANDONNEUR",
                               participants.get(information.position).getPrenom());
            intention.putExtra("AGE_RANDONNEUR",
                               participants.get(information.position).getAge());
            intention.putExtra("NIVEAU_RANDONNEUR",
                               participants.get(information.position)
                                           .getNiveauEntrainement().toString());
            intention.putExtra("MORPHOLOGIE_RANDONNEUR",
                               participants.get(information.position)
                                           .getMorphologie().toString());

            startActivity(intention);
            // TODO vue créer participant avec infos sur participant

            //lanceCreation.launch(intention);
        } else if (item.getItemId() == R.id.supprimer
                    && information.position != 0) {
            participants.remove(information.position);
            listeParticipant.setAdapter(adaptateur);
        } else {
            // l'utilisateur tente de supprimer le créateur de la randonnée
            Toast.makeText(this, "Vous ne pouvez pas supprimer le créateur de"
                                        + " la randonnée", Toast.LENGTH_LONG)
                            .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // On affiche le menu burger
        new MenuInflater(this).inflate(R.menu.menu_activite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // On envoi la vue choisie et le contexte à la méthode
        // permettant de changer de vue

        ChangeVue.changeurVue(item, ControleurListeParticipant.this);

        return super.onOptionsItemSelected(item);
    }

    /**
     * Envoi vers la vue permettant de créer un participant
     * @param view non utilisé
     */
    public void ajouterParticipant(View view) {
        Intent intention = new Intent(this, ControleurAjoutRandonneur.class);

        startActivity(intention);
    }
}
