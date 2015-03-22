/**
 * File to Test Contacts
 * @author Annabel Jump
 * for PiJ Coursework 4
 */


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.*;

public class ContactImplTest {

    public ContactImpl testContact;

    //initialise variables for new Contact for test
    //integer for ID and String for name
    @Before
    public void buildUp(){
        testContact = new ContactImpl(1, "Rowan");
    }

    //reset after test
    @After
    public void cleanUp() {
        testContact = null;
    }

    //test getId method
    @Test
    public void testIDfetch() {
        assertEquals(1, testContact.getId());
    }

    //test getName method
    @Test
    public void testNameFetch() {
        assertEquals("Rowan", testContact.getName());
    }

    //test getNotes with empty notes
    @Test
    public void testGetEmptyNotes() {
        assertEquals("", testContact.getNotes());
    }

    //test addNotes by adding notes and fetching them
    @Test
    public void testAddFakeNotes() {
        testContact.addNotes("Rowan is Callisto Prime");
        assertEquals("Rowan is Callisto Prime", testContact.getNotes());
        /**
         * If notes are already made on contact, adding more needs to be thought about
         * can add a punctuation separator in between notes added at different times.
         */
        testContact.addNotes("Primes are T1 level Talents");
        assertEquals("Rowan is Callisto Prime; Primes are T1 level Talents", testContact.getNotes());
    }
}