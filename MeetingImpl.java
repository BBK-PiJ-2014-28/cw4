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
		this.requestedMeeting = null; //should throw exception
		this.meetersAndGreeters = null; //should throw exception
	}
	
	MeetingImpl(Calendar reserveTheBoardroom){
		this.iD = 0; //will need to make sure 0 throws exception
		this.requestedMeeting = reserveTheBoardroom;
		this.meetersAndGreeters = null; //exception to be thrown
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
		this.meetersAndGreeters = null; //this should throw exception
	}
	
	MeetingImpl(int iDent, Set<Contact> theApprentice){
		this.iD = iDent;
		this.requestedMeeting = null; //exception
		this.meetersAndGreeters = theApprentice;
	}
	
	MeetingImpl(Calendar reserveTheBoardroom, Set<Contact> theApprentice){
		this.iD = 0; //exception
		this.requestedMeeting = reserveTheBoardroom;
		this.meetersAndGreeters = theApprentice;
	}
	
	
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
