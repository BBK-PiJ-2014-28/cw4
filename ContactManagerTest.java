/**
 * Class to Test ContactManager using JUnit
 * @author Annabel Jump
 * for PiJ Coursework 4
 */

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import java.io.*;

public class ContactManagerTest {

	public int meetingCounter, contactCounter;
	public File testData;
	public ContactManager testManager;
	public Calendar testCalendar, todayTodayToday;
	public Set<Contact> testContacts;
	public Contact rowan, raven;
	
	//Initialise variable before tests
	
	@Before
	public void buildUp(){
		rowan = new ContactImpl("Rowan Gwyn");
		raven = new ContactImpl("Jeff Raven");
		rowan.addNotes("Callisto T1 Prime");
		raven.addNotes("Deneb Untrained T1 Prime");
		testContacts = new HashSet<Contact>();
		testContacts.add(rowan);
		testContacts.add(raven);
		testData = new File("testContacts.txt");
		meetingCounter = 0;
		contactCounter = 0;
		todayTodayToday = GregorianCalendar.getInstance();
		testCalendar = new GregorianCalendar(2015, 8, 28, 6, 33);
		testManager = new ContactManagerImpl();		
	}
	
	//Close down all variables after tests
	
	@After
	public void cleanUp(){
		testManager.flush();
		rowan = null;
		raven = null;
		testContacts = null;
		meetingCounter = 0;
		contactCounter = 0;
		todayTodayToday = null;
		testCalendar = null;
		testManager = null;
	}
	
	@Test
	public void testAddFutureMeeting() {
		
	}

}
