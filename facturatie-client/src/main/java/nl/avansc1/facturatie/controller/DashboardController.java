package nl.avansc1.facturatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class DashboardController {

    @ModelAttribute("page")
    public String module() {
        return "dashboard";
    }

    @RequestMapping("/")
    String index(Model model) {
        return "redirect:customer";
    }

}
