package nl.avansc1.facturatie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Matthijs on 13-10-2016.
 */
@Controller
@RequestMapping("/policy")
public class PolicyController {
    @RequestMapping("")
    public String index(){
        return "policy/index";
    }
}
