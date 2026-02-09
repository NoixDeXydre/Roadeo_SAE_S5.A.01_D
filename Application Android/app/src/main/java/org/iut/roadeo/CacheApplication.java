package org.iut.roadeo;

import org.iut.roadeo.modele.Utilisateur;

/**
 * Stocke des données globales après un appel API
 * permettant aux autres modules de faire des opérations en local.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class CacheApplication {

    private static CacheApplication cacheApplication;

    private Utilisateur utilisateurConnecte;

    /** @return Le singleton CacheApplication */
    public static CacheApplication getInstance() {

        if (cacheApplication == null) {
            cacheApplication = new CacheApplication();
        }

        return cacheApplication;
    }

    /**
     * Supprime l'instance courante et les données avec.
     */
    public static void detruireCache() {
        cacheApplication = null;
    }

    /** @return l'utilisateur connecté */
    public Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    /**
     * Met le nouvel utilisateur dans le cache.
     * @param utilisateur
     */
    public void setUtilisateurConnecte(Utilisateur utilisateur) {
        utilisateurConnecte = utilisateur;
    }
}
