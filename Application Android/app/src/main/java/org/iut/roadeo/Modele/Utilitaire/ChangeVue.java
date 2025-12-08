package org.iut.roadeo.Modele.Utilitaire;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import org.iut.roadeo.Controleurs.ControleurCompteUtilisateur;
import org.iut.roadeo.Controleurs.ControleurMenuPrincipal;
import org.iut.roadeo.R;

public class ChangeVue {
    public static void changeurVue(MenuItem item, Context contexte) {
        Intent intention;

        intention = null;

        if(item.getItemId() == R.id.afficheMenu){
            intention = new Intent(contexte, ControleurMenuPrincipal.class);
        } else if(item.getItemId() == R.id.afficheCompte){
            intention = new Intent(contexte, ControleurCompteUtilisateur.class);
        }

        startActivity(contexte, intention, null);
    }
}
