package org.iut.roadeo.controleurs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.CacheApplication;
import org.iut.roadeo.R;
import org.iut.roadeo.modele.Produit;
import org.iut.roadeo.modele.Randonnee;
import org.iut.roadeo.modele.Randonneur;
import org.iut.roadeo.modele.SacADos;
import org.iut.roadeo.modele.typedonnees.Morphologie;
import org.iut.roadeo.modele.typedonnees.NiveauEntrainement;

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

        // On prend l'id de la randonnée
        // où les informations doivent être soutirées.
        int id = getIntent().getIntExtra("RANDONNEE", -1);

        Randonnee randonnee = null;
        for (Randonnee r : CacheApplication.getInstance().getUtilisateurConnecte().getRandonnees()) {
            if (r.getId() == id) {
                randonnee = r;
                break;
            }
        }

        if (randonnee == null)
            return;

        // FIXME liste des randonneurs à alimenter ici.
        ArrayList<Randonneur> listeRandonneurs = randonnee.getRandonneurs();
        for (Randonneur randonneur : listeRandonneurs) {

            // On gonfle la carte du randonneur
            View randoView = inflater.inflate(R.layout.item_randonneur_sac,
                    mainContainer, false);

            // On récupère les vues de la carte
            TextView tvNom = randoView.findViewById(R.id.tv_nom_randonneur);
            TextView tvPoids = randoView.findViewById(R.id.tv_poids_sac);
            LinearLayout containerProduits = randoView.findViewById(R.id.container_produits);

            // Insertion des données
            tvNom.setText(randonneur.getNom() + " " + randonneur.getPrenom());

            SacADos sac = randonneur.getSacADos();

            String infoPoids = String.format("Poids : %.1f kg / %.1f kg",
                    sac.getPoidsTotal(), sac.getPoidsMax());
            tvPoids.setText(infoPoids);

            // On boucle sur le contenu du sac pour ajouter les produits
            for (Produit produit : sac.getContenu()) {

                TextView tvProduit = new TextView(this);

                // On affiche les infos du produit.
                tvProduit.setText("• Nom : " + produit.getNom() + "\n Description : " + produit.getDescription()
                        + "\n Dénomination : " + produit.getDenomination()
                        + "\n Catégorie : " + produit.getCategorie() + "\n Poids : " + produit.getPoids()
                        + " g\n Prix : " + produit.getPrix() + " €\n Masse Energétique : " + produit.getNutrition() + " kcal");
                tvProduit.setTextSize(16);
                tvProduit.setPadding(0, 4, 0, 4);

                containerProduits.addView(tvProduit);
            }

            // Ajout de la Carte finalisée dans la vue.
            mainContainer.addView(randoView);
        }
    }
}
