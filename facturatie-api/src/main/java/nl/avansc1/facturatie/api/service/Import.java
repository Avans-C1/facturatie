package nl.avansc1.facturatie.api.service;

import nl.avansc1.facturatie.api.repository.DeclarationDAO;
import nl.avansc1.facturatie.api.repository.TreatmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible to import new declarations
 *
 * @author Bob van der Valk
 */
public class Import {
    private Document document;

    /**
     * This method imports the data from the xml example sheet
     * @param fileName
     * @return
     */
    public static Import importWithTestFile(String fileName) throws IOException, SAXException, ParserConfigurationException {
        File file = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return new Import(dBuilder.parse(file));
    }

    public Import(Document document) {
        this.document = document;
    }

    @Autowired
    private DeclarationDAO declarationDAO;

    @Autowired
    private TreatmentDAO treatmentDAO;
}
