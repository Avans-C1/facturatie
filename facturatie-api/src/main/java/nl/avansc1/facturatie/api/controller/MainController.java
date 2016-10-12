package nl.avansc1.facturatie.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Loads a default starting page
 *
 * @author Bob van der Valk
 */
@Controller
public class MainController {
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Gemaakt door Avans-c1";
    }
}
