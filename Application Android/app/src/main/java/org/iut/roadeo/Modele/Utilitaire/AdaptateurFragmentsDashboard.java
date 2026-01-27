package org.iut.roadeo.Modele.Utilitaire;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.iut.roadeo.Controleurs.ControleurCarte;
import org.iut.roadeo.Controleurs.ControleurListeRandonnee;

/**
 * Gère la position des fragments du contrôleur Dashboard.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class AdaptateurFragmentsDashboard extends FragmentStateAdapter {

    public final static int NOMBRE_FRAGMENTS = 2;

    public AdaptateurFragmentsDashboard(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        
        switch (position) {
            case 0:
                return ControleurListeRandonnee.newInstance();
            case 1:
                return ControleurCarte.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NOMBRE_FRAGMENTS;
    }
}
