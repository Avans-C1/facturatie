package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.billing.Declaration;
import nl.avansc1.facturatie.repository.DeclarationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Robin on 11-10-16.
 */
@Controller
public class DeclarationController {

    private final Logger logger = LoggerFactory.getLogger(DeclarationController.class);
    private DeclarationRepository declarationRepository;

    @Autowired
    public DeclarationController(DeclarationRepository declarationRepository) {
        this.declarationRepository = declarationRepository;
        logger.debug("declaration controller");
    }

    @ModelAttribute("page")
    public String module() {
        return "declarations";
    }

    @GetMapping(value = "/declaration")
    String index(Model model) {
        logger.debug("index");

        return "declaration/index";
    }

    /**
     * Hiermee open je de create view om een nieuwe declaration aan te maken.
     *
     * @param declaration Dit object wordt aan de view meegegeven. Het object wordt gevuld met de waarden uit het formulier.
     * @param model
     * @return
     */
    @GetMapping(value = "/declaration/create")
    public String showCreateDeclarationForm(final Declaration declaration, final ModelMap model) {
        logger.debug("showCreateDeclarationForm");

        return "declaration/create";
    }

    /**
     * Deze methode handelt een ingevuld formulier af. Als er fouten zijn opgetreden blijven we in dezelfde view.
     * Als er geen fouten waren maken we een nieuwe declaration en gaan we direct naar de list view voor het overzicht.
     * De nieuwe declaration moet dan in het overzicht staan.
     *
     * @param declaration   De declaration uit het formulier. De velden van declaration komen uit de input velden van het formulier.
     * @param bindingResult Het resultaat van de view.
     * @param model
     * @return
     */
    @RequestMapping(value = "/declaration/create", method = RequestMethod.POST)
    public String validateAndSaveDeclaration(@Valid Declaration declaration, final BindingResult bindingResult, final ModelMap model) {
        logger.debug("validateAndSaveDeclaration - new declaration = " + declaration.getId());

        if (bindingResult.hasErrors()) {
            // Als er velden in het formulier zijn die niet correct waren ingevuld vinden we die hier.
            // We blijven dan op dezelfde pagina. De foutmeldingen worden daar getoond
            // (zie het create.html bestand.
            logger.debug("validateAndSaveDeclaration - bindingResult.hasErrors");
            return "declaration/create";
        }

        // Maak de declaration aan via de repository
        Declaration createdDeclaration = this.declarationRepository.create(declaration);
        // We gaan de lijst met declarations tonen, met een bericht dat de nieuwe declaration toegevoegd is.
        // Zet de opgevraagde declarations in het model
        model.addAttribute("declarations", declarationRepository.findAll());
        model.addAttribute("info", "Declaration is toegevoegd.");
        // Open de juiste view template als resultaat.
        return "declaration/index";
    }

    @DeleteMapping(value = "/declaration/{id}")
    public String deleteDeclaration(Model model, @PathVariable int id) {
        logger.debug("deleteDeclaration, id = " + id);

        // Delete de declaration aan via de repository
        this.declarationRepository.deleteDeclarationById(id);

        // We gaan de lijst met declarations tonen, met een bericht dat de nieuwe declaration toegevoegd is.
        // Zet de opgevraagde declarations in het model
        model.addAttribute("declarations", declarationRepository.findAll());
        model.addAttribute("info", "Declaration is verwijderd.");

        // Open de juiste view template als resultaat.
        return "declaration/index";
    }

    /**
     * Als de database een exception geeft vangen we die hier op. Kan bv. wanneer
     * de database server niet draait.
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "Database error");
        mav.addObject("lead", "Geen verbinding met de database mogelijk");
        mav.addObject("message", "Het lukte niet om verbinding met de database te maken. Is de database server bereikbaar en de database beschikbaar?");
        mav.addObject("exception", ex);

        // Je kunt hier kiezen in welke view je een melding toont - op een
        // aparte pagina, of als alertbox op de huidige pagina.
        mav.setViewName("error/error");
        return mav;
    }
}
