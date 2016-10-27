package nl.avansc1.facturatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Robin on 27/10/2016.
 */
@Controller
@RequestMapping("/")
public class AuthController {
    // Login form
    @RequestMapping("/login")
    public String login(HttpSession session) {
        return "auth/login";
    }
}
