package org.iut.roadeo.modele.utilitaire;

import android.view.View;

/**
 * Classe utilitaire pour les gérer les vues visuellement.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ViewUtils {

    /**
     * Active la vue visuellement.
     * @param view
     */
    public static void activerVisuellementView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * Désactive la vue visuellement.
     * @param view
     */
    public static void desactiverVisuellementView(View view) {
        view.setVisibility(View.GONE);
    }
}
