package org.iut.roadeo.Controleurs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.iut.roadeo.CacheApplication;
import org.iut.roadeo.Modele.Parcours;
import org.iut.roadeo.Modele.Randonnee;
import org.iut.roadeo.Modele.Utilisateur;
import org.iut.roadeo.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Affiche la carte du parcours.
 *
 * Une partie du code vient de
 * https://github.com/osmdroid/osmdroid/wiki/How-to-use-the-osmdroid-library-(Java)
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurCarte extends Fragment {

    // TODO manipulation du cache après appel API.
    // TODO appel API


    // Le dernier chiffre et le celui affiché en dernier.
    private final int ORDRE_DESSIN_POINT_UTILISATEUR = 0;
    private final int ORDRE_DESSIN_POINT_INTERET = 1;

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;

    private IMapController controleurMapView;
    private MapView mapView;

    private FusedLocationProviderClient clientDeLocalisation;
    private Location derniereLocalisationUtilisateur;

    private TextView titreRandonnee;
    private TextView dateParcours;
    private Parcours parcoursAfficheUtilisateur;
    private Marker marqueurUtilisateur;

    private ArrayList<Marker> pointsInteretCarte;

    public static ControleurCarte newInstance() {
        return new ControleurCarte();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        pointsInteretCarte = new ArrayList<>();

        clientDeLocalisation = LocationServices.getFusedLocationProviderClient(getContext());

        // TODO Données tests à enlever ici
        Utilisateur ut = CacheApplication.getInstance().getUtilisateurConnecte();
        Parcours parcours = new Parcours(new Randonnee
                ("Ma randonnée", 3, null, null),
                new Date());
        ut.ajouterParcours(parcours);
        parcoursAfficheUtilisateur = parcours;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vue = inflater.inflate(R.layout.carte, container, false);

        titreRandonnee = vue.findViewById(R.id.titre_randonnee_formate);
        dateParcours = vue.findViewById(R.id.date_parcours);

        Configuration.getInstance().load(
                vue.getContext(),
                PreferenceManager.getDefaultSharedPreferences(vue.getContext()));

        mapView = vue.findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        // Permet de ne pas détruire le mapview quand
        // on change de page dans un fragment.
        // https://github.com/osmdroid/osmdroid/issues/1641
        mapView.setDestroyMode(false);

        // La correction du tactile
        mapView.setMultiTouchControls(true); // Important pour le zoom avec deux doigts

        // Corrige un problème où le fragment empêche le tactile de fonctionner.
        mapView.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    // Empêche le ScrollView parent d'intercepter le toucher
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    break;

                case MotionEvent.ACTION_UP:
                    // Rend le contrôle au ScrollView parent quand on relâche
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }

            // Renvoie false pour laisser la MapView gérer l'événement (zoom, pan)
            return false;
        });

        // On code ici le clic sur la carte.
        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                creerPointInteret(p);
                return true; // Retourne true pour dire que l'événement est géré
            }

            // À coder, éventuellement
            // Vous pouvez gérer le clic long ici si besoin (return false sinon)
            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };

        // Ajouter en index 0 pour qu'il soit "derrière" les autres marqueurs
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(mReceive);
        mapView.getOverlays().add(0, mapEventsOverlay);

        controleurMapView = mapView.getController();
        controleurMapView.setZoom(18.0);

        // FIXME Juste pour les tests :3
        controleurMapView.setCenter(new GeoPoint(44.360054998826f,
                2.57556698405f));
        parcoursAfficheUtilisateur.ajouterPointInteret(new GeoPoint(44.360054998826f,
                2.57556698405f));

        mettreAJourCarteParcours(parcoursAfficheUtilisateur);
        mettreAJourPositionUtilisateur();

        return vue;
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * Crée et affiche sur la carte le point d'intérêt.
     * @param position
     * @return le point d'intérêt
     */
    private Marker creerPointInteret(GeoPoint position) {

        Marker pointInteret = new Marker(mapView);
        pointInteret.setPosition(position);
        pointInteret.setTitle("Point d'intérêt Roadeo");
        pointInteret.setIcon(getResources().getDrawable(R.drawable.point_interet_marqueur));
        pointInteret.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);

        // Ecriture dans le cache
        parcoursAfficheUtilisateur.ajouterPointInteret(position);

        pointsInteretCarte.add(pointInteret);

        pointInteret.setOnMarkerClickListener((marker, mapView) -> {
            supprimerPointInteret(marker);
            return true;
        });

        mapView.getOverlays().add(ORDRE_DESSIN_POINT_INTERET, pointInteret);

        return pointInteret;
    }

    /**
     * Supprime un point d'intérêt de la carte
     * @param pointInteret
     */
    private void supprimerPointInteret(Marker pointInteret) {
        parcoursAfficheUtilisateur.supprimerPointInteret(pointInteret.getPosition());
        pointInteret.remove(mapView);
        pointsInteretCarte.remove(pointInteret);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }

        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {

        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }

        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    // Met à jour la position de l'utilisateur en temps réel.
    private void mettreAJourPositionUtilisateur() throws SecurityException {

        LocationRequest requeteLocalisation = new LocationRequest.Builder
                (10000).setMinUpdateIntervalMillis(5000).build();
        clientDeLocalisation.requestLocationUpdates(
                requeteLocalisation,
                new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult resultatLocalisation) {

                        derniereLocalisationUtilisateur = resultatLocalisation.getLastLocation();

                        if (marqueurUtilisateur != null)
                            marqueurUtilisateur.remove(mapView);

                        marqueurUtilisateur = new Marker(mapView);
                        marqueurUtilisateur.setPosition(new GeoPoint
                                (derniereLocalisationUtilisateur.getLatitude(),
                                 derniereLocalisationUtilisateur.getLongitude(),
                                 derniereLocalisationUtilisateur.getAltitude()));
                        marqueurUtilisateur.setIcon(getResources()
                                .getDrawable(R.drawable.utilisateur_marqueur));
                        marqueurUtilisateur.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);

                        mapView.getOverlays().add(ORDRE_DESSIN_POINT_UTILISATEUR,
                                marqueurUtilisateur);
                    }
                },
                Looper.getMainLooper());
    }

    // À appeler à chaque fois qu'on change de parcours.
    private void mettreAJourCarteParcours(Parcours parcoursALire) {

        // Le parcours devrait être référencé également par l'utilisateur.
        parcoursAfficheUtilisateur = parcoursALire;

        // Mise à jour des points d'intérêt.

        // On supprime d'abord les points d'intérêt de la carte.
        for (Marker pointInteretCarte : pointsInteretCarte) {
            pointInteretCarte.remove(mapView);
            pointsInteretCarte.remove(pointInteretCarte);
        }

        // Puis on met ceux du cache.
        for (GeoPoint positionPointInteret : parcoursAfficheUtilisateur.getPointsInteret()) {
            creerPointInteret(positionPointInteret);
        }

        // Mise à jour des informations de la page.

        Randonnee randonnee = parcoursAfficheUtilisateur.getRandonneeParcours();

        titreRandonnee.setText(randonnee.getLibelle());
        dateParcours.setText(getString(R.string.date_carte_parcours_formatage,
                parcoursAfficheUtilisateur.getDate().toLocaleString()));
    }
}
