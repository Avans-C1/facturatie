package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.administration.InsuranceCompany;
import nl.avansc1.facturatie.repository.InsuranceCompanyRepository;
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
public class InsuranceCompanyController {


    private final Logger logger = LoggerFactory.getLogger(DeclarationController.class);
    private InsuranceCompanyRepository insuranceCompanyRepository;

    @Autowired
    public InsuranceCompanyController(InsuranceCompanyRepository insuranceCompanyRepository) {
        this.insuranceCompanyRepository = insuranceCompanyRepository;
        logger.debug("insuranceCompany controller");
    }

    @ModelAttribute("page")
    public String module() {
        return "insuranceCompany";
    }

    @GetMapping(value = "/insuranceCompany")
    String index(Model model) {
        logger.debug("index");

        return "insuranceCompany/index";
    }

    /**
     * Hiermee open je de create view om een nieuwe declaration aan te maken.
     *
     * @param insuranceCompany Dit object wordt aan de view meegegeven. Het object wordt gevuld met de waarden uit het formulier.
     * @param model
     * @return
     */
    @GetMapping(value = "/declaration/create")
    public String showCreateInsuranceCompanyForm(final InsuranceCompany insuranceCompany, final ModelMap model) {
        logger.debug("showCreateInsuranceCompanyForm");

        return "InsuranceCompany/create";
    }

    /**
     * Deze methode handelt een ingevuld formulier af. Als er fouten zijn opgetreden blijven we in dezelfde view.
     * Als er geen fouten waren maken we een nieuwe insuranceCompany en gaan we direct naar de list view voor het overzicht.
     * De nieuwe insuranceCompany moet dan in het overzicht staan.
     *
     * @param insuranceCompany   De declaration uit het formulier. De velden van declaration komen uit de input velden van het formulier.
     * @param bindingResult Het resultaat van de view.
     * @param model
     * @return
     */
    @RequestMapping(value = "/declaration/create", method = RequestMethod.POST)
    public String validateAndSaveDeclaration(@Valid InsuranceCompany insuranceCompany, final BindingResult bindingResult, final ModelMap model) {
        logger.debug("validateAndSaveInsuranceCompany - new insuranceCompany = " + insuranceCompany.getId());

        if (bindingResult.hasErrors()) {
            // Als er velden in het formulier zijn die niet correct waren ingevuld vinden we die hier.
            // We blijven dan op dezelfde pagina. De foutmeldingen worden daar getoond
            // (zie het create.html bestand.
            logger.debug("validateAndSaveDeclaration - bindingResult.hasErrors");
            return "declaration/create";
        }

        // Maak de declaration aan via de repository
        InsuranceCompany createdInsuranceCompany = this.insuranceCompanyRepository.create(insuranceCompany);
        // We gaan de lijst met declarations tonen, met een bericht dat de nieuwe declaration toegevoegd is.
        // Zet de opgevraagde declarations in het model
        model.addAttribute("InsuranceCompany", insuranceCompanyRepository.findAll());
        model.addAttribute("info", "InsuranceCompany is toegevoegd.");
        // Open de juiste view template als resultaat.
        return "insuranceCompany/index";
    }

    @DeleteMapping(value = "/insuranceCompany/{id}")
    public String deleteInsuranceCompany(Model model, @PathVariable int id) {
        logger.debug("deleteInsuranceCompany, id = " + id);

        // Delete de insuranceCompany aan via de repository
        this.insuranceCompanyRepository.deleteInsuranceCompanyById(id);

        // We gaan de lijst met declarations tonen, met een bericht dat de nieuwe insuranceCompany toegevoegd is.
        // Zet de opgevraagde declarations in het model
        model.addAttribute("insuranceCompany",insuranceCompanyRepository.findAll() );
        model.addAttribute("info", "insuranceCompany is verwijderd.");

        // Open de juiste view template als resultaat.
        return "insuranceCompany/index";
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
