package nl.avansc1.facturatie.api.controller;

import nl.avansc1.facturatie.api.model.billing.Declaration;
import nl.avansc1.facturatie.api.model.billing.Treatment;
import nl.avansc1.facturatie.api.model.customers.Customer;
import nl.avansc1.facturatie.api.repository.CustomerDao;
import nl.avansc1.facturatie.api.repository.DeclarationDAO;
import nl.avansc1.facturatie.api.repository.TreatmentDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;

/**
 * This controller is responsible for importing declarations
 *
 * @author Bob van der Valk
 */
@RequestMapping("/import")
@Controller
@ResponseBody
public class ImportController {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private DeclarationDAO declarationDAO;

    @Autowired
    private TreatmentDAO treatmentDAO;

    @RequestMapping(value = "/{csn}", method = RequestMethod.GET)
    public String importRoute(Model theModel, @PathVariable int csn) throws ParserConfigurationException, SAXException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(("https://fysiocenter.herokuapp.com/api/treatment/" + csn), String.class);
        JSONArray obj = new JSONArray(jsonString);
        for (int temp = 0; temp < obj.length(); temp++) {
            JSONObject d = obj.getJSONObject(temp);
            Customer customer = customerDao.findByCsn((d.getInt("clientBsn")));
            Treatment treatment = treatmentDAO.findOne((d.getInt("treatmentCode")));
            Date today = new Date();
            Declaration declaration = new Declaration(customer, treatment, today, treatment.getPrice(), 0);
            declarationDAO.save(declaration);
        }
        return "Import successfully completed";
    }

    @RequestMapping("/")
    public String importRouteTest() throws ParserConfigurationException, SAXException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(("https://fysiocenter.herokuapp.com/api/treatment/169005078"), String.class);
        JSONArray obj = new JSONArray(jsonString);
        for (int temp = 0; temp < obj.length(); temp++) {
            JSONObject d = obj.getJSONObject(temp);
            Customer customer = customerDao.findByCsn((d.getInt("clientBsn")));
            Treatment treatment = treatmentDAO.findOne((d.getInt("treatmentCode")));
            Date today = new Date();
            Declaration declaration = new Declaration(customer, treatment, today, treatment.getPrice(), 0);
            declarationDAO.save(declaration);
        }
        return "Import successfully completed";
    }
}
