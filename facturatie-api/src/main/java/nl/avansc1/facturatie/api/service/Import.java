package nl.avansc1.facturatie.api.service;

import nl.avansc1.facturatie.api.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * This class is responsible to import new declarations
 *
 * @author Bob van der Valk
 */
public class Import {
    private BufferedReader reader;

    /**
     * This method imports the data from the xml example sheet
     * @param fileName
     * @return
     */
    public static Import importWithTestFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        return new Import(new BufferedReader(new FileReader(file)));

    }
    // TODO: maak deze functie af wanneer de API beschikbaar is
    public static Import importWithURL(String url) {
        return null;
    }

    public Import(BufferedReader reader) {
        this.reader = reader;
    }

    public void start() {

    }

    @Autowired
    private CustomerDao customerDao;
}
