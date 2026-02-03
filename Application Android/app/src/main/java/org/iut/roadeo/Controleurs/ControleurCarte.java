package org.iut.roadeo.Controleurs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

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
import org.osmdroid.views.overlay.Polyline;

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

    private final int TEMPS_ATTENTE_RAFRAICHISSEMENT_POSITION = 1000;
    private final double DISTANCE_MAX_NOTIFICATION_POINT_INTERET = 200.0f;

    // Le dernier chiffre et le celui affiché en dernier.

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;

    private IMapController controleurMapView;
    private MapView mapView;

    private FusedLocationProviderClient clientDeLocalisation;
    private Location derniereLocalisationUtilisateur;

    private ImageView avertissementPointInteret;
    private TextView titreRandonnee;
    private TextView dateParcours;
    private Parcours parcoursAfficheUtilisateur;

    private Marker marqueurUtilisateur;
    private Marker marqueurDepart;
    private Marker marqueurArrive;

    private Marker dernierPointInteretNotification;

    private Polyline trajetRealise;

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
                (1, "Ma randonnée", 3,
                        new GeoPoint(44.360287526289454, 2.575853338825084),
                        new GeoPoint(44.35970734903577, 2.576335155660985)),
                new Date());
        ut.ajouterParcours(parcours);
        parcoursAfficheUtilisateur = parcours;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vue = inflater.inflate(R.layout.carte, container, false);

        avertissementPointInteret = vue.findViewById(R.id.avertissement_point_interet);
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

        requestPermissionsIfNecessary
                (new String[] {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                });
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

        mapView.getOverlays().add(pointInteret);
        mapView.invalidate();

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

        mapView.invalidate();
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

    // Point départ et point arrivée
    private void mettreAJourPointsExtremes() {

        if (marqueurDepart != null)
            marqueurDepart.remove(mapView);
        if (marqueurArrive != null)
            marqueurArrive.remove(mapView);

        marqueurDepart = new Marker(mapView);
        marqueurDepart.setPosition(parcoursAfficheUtilisateur.getRandonneeParcours()
                .getPointDepart());
        marqueurDepart.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        marqueurDepart.setIcon(getResources().getDrawable(R.drawable.depart_marqueur));
        marqueurDepart.setTitle(getString(R.string.infobulle_point_depart));

        marqueurArrive = new Marker(mapView);
        marqueurArrive.setPosition(parcoursAfficheUtilisateur.getRandonneeParcours()
                .getPointArrive());
        marqueurArrive.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        marqueurArrive.setIcon(getResources().getDrawable(R.drawable.arrive_marqueur));
        marqueurArrive.setTitle(getString(R.string.infobulle_point_arrive));

        mapView.getOverlays().add(marqueurDepart);
        mapView.getOverlays().add(marqueurArrive);
    }

    // Met à jour la position de l'utilisateur en temps réel.
    private void mettreAJourPositionUtilisateur() throws SecurityException {

        LocationRequest requeteLocalisation =
                new LocationRequest.Builder
                        (Priority.PRIORITY_HIGH_ACCURACY, TEMPS_ATTENTE_RAFRAICHISSEMENT_POSITION)
                        .setMinUpdateIntervalMillis(TEMPS_ATTENTE_RAFRAICHISSEMENT_POSITION)
                        .setWaitForAccurateLocation(false)
                        .build();

        clientDeLocalisation.requestLocationUpdates(
                requeteLocalisation,
                new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult resultatLocalisation) {

                        derniereLocalisationUtilisateur = resultatLocalisation.getLastLocation();
                        GeoPoint pointDerniereLocalisation = new GeoPoint
                                (derniereLocalisationUtilisateur.getLatitude(),
                                derniereLocalisationUtilisateur.getLongitude(),
                                derniereLocalisationUtilisateur.getAltitude());

                        trajetRealise.addPoint(pointDerniereLocalisation);
                        parcoursAfficheUtilisateur.ajouterPointTrajet(pointDerniereLocalisation);

                        if (marqueurUtilisateur != null)
                            marqueurUtilisateur.remove(mapView);

                        marqueurUtilisateur = new Marker(mapView);
                        marqueurUtilisateur.setPosition(pointDerniereLocalisation);
                        marqueurUtilisateur.setIcon(getResources()
                                .getDrawable(R.drawable.utilisateur_marqueur));
                        marqueurUtilisateur.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);

                        mapView.getOverlays().add(0, marqueurUtilisateur);
                        mapView.invalidate();

                        // Notification du point d'intérêt

                        int i = 0;
                        boolean isPointTrouve = false;
                        Marker pointTraite = null;
                        while (i < pointsInteretCarte.size() && !isPointTrouve) {
                            pointTraite = pointsInteretCarte.get(i);
                            isPointTrouve = getDistanceDeuxPoints(pointTraite
                                    .getPosition(), pointDerniereLocalisation)
                                    < DISTANCE_MAX_NOTIFICATION_POINT_INTERET;
                            i++;
                        }

                        if (isPointTrouve && !pointTraite.equals(dernierPointInteretNotification)) {

                            dernierPointInteretNotification = pointTraite;

                            Toast.makeText(getView().getContext(),
                                    getString(R.string.avertissement_point_interet),
                                    Toast.LENGTH_LONG).show();

                            avertissementPointInteret.setAlpha(1.0f);

                        } else if (isPointTrouve) {
                            // Corps vide
                        } else {
                            dernierPointInteretNotification = null;
                            avertissementPointInteret.setAlpha(0.0f);
                        }
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
        for (GeoPoint positionPointInteret : parcoursAfficheUtilisateur.getPointsInteret())
            creerPointInteret(positionPointInteret);

        // MAJ du trajet réalisé

        if (trajetRealise != null)
            mapView.getOverlays().remove(trajetRealise);

        trajetRealise = new Polyline();
        trajetRealise.setPoints(parcoursAfficheUtilisateur.getTrajetRealise());
        System.out.println(parcoursAfficheUtilisateur.getTrajetRealise().size());
        trajetRealise.setWidth(35f);
        trajetRealise.setColor(getResources().getColor(R.color.marron_boue));
        mapView.getOverlays().add(trajetRealise);

        // Départ et arrivé
        mettreAJourPointsExtremes();

        mapView.invalidate();

        // Mise à jour des informations de la page.

        Randonnee randonnee = parcoursAfficheUtilisateur.getRandonneeParcours();

        titreRandonnee.setText(randonnee.getLibelle());
        dateParcours.setText(getString(R.string.date_carte_parcours_formatage,
                parcoursAfficheUtilisateur.getDate().toLocaleString()));
    }

    // Calcule la distance entre deux points, en mètres.
    // https://www.movable-type.co.uk/scripts/latlong.html
    private double getDistanceDeuxPoints(GeoPoint p1, GeoPoint p2) {

        double R = 6371e3; // Rayon en metres

        double degres = Math.PI / 180;
        double φ1 = p1.getLatitude() * degres; // φ, λ in radians
        double φ2 = p2.getLatitude() * degres;
        double Δφ = (p2.getLatitude() - p1.getLatitude()) * degres;
        double Δλ = (p2.getLongitude() - p1.getLongitude()) * degres;

        double a = Math.sin(Δφ * 0.5) * Math.sin(Δφ * 0.5) +
                        Math.cos(φ1) * Math.cos(φ2) *
                                Math.sin(Δλ * 0.5) * Math.sin(Δλ * 0.5);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // in metres
    }
}
