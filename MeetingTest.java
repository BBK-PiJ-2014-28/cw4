/**
 * File to Test Meeting interface (and implementation)
 * @author Annabel Jump
 * for PiJ Coursework 4
 */


import static org.junit.Assert.*;

import org.junit.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;



public class MeetingTest {

	public Meeting testMeeting;
	public Calendar meetingDate;
	public Set<Contact> testSet, correctSet;
	public Contact rowan, raven;
	public int iD = 12;
	
	@Before
	public void buildUp(){
		rowan = new ContactImpl("Rowan Gwyn");
		raven = new ContactImpl("Jeff Raven");
		testSet.add(rowan);
		testSet.add(raven);
		meetingDate = new GregorianCalendar(2015, 8, 28, 6, 33);
		//meeting set up removed so can test the incorrect configurations
	}
	
	@After
	public void cleanUp(){
		testMeeting = null;
	}
	
	@Test
	public void testSetUp(){
		//TODO insert tests for different constructions
	}
	
	@Test
	public void testGetMeetingID() {
		testMeeting = new MeetingImpl(12, meetingDate, testSet);
		assertEquals(12, testMeeting.getId());
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetDate() {
		Calendar correctDate = new GregorianCalendar(2015, 8, 28, 6, 33);
		assertEquals(correctDate, testMeeting.getDate());
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetContacts() {
		correctSet.add(rowan);
		correctSet.add(raven);
		assertEquals(correctSet, testMeeting.getContacts());
		fail("Not yet implemented");	
	}
}
