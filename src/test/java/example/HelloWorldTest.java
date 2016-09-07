package example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Simple example unit test for everybody who has never done a unit test
 *
 * Intellij has a nice interface to show how a test is run in the system.
 * It will tell you directly if a test is passed or a error occurred, so you don't have to write that in the test itself
 *
 * @author Bob van der Valk
 */
public class HelloWorldTest {

    /**
     * Everything you put into a @BeforeClass will run before the class is called
     */
    @BeforeClass
    public static void oneTimeSetup() {
        System.out.println("initialization test");
    }

    /**
     * This is a random method that runs a random test
     */
    @Test
    public void checkEquals() {
        String testString = "HelloWorld";
        assertEquals("HelloWorld", testString);
    }

    /**
     * This will do shit after the class is done
     */
    @AfterClass
    public static void testDone() {
        System.out.println("The test is done");
    }
}
