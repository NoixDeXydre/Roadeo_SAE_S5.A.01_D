package fr.iutrodez.roadeo.web.controleur;

import org.springframework.ui.Model;
import fr.iutrodez.roadeo.modele.SacADos;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RecapSacControleur {

    public RecapSacControleur() {}

    /**
     * Affiche la page récapitulative.
     * Les données "sacsDistribues" proviennent de la redirection du contrôleur précédent.
     */
    @GetMapping("/recapSac")
    public String afficherListeSacs(Model model, HttpSession session) {
        // On vérifie si les données sont dans le modèle (FlashAttributes)
        // ou déjà sauvegardées en session
        if (!model.containsAttribute("sacsDistribues")) {
            Object sacsEnSession = session.getAttribute("sacsDistribues");
            if (sacsEnSession == null) {
                // Si aucune donnée n'est trouvée, on redirige vers l'ajout pour éviter une page vide
                return "redirect:/ajoutObjet";
            }
            model.addAttribute("sacsDistribues", sacsEnSession);
        } else {
            // On sauvegarde en session pour permettre le rafraîchissement de la page
            session.setAttribute("sacsDistribues", model.getAttribute("sacsDistribues"));
        }

        return "recapSac";
    }

    /**
     * Action déclenchée par le bouton "Confirmer"
     */
    @PostMapping("/sac/validerAction")
    public String confirmerSac(@RequestParam String nomParticipant, HttpSession session) {
        // Ici, tu peux récupérer le sac spécifique pour faire une action en BDD
        Map<String, SacADos> sacs = (Map<String, SacADos>) session.getAttribute("sacsDistribues");
        SacADos sacValide = sacs.get(nomParticipant);

        System.out.println("Enregistrement du sac de " + nomParticipant + " dans la base de données...");

        // Logique métier : ex: utilisateurService.sauvegarderSac(nomParticipant, sacValide);

        return "redirect:/recapSac?success=" + nomParticipant;
    }

    /**
     * Action de retour (Bouton Modifier)
     */
    @GetMapping("/sac/retour")
    public String retourModification() {
        // Logique optionnelle avant de revenir
        return "redirect:/ajoutObjet";
    }
}
