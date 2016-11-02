package nl.avansc1.facturatie.api.service;

import nl.avansc1.facturatie.api.model.billing.Declaration;
import nl.avansc1.facturatie.api.model.billing.Treatment;
import nl.avansc1.facturatie.api.model.customers.Customer;
import nl.avansc1.facturatie.api.repository.CustomerDao;
import nl.avansc1.facturatie.api.repository.DeclarationDAO;
import nl.avansc1.facturatie.api.repository.TreatmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * This class is responsible to import new declarations
 *
 * @author Bob van der Valk
 */
public class Import {
    private Document document;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private DeclarationDAO declarationDAO;
    @Autowired
    private TreatmentDAO treatmentDAO;

    public Import(Document object) {
        this.document = object;
    }

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

    //TODO: finish this function when the other group api is done
    public static Import importWithURL(String url) throws IOException, SAXException, ParserConfigurationException {
        return null;
    }

    public void start() {
        NodeList nList = document.getElementsByTagName("treatments");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Customer customer = customerDao.findByCsn(Integer.parseInt(eElement.getAttribute("customer")));
                Treatment treatment = treatmentDAO.findOne(Integer.parseInt(eElement.getAttribute("code")));
                Date today = new Date();
                Declaration declaration = new Declaration(customer, treatment, today, treatment.getPrice(), 0);
                declarationDAO.save(declaration);
            }
        }
    }
}
