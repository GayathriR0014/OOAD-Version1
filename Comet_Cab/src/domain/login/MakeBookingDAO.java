package domain.login;

import model.CardDetails;
import model.Driver;
import model.Cab;
import model.Booking;
//import model.CabType;



public interface MakeBookingDAO {
	public CardDetails RetrieveCardDetails(String netId);
	public Cab RetrieveCabDetails(String cabTypeInfo);
	public Booking SetBookingInfo(Booking bookingInfo);
	public Booking GetBookingId(Booking bookingInfo);
	public Driver RetrieveDriverDetails(String licenseNo);

}




