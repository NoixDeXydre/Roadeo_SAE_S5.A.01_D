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

/**
 * Affiche la carte du parcours.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurCarte extends AppCompatActivity {

    private IMapController controleurMapView;
    private MapView mapview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.carte);

        Configuration.getInstance().load(
                getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        mapview = findViewById(R.id.mapview);
        mapview.setTileSource(TileSourceFactory.MAPNIK);

        controleurMapView = mapview.getController();
        controleurMapView.setZoom(18.0);
    }
}
