package fr.iutrodez.roadeo.web.controleur;

import fr.iutrodez.roadeo.dao.ObjetInterfaceMongoDB;
import fr.iutrodez.roadeo.dao.ParcoursInterfaceMongoDB;
import fr.iutrodez.roadeo.dao.RandoneeInterfaceMongoDB;
import fr.iutrodez.roadeo.dao.UtilisateurInterfaceMongoDB;
import fr.iutrodez.roadeo.modele.Produits;
import fr.iutrodez.roadeo.modele.Parcours;
import fr.iutrodez.roadeo.modele.Participant;
import fr.iutrodez.roadeo.modele.SacADos;
import fr.iutrodez.roadeo.service.ObjetService;
import fr.iutrodez.roadeo.service.RandonneeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AjoutObjetSacControleur {

    private ObjetService objetService;
    private RandonneeService randonneeService;

    private Parcours parcours;
    private ArrayList<Produits> objetSac = new ArrayList<>();
    private List<Produits> produitsCategorie;
    private SacADos sacReserve;
    private String categorieSelectionne;
    private Produits detailProduit;
    List<String> categorie;
    private double prixTotal;
    private double calorieTotal;
    private double caloriesAttentues;
    private double poidsMax;

    /**
     * Crée le controleur et initialise les variables avec les données de mongodb
     * @param serviceO
     * @param serviceR
     */
    public AjoutObjetSacControleur(ObjetService serviceO, RandonneeService serviceR) {
        // Récupération des objets en paramètre
        this.objetService = serviceO;
        this.randonneeService = serviceR;

        // Données initiales
        this.parcours = randonneeService.getParcoursByIdRando("2").get(0);
        this.sacReserve = new SacADos(parcours.poidsMax(), 0);
        this.caloriesAttentues = parcours.calculKcalTotal(1);
        this.poidsMax = parcours.poidsMax();
        this.prixTotal = 0;
        this.calorieTotal = 0;
        this.objetSac = new ArrayList<>();

        this.categorieSelectionne = "";
        this.detailProduit = null;

        this.objetSac = objetService.recupListeObjet();
        this.categorie = objetService.listeCategorie();
        categorieSelectionne = categorie.get(0);
        selectionCategorie();
        selectionProduit(produitsCategorie.get(0));

    }

    /**
     * Page appelé à chaque rafraichissement pour mettre les données de la page à jour
     * @param model
     * @return
     */
    @GetMapping("/ajoutObjet")
    public String afficherSac(Model model) {
        model.addAttribute("objetsSac", sacReserve.getContenu()); // Chargement des données du sac
        model.addAttribute("poidsTotal", sacReserve.poidsTotal()); // poids total du sac
        model.addAttribute("poidsMax", poidsMax); // poids max du sac

        model.addAttribute("caloriesTotal", calorieTotal); // total calories dans le sac
        model.addAttribute("caloriesRequise", caloriesAttentues); // calories

        model.addAttribute("prixTotal", prixTotal);

        model.addAttribute("typesObjets", categorie);


        //model.addAttribute("objet", new Produits());
        model.addAttribute("objetsListe", produitsCategorie);
        model.addAttribute("objetsDetail", detailProduit);
        return "ajoutObjet"; // ajoutObjet.html
    }

    public void supprimerObjetSac(Produits produits) {
        sacReserve.supprimeProduits(produits);
    }

    public void ajouteObjetSac(Produits produits) {
        sacReserve.addObjet(produits);
    }

    private void selectionCategorie() {
        produitsCategorie = new ArrayList<>();
        if (categorieSelectionne != "") {
            for (Produits prod : objetSac) {
                if(prod != null && prod.getCategorie().equals(categorieSelectionne)) {
                    produitsCategorie.add(prod);
                }
            }
        }
    }

    private void selectionProduit(Produits prod) {
        detailProduit = prod;
    }

    // à supprimer
    private void rafraichirDonnee(Model model) {
        objetSac = objetService.recupListeObjet();
        List<String> categorie = objetService.listeCategorie();

        model.addAttribute("objetsSac", sacReserve.getContenu());
        model.addAttribute("poidsTotal", sacReserve.poidsTotal());
        model.addAttribute("poidsMax", parcours.poidsMax());

        model.addAttribute("caloriesTotal", 0);
        model.addAttribute("caloriesRequise", parcours.calculKcalTotal(1));

        model.addAttribute("prixTotal", 0);

        model.addAttribute("typesObjets", categorie);
        categorieSelectionne = categorie.get(0); //STUB
        selectionCategorie();
        model.addAttribute("objet", new Produits());
        System.out.println(produitsCategorie);
        model.addAttribute("objetsListe", produitsCategorie);
        selectionProduit(produitsCategorie.get(1));
        model.addAttribute("objetsDetail", detailProduit);
        System.out.print(objetService.listeCategorie());
    }

    private void ajouterSac() {
        sacReserve.addObjet(detailProduit);
    }

    // AJOUTER UN OBJET
    // doit récupérer l'objet pour l'ajouter dans le sac réserve
    @PostMapping("/sac/ajouter")
    public String ajouterObjet(@RequestParam String idObjet) {
        // On cherche l'objet dans la liste globale par son ID
        Produits aAjouter = objetService.getObjetById(idObjet);
        if (aAjouter != null) {
            sacReserve.addObjet(aAjouter);
        }
        return "redirect:/ajoutObjet"; // Recharge la page pour voir les changements
    }

    // SUPPRIMER UN OBJET
    // prend l'objet en paramètre pour être supprimer du sac de reserve
    @PostMapping("/sac/supprimer")
    public String supprimerObjet(@RequestParam String idObjet) {
        // Logique pour trouver et supprimer l'objet du sac
        sacReserve.supprimeProduitsById(idObjet);
        return "redirect:/ajoutObjet";
    }

    // CHANGER DE CATÉGORIE
    // s'active dès que la catégorie sélectionné change et mes à jour la liste des objets (objetSac)
    @GetMapping("/sac/selectionnerType")
    public String selectionnerType(@RequestParam(required = false) String categorie) {
        if (categorie != null) this.categorieSelectionne = categorie;
        return "redirect:/ajoutObjet";
    }

    //Selon l'objet sélectionnée récupère l'objet selon l'id pour le mettre dans détailProduit
    @GetMapping("/sac/selectionnerObjet")
    public String selectionnerObjet(@RequestParam(required = false) String idProduit) {
        if (idProduit != null) this.detailProduit =
                objetSac.stream().filter(produits -> produits.getCategorie().equals(categorieSelectionne));
        return "redirect:/ajoutObjet";
    }

    // VALIDER LE SAC
    // Vérifie que les kcal total ne sont pas inférieur au kcal max et que le poids des objets ne dépasse pas plus de 10% du poids max
    @PostMapping("/sac/valider")
    public String validerSac() {
        // Logique de sauvegarde finale (ex: mettre à jour le parcours en base)
        System.out.println("Sac validé !");
        return "redirect:/accueil";
    }
}
