package org.iut.roadeo.Controleurs;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.iut.roadeo.R;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

/**
 * Affiche la carte du parcours.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurCarte extends Fragment {

    // TODO manipulation du cache après appel API.
    // TODO appel API

    private IMapController controleurMapView;
    private MapView mapView;

    public static ControleurCarte newInstance() {
        return new ControleurCarte();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vue = inflater.inflate(R.layout.carte, container, false);

        Configuration.getInstance().load(
                vue.getContext(),
                PreferenceManager.getDefaultSharedPreferences(vue.getContext()));

        mapView = vue.findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        // La correction du tactile
        mapView.setMultiTouchControls(true); // Important pour le zoom avec deux doigts
        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
            }
        });

        controleurMapView = mapView.getController();
        controleurMapView.setZoom(18.0);

        // FIXME Juste pour les tests :3
        controleurMapView.setCenter(new GeoPoint(44.360054998826f,
                2.57556698405f));
        Marker a = creerPointInteret(new GeoPoint(44.362608f, 2.582049f));
        //supprimerPointInteret(a);

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
        mapView.getOverlays().add(pointInteret);

        return pointInteret;
    }

    /**
     * Supprime un point d'intérêt de la carte
     * @param pointInteret
     */
    private void supprimerPointInteret(Marker pointInteret) {
        pointInteret.remove(mapView);
    }
}
