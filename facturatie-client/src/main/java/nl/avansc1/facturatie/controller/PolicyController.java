package nl.avansc1.facturatie.controller;

import nl.avansc1.facturatie.model.insurances.Insurance;
import nl.avansc1.facturatie.model.insurances.Policy;
import nl.avansc1.facturatie.repository.PolicyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.util.List;

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
    /**
     * Overview page
     * @return template/customer/overview.html
     */

    @RequestMapping("/new")
    public String add() {
        return "policy/add";
    }

    /**
     * Process the saving stuff
     * @return saved message is the policy is saved
     */

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addHandler(float Contribution, Date dateStart, Date dateEnd, boolean active, float ContributionUsed, List<Insurance> insurances ) {
        try {
            Policy policy = new Policy(Contribution, dateStart, dateEnd, active, ContributionUsed, insurances);
            policyDAO.save(policy);
        } catch (Exception ex) {
            return "saving error";
        }

        return "saved!";
    }

@Autowired
private PolicyDAO policyDAO;

}
