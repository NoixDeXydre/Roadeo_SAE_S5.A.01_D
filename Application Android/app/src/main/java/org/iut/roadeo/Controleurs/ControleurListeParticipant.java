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

    /* contient les différents participants */
    private ArrayList<Randonneur> participants;

    private ArrayAdapter<Randonneur> adaptateur;

    private ListView listeParticipant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_participants);

        // on initialise les differents elements
        listeParticipant = findViewById(R.id.listeParticipant);

        // FIXME bouchon à remlacer
        Utilisateur randonneur1 = new Utilisateur("Marcenac", "Marcel", 20,
                                                  NiveauEntrainement.SPORTIF,
                                                  Morphologie.FORT,
                                                  "qqchose", "email@mail.com",
                                                  "ici");
        Randonneur randonneur2 = new Randonneur("Un", "Randonneur", 22,
                                                NiveauEntrainement.ENTRAINE,
                                                Morphologie.MOYEN);

        participants = new ArrayList<>();
        participants.add(randonneur1);
        participants.add(randonneur2);

        adaptateur = new ArrayAdapter<>(this,
                                        androidx.appcompat.R.layout.
                                        support_simple_spinner_dropdown_item, participants);
        listeParticipant.setAdapter(adaptateur);

        // on associe le menu contextuel
        registerForContextMenu(listeParticipant);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        new MenuInflater(this).inflate(R.menu.menu_ajouter_liste, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo information =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // On regarde l'option choisie par l'utilisateur
        if(item.getItemId() == R.id.detailParticipant){
            Intent intention = new Intent(this,
                                          ControleurAjoutRandonneur.class);

            intention.putExtra("NOM_RANDONNEUR",
                               participants.get(information.position).getNom());
            intention.putExtra("PRENOM_RANDONNEUR",
                               participants.get(information.position).getPrenom());
            intention.putExtra("AGE_RANDONNEUR",
                               participants.get(information.position).getAge());

            startActivity(intention);
            // TODO vue créer participant avec infos sur participant

            //lanceCreation.launch(intention);
        } else if (item.getItemId() == R.id.supprimerParticipant
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
