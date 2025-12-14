package org.iut.roadeo.Modele.Utilitaire;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import org.iut.roadeo.Controleurs.ControleurCompteUtilisateur;
import org.iut.roadeo.Controleurs.ControleurListeParticipant;
import org.iut.roadeo.Controleurs.ControleurDashboard;
import org.iut.roadeo.R;

/**
 * Permet de passer d'une activitée a une autre
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ChangeVue {

    /**
     * remplace l'activitee actuelle par l'activitee selectionnee
     * par l'utilisateur à partir du menu burger
     * @param item activitee a afficher
     * @param contexte activitee actuellement affichee
     */
    public static void changeurVue(MenuItem item, Context contexte) {
        Intent intention;

        // on initialise l'intention
        intention = null;

        // en fonction de l'option choisie on lance l'activitée associée
        if(item.getItemId() == R.id.afficheMenu){
            intention = new Intent(contexte, ControleurDashboard.class);
        } else if(item.getItemId() == R.id.afficheCompte){
            intention = new Intent(contexte, ControleurCompteUtilisateur.class);
        } else if(item.getItemId() == R.id.afficheListeParticipants){
            intention = new Intent(contexte, ControleurListeParticipant.class);
        }

        startActivity(contexte, intention, null);
    }
}
