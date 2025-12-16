package org.iut.roadeo;

import org.iut.roadeo.Modele.TypeDonnees.Morphologie;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;
import org.iut.roadeo.Modele.Utilisateur;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test de la classe CacheApplication.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class TestCacheApplication {

    @Test
    public void testCacheApplication() {
        assertNotNull(CacheApplication.getInstance());
    }

    @Test
    public void testSetUtilisateurConnecte() {

        CacheApplication cacheApplication = CacheApplication.getInstance();
        assertNull(cacheApplication.getUtilisateurConnecte());

        Utilisateur utilisateurAInsere = new Utilisateur("Marcel", "Marcenac",
                25, NiveauEntrainement.SPORTIF, Morphologie.MOYEN,
                "mdp", "B", "B");
        cacheApplication.setUtilisateurConnecte(utilisateurAInsere);

        assertEquals(utilisateurAInsere, cacheApplication.getUtilisateurConnecte());
    }
}