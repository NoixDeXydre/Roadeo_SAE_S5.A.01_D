package org.iut.roadeo.modele.utilitaire.champ;

import static org.iut.roadeo.modele.utilitaire.champ.RecuperateurChamps.*;

import static org.junit.Assert.*;

import android.content.Context;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

/**
 * Classe de tests d'intégration de la classe RecuperateurChamps.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestRecuperateurChamps {

    @Test
    public void testGetIntFromChampRecupererValeurDefaut() {

        Context contexte = InstrumentationRegistry
                .getInstrumentation().getTargetContext();

        EditText champ = new EditText(contexte);

        // Cas où le champ est vide.
        assertEquals(getIntFromChamp(champ, 0), 0);
        assertEquals(getIntFromChamp(champ, 100), 100);
        assertEquals(getIntFromChamp(champ, -1), -1);

        // Cas où le champ contient une valeur invalide.
        champ.setText("Test 0");
        assertEquals(getIntFromChamp(champ, 0), 0);
        assertEquals(getIntFromChamp(champ, 100), 100);
        assertEquals(getIntFromChamp(champ, -1), -1);
    }

    @Test
    public void testGetIntFromChampRecupererValeurChamp() {

        Context contexte = InstrumentationRegistry
                .getInstrumentation().getTargetContext();

        EditText champ = new EditText(contexte);

        champ.setText("1");
        assertEquals(getIntFromChamp(champ, 0), 1);

        champ.setText("-1");
        assertEquals(getIntFromChamp(champ, 0), -1);

        champ.setText("100");
        assertEquals(getIntFromChamp(champ, 0), 100);
    }
}
