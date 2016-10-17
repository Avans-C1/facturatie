package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.administration.User;
import nl.avansc1.facturatie.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Robin on 15-10-16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @ModelAttribute("page")
    public String module() {
        return "users";
    }


    // index

    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("users", userDAO.findAll());

        return "user/index";
    }


    // create

    @GetMapping(value = "/create")
    String create(Model model) {
        model.addAttribute("user", new User());

        return "user/edit";
    }

    @PostMapping(value = "/create")
    String store(Model model, @ModelAttribute User user) {
        userDAO.save(user);

        model.addAttribute("success", "User successfully saved");

        return this.index(model);
    }


    // edit

    @GetMapping(value = "/edit/{id}")
    String edit(Model model, @PathVariable int id) {
        model.addAttribute("user", userDAO.findOne(id));

        return "user/edit";
    }


    // delete

    @GetMapping(value = "/delete/{id}")
    String delete(Model model, @PathVariable int id) {
        userDAO.delete(id);

        model.addAttribute("success", "User successfully removed");

        return this.index(model);
    }
}