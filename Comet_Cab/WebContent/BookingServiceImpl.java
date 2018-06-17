package service;
import domain.login.*;

import domain.login.BookingDAO;
import domain.login.BookingDAOImpl;
import model.CabType;
import model.CardDetails;
import model.Location;
import model.Cab;
import model.Driver;
import model.Booking;

public class BookingServiceImpl implements BookingService{

	BookingDAO bookingDao = new BookingDAOImpl();
	@Override
	public float estimateFare(Location location, CabType cabType, String netId) {
		int distance=bookingDao.getDistance(location);
		//Location location, float fare, CabType cabType, String netId
		/*Float eFare=distance*cabType.getMultiplier();
		Booking BookingInfo = new Booking(location,eFare,cabType,netId);*/
		return (distance*cabType.getMultiplier());
	}

	MakeBookingDAO MakebookingDao = new MakeBookingDAOImpl();
	@Override
	public String makeBooking(String netId, Location location, float fare, String cabTypeInfo) 
	{
		String Confirmation=null;
		CardDetails CardInfo=MakebookingDao.RetrieveCardDetails(netId);
		if(CardInfo.getBalance()<fare)
		{
			Confirmation="You have insufficient balance to make this trip. Please recharge and try again";
			//Confirmation="not enough balance";
		}
			else
		{
		Cab cabDetails=MakebookingDao.RetrieveCabDetails(cabTypeInfo);
		System.out.println("license no: "+cabDetails.getLicensePlateNo());
		if((cabDetails.getLicensePlateNo()==null)||cabDetails.getLicensePlateNo().length()<1||cabDetails.getLicensePlateNo()=="")
		{
			Confirmation="The Cab type selected is not available at the moment. Please try after sometime";
			//Confirmation="cab type not available";
		}
		else
		{
			CabType cabTypeDets = CabType.valueOf(cabTypeInfo.trim());
			Booking bookingInfo = new Booking(location,fare,cabTypeDets,netId);
			bookingInfo=MakebookingDao.SetBookingInfo(bookingInfo);
			Driver driverDetails=MakebookingDao.RetrieveDriverDetails(cabDetails.getLicensePlateNo());
			
			Confirmation="Your booking is confirmed. Your booking ID is "+bookingInfo.getBookingId();
			Confirmation=Confirmation+"<br>Your driver is "+driverDetails.getLastName()+","+driverDetails.getFirstName();
			
		}
		}
		return Confirmation;
	}

	
/*	@Override
	public Driver allocateRide(String bookingId) {
	Driver driverDetails=MakebookingDao.RetrieveDriverDetails(cabDetails.getLicensePlateNo());
	return driverDetails;
	}*/


	}
