import java.util.List;

/**
 * Class to build a Carpool
 */
public class Carpool {
	
	// Variables
	private int carpoolID;
	private int driverID;
	private int passengerLimit;
	private List<Integer> passengerIDs;
	private String pickupLocation;
	private String destination;
	private String pickupDate;
	private String pickupTime;
	private Boolean status;
	
	/**
	 * Constructor to build Carpool
	 * @param carpoolID - The Carpool's ID
	 * @param driverID - The Driver's ID
	 * @param passengerIDs - Are passenger IDs of attendants 
	 * @param passengerLimit - Total passengers Carpool can carry
	 * @param pickupLocation - Pickup location for Carpool
	 * @param destination - Destination for Carpool
	 * @param pickupDate - Pickup date for Carpool
	 * @param pickupTime - Pickup time for Carpool
	 * @param status - Carpool's availability 
	 */
	public Carpool(int carpoolID, int driverID, int passengerLimit, List<Integer> passengerIDs, 
		String pickupLocation, String destination, String pickupDate, 
		String pickupTime, Boolean status) {
			
		this.carpoolID = carpoolID;
		this.driverID = driverID;
		this.passengerLimit = passengerLimit;
		this.passengerIDs = passengerIDs;
		this.pickupLocation = pickupLocation;
		this.destination = destination;
		this.pickupDate = pickupDate;
		this.pickupTime = pickupTime;
		this.status = status;
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

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}

	public int getPassengerLimit() {
		return passengerLimit;
	}

	public void setPassengerLimit(int passengerLimit) {
		this.passengerLimit = passengerLimit;
	}

	public List<Integer> getPassengerIDs() {
		return passengerIDs;
	}

	public void setPassengerIDs(List<Integer> passengerIDs) {
		this.passengerIDs = passengerIDs;
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

	public String getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	

}
