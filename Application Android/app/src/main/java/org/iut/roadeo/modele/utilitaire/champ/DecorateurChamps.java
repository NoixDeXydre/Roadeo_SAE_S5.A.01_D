package org.iut.roadeo.modele.utilitaire.champ;

import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import org.iut.roadeo.R;

/**
 * Décore les champs de différentes façons.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class DecorateurChamps {

    /**
     * Modifie le couleur de la barre en dessous du champ.
     * @param champ
     * @param couleurID
     */
    public static void setBarreCouleurChamp(EditText champ, int couleurID) {

        champ.getBackground().setColorFilter(
                ContextCompat.getColor(champ.getContext(), couleurID),
                PorterDuff.Mode.SRC_IN
        );
    }

    /**
     * Modifie le champ pour que la couleur de la barre soit noire
     * quand l'utilisateur écrit dedans.
     * Ca permet d'enlever une potentielle barre rouge, synonyme d'erreur.
     * @param champ
     * @return le champ modifié
     */
    public static EditText setChampListenerResetErreurOnEcriture(EditText champ) {

        champ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setBarreCouleurChamp(champ, R.color.black);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        return champ;
    }
}
