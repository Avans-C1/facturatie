package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.repository.DeclarationDAO;
import nl.avansc1.facturatie.model.billing.Declaration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Robin Valk on 11-10-16.
 *
 * Controller for the Declaration model.
 * This controller regulates the mapping of the declaration page.
 * Declarations are imported via the api and therefore no other pages
 * other than the overview are required.
 *
 * @author Robin Valk
 * @version 1.0
 * @see Declaration
 * @see DeclarationDAO
 */
@Controller
@RequestMapping("/declaration")
public class DeclarationController {

    private DeclarationDAO declarationDAO;

    /**
     * Constructor
     * @param declarationDAO
     */
    @Autowired
    public DeclarationController(DeclarationDAO declarationDAO) {
        this.declarationDAO = declarationDAO;
    }

    /**
     * Defines `module` variable for usage in the sidebar
     * @return String
     */
    @ModelAttribute("page")
    public String module() {
        return "declarations";
    }

    /**
     * Mapping of the overview page containing all the imported declarations.
     * @param model
     * @return declaration/index
     */
    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("declarations", declarationDAO.findAll());

        return "declaration/index";
    }
}
