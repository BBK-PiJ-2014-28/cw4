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
		ObjectInputStream testStream = new ObjectInputStream(new FileInputStream("testContacts.txt"));
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
		
		/**
		 * I think the best way to read/write from the file is to make a master list which
		 * contains other more specific lists - one per meeting type and for contacts
		 * Arrays are the lists that I prefer.
		 */
		
		List<Contact> listContactTest = (ArrayList<Contact>) testInput.get(2);
		assertEquals(listContactTest.get(0), rowan);

		List<FutureMeeting> futureMeetingTestList = (ArrayList<FutureMeeting>) testInput.get(0);
		assertEquals(futureMeetingTestList.get(0), testCalendar);
		
		List<PastMeeting> pastMeetingTestList = (ArrayList<PastMeeting>) testInput.get(1);
		assertEquals(pastMeetingTestList.get(0).getNotes(), "Went Well");
	}
	
	//Test getPastMeeting
	
	@Test
	public void testGetPastMeeting() {
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 8, 27, 13, 00));
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 28, 6, 33), "Birthday");
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 5, 17, 9, 45));
		assertEquals(testManager.getPastMeeting(3).getNotes(), "Birthday"); //ID should be actual number of meeting
	}

	//getPastMeeting should return null for nonexistant meeting
	
	@Test
	public void testGetPastMeetingReturnsNull(){
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 8, 27, 13, 00));
		assertNull(testManager.getPastMeeting(5));
	}
	
	//getPastMeeting should throw exception when FutureMeeting attempted to be retrieved
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetPastMeetingException1(){
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 8, 27, 13, 00));
		testManager.getPastMeeting(2);
	}

	/**
	 * can't get a past meeting without adding them!
	 * Next test addNewPastMeeting!
	 */
	
	@Test
	public void testAddNewPastMeeting() {
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
		assertEquals(testManager.getPastMeeting(1).getNotes(), "Wedding Day");
	}

	//should throw null exception if contacts null
	
	@Test(expected= NullPointerException.class)
	public void testNewPastMeetingNoContacts(){
		testManager.addNewPastMeeting(null, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
	}
	
	//should throw null exception if date is null
	
	@Test(expected= NullPointerException.class)
	public void testNewPastMeetingNoDate(){
		testManager.addNewPastMeeting(testContacts, null, "Wedding Day");
	}
	
	//should throw null exception if notes are null
	
	@Test(expected= NullPointerException.class)
	public void testNewPastMeetingNoNotes(){
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 27, 13, 00), null);
	}
	
	//illegal argument exception should be thrown if contacts in set are invalid
	
	@Test(expected = IllegalArgumentException.class)
	public void testNewPastMeetingWithNobody(){
		Set<Contact> falseSet = new HashSet<Contact>();
		falseSet.add(new ContactImpl(302, "Kim"));
		testManager.addNewPastMeeting(falseSet, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
	}

	//empty contact set should also throw exception
	
	@Test(expected = IllegalArgumentException.class)
	public void testNewPastMeetingWithAbsolutelyNobody(){
		Set<Contact> falseSet = new HashSet<Contact>();
		testManager.addNewPastMeeting(falseSet, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
	}
	
	//next test getFutureMeeting
	@Test
	public void testGetFutureMeeting() {
		Calendar weddingAnniversary = new GregorianCalendar(2015, 8, 27, 13, 00);
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 8, 27, 13, 00));
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 28, 6, 33), "Birthday");
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 5, 17, 9, 45));
		assertEquals(testManager.getPastMeeting(2).getDate(), weddingAnniversary);
	}
	
	//getFutureMeeting should return null when null
	
	@Test
	public void testGetFutureMeetingReturnsNull(){
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 8, 27, 13, 00));
		assertNull(testManager.getPastMeeting(5));
	}
	
	//getFutureMeeting should throw exception if is in fact a past meeting
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetFutureMeetingException(){
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 8, 27, 13, 00));
		testManager.getFutureMeeting(1);
	}
	
	//Moving on, test plan getMeeting - will test null return in same test
	
	@Test
	public void testGetMeeting() {
		Calendar weddingAnniversary = new GregorianCalendar(2015, 8, 27, 13, 00);
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 27, 13, 00), "Wedding Day");
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 8, 27, 13, 00));
		testManager.addNewPastMeeting(testContacts, new GregorianCalendar(2009, 8, 28, 6, 33), "Birthday");
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 5, 17, 9, 45));
		assertEquals(testManager.getMeeting(2).getDate(), weddingAnniversary);
		assertNull(testManager.getMeeting(7));
	}
	
	//Next tests for getFutureMeetingList(Contact)
	
	@Test
	public void testGetFutureMeetingContactList(){
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 8, 27, 13, 00));
		List<Meeting> personalMeetingSchedule = testManager.getFutureMeetingList(rowan);
		assertTrue(personalMeetingSchedule.get(0).getContacts().equals(testContacts));
	}
	
	//meetings should be sorted chronologically
	
	@Test
	public void testSortChronologically(){
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2019, 8, 27, 13, 00));
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 8, 27, 13, 00));
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2019, 8, 28, 6, 33));
		testManager.addFutureMeeting(testContacts, new GregorianCalendar(2015, 5, 17, 9, 45));
		List<Meeting> dateOrder = testManager.getFutureMeetingList(rowan);
		Calendar first = new GregorianCalendar(2015, 5, 17, 9, 45);
		assertEquals(dateOrder.get(0).getDate(), first);
	}
	
	//future meeting lists with a nonexistant contact throw an exception
	@Test(expected = IllegalArgumentException.class)
	public void testFutureMeetingListWithNobody(){
		Contact earthPrime = new ContactImpl("Reidinger");
		List<Meeting> clairvoyantMeetings = testManager.getFutureMeetingList(earthPrime);
	}
}

