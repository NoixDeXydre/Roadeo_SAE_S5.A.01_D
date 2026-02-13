package org.iut.roadeo.controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.modele.interfaces.IAPIParcoursCallback;
import org.iut.roadeo.modele.Parcours;
import org.iut.roadeo.R;

import java.util.ArrayList;

public class ControleurListeParcours extends AppCompatActivity {

    /* Contient les différents participants */
    private ArrayList<Parcours> parcours;

    private ArrayAdapter<Parcours> adaptateur;

    /* Liste les différents participants */
    private ListView listeParcours;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_parcours);

        // on récupère les informations
        Intent intention = getIntent();
        int idRando = intention.getIntExtra("RANDONNEE", 0);

        parcours = new ArrayList<>();
        listeParcours = findViewById(R.id.listeParcours);

        if (idRando>0) {
            ControleurPageConnexion.apiRequeteur.listerParcours(idRando,
                    new IAPIParcoursCallback(){
                        @Override
                        public void onSuccess(ArrayList<Parcours>
                                                      parcoursListe) {
                            parcours.addAll(parcoursListe);
                            adaptateur = new ArrayAdapter<>(
                                    getApplicationContext(),
                                    androidx.appcompat.R.layout.
                                            support_simple_spinner_dropdown_item,
                                    parcours);
                            listeParcours.setAdapter(adaptateur);
                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(getApplicationContext(),
                                    "Les parcours n'ont pas pu" +
                                            " être affichés\n" +
                                            message,
                                    Toast.LENGTH_LONG).show();
                        }
            });
        }

        // on associe le menu contextuel
        registerForContextMenu(listeParcours);
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
            Parcours parcoursChoisi;
            Intent intention = new Intent(this, ControleurParamParcours.class);

            parcoursChoisi = parcours.get(information.position);
            intention.putExtra("NOM", parcoursChoisi.getLibelle());

            startActivity(intention);
        } else if (item.getItemId() == R.id.supprimer) {
            parcours.remove(information.position);
            listeParcours.setAdapter(adaptateur);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Envoi vers la vue permettant de créer un parcours
     * @param view non utilisé
     */
    public void ajouterParcours(View view) {
        Intent intention = new Intent(this, ControleurParamParcours.class);

        startActivity(intention);
    }
}
