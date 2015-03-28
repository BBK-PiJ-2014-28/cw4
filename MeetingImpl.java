/**
 * File to Implement Meeting interface
 * @author Annabel Jump
 * for PiJ Coursework 4
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;


public class MeetingImpl implements Meeting {
	
	public int iD;
	public Calendar requestedMeeting;
	public Set<Contact> meetersAndGreeters;

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Calendar getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Contact> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}

}
