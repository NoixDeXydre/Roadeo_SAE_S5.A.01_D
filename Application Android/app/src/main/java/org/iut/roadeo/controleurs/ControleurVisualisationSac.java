package org.iut.roadeo.controleurs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.R;
import org.iut.roadeo.modele.Produit;
import org.iut.roadeo.modele.Randonneur;
import org.iut.roadeo.modele.SacADos;

import java.util.ArrayList;

/**
 * Génère et contrôle le menu visualisant
 * chaque sac pour un randonneur.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurVisualisationSac extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualisation_sacs);

        // Remplissage de la vue

        LinearLayout mainContainer = findViewById(R.id.container_liste_randonneurs);
        LayoutInflater inflater = LayoutInflater.from(this);

        // FIXME liste des randonneurs à alimenter ici.
        ArrayList<Randonneur> listeRandonneurs = new ArrayList<>();
        for (Randonneur randonneur : listeRandonneurs) {

            // 1. On gonfle la carte du randonneur
            View randoView = inflater.inflate(R.layout.item_randonneur_sac,
                    mainContainer, false);

            // 2. On récupère les vues de la carte
            TextView tvNom = randoView.findViewById(R.id.tv_nom_randonneur);
            TextView tvPoids = randoView.findViewById(R.id.tv_poids_sac);
            LinearLayout containerProduits = randoView.findViewById(R.id.container_produits);

            // Insertion des données
            tvNom.setText(randonneur.getPrenom() + " " + randonneur.getNom());

            SacADos sac = randonneur.getSacADos();

            String infoPoids = String.format("Poids : %.1f kg / %.1f kg",
                    sac.getPoidsTotal(), sac.getPoidsMax());
            tvPoids.setText(infoPoids);

            // On boucle sur le contenu du sac pour ajouter les produits
            for (Produit produit : sac.getContenu()) {

                TextView tvProduit = new TextView(this);

                // On affiche les infos du produit.
                tvProduit.setText("• " + produit.toString());
                tvProduit.setTextSize(16);
                tvProduit.setPadding(0, 4, 0, 4);

                containerProduits.addView(tvProduit);
            }

            // Ajout de la Carte finalisée dans la vue.
            mainContainer.addView(randoView);
        }
    }
}
