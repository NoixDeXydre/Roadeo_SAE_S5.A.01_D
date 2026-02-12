package org.iut.roadeo.controleurs;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.R;
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

    private IMapController controleurMapView;
    private MapView mapView;

    private Marker pointDepart;
    private Marker pointArrivee;

    private boolean modeDepart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualisation_randonnee);

        mapView = findViewById(R.id.map_view_randonnee);
        mapView.getController().setCenter(new GeoPoint(44.360054998826f,
                                                       2.57556698405f));
        mapView.getController().setZoom(18.0);

        pointDepart = new Marker(mapView);
        pointDepart.setTitle("Départ de la randonnée");
        pointDepart.setIcon(getDrawable(R.drawable.depart_marqueur));
        pointDepart.setAnchor(0.25f, 0.25f);
        pointDepart.setInfoWindowAnchor(0.25f, Marker.ANCHOR_TOP);

        pointArrivee = new Marker(mapView);
        pointArrivee.setTitle("Arrivée de la randonnée");
        pointArrivee.setIcon(getDrawable(R.drawable.arrive_marqueur));
        pointArrivee.setAnchor(0.25f, 0.25f);
        pointArrivee.setInfoWindowAnchor(0.25f, Marker.ANCHOR_TOP);

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
                    // on ajoute le point d'arrivée
                    pointArrivee.setPosition(p);
                    mapView.getOverlays().add(pointArrivee);
                }
                mapView.invalidate();
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
    }

    public void clickModeDepart(View view) {
        modeDepart = true;
    }

    public void clickModeArrive(View view) {
        modeDepart = false;
    }
}
