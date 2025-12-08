package org.iut.roadeo.Controleurs;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.iut.roadeo.Modele.Randonneur;
import org.iut.roadeo.Modele.TypeDonnees.Morphologie;
import org.iut.roadeo.Modele.TypeDonnees.NiveauEntrainement;
import org.iut.roadeo.Modele.Utilisateur;
import org.iut.roadeo.Modele.Utilitaire.ChangeVue;
import org.iut.roadeo.R;

import java.util.ArrayList;

/**
 * Affiche les différents participants d'une randonnée.
 *
 * @author BOYER Djedline
 * @author M'TIMA LESNIAK Noa
 * @author VIGUE Adrien
 */
public class ControleurListeParticipant extends AppCompatActivity {

    /* contient les différents participants */
    private ArrayList<Randonneur> participants;

    private ArrayAdapter<String> adaptateur;

    private ListView listeParticipant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_participants);

        // on initialise les differents elements
        listeParticipant = findViewById(R.id.listeParticipant);

        // FIXME bouchon à remlacer
        Utilisateur randonneur1 = new Utilisateur("Marcenac", "Marcel", 20,
                                                  NiveauEntrainement.SPORTIF,
                                                  Morphologie.FORT,
                                                  "qqchose", "email@mail.com",
                                                  "ici");
        Randonneur randonneur2 = new Randonneur("Un", "Randoneur", 22,
                                                NiveauEntrainement.ENTRAINE,
                                                Morphologie.MOYEN);

        participants = new ArrayList<>();
        participants.add(randonneur1);
        participants.add(randonneur2);

        adaptateur = new ArrayAdapter<>(this,
                                        androidx.appcompat.R.layout.
                                        support_simple_spinner_dropdown_item);
        adaptateur.add(participants.get(0).toString());
        adaptateur.add(participants.get(1).toString());
        listeParticipant.setAdapter(adaptateur);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // On affiche le menu burger
        new MenuInflater(this).inflate(R.menu.menu_activite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* On envoi la vue choisie et le contexte à la méthode
         * permettant de changer de vue
         */
        ChangeVue.changeurVue(item, ControleurListeParticipant.this);

        return super.onOptionsItemSelected(item);
    }
}
