package nl.avansc1.facturatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Robin on 27/10/2016.
 * Controller for the custom login page.
 *
 * @author Robin Valk
 * @version 1.0
 * @see HttpSession
 */
@Controller
@RequestMapping("/")
public class AuthController {

    /**
     * Map the login request to the login view
     *
     * @param session Required to prevent the session headers to be send prematurely
     * @return auth/login
     */
    @RequestMapping("/login")
    public String login(HttpSession session) {
        return "auth/login";
    }
}
