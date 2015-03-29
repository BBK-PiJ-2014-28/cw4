/**
 * Class to Implement Meeting interface
 * @author Annabel Jump
 * for PiJ Coursework 4
 */

import java.util.*;
import java.io.*;
import java.nio.*;


public class ContactManagerImpl implements ContactManager {
	
	public int meetingCounter, contactCounter;
	public File contactData;
	public ContactManager contactManager;
	public Calendar requestedDate, todayTodayToday;
	public Set<Contact> meetingWith;
	public ObjectInputStream inputToFile;
	public List<List<?>> contactManagerWrapperList;
	public List<PastMeeting> pastMeetings;
	public List<FutureMeeting> futureMeetings;
	public List<Contact> netWorkThoseContacts;
	
	public ContactManagerImpl() throws IOException {
		contactData = new File("contactsMeetings.txt");
		todayTodayToday = GregorianCalendar.getInstance(); 
		try {
			
			//if not first time ContactManager used, retrieve old data
			if (contactData.exists() && contactData.length() > 0) {
				inputToFile = new ObjectInputStream(new FileInputStream(contactData));
				contactManagerWrapperList = (ArrayList) inputToFile.readObject();
				futureMeetings = (ArrayList) contactManagerWrapperList.get(0);
				pastMeetings = (ArrayList) contactManagerWrapperList.get(1);
				netWorkThoseContacts = (ArrayList) contactManagerWrapperList.get(2);
			
			
			} else { //no data, initialise
				pastMeetings = new ArrayList<PastMeeting>();
				futureMeetings = new ArrayList<FutureMeeting>();
				netWorkThoseContacts = new ArrayList<Contact>();
				contactManagerWrapperList = new ArrayList<List<?>>();
				contactManagerWrapperList.add(futureMeetings);
				contactManagerWrapperList.add(pastMeetings);
				contactManagerWrapperList.add(netWorkThoseContacts);
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PastMeeting getPastMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting getMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date,
			String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMeetingNotes(int id, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNewContact(String name, String notes) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Contact> getContacts(int... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

}
