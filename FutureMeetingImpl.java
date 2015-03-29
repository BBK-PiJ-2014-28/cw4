import java.util.Calendar;
import java.util.Set;
import java.io.*;


public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Serializable {

	public FutureMeetingImpl(int iD, Calendar reserveTheBoardroom, Set<Contact> theApprentice){
		super(iD, reserveTheBoardroom, theApprentice);
	}
	

}
