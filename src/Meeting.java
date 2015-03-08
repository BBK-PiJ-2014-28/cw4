/**
 * Default interface provided by Programming in Java tutors
 * for Coursework number 4 - not edited from coursework PDF file
 * NOT written by Annabel Jump, WRITTEN BY PiJ Tutors!
 */


import java.util.Calendar;
import java.util.Set;
/**
 * A class to represent meetings
 *
 * Meetings have unique IDs, scheduled date and a list of participating contacts
 */
public interface Meeting {

    /**
     * Returns the id of the meeting.
     *
     * @return the id of the meeting.
     */
    int getId();

    /**
     * Return the date of the meeting.
     *
     * @return the date of the meeting.
     */
    Calendar getDate();

    /**
     * Return the details of people that attended the meeting.
     *
     * The list contains a minimum of one contact (if there were
     * just two people: the user and the contact) and may contain an
     * arbitraty number of them.
     *
     * @return the details of people that attended the meeting.
     */
    Set<Contact> getContacts();
}