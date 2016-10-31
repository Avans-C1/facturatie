package Invoice;

import nl.avansc1.facturatie.controller.InvoiceController;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevin on 27-10-2016.
 */


public class InvoiceTest {

    @Autowired
    private InvoiceController invoiceController;

    @BeforeClass
    public static void oneTimeSetup() {
        System.out.println("initialization test");
    }

    /**
     * This will do shit after the class is done
     */
    @AfterClass
    public static void testDone() {
        System.out.println("The test is done");
    }

    /**
     * This is a random method that runs a random test
     */
    @Test
    public void checkEquals() {
        String testString = "HelloWorld";
        assertEquals("HelloWorld", testString);
    }
}
