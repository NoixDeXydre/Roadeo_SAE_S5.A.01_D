package org.iut.roadeo.controleurs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.iut.roadeo.modele.Randonnee;
import org.iut.roadeo.modele.utilitaire.AdaptateurFragmentsDashboard;
import org.iut.roadeo.modele.utilitaire.ChangeVue;
import org.iut.roadeo.R;

/**
 * Affiche les informations principales de l'application.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurDashboard extends AppCompatActivity
             implements ControleurListeRandonnee.EcouteurGeneration {

    // FIXME
    // on aura dashboard avec les parcours et les randonnées.

    /* La randonnée communiquée de la liste vers les paramêtres */
    private Randonnee randonnee;

    private ViewPager2 viewPager2;
    public static TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        viewPager2 = findViewById(R.id.dashboard_viewpager);
        tabLayout = findViewById(R.id.dashboard_tab_layout);
        viewPager2.setAdapter(new AdaptateurFragmentsDashboard(this)) ;

        String[] titreOnglet = getResources().getStringArray(R.array.onglets);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(titreOnglet[position])
        ).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // On affiche le menu burger
        new MenuInflater(this).inflate(R.menu.menu_activite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* On envoi la vue choisie et le contexte à la méthode
         * permettant de changer de vue
         */
        ChangeVue.changeurVue(item, ControleurDashboard.this);

        return super.onOptionsItemSelected(item);
    }

    public void ouvrirCarte(View view) {
        startActivity(new Intent(ControleurDashboard.this,
                ControleurCarte.class));
    }

    /**
     * Récupère la randonnée choisie par l'utilisateur dans la liste
     * et la renvoi aux paramètres
     * @param randonneeChoisie la randonnnée choisie dans la liste
     */
    @Override
    public void recevoirRandonnee(Randonnee randonneeChoisie) {
        randonnee = randonneeChoisie;

        /* Récupération d'un accès à ControleurParamRandonnee */
        ControleurParamRandonnee fragmentAModifier =
                (ControleurParamRandonnee) getSupportFragmentManager()
                                           .findFragmentByTag("f1");

        /* On vérifie que l'onglet à déjà été ouvert */
        if (fragmentAModifier != null) {
            fragmentAModifier.mettreAJourLabels(randonnee);
        }
    }

    /**
     * Renvoi la randonnée à afficher dans les paramètres
     * @return la randonnée choisie dans la liste
     */
    public Randonnee getRandonneeCommunique() {
        return randonnee;
    }
}
