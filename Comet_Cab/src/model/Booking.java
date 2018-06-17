package model;

public class Booking {

	private Location location;
	private float fare;
	private CabType cabType;
	private String netId;
	private int bookingId;



	public Booking(Location location, float fare, CabType cabType, String netId) {
		super();
		this.location = location;
		this.fare = fare;
		this.cabType = cabType;
		this.netId = netId;
		}
	
	public Booking() {
		super();
	}
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public float getFare() {
		return fare;
	}

	public void setFare(float fare) {
		this.fare = fare;
	}

	
	public CabType getCabType() {
		return cabType;
	}

	public void setCabType(CabType cabType) {
		this.cabType = cabType;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}
}
