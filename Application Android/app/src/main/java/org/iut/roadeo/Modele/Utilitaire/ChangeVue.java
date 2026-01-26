package org.iut.roadeo.Modele.Utilitaire;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import org.iut.roadeo.CacheApplication;
import org.iut.roadeo.Controleurs.ControleurCompteUtilisateur;
import org.iut.roadeo.Controleurs.ControleurListeParticipant;
import org.iut.roadeo.Controleurs.ControleurDashboard;
import org.iut.roadeo.Controleurs.ControleurPageConnexion;
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
     * @param activite activité actuellement affichee
     */
    public static void changeurVue(MenuItem item, Activity activite) {
        Intent intention;

        // on initialise l'intention
        intention = null;

        // en fonction de l'option choisie on lance l'activitée associée
        if (item.getItemId() == R.id.afficheMenu){
            intention = new Intent(activite, ControleurDashboard.class);
        } else if (item.getItemId() == R.id.afficheCompte){
            intention = new Intent(activite, ControleurCompteUtilisateur.class);
        } else if (item.getItemId() == R.id.afficheListeParticipants){
            intention = new Intent(activite, ControleurListeParticipant.class);
        } else if (item.getItemId() == R.id.seDeconnecter) {
            intention = new Intent(activite, ControleurPageConnexion.class);
            CacheApplication.detruireCache();
        }

        activite.startActivity(intention, null);
        if (item.getItemId() == R.id.seDeconnecter) {
            activite.finishAffinity(); // On supprime toutes les activités enfantes.
        }
    }
}
