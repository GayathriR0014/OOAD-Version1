package domain.login;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Booking;
import model.Cab;
//import model.CabType;
import model.CardDetails;
import db.DbManager;
import model.Driver;;


public class MakeBookingDAOImpl implements MakeBookingDAO {

	static Connection conn1;
	static Connection conn2;
	static PreparedStatement ps1;
	static PreparedStatement ps2;
	DbManager db1 = new DbManager();
	

	public CardDetails RetrieveCardDetails(String netId) {
		CardDetails CardInfo=new CardDetails();
		CardInfo.setNetId(netId);
		try{
			
			conn1 = db1.getConnection();
			ps1 =conn1.prepareStatement("Select cardNo,balance from carddetails where netId= ?");
			ps1.setString(1, CardInfo.getNetId());
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				CardInfo.setCardNo(rs1.getString(1));
				CardInfo.setBalance(Float.parseFloat(rs1.getString(2)));
			}
			conn1.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return CardInfo;
	}
	
	
	public Cab RetrieveCabDetails(String cabTypeInfo)
	{
		Cab cabInfo = new Cab();
		try{
			conn1 = db1.getConnection();
			ps1 =conn1.prepareStatement("Select licenseNo,model from cab where cabtype= ? and availability=?");
			ps1.setString(1, cabTypeInfo);
			ps1.setString(2, "T");
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				cabInfo.setLicensePlateNo(rs1.getString(1));
				cabInfo.setModel(rs1.getString(2));
				}
			conn1.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return cabInfo;
	}
	
	public Driver RetrieveDriverDetails(String licenseNo)
	{
		Driver driverInfo = new Driver();
		try{
			conn1 = db1.getConnection();
			ps1 =conn1.prepareStatement("Select firstname,lastname,phoneNo from driver where licenseNo= ?");
			ps1.setString(1, licenseNo);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				driverInfo.setFirstName(rs1.getString(1));
				driverInfo.setLastName(rs1.getString(2));
				driverInfo.setPhoneNo(rs1.getString(3));
				}
			conn1.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return driverInfo;
	}
	public Booking SetBookingInfo(Booking bookingInfo)
	{
			try{
				int status=0;
			conn1 = db1.getConnection();
			// Test begins
			System.out.println("BEFORE SQL");
			System.out.println("NET ID :"+bookingInfo.getNetId());
			System.out.println("GET LOCATION :"+bookingInfo.getLocation().getPickUpLocation().name());
			System.out.println("DROP OFF LOCATION :"+bookingInfo.getLocation().getDropOffLocation().name());
			System.out.println("FARE :"+String.valueOf(bookingInfo.getFare()));
			System.out.println("CAB NAME :"+bookingInfo.getCabType().name());
			// test ends
			
			ps1 =conn1.prepareStatement("Insert into bookings(netId,pickupLocation,dropOffLocation,fare,cabtype) values(?,?,?,?,?)");
			ps1.setString(1,bookingInfo.getNetId());
			ps1.setString(2,bookingInfo.getLocation().getPickUpLocation().name());
			ps1.setString(3,bookingInfo.getLocation().getDropOffLocation().name());
			ps1.setString(4,String.valueOf(bookingInfo.getFare()));
			ps1.setString(5,bookingInfo.getCabType().name() );
			status = ps1.executeUpdate();
			conn1.close();
			GetBookingId(bookingInfo);
			}catch(Exception e){
			System.out.println(e);
		}
		return bookingInfo;
	}


public Booking GetBookingId(Booking bookingInfo)
{
	conn2 = db1.getConnection();
	try
	{
	ps2 = conn2.prepareStatement("select max(bookingId) FROM bookings");
	ResultSet rs2 = ps2.executeQuery();
	while(rs2.next()){
		int lastid = Integer.parseInt(rs2.getString(1));
		bookingInfo.setBookingId(lastid);
		}
	conn2.close();
	System.out.println("AUTO incremented value :"+bookingInfo.getBookingId());
	}
	catch(Exception e){
		System.out.println(e);
	}
return (bookingInfo);
}

}
	
