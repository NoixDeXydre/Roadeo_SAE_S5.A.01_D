package org.iut.roadeo.controleurs;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.R;
import org.iut.roadeo.modele.PointInteret;
import org.osmdroid.api.IMapController;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

/**
 * Permet de visualiser une randonnée et de placer son point de départ
 * et d'arrivée.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurVisualisationRandonnee extends AppCompatActivity {

    /** Carte sur laquelle interragir */
    private MapView mapView;

    private Marker pointDepart;
    private Marker pointArrivee;

    private boolean modeDepart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualisation_randonnee);

        // On crée les paramètres de la carte
        mapView = findViewById(R.id.map_view_randonnee);
        mapView.getController().setCenter(new GeoPoint(44.360054998826f,
                                                       2.57556698405f));
        mapView.getController().setZoom(18.0);

        // On récupère les données envoyées si modification d'une randonnée
        double[] coordDepart = new double[2];
        double[] coordArrive = new double[2];

        Intent intention = getIntent();

        coordDepart[0] = intention.getDoubleExtra("LATITUDE_DEPART", Double.NaN);
        coordDepart[1] = intention.getDoubleExtra("LONGITUDE_DEPART", Double.NaN);
        coordArrive[0] = intention.getDoubleExtra("LATITUDE_ARRIVE", Double.NaN);
        coordArrive[1] = intention.getDoubleExtra("LONGITUDE_ARRIVE", Double.NaN);

        // On crée le marqueur du point de départ
        pointDepart = new Marker(mapView);
        pointDepart.setTitle("Départ de la randonnée");
        pointDepart.setIcon(getDrawable(R.drawable.depart_marqueur));
        pointDepart.setAnchor(0.25f, 0.25f);
        pointDepart.setInfoWindowAnchor(0.25f, Marker.ANCHOR_TOP);

        // On crée le marqueur du point d'arrivée
        pointArrivee = new Marker(mapView);
        pointArrivee.setTitle("Arrivée de la randonnée");
        pointArrivee.setIcon(getDrawable(R.drawable.arrive_marqueur));
        pointArrivee.setAnchor(0.25f, 0.25f);
        pointArrivee.setInfoWindowAnchor(0.25f, Marker.ANCHOR_TOP);

        // si informations reçues, on met les points en place
        if (!Double.isNaN(coordDepart[0])) { // une seule vérif nécessaire
            PointInteret depart = new PointInteret("depart", coordDepart);
            PointInteret arrive = new PointInteret("arrive", coordArrive);

            pointDepart.setPosition(depart.getCoordonnees());
            pointArrivee.setPosition(arrive.getCoordonnees());
            mapView.getOverlays().add(pointDepart);
            mapView.getOverlays().add(pointArrivee);
        }

        modeDepart = true;

        // On code ici le clic sur la carte.
        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                if (modeDepart) {
                    // On ajoute le point de départ
                    pointDepart.setPosition(p);
                    mapView.getOverlays().add(pointDepart);
                } else {
                    // On ajoute le point d'arrivée
                    pointArrivee.setPosition(p);
                    mapView.getOverlays().add(pointArrivee);
                }
                mapView.invalidate();
                return true; // Retourne true pour dire que l'événement est géré
            }

            // Gestion du clic long inutilisé pour le moment
            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };

        // Ajouter en index 0 pour qu'il soit "derrière" les autres marqueurs
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(mReceive);
        mapView.getOverlays().add(0, mapEventsOverlay);
    }

    /**
     * Quand le bouton est cliqué, on peut placer le point de départ
     * @param view inutilisé
     */
    public void clickModeDepart(View view) {
        modeDepart = true;
    }

    /**
     * Quand le bouton est cliqué, on peut placer le point d'arrivé
     * @param view inutilisé
     */
    public void clickModeArrive(View view) {
        modeDepart = false;
    }
}
