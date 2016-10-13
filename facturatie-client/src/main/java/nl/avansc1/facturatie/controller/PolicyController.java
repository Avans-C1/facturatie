package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.insurances.Policy;
import nl.avansc1.facturatie.repository.PolicyRepository;
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
 * Created by Matthijs on 12-10-2016.
 */
@Controller
public class PolicyController {


    private final Logger logger = LoggerFactory.getLogger(PolicyController.class);
    private PolicyRepository policyRepository;

    @Autowired
    public PolicyController (PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
        logger.debug("policy controller");
    }

    @ModelAttribute("page")
    public String module() {
        return "policies";
    }

    @GetMapping(value = "/policy")
    String index(Model model) {
        logger.debug("index");

        return "policy/index";
    }

    /**
     * Hiermee open je de create view om een nieuwe declaration aan te maken.
     *
     * @param policy Dit object wordt aan de view meegegeven. Het object wordt gevuld met de waarden uit het formulier.
     * @param model
     * @return
     */
    @GetMapping(value = "/policy/create")
    public String showCreatePolicyForm(final Policy policy, final ModelMap model) {
        logger.debug("showCreatePolicyForm");

        return "policy/create";
    }

    /**
     * Deze methode handelt een ingevuld formulier af. Als er fouten zijn opgetreden blijven we in dezelfde view.
     * Als er geen fouten waren maken we een nieuwe declaration en gaan we direct naar de list view voor het overzicht.
     * De nieuwe policy moet dan in het overzicht staan.
     *
     * @param policy  De policy uit het formulier. De velden van policy komen uit de input velden van het formulier.
     * @param bindingResult Het resultaat van de view.
     * @param model
     * @return
     */
    @RequestMapping(value = "/policies/create", method = RequestMethod.POST)
    public String validateAndSavePolicy(@Valid Policy policy, final BindingResult bindingResult, final ModelMap model) {
        logger.debug("validateAndSavePolicy - new policy = " + policy.getId());

        if (bindingResult.hasErrors()) {
            // Als er velden in het formulier zijn die niet correct waren ingevuld vinden we die hier.
            // We blijven dan op dezelfde pagina. De foutmeldingen worden daar getoond
            // (zie het create.html bestand.
            logger.debug("validateAndSavePolicy - bindingResult.hasErrors");
            return "policies/create";
        }

        // Maak de declaration aan via de repository
        Policy createdPolicy = this.policyRepository.create(policy);
        // We gaan de lijst met declarations tonen, met een bericht dat de nieuwe policy toegevoegd is.
        // Zet de opgevraagde declarations in het model
        model.addAttribute("policies", policyRepository.findAll());
        model.addAttribute("info", "Policy is toegevoegd.");
        // Open de juiste view template als resultaat.
        return "policies/index";
    }

    @DeleteMapping(value = "/policies/{id}")
    public String deletePolicy(Model model, @PathVariable int id) {
        logger.debug("deletePolicy, id = " + id);

        // Delete de policy aan via de repository
        this.policyRepository.deletePolicyById(id);

        // We gaan de lijst met policies tonen, met een bericht dat de nieuwe policy toegevoegd is.
        // Zet de opgevraagde declarations in het model
        model.addAttribute("policies", policyRepository.findAll());
        model.addAttribute("info", "Policy is verwijderd.");

        // Open de juiste view template als resultaat.
        return "policy/index";
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
