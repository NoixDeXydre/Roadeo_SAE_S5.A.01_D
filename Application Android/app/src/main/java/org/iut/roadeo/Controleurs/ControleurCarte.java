package org.iut.roadeo.Controleurs;

import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

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
public class ControleurCarte extends AppCompatActivity {

    // TODO manipulation du cache après appel API.
    // TODO appel API

    private IMapController controleurMapView;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.carte);

        Configuration.getInstance().load(
                getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        mapView = findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        controleurMapView = mapView.getController();
        controleurMapView.setZoom(18.0);

        // FIXME Juste pour les tests :3
        controleurMapView.setCenter(new GeoPoint(44.360054998826f,
                2.57556698405f));
        Marker a = creerPointInteret(new GeoPoint(44.362608f, 2.582049f));
        //supprimerPointInteret(a);
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
