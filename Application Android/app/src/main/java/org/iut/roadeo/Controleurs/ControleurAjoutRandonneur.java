package org.iut.roadeo.Controleurs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.Modele.Randonneur;
import org.iut.roadeo.Modele.TypeDonnees.Morphologie;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;
import org.iut.roadeo.R;

import java.util.ArrayList;

public class ControleurAjoutRandonneur extends AppCompatActivity {

    private ArrayList<String> niveauPhysique;
    private ArrayList<String> morphologie;

    private ArrayAdapter<String> adaptateur1;
    private ArrayAdapter<String> adaptateur2;

    private Spinner niveauRandonneur;
    private Spinner morphologieRandonneur;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_randonneur);

        niveauRandonneur = findViewById(R.id.niveauRandonneur);
        morphologieRandonneur = findViewById(R.id.morphologieRandonneur);

        // on récupère les informations
        Intent intention = getIntent();
        int positionRandonneur = intention.getIntExtra("POSITION", -1);

        // si >= 0 alors on modifie un randonneur existant
        if(positionRandonneur >= 0) {
            // TODO afficher les informations du randonneur
        } else {
            /* Remplissage spinner */
            niveauPhysique = new ArrayList<>();
            niveauPhysique.add(NiveauEntrainement.SPORTIF.toString()
                                                         .toLowerCase());
            niveauPhysique.add(NiveauEntrainement.ENTRAINE.toString()
                                                          .toLowerCase());
            niveauPhysique.add(NiveauEntrainement.DEBUTANT.toString()
                                                          .toLowerCase());
            adaptateur1 = new ArrayAdapter<>(this,
                    androidx.appcompat.R.layout.
                            support_simple_spinner_dropdown_item,
                    niveauPhysique);
            niveauRandonneur.setAdapter(adaptateur1);

            morphologie = new ArrayList<>();
            morphologie.add(Morphologie.LEGER.toString().toLowerCase());
            morphologie.add(Morphologie.MOYEN.toString().toLowerCase());
            morphologie.add(Morphologie.FORT.toString().toLowerCase());
            adaptateur2 = new ArrayAdapter<>(this,
                    androidx.appcompat.R.layout.
                            support_simple_spinner_dropdown_item,
                    morphologie);
            morphologieRandonneur.setAdapter(adaptateur2);
        }
    }
}
