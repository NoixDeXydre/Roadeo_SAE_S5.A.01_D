package org.iut.roadeo.controleurs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.modele.typedonnees.Morphologie;
import org.iut.roadeo.modele.typedonnees.NiveauEntrainement;
import org.iut.roadeo.R;

import java.util.ArrayList;

public class ControleurAjoutRandonneur extends AppCompatActivity {

    private ArrayList<String> niveauPhysique;
    private ArrayList<String> morphologie;

    private ArrayAdapter<String> adaptateur1;
    private ArrayAdapter<String> adaptateur2;

    private EditText champsNomRandonneur;
    private EditText champsPrenomRandonneur;
    private EditText champsAgeRandonneur;

    private Spinner champsNiveauRandonneur;
    private Spinner champsMorphologieRandonneur;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_randonneur);

        champsNiveauRandonneur = findViewById(R.id.niveauRandonneur);
        champsMorphologieRandonneur = findViewById(R.id.morphologieRandonneur);
        champsNomRandonneur = findViewById(R.id.nomRandonneur);
        champsPrenomRandonneur = findViewById(R.id.prenomRandonneur);
        champsAgeRandonneur = findViewById(R.id.ageRandonneur);

        // on récupère les informations
        Intent intention = getIntent();

        String morphologieRandonneur = intention.getStringExtra("MORPHOLOGIE_RANDONNEUR");
        String niveauRandonneur = intention.getStringExtra("NIVEAU_RANDONNEUR");
        String nomRandonneur = intention.getStringExtra("NOM_RANDONNEUR");
        String prenomRandonneur = intention.getStringExtra("PRENOM_RANDONNEUR");

        int ageRandonneur = intention.getIntExtra("AGE_RANDONNEUR", -1);

        /* Remplissage spinner */
        niveauPhysique = new ArrayList<>();

        morphologie = new ArrayList<>();

        // si >= 0 alors on modifie un randonneur existant
        if(intention.getExtras() != null) {
            champsNomRandonneur.setText(nomRandonneur);
            champsPrenomRandonneur.setText(prenomRandonneur);
            champsAgeRandonneur.setText(Integer.toString(ageRandonneur));

            niveauPhysique.add(niveauRandonneur.toLowerCase());
            if(niveauPhysique.get(0).equals(NiveauEntrainement.SPORTIF.name().toLowerCase())) {
                niveauPhysique.add(NiveauEntrainement.ENTRAINE.name().toLowerCase());
                niveauPhysique.add(NiveauEntrainement.DEBUTANT.name().toLowerCase());
            } else if(niveauPhysique.get(0).equals(NiveauEntrainement.ENTRAINE.name()
                                                                     .toLowerCase())) {
                niveauPhysique.add(NiveauEntrainement.SPORTIF.name().toLowerCase());
                niveauPhysique.add(NiveauEntrainement.DEBUTANT.name().toLowerCase());
            } else {
                niveauPhysique.add(NiveauEntrainement.SPORTIF.name().toLowerCase());
                niveauPhysique.add(NiveauEntrainement.ENTRAINE.name().toLowerCase());
            }
            adaptateur1 = new ArrayAdapter<>(this,
                    androidx.appcompat.R.layout.
                            support_simple_spinner_dropdown_item,
                    niveauPhysique);
            champsNiveauRandonneur.setAdapter(adaptateur1);

            morphologie.add(morphologieRandonneur.toLowerCase());
            if(morphologie.get(0).equals(Morphologie.FORT.name().toLowerCase())) {
                morphologie.add(Morphologie.MOYEN.name().toLowerCase());
                morphologie.add(Morphologie.LEGER.name().toLowerCase());
            } else if(morphologie.get(0).equals(Morphologie.MOYEN.name().toLowerCase())) {
                morphologie.add(Morphologie.FORT.name().toLowerCase());
                morphologie.add(Morphologie.LEGER.name().toLowerCase());
            } else {
                morphologie.add(Morphologie.FORT.name().toLowerCase());
                morphologie.add(Morphologie.MOYEN.name().toLowerCase());
            }
            adaptateur2 = new ArrayAdapter<>(this,
                    androidx.appcompat.R.layout.
                            support_simple_spinner_dropdown_item,
                    morphologie);
            champsMorphologieRandonneur.setAdapter(adaptateur2);
        } else {
            niveauPhysique.add(NiveauEntrainement.DEBUTANT.name()
                    .toLowerCase());
            niveauPhysique.add(NiveauEntrainement.ENTRAINE.name()
                    .toLowerCase());
            niveauPhysique.add(NiveauEntrainement.SPORTIF.name()
                    .toLowerCase());
            adaptateur1 = new ArrayAdapter<>(this,
                    androidx.appcompat.R.layout.
                            support_simple_spinner_dropdown_item,
                    niveauPhysique);
            champsNiveauRandonneur.setAdapter(adaptateur1);

            morphologie.add(Morphologie.LEGER.name().toLowerCase());
            morphologie.add(Morphologie.MOYEN.name().toLowerCase());
            morphologie.add(Morphologie.FORT.name().toLowerCase());
            adaptateur2 = new ArrayAdapter<>(this,
                    androidx.appcompat.R.layout.
                            support_simple_spinner_dropdown_item,
                    morphologie);
            champsMorphologieRandonneur.setAdapter(adaptateur2);
        }
    }

    /**
     * Termine l'activité et renvoi vers la liste
     * @param view non utilisé
     */
    public void confirmerRandonneur(View view) {
        // TODO transmettre les données
        finish();
    }
}
