/**
 * Class to build a review
 */
public class Review {
	
	// Variables
	private int driverID;
	private int passengerID;
	private int rating;
	private String comment;
	
	/**
	 * Constructor to build a Review
	 * @param driverID - The host's ID
	 * @param passengerID - The passenger's ID
	 * @param rating - Passenger's rating for Driver
	 * @param comment - Passenger's Review for Driver
	 */
	public Review(int driverID, int passengerID, int rating, String comment) {	
		this.driverID = driverID;
		this.passengerID = passengerID;
		this.rating = rating;
		this.comment = comment;
	}
	
	// Getters & Setters
	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}

	public int getPassengerID() {
		return passengerID;
	}

	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
