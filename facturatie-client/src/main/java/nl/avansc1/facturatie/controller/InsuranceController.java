package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.insurances.Insurance;
import nl.avansc1.facturatie.repository.InsuranceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Matthijs on 13-10-2016.
 */
@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    @RequestMapping("")
    public String index(){
        return "insurance/index";
    }
    /**
     * Overview page
     * @return template/insurance/overview.html
     */

    @RequestMapping("/new")
    public String add() {
        return "insurance/add";
    }

    /**
     * Process the saving stuff
     * @return saved message is the insurance is saved
     */

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addHandler(int id, String name, float monthlyFee, int coveredTreatments) {
        try {
            Insurance insurance = new Insurance(id, name, monthlyFee, coveredTreatments);
            insuranceDAO.save(insurance);
        } catch (Exception ex) {
            return "saving error";
        }

        return "saved!";
    }

    @Autowired
    private InsuranceDAO insuranceDAO;
}
