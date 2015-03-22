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
	public Set<Contact> testSet;
	public Contact rowan, raven;
	public int iD = 12;
	
	@Before
	public void buildUp(){
		rowan = new ContactImpl("Rowan Gwyn");
		raven = new ContactImpl("Jeff Raven");
		testSet.add(rowan);
		testSet.add(raven);
		meetingDate = new GregorianCalendar(2015, 8, 28, 6, 33);
		testMeeting = new Meeting(meetingDate, testSet, iD);
	}
	
	@After
	public void cleanUp(){
		testMeeting = null;
	}
	
	@Test
	public void testGetMeetingID() {
		assertEquals(12, testMeeting.getId());
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetDate() {
		Calendar correctDate = new GregorianCalendar(2015, 8, 28, 6, 33);
		assertEquals(correctDate, testMeeting.getDate());
		fail("Not yet implemented");
	}

}
