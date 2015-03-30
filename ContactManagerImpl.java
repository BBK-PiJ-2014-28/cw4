/**
 * Class to Implement Meeting interface
 * @author Annabel Jump
 * for PiJ Coursework 4
 */

import java.util.*;
import java.io.*;
import java.nio.*;


public class ContactManagerImpl implements ContactManager {
	
	public int meetingCounter = 0, contactCounter = 0;
	public File contactData;
	public ContactManager contactManager;
	public Calendar requestedDate, todayTodayToday;
	public Set<Contact> meetingWith;
	public ObjectInputStream inputToFile;
	public List<List<?>> contactManagerWrapperList;
	public List<PastMeeting> pastMeetings;
	public List<FutureMeeting> futureMeetings;
	public List<Meeting> allMeetings;
	public List<Contact> netWorkThoseContacts;
	
	
	public ContactManagerImpl() throws IOException {
		contactData = new File("Contacts.txt");
		todayTodayToday = GregorianCalendar.getInstance(); 
		try {
			
			//if not first time ContactManager used, retrieve old data
			if (contactData.exists() && contactData.length() > 0) {
				inputToFile = new ObjectInputStream(new FileInputStream(contactData));
				contactManagerWrapperList = (ArrayList) inputToFile.readObject();
				futureMeetings = (ArrayList) contactManagerWrapperList.get(0);
				pastMeetings = (ArrayList) contactManagerWrapperList.get(1);
				netWorkThoseContacts = (ArrayList) contactManagerWrapperList.get(2);
				allMeetings = (ArrayList) contactManagerWrapperList.get(3);
			
			
			} else { //no data, initialise
				pastMeetings = new ArrayList<PastMeeting>();
				futureMeetings = new ArrayList<FutureMeeting>();
				allMeetings = new ArrayList<Meeting>();
				netWorkThoseContacts = new ArrayList<Contact>();
				contactManagerWrapperList = new ArrayList<List<?>>();
				contactManagerWrapperList.add(futureMeetings);
				contactManagerWrapperList.add(pastMeetings);
				contactManagerWrapperList.add(netWorkThoseContacts);
				contactManagerWrapperList.add(allMeetings);
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		/**
		 * initialise meetingCounter as being 1 larger than the size of both past + future arrays
		 * thus if no meetings, first meeting will be 1.
		 * (now that allMeetings added, only size of that is required)
		 */
		meetingCounter = allMeetings.size() + 1;
		//had to move here otherwise would be reset every time meeting added.
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) 
			throws IllegalArgumentException {
		//first check date valid
		if (date.before(todayTodayToday)) {
			throw new IllegalArgumentException();
			
		//check all contacts exist	
		} else if (!netWorkThoseContacts.containsAll(contacts)) {
			throw new IllegalArgumentException();
		
		//proceed with adding meeting
		} else {
		
		//make new meeting
		FutureMeeting requestedMeeting = new FutureMeetingImpl(meetingCounter, date, contacts);
		futureMeetings.add(requestedMeeting); //add to list
		allMeetings.add(requestedMeeting); //add to total list for easier searching
		}
		
		try {
			ObjectOutputStream outPut = new ObjectOutputStream(new FileOutputStream(contactData));
			outPut.reset();
			outPut.writeObject(contactManagerWrapperList);
			outPut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		meetingCounter++;

		int requestedID = requestedMeeting.getId();
		
		return requestedID;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public PastMeeting getPastMeeting(int id) throws IllegalArgumentException {
			PastMeeting requested = null;
			for (PastMeeting myOldMeeting: pastMeetings);
			if (myOldMeeting.getId().equals(id)) {
				requested = myOldMeeting;
			}
			for (FutureMeeting myMeeting: futureMeetings);
				if (myMeeting.getId().equals(id)){
					throw new IllegalArgumentException();
				}
			return requested;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		FutureMeeting requested = null;
		for (FutureMeeting myPlannedMeeting: futureMeetings);
		if (myPlannedMeeting.getId().equals(id)) {
			requested = myPlannedMeeting;
		}
		for (PastMeeting myMeeting: pastMeetings);
			if (myMeeting.getId().equals(id)){
				throw new IllegalArgumentException();
		}
		return requested;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public Meeting getMeeting(int id) {
		Meeting requested = null;
		for (FutureMeeting myPlannedMeeting: futureMeetings);
			if (myPlannedMeeting.getId().equals(id)) {
				requested = myPlannedMeeting;
		}
		for (PastMeeting myMeeting: pastMeetings);
			if (myMeeting.getId().equals(id)){
				requested = myMeeting;
		}
		return requested;
	}

	/**
     * Returns the list of future meetings scheduled with this contact.
     *
     * If there are none, the returned list will be empty. Otherwise,
     * the list will be chronologically sorted and will not contain any
     * duplicates.
     *
     * @param contact one of the user’s contacts
     * @return the list of future meeting(s) scheduled with this contact (maybe empty).
     * @throws IllegalArgumentException if the contact does not exist
     */
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException {
		if (!netWorkThoseContacts.contains(contact)) {
			throw new IllegalArgumentException();
		} 
		List<Meeting> scheduledMeetings = new ArrayList<Meeting>();
		for (Meeting plannedMeeting : allMeetings) {
			if (plannedMeeting.getContacts().contains(contact)
					&& plannedMeeting.getDate().after(todayTodayToday)) {
				scheduledMeetings.add(plannedMeeting);
			}
		} 
		Collections.sort(scheduledMeetings, (m1, m2) -> m1.getDate().compareTo(m2.getDate()));
		//sort into chronological order
		return scheduledMeetings;
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

	
	 /**
     * {@inheritDoc}
     */
	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date,
			String text) throws IllegalArgumentException, NullPointerException {
			if (contacts == null) { 
				throw new NullPointerException();
			} else if (date == null) {
				throw new NullPointerException();
			} else if (text == null) {
				throw new NullPointerException();
			}
			if (contacts.isEmpty()){
				throw new IllegalArgumentException();
			} else if (!netWorkThoseContacts.containsAll(contacts)){
				throw new IllegalArgumentException();
			}

			PastMeeting pastMeeting = new PastMeetingImpl(meetingCounter,date, contacts, text);
			pastMeetings.add(pastMeeting);
			allMeetings.add(pastMeeting);
			try {
				ObjectOutputStream outPut = new ObjectOutputStream(new FileOutputStream(contactData));
				outPut.reset();
				outPut.writeObject(contactManagerWrapperList);
				outPut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			meetingCounter++;
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


