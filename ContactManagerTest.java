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
import java.util.*;

import java.io.*;
import java.nio.*;

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
	
	//Test adding a future meeting, as is first meeting, int return should be 1

	@Test
	public void testAddFutureMeeting() {
		meetingCounter = testManager.addFutureMeeting(testContacts, testCalendar);
		assertEquals(1, meetingCounter);
		meetingCounter = testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 8, 7, 3, 45));
		assertEquals(2, meetingCounter); //second meeting added therefore should be 2
	}
	
	/**
	 * exception should be thrown where meeting added is
	 * in fact, in the past
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddPastFutureMeeting(){
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2014, 8, 28, 14, 50));
	}
	
	/**
	 * exception should be thrown where contacts in set do not exist
	 * (program should check the list of entered contacts against 
	 * contacts in the set)
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddMeetingWithNobody(){
		Set<Contact> falseSet = new HashSet<Contact>();
		falseSet.add(rowan);
		falseSet.add(new ContactImpl("Nobody"));
		testManager.addFutureMeeting(falseSet, testCalendar);
	}

	/**
	 * need to test serialization and reading/writing to file
	 * Start with serialization test
	 */
	
	@Test
	public void serialIzeTest() throws IOException {
		ByteArrayOutputStream test = new ByteArrayOutputStream();
		ObjectOutputStream serIalIze = new ObjectOutputStream(test);
		serIalIze.writeObject(rowan);
		serIalIze.close();
		assertTrue(test.toByteArray().length > 0);
		ByteArrayInputStream testIn = new ByteArrayInputStream(test.toByteArray());
		ObjectInputStream deSerIalIze = new ObjectInputStream(testIn);
		Contact testContact = null;
		try {
			testContact = (Contact) deSerIalIze.readObject();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		assertEquals(testContact, rowan);
	}	
		
	//read/write test
	
	@Test
	public void readWriteTest() throws IOException {
		assertTrue(testData.exists());
		testManager.addFutureMeeting(testContacts, testCalendar);
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2014, 5, 17, 9, 35), "Went Well");
		testManager.flush();
		ObjectInputStream testStream = new ObjectInputStream(new FileInputStream("contactTest.txt"));
		List testInput = null;
		try {
			testInput = (ArrayList) testStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				testStream.close();
			} catch (IOException eXio){
				eXio.printStackTrace();
			}
		}

		List<Contact> listContactTest = (ArrayList<Contact>) testInput.get(2);

		assertEquals(listContactTest.get(0), rowan);
	}
}
