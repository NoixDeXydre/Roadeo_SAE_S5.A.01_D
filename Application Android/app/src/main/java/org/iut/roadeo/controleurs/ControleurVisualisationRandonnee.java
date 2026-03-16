package org.iut.roadeo.controleurs;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    private Button boutonChangerPoint;
    private Button boutonArriveeDepart;

    /** Carte sur laquelle interragir */
    private MapView mapView;

    private Marker pointDepart;
    private Marker pointArrivee;
    private Marker pointArriveeDepart;

    private boolean modeDepart;

    private boolean modeArriveeDepart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualisation_randonnee);

        mapView = findViewById(R.id.map_view_randonnee);
        boutonChangerPoint = findViewById(R.id.bouton_changer_mode);
        boutonArriveeDepart = findViewById(R.id.bouton_arrivee_depart);

        // On crée les paramètres de la carte
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

        // On crée le marqueur du point d'arrivée
        pointArriveeDepart = new Marker(mapView);
        pointArriveeDepart.setTitle("Départ et arrivée de la randonnée");
        pointArriveeDepart.setIcon(getDrawable(R.drawable.arrivee_depart_marqueur));
        pointArriveeDepart.setAnchor(0.25f, 0.25f);
        pointArriveeDepart.setInfoWindowAnchor(0.25f, Marker.ANCHOR_TOP);

        // si informations reçues, on met les points en place
        if (!Double.isNaN(coordDepart[0])) { // une seule vérif nécessaire
            if (coordDepart[0] != coordArrive[0]
                || coordDepart[1] != coordArrive[1]) {
                PointInteret depart = new PointInteret("depart", coordDepart);
                PointInteret arrive = new PointInteret("arrive", coordArrive);

                pointDepart.setPosition(depart.getCoordonnees());
                pointArrivee.setPosition(arrive.getCoordonnees());
                mapView.getOverlays().add(pointDepart);
                mapView.getOverlays().add(pointArrivee);
            } else {
                PointInteret departArrive = new PointInteret("depart arrive",
                                                             coordDepart);
                pointArriveeDepart.setPosition(departArrive.getCoordonnees());
                mapView.getOverlays().add(pointArriveeDepart);
            }
        }

        modeDepart = true;
        modeArriveeDepart = false;

        // On code ici le clic sur la carte.
        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                if (modeArriveeDepart) {
                    // On enlève le point précédent
                    mapView.getOverlays().remove(pointArriveeDepart);

                    // On enlève le point de depart
                    System.out.println(mapView.getOverlays().remove(pointDepart));
                    System.out.println(mapView.getOverlays().remove(pointArrivee));

                    // On ajoute le point d'arrivé départ
                    pointArriveeDepart.setPosition(p);
                    mapView.getOverlays().add(pointArriveeDepart);
                } else if (modeDepart) {
                    // On enlève le point précédent
                    mapView.getOverlays().remove(pointDepart);

                    // On enlève le point d'arrivee et depart commun
                    mapView.getOverlays().remove(pointArriveeDepart);

                    // On ajoute le point de départ
                    pointDepart.setPosition(p);
                    mapView.getOverlays().add(pointDepart);
                } else {
                    // On enlève le point précédent
                    mapView.getOverlays().remove(pointArrivee);

                    // On enlève le point d'arrivee et depart commun
                    mapView.getOverlays().remove(pointArriveeDepart);

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
    public void clickChangementMode(View view) {
        modeDepart = !modeDepart;
        modeArriveeDepart = false;
        if (modeDepart) {
            boutonChangerPoint.setText(R.string.arrivee);
        } else {
            boutonChangerPoint.setText(R.string.depart);
        }
        boutonArriveeDepart.setText(R.string.arrivee_depart);
        boutonArriveeDepart.setEnabled(true);
    }

    /**
     * Quand le bouton est cliqué, on peut placer le point commun de départ et arrivé
     * @param view inutilisé
     */
    public void clickArriveeDepart(View view) {
        modeArriveeDepart = true;
        boutonArriveeDepart.setText(R.string.arrivee_depart_actif);
        boutonArriveeDepart.setEnabled(false);
    }
}
