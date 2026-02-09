package org.iut.roadeo.modele.utilitaire.champ;

import static org.iut.roadeo.modele.utilitaire.champ.VerificateurChamps.*;

import static org.junit.Assert.*;

import android.content.Context;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

/**
 * Classe de tests d'intégration de la classe VerificateurChamps.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestVerificateurChamps {

    private final static String[][] DONNEES_IDENTIQUES = {
        {"mon_mdp", "mon_mdp"},
        {"   ", "   "},
        {" z ", " z "},
        {"", ""}
    };

    private final static String[][] DONNEES_NON_IDENTIQUES = {
            {"  ", "   "},
            {"", " "},
            {"mon_mdp1", "mon_mdp2"},
            {" z ", " a "}
    };

    @Test
    public void testIsChampsMdpIdentiquesEchec() {

        assertFalse(isChampsMdpIdentiques(null, null));

        Context contexte = InstrumentationRegistry
                .getInstrumentation().getTargetContext();

        EditText champ1 = new EditText(contexte);
        EditText champ2 = new EditText(contexte);

        for (String[] donneesNonIdentiques : DONNEES_NON_IDENTIQUES) {

            champ1.setText(donneesNonIdentiques[0]);
            champ2.setText(donneesNonIdentiques[1]);

            assertFalse(isChampsMdpIdentiques(champ1, champ2));
        }
    }

    @Test
    public void testIsChampsMdpIdentiquesSucces() {

        Context contexte = InstrumentationRegistry
                .getInstrumentation().getTargetContext();

        EditText champ1 = new EditText(contexte);
        EditText champ2 = new EditText(contexte);

        for (String[] donneesIdentiques : DONNEES_IDENTIQUES) {

            champ1.setText(donneesIdentiques[0]);
            champ2.setText(donneesIdentiques[1]);

            assertTrue(isChampsMdpIdentiques(champ1, champ2));
        }
    }
}
