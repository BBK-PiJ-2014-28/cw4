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

	//Constructors
	
	MeetingImpl(int iDent){
		this.iD = iDent;
		this.requestedMeeting = null; //should throw exception or be dealt with in code
		this.meetersAndGreeters = null; //should throw exception or be dealt with in code
	}
	
	MeetingImpl(Calendar reserveTheBoardroom){
		this.iD = 0; //exception/code
		this.requestedMeeting = reserveTheBoardroom;
		this.meetersAndGreeters = null; //exception/code
	}
	
	MeetingImpl(Set<Contact> theApprentice){
		this.iD = 0; //exception to be thrown
		this.requestedMeeting = null; //exception
		this.meetersAndGreeters = theApprentice;
	}
	
	//the only fully acceptable way to initialise a meeting - all variables accounted for	
	MeetingImpl(int iDent, Calendar reserveTheBoardroom, Set<Contact> theApprentice){
		this.iD = iDent;
		this.requestedMeeting = reserveTheBoardroom;
		this.meetersAndGreeters = theApprentice;
	}
	
	MeetingImpl(int iDent, Calendar reserveTheBoardroom){
		this.iD = iDent;
		this.requestedMeeting = reserveTheBoardroom;
		this.meetersAndGreeters = null; //exception/code
	}
	
	MeetingImpl(int iDent, Set<Contact> theApprentice){
		this.iD = iDent;
		this.requestedMeeting = null; //exception/code
		this.meetersAndGreeters = theApprentice;
	}
	
	MeetingImpl(Calendar reserveTheBoardroom, Set<Contact> theApprentice){
		this.iD = 0; //exception/code
		this.requestedMeeting = reserveTheBoardroom;
		this.meetersAndGreeters = theApprentice;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getId() {
		return this.iD;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Calendar getDate() {
		return this.requestedMeeting;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Contact> getContacts() {
		return this.meetersAndGreeters;
	}

}
