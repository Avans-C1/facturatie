package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.billing.Declaration;
import nl.avansc1.facturatie.repository.DeclarationDAO;
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
@RequestMapping("/declaration")
public class DeclarationController {

    private DeclarationDAO declarationDAO;

    @Autowired
    public DeclarationController(DeclarationDAO declarationDAO) {
        this.declarationDAO = declarationDAO;
    }

    @ModelAttribute("page")
    public String module() {
        return "declarations";
    }

    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("declarations", declarationDAO.findAll());

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
