package org.iut.roadeo.controleurs;

import android.content.Intent;
import android.os.Bundle;
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
    }
}
