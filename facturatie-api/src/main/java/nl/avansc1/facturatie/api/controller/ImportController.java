package nl.avansc1.facturatie.api.controller;

import nl.avansc1.facturatie.api.service.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * This controller is responsible for importing declarations
 *
 * @author Bob van der Valk
 */
@RequestMapping("/import")
@Controller
public class ImportController {
    @RequestMapping("/")
    public String importRoute() throws ParserConfigurationException, SAXException, IOException {
        Import xml = Import.importWithTestFile("treatments.xml");
        xml.start();
        return "Import succesfully completed";
    }
}
