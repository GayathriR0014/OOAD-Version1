package service;
import model.CabType;
import model.Driver;
import model.CardDetails;
import model.Location;

public interface BookingService {

	float estimateFare(Location location, CabType cabType, String netId);

	String makeBooking(String netId, Location location, float fare, String cabTypeInfo);
	
//	public Driver allocateRide(String bookingId);
	
}
