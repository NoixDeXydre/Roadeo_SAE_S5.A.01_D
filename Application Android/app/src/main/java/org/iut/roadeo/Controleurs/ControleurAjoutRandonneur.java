package org.iut.roadeo.Controleurs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.Modele.Randonneur;
import org.iut.roadeo.R;

import java.util.ArrayList;

public class ControleurAjoutRandonneur extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_randonneur);

        // on récupère les informations
        Intent intention = getIntent();
        int positionRandonneur = intention.getIntExtra("POSITION", -1);

        // si >= 0 alors on modifie un randonneur existant
        if(positionRandonneur >= 0) {
            // TODO récupérer les informations du randonneur
        }
    }
}
