public class Carpool {
	
	// Variables
	private int carpoolID;
	private int driverID;
	private int passengerID;
	private String pickupLocation;
	private String destination;
	private int dateTime;
	private String status;
	
	// Constructor
	public Carpool() {
	}
	
	// Getters & Setters
	public int getCarpoolID() {
		return carpoolID;
	}

	public void setCarpoolID(int carpoolID) {
		this.carpoolID = carpoolID;
	}

	public int getDriverID() {
		return driverID;
	}

	public int getPassengerID() {
		return passengerID;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDateTime() {
		return dateTime;
	}

	public void setDateTime(int dateTime) {
		this.dateTime = dateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
