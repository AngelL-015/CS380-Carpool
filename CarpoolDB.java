import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 * Class manages the "carpools" & "carpoolPassenger" tables in the database
 */
public class CarpoolDB {

	// Variables
	private Connection con;
	private List<Carpool> carpools;
	//private DBSortingUtils sortingUtils;
	
	
	/**
	 * Constructor of class. Manages and retrieves Carpools.
	 * @param con
	 */
	public CarpoolDB(Connection con) {
		this.con = con;
		carpools = new ArrayList<>();
		//this.sortingUtils = new DBSortingUtils(con);
	}
	
	
	/**
	 * Function adds Carpool to "carpools" table in database
	 * @param carpoolId - The Carpool's ID
	 * @param driverId - The Driver's ID
	 * @param passengerIDs - Are passenger IDs of attendants 
	 * @param passengerLimit - Total passengers Carpool can carry
	 * @param pickupLocation - Pickup location for Carpool
	 * @param destination - Destination for Carpool
	 * @param pickupDate - Pickup date for Carpool
	 * @param pickupTime - Pickup time for Carpool
	 * @param status - Carpool's availability 
	 * @throws SQLException
	 */
	public void addCarpool(int carpoolId, int driverId, int passengerLimit, List<Integer> passengerIDs,
		String pickupLocation, String destination, String pickupDate, String pickupTime, Boolean status) throws SQLException {
		
		// Command to check if Carpool ID exists
		String query = "SELECT * FROM carpools WHERE carpoolId = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId);
		ResultSet rslt = pstmt.executeQuery();
		
		// Checks if Carpool exist
		if (!rslt.next()) {
			
			// Adds Carpool to database
			query = "INSERT INTO carpools (carpoolId, driverId, passengerLimit, pickupLocation, destination, pickupDate, pickupTime, carpoolStatus) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, carpoolId);
			pstmt.setInt(2, driverId);
			pstmt.setInt(3, passengerLimit);
			pstmt.setString(4, pickupLocation);
			pstmt.setString(5, destination);
			pstmt.setString(6, pickupDate);
			pstmt.setString(7, pickupTime);
			pstmt.setBoolean(8, status);
			pstmt.executeUpdate(); 
			
			// Insert passenger IDs
			if (passengerIDs != null) {	
				for (int ids:passengerIDs) {
					query = "insert into carpoolPassenger (carpoolId, passengerId) values (?, ?)";
					pstmt = con.prepareStatement(query);
					pstmt.setInt(1, carpoolId);
					pstmt.setInt(2, ids);
					pstmt.executeUpdate(); 
				}
			}
			
		} else {
			// Carpool already exist
			System.out.println("Carpool already exist"); 
		}
		
	}

	
	/**
	 * Function deletes selected Carpool from database 
	 * @param carpoolId - The Carpool's ID
	 * @throws SQLException
	 */
	public void deleteCarpool(int carpoolId) throws SQLException {
		
		// Deletes a Carpool from "carpools" table
		String query = "DELETE FROM carpools WHERE carpoolId = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId);
		int rowsAffected = pstmt.executeUpdate();
				
		// Checks If changes occur
		if (rowsAffected > 0) {
			// Carpool has been deleted
			System.out.println("Deleted from 1st Table!"); 
		} else {
			// Carpool does not exist
			System.out.println("Carpool Doesn't exist!"); 
		}
		
		// Delete Carpool passengers from "carpoolpassenger" table
		query = "DELETE FROM carpoolpassenger WHERE carpoolId = ?";
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId);
		rowsAffected = pstmt.executeUpdate(); 
		
		// Checks If changes occur
		if (rowsAffected > 0) {
			// Carpool passengers deleted
			System.out.println("Deleted from 2nd DataBase Table!"); 
		} else {
			// Carpool passengers do not exist OR no passengers exist
			System.out.println("Changes already applied OR did not exist!"); 
		}
		
	}
	
	
	/**
	 * Function Updates the main aspects of the Carpool
	 * @param carpoolId - The Carpool's ID
	 * @param passengerLimit - Total passengers Carpool can carry
	 * @param pickupLocation - Pickup location for Carpool
	 * @param destination - Destination for Carpool
	 * @param pickupDate - Pickup date for Carpool
	 * @param pickupTime - Pickup time for Carpool
	 * @throws SQLException
	 */
	public void updateCarpool(int carpoolId, int passengerLimit, String pickupLocation,
		String destination, String pickupDate, String pickupTime) throws SQLException {
		
		// Selected Carpool is updated
		String query = "UPDATE carpools SET passengerLimit = ?, pickupLocation = ?, destination = ?, pickupDate = ?, pickupTime = ? "
			+ "WHERE carpoolId = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, passengerLimit);
		pstmt.setString(2, pickupLocation);
		pstmt.setString(3, destination);
		pstmt.setString(4, pickupDate);
		pstmt.setString(5, pickupTime);
		pstmt.setInt(6, carpoolId);
		int rowsAffected = pstmt.executeUpdate();		
		
		// Checks If changes occur
		if (rowsAffected > 0) {
			// Carpool has been updated
			System.out.println("Carpool has been Updated!");			
		} else {
			// Carpool doesn't exist
			System.out.println("Carpool does not exist!"); 
		}
	}
	
	
	/**
	 * Function updates carpools ArrayList.
	 * @throws SQLException
	 */
	public void updateCarpoolList() throws SQLException {
		
		// Clears current ArrayList
		carpools.clear();
		
		// Command selects every carpool in database 
		String query = "SELECT * FROM carpools";
		Statement stmt = con.createStatement();
		ResultSet rslt = stmt.executeQuery(query);
		
		// Gets and stores Carpools
		while (rslt.next()) {
			int carpoolId = rslt.getInt("carpoolId");
		    int driverId = rslt.getInt("driverId");
		    int passengerLimit = rslt.getInt("passengerLimit");
		    String pickupLocation = rslt.getString("pickupLocation");
		    String destination = rslt.getString("destination");
		    String pickupDate = rslt.getString("pickupDate");
		    String pickupTime = rslt.getString("pickupTime");
		    Boolean carpoolStatus = rslt.getBoolean("carpoolStatus");
		    
		    // Gets and stores the Carpool's passengers
		    String query2 = "select * from carpoolpassenger where carpoolId = ?";
		    PreparedStatement pstmt = con.prepareStatement(query2);
		    pstmt.setInt(1, carpoolId);
			ResultSet rsltPassengers = pstmt.executeQuery();
			
			List<Integer> passengerIds = new ArrayList<Integer>();
			while (rsltPassengers.next()) {
				int passengerId = rsltPassengers.getInt("passengerId");
				passengerIds.add(passengerId);
			}
		    
			// Creates & Adds Carpool to ArrayList
		    Carpool currCarpool = new Carpool (carpoolId, driverId, passengerLimit, passengerIds, 
		    		pickupLocation, destination, pickupDate, pickupTime, carpoolStatus);
		    carpools.add(currCarpool);
		    
		}
		
	}
	
	
	/**
	 * Function returns an ArrayList of all carpools.
	 * @return ArrayList of Carpools
	 * @throws SQLException
	 */
	public List<Carpool> getCarpoolList() throws SQLException {
		updateCarpoolList(); // Updates Carpool ArrayList
		return carpools;
	}
	
	
	/**
	 * Function returns an ArrayList of Carpools where user ID is a driver.
	 * @param userId - The User's ID
	 * @return an arrayList of Carpools that User ID is in as a driver
	 * @throws SQLException
	 */
	public List<Carpool> getCarpoolsAsDriver(int userId) throws SQLException {
		
		// Updates Carpool ArrayList
		updateCarpoolList();
		
		// Populates and returns array
		List<Carpool> hosted = new ArrayList<Carpool>();
		for (Carpool element:carpools) {
			if ( userId == element.getDriverID()) {
				hosted.add(element);
			}
		}
		return hosted;
	}
	
	
	/**
	 * Function returns an ArrayList of carpools where user ID is a passenger.
	 * @param userId - The User's ID
	 * @return an arrayList of Carpools that User ID is in as a passenger
	 * @throws SQLException
	 */
	public List<Carpool> getCarpoolsAsPassenger(int userId) throws SQLException {
		
		// Updates Carpool ArrayList
		updateCarpoolList();
		
		// Populates and returns array
		List<Carpool> asPassenger = new ArrayList<Carpool>();
		for (Carpool element:carpools) {
			for (Integer passengers:element.getPassengerIDs()) {
				if (userId == passengers ) {
					asPassenger.add(element);
				}
			}
		}
		return asPassenger;
	}
	
	
	/**
	 * Function Updates Carpool Status based on attending Passengers.
	 * @param carpoolId - The Carpool's ID
	 * @throws SQLException
	 */
	public void updatePassengerStatus(int carpoolId) throws SQLException {
		
		// Command to update CarpoolStatus. If passengers > limit, sets false.
		String query = "UPDATE carpools AS cp "
			+ "SET carpoolStatus = ("
			+ "SELECT COUNT(*) > cp.passengerLimit "
			+ "FROM carpoolPassenger "
			+ "WHERE carpoolId = ?)"
			+ "WHERE cp.carpoolId = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId); 
		pstmt.setInt(2, carpoolId); 
		pstmt.executeUpdate();
		System.out.println("Success!");
	}
	
	
	/**
	 * Function adds passenger to Carpool
	 * @param carpoolId - The Carpool's ID
	 * @param passengerId - The Passenger's ID
	 * @throws SQLException
	 */
	public void addPassenger(int carpoolId, int passengerId) throws SQLException  {
		
		// Command to insert passenger to Carpool
		String query = "SELECT * FROM carpoolpassenger "
				+ "WHERE carpoolId = ? AND passengerId = ?"; 
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId);
		pstmt.setInt(2, passengerId);
		ResultSet rslt = pstmt.executeQuery();
		
		// Checks if Passenger exist
		if (!rslt.next()) {
			
			// Passenger added to Carpool
			query = "INSERT INTO carpoolpassenger(carpoolId, passengerId) VALUES (?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, carpoolId);
			pstmt.setInt(2, passengerId);
			pstmt.executeUpdate();
			
			// Updates Carpool Status
			updatePassengerStatus(passengerId);
			
		} else {
			// Passenger already Exist
			System.out.println("Passenger already exist"); 
		}		
		
	}
	
	
	/**
	 * Function deletes passenger from Carpool
	 * @param carpoolId - The Carpool's ID
	 * @param passengerId - The Passenger's ID
	 * @throws SQLException
	 */
	public void deletePassenger(int carpoolId, int passengerId) throws SQLException  {
		
		// Command to delete passenger from Carpool
		String query = "DELETE FROM carpoolpassenger WHERE carpoolId = ? AND passengerId = ?"; 
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId);
		pstmt.setInt(2, passengerId);
		int rowsAffected = pstmt.executeUpdate();
			
		if (rowsAffected > 0) {
			// Passenger was deleted
			updatePassengerStatus(passengerId);
		} else {
			// Passenger does not exist
			System.out.println("Carpool Does not Exist"); 
		}
		
	}
	
}