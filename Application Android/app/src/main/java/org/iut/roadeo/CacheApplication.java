package org.iut.roadeo;

import org.iut.roadeo.Modele.APIRequeteur;

/**
 * Stocke des données globales après un appel API
 * permettant aux autres modules de faire des opérations en local.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class CacheApplication {

    // TODO mettre cette URL dans un fichier texte.
    // FIXME mettre l'URL du serveur distant
    /** URL pointant vers l'API du serveur */
    public final static String URL_API_PREFIXE = "http://10.0.2.2:8080/api/";

    /** Le module formulant des requêtes. */
    public static APIRequeteur apiRequeteur;

    // TODO singleton
    // On aurait juste à stocker l'utilisateur en théorie + la clé API.
}
