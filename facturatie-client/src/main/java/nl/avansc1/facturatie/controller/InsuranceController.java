package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.insurances.Insurance;
import nl.avansc1.facturatie.repository.InsuranceRepository;
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
public class InsuranceController {


    private final Logger logger = LoggerFactory.getLogger(InsuranceController.class);
    private InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceController(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
        logger.debug("insurance controller");
    }

    @ModelAttribute("page")
    public String module() {
        return "insurances";
    }

    @GetMapping(value = "/insurance")
    String index(Model model) {
        logger.debug("index");

        return "insurance/index";
    }

    /**
     * Hiermee open je de create view om een nieuwe insurance aan te maken.
     *
     * @param insurance Dit object wordt aan de view meegegeven. Het object wordt gevuld met de waarden uit het formulier.
     * @param model
     * @return
     */
    @GetMapping(value = "/insurance/create")
    public String showCreateInsuranceForm(final Insurance insurance, final ModelMap model) {
        logger.debug("showCreateInsuranceForm");

        return "insurance/create";
    }

    /**
     * Deze methode handelt een ingevuld formulier af. Als er fouten zijn opgetreden blijven we in dezelfde view.
     * Als er geen fouten waren maken we een nieuwe declaration en gaan we direct naar de list view voor het overzicht.
     * De nieuwe insurance moet dan in het overzicht staan.
     *
     * @param insurance   De declaration uit het formulier. De velden van declaration komen uit de input velden van het formulier.
     * @param bindingResult Het resultaat van de view.
     * @param model
     * @return
     */
    @RequestMapping(value = "/insurance/create", method = RequestMethod.POST)
    public String validateAndSaveInsurance(@Valid Insurance insurance, final BindingResult bindingResult, final ModelMap model) {
        logger.debug("validateAndSaveInsurance - new insurance = " + insurance.getId());

        if (bindingResult.hasErrors()) {
            // Als er velden in het formulier zijn die niet correct waren ingevuld vinden we die hier.
            // We blijven dan op dezelfde pagina. De foutmeldingen worden daar getoond
            // (zie het create.html bestand.
            logger.debug("validateAndSaveInsurance - bindingResult.hasErrors");
            return "insurance/create";
        }

        // Maak de insurance aan via de repository
        Insurance createdInsurance = this.insuranceRepository.create(insurance);
        // We gaan de lijst met insurances tonen, met een bericht dat de nieuwe insurance toegevoegd is.
        // Zet de opgevraagde insurances in het model
        model.addAttribute("insurances", insuranceRepository.findAll());
        model.addAttribute("info", "Insurance is toegevoegd.");
        // Open de juiste view template als resultaat.
        return "insurance/index";
    }

    @DeleteMapping(value = "/declaration/{id}")
    public String deleteInsurance (Model model, @PathVariable int id) {
        logger.debug("deleteInsurance, id = " + id);

        // Delete de insurance aan via de repository
        this.insuranceRepository.deleteInsuranceById(id);

        // We gaan de lijst met insurances tonen, met een bericht dat de nieuwe insurance toegevoegd is.
        // Zet de opgevraagde insurances in het model
        model.addAttribute("declarations", insuranceRepository.findAll());
        model.addAttribute("info", "Insurance is verwijderd.");

        // Open de juiste view template als resultaat.
        return "insurance/index";
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
