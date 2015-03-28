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
import java.util.HashSet;



public class MeetingTest {

	public Meeting testMeeting;
	public Calendar meetingDate = new GregorianCalendar(2015, 8, 28, 6, 33);;
	public Set<Contact> testSet, correctSet;
	public Contact rowan, raven;
	public int iD = 12;
	
	@Before
	public void buildUp(){
		rowan = new ContactImpl("Rowan Gwyn");
		raven = new ContactImpl("Jeff Raven");
		testSet = new HashSet<Contact>();
		testSet.add(rowan);
		testSet.add(raven);
		//meeting set up removed so can test the incorrect configurations
	}
	
	@After
	public void cleanUp(){
		testMeeting = null;
	}
	
	@Test
	public void testGetMeetingID() {
		testMeeting = new MeetingImpl(12, meetingDate, testSet);
		assertEquals(12, testMeeting.getId());
	}
	
	@Test
	public void testGetDate() {
		testMeeting = new MeetingImpl(12, meetingDate, testSet);
		Calendar correctDate = new GregorianCalendar(2015, 8, 28, 6, 33);
		assertEquals(correctDate, testMeeting.getDate());
	}
	
	@Test
	public void testGetContacts() {
		testMeeting = new MeetingImpl(12, meetingDate, testSet);
		correctSet = new HashSet<Contact>();
		correctSet.add(rowan);
		correctSet.add(raven);
		assertEquals(correctSet, testMeeting.getContacts());	
	}
	
	//Test of constructors at end so that other (necessary) methods tested first
	
	@Test
	public void testSetUp1(){
		testMeeting = new MeetingImpl(12, meetingDate);
		assertEquals(null, testMeeting.getContacts());
	}
	
	@Test
	public void testSetUp2(){
		testMeeting = new MeetingImpl(meetingDate, testSet);
		assertEquals(0, testMeeting.getId());
	}
	
	@Test
	public void testSetUp3(){
		testMeeting = new MeetingImpl(12, testSet);
		assertEquals(null, testMeeting.getDate());
	}
	
	@Test
	public void testSetUp4(){
		testMeeting = new MeetingImpl(12);
		Meeting secondTest = new MeetingImpl(meetingDate);
		Meeting thirdTest = new MeetingImpl(testSet);
		assertEquals(null, testMeeting.getDate());
		assertEquals(null, testMeeting.getContacts());
		assertEquals(null, secondTest.getContacts());
		assertEquals(0, secondTest.getId());
		assertEquals(null, thirdTest.getDate());
		assertEquals(0, thirdTest.getId());
	}
	
}
