/**
 * File to Implement PastMeeting interface
 * @author Annabel Jump
 * for PiJ Coursework 4
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

	public String meetingNotes;
	
	//Constructors to take into account notes and no notes.
	
	PastMeetingImpl(int iDent, Calendar reserveTheBoardRoom, Set<Contact> theApprentice, String doodles){
		super(iDent, reserveTheBoardRoom, theApprentice);
		this.meetingNotes = doodles;
	}
	
	PastMeetingImpl(int iDent, Calendar reserveTheBoardRoom, Set<Contact> theApprentice){
		super(iDent, reserveTheBoardRoom, theApprentice);
		this.meetingNotes = "";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNotes() {
		return meetingNotes;
	}

}
