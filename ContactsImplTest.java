package testing;

/**
 * File to Test Contacts/ContactsImpl
 * @author Annabel Jump
 * for PiJ Coursework 4
 */


import src.Contacts;
import src.ContactsIpl;
import java.io.*;

import org.junit.*;
import org.junit.Assert.*;

public class ContactsImplTest {

    public Contact testContact;

    //initialise (nonexistant) new Contact for test
    @before
    public void buildUp() {
        testContact = new Contact();
    }

    //reset after (hopefully un-) successful test
    @after
    public void cleanUp() {
        testContact = null;
    }



}