package fr.iutrodez.roadeo.TestService;

import fr.iutrodez.roadeo.dao.ParcoursInterfaceMongoDB;
import fr.iutrodez.roadeo.dao.RandoneeInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.*;
import fr.iutrodez.roadeo.service.RandonneeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjetServiceTest {

    @Mock
    private RandoneeInterfaceMongoDB repository;

    @Mock
    private ParcoursInterfaceMongoDB parcoursRepository;

    private RandonneeService service;

    private ArrayList<Parcours> listeParcours;
    private ArrayList<Participant>  listeParticipants;
    private List<Randonnee> expectedList;
    private ArrayList<Produits> listesProduits;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ArrayList<PointInteret> interet = new ArrayList<>();
        listeParcours = new ArrayList<>();
        Parcours parcours1 = new Parcours("1", "2", "1", "Parcours champètre", interet);
        Parcours parcours2 = new Parcours("2", "2", "1", "Parcours foret", interet);
        Participant participant1 = new Participant("nom", "prenom", 17, "Debutant", "Leger");
        Participant participant2 = new Participant("nom2", "prenom2", 17, "Debutant", "Leger");

        // Initialisation des produits avec des données fictives
        Produits chips = new Produits("Chips", "Snack salé", "Nourriture", "Chips paprika", 2.5, 200, 1, 500);
        Produits Tente = new Produits("Tente", "Tente 2 places", "Équipement", "Tente légère", 150.0, 2500, 2, 0);
        Produits riz = new Produits("Riz", "Riz basmati", "Nourriture", "Riz 500g", 1.8, 500, 1, 1800);
        Produits Spaghetti = new Produits("Spaghetti", "Pâtes longues", "Nourriture", "Spaghetti 500g", 1.5, 500, 1, 1700);
        Produits bivoique = new Produits("Boisson", "Boisson isotonique", "Nourriture", "Bouteille 1L", 3.0, 1000, 0, 200);
        Produits gourdeEau = new Produits("Gourde", "Gourde en aluminium", "Équipement", "Gourde 1L", 15.0, 1000, 2, 0);
        Produits Chaussette = new Produits("Chaussettes", "Chaussettes techniques", "Vêtement", "Paire de chaussettes", 12.0, 100, 0, 0);
        Produits batonMarche = new Produits("Bâton", "Bâton de marche télescopique", "Équipement", "Paire de bâtons", 40.0, 500, 2, 0);
        Produits surplus = new Produits("Surplus", "Objet supplémentaire", "Divers", "Surplus lourd", 10.0, 3000, 0, 0); // Utilité à modifier selon les tests


        listeParticipants = new ArrayList<>();
        service = new RandonneeService(repository, parcoursRepository);
        listeParcours.addAll(Arrays.asList(parcours1, parcours2));
        interet.add(new PointInteret("prairie laitière", new double[]{15.0, 45.0}));
        interet.add(new PointInteret("prairie caverneuse", new double[]{20.5, 45.0}));
        listeParticipants.addAll(Arrays.asList(participant1, participant2));

        Randonnee randonnee1 = new Randonnee("1", "randonnée", 2,
                new PointInteret("départ", new double[]{12.0,45.0}),
                new PointInteret("arrivee", new double[]{54.12,95.3}),
                new ArrayList<>());

        Randonnee randonnee2 = new Randonnee("2", "randonnée", 1,
                new PointInteret("départ", new double[]{12.0,45.0}),
                new PointInteret("arrivee", new double[]{54.12,95.3}),
                listeParticipants);

        // Initialisation des sacs à dos pour les participants
        SacADos sac1 = new SacADos(participant1.poidsRando());
        SacADos sac2 = new SacADos(participant2.poidsRando());
        participant1.setSacADos(sac1);
        participant2.setSacADos(sac2);

        // Ajout des produits à la randonnée 1
        listesProduits = new ArrayList<>(Arrays.asList(chips, Tente, riz, Spaghetti, bivoique, gourdeEau, Chaussette, batonMarche));
        randonnee1.setProduits(listesProduits);
        listesProduits.add(surplus);
        randonnee2.setProduits(listesProduits);

        expectedList = Arrays.asList(randonnee1, randonnee2);
    }

    @Test
    void testDistribuerProduits_SurplusUtilite2_Exception() {
        // given

        // when
        // Appel de la méthode à tester (ex: service.distribuerProduits(randonnee1))
        // Assertions.assertThrows(IllegalArgumentException.class, () -> { ... });
    }

    @Test
    void testDistribuerProduits_SurplusUtilite1_Exception() {
        // given

        // when
        // Appel de la méthode à tester (ex: service.distribuerProduits(randonnee1))
        // Assertions.assertThrows(IllegalArgumentException.class, () -> { ... });
    }

    @Test
    void testDistribuerProduits_SurplusUtilite0_SansErreur() {
        // given randonnee 2

        // when
        // Appel de la méthode à tester (ex: service.distribuerProduits(randonnee1))
        // Assertions.assertDoesNotThrow(() -> { ... });
    }

    @Test
    void testDistribuerProduits_DistributionNormale() {
        // given
        // Les produits sont déjà dans listesProduits et randonnee1

        // when
        // Appel de la méthode à tester (ex: service.distribuerProduits(randonnee1))
        // Vérification que les sacs contiennent les produits attendus
    }


}
