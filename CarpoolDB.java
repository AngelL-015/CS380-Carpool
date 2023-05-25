import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class CarpoolDB {

	// Variables
	private Connection con;
	private List<Carpool> carpools; // Stores all existing carpools in database
	//private DatabaseSortingUtils sortingUtils; // May Add Later, Difficult To handle through SQL
	
	public CarpoolDB(Connection con) {
		this.con = con;
		carpools = new ArrayList<>();
		//this.sortingUtils = new DatabaseSortingUtils(con);
	}
	
	// Adds a carpool
	public void addCarpool(int carpoolId, int driverId, int passengerLimit, List<Integer> passengerIDs,
		String pickupLocation, String destination, String pickupDate, String pickupTime, Boolean status) throws SQLException {
		
		// Command to check if Carpool ID exists
		String query = "SELECT * FROM carpools WHERE carpoolId = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId);
		ResultSet rslt = pstmt.executeQuery();
		
		if (!rslt.next()) {
			
			// Carpool does not exist, adds a new carpool
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

	// Deletes a carpool
	public void deleteCarpool(int carpoolId) throws SQLException {
		// Command to delete a Carpool from carpools table(1st table)
		String query = "DELETE FROM carpools WHERE carpoolId = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId);
		int rowsAffected = pstmt.executeUpdate();
				
		// Checks if Carpool ID is in use
		if (rowsAffected > 0) {
			// Carpool associated with id is deleted
			System.out.println("Deleted from 1st DataBase Table!"); 
		} else {
			// Carpool does not exist
			System.out.println("Carpool Does not exist DataBase!"); 
		}
		
		// Command to delete a Carpool from carpoolpassenger(2nd table)
		query = "DELETE FROM carpoolpassenger WHERE carpoolId = ?";
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId);
		rowsAffected = pstmt.executeUpdate(); 
		
		if (rowsAffected > 0) {
			// Carpool associated with id is deleted
			System.out.println("Deleted from 2nd DataBase Table!"); 
		} else {
			// Carpool does not exist
			System.out.println("Carpool does not exist OR there are no passengers!"); 
		}
		
	}
	
	// Updates a carpool, only the certain variables
	public void updateCarpool(int carpoolId, int passengerLimit, String pickupLocation,
		String destination, String pickupDate, String pickupTime) throws SQLException {
		
		// Command to get Carpool // Populate & execute query
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
		
		if (rowsAffected > 0) {
			// Carpool has been updated
			System.out.println("Carpool has been Updated!");			
		} else {
			// Carpool does not exist
			System.out.println("Carpool does not exist!"); 
		}
	}
	
	// Gets Car pool List // Empties list each call to get most updated // Change here?
	public void updateCarpoolList() throws SQLException {
		
		// Clears current ArrayList
		carpools.clear();
		
		// Command to select every carpool 
		String query = "SELECT * FROM carpools";
		Statement stmt = con.createStatement();
		ResultSet rslt = stmt.executeQuery(query);
		
		// Checks if User exist
		while (rslt.next()) {
			int carpoolId = rslt.getInt("carpoolId");
		    int driverId = rslt.getInt("driverId");
		    int passengerLimit = rslt.getInt("passengerLimit");
		    String pickupLocation = rslt.getString("pickupLocation");
		    String destination = rslt.getString("destination");
		    String pickupDate = rslt.getString("pickupDate");
		    String pickupTime = rslt.getString("pickupTime");
		    Boolean carpoolStatus = rslt.getBoolean("carpoolStatus");
		    
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
	
	// Gets carpool List, Can Combine with updateCarpoolList() if wanted
	public List<Carpool> getCarpoolList() throws SQLException {
		updateCarpoolList(); // Updates before getting list // Over thinking?
		return carpools;
	}
	
	// Possibly delete The following - Retrieves specific ArrayList 
	public List<Carpool> getCarpoolsAsDriver(int userId) throws SQLException {
		
		updateCarpoolList();
		
		List<Carpool> hosted = new ArrayList<Carpool>();
		
		for (Carpool element:carpools) {
			if ( userId == element.getDriverID()) {
				hosted.add(element);
			}
		}
		
		return hosted;
	}
	
	// Gets Carpools where user is a passenger
	public List<Carpool> getCarpoolsAsPassenger(int userId) throws SQLException {
		
		updateCarpoolList();
		
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
	// Additional Functions - May move to a different class
	
	// Update Passenger status
	public void updatePassengerStatus(int carpoolId) throws SQLException {
		
		// Command to update CarpoolStatus
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
	
	
	// Adds passenger to a carpool
	public void addPassenger(int carpoolId, int passengerId) throws SQLException  {
		
		// Command to insert Carpool passenger
		String query = "SELECT * FROM carpoolpassenger "
				+ "WHERE carpoolId = ? AND passengerId = ?"; 
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId);
		pstmt.setInt(2, passengerId);
		ResultSet rslt = pstmt.executeQuery();
		
		if (!rslt.next()) {
			// Carpool passenger added
			query = "INSERT INTO carpoolpassenger(carpoolId, passengerId) VALUES (?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, carpoolId);
			pstmt.setInt(2, passengerId);
			pstmt.executeUpdate();
			
			// Updates Carpool Limit Status
			updatePassengerStatus(passengerId);
		} else {
			// Carpool passenger already Exist
			System.out.println("Carpool passenger already exist"); 
		}		
		
	}
	
	
	// Deletes passenger from carpool
	public void deletePassenger(int carpoolId, int passengerId) throws SQLException  {
		
		// Command to delete Carpool passenger
		String query = "DELETE FROM carpoolpassenger WHERE carpoolId = ? AND passengerId = ?"; 
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, carpoolId);
		pstmt.setInt(2, passengerId);
		int rowsAffected = pstmt.executeUpdate();
			
		if (rowsAffected > 0) {
			// Carpool was found and deleted
			updatePassengerStatus(passengerId);
		} else {
			// Carpool does not exist
			System.out.println("Carpool Does not Exist"); 
		}
		
	}
	
		

}
