import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class manages the "reviews" table in the database
 * Adds, Deletes and Updates a review
 */
public class ReviewDB {
	
	// Variables
	private Connection con;
	private List<Review> reviews;
	
	/**
	 * Constructor of Class
	 * @param con - Used to establish connection to database
	 */
	public ReviewDB(Connection con) {
		this.con = con;
		reviews = new ArrayList<>();
	}
	
	/**
	 * Function adds review to "reviews" table in database
	 * @param driverId - The host's ID
	 * @param passengerId - The passenger's ID
	 * @param rating - Passenger's rating for Driver
	 * @param comment - Passenger's Review for Driver
	 * @throws SQLException
	 */
	public void addReview(int driverId, int passengerId, int rating, String comment) throws SQLException {
			
			// Checks if Review for user exists
			String query = "SELECT * FROM reviews WHERE driverId = ? AND passengerId = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, driverId);
			pstmt.setInt(2, passengerId);
			ResultSet rslt = pstmt.executeQuery();
			
			// Inserts new review
			if (!rslt.next()) {
				
				query = "INSERT INTO reviews (driverId, passengerId, rating, comment) "
					+ "VALUES (?, ?, ?, ?)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, driverId);
				pstmt.setInt(2, passengerId);
				pstmt.setInt(3, rating);
				pstmt.setString(4, comment);
				pstmt.executeUpdate(); // Executes Changes
				
			} else {
				// Review Already Exist
				System.out.println("Review already exist"); 
			}
			
		}

	/**
	 * Function deletes a review from "reviews" table in database
	 * @param driverId - The host's ID
	 * @param passengerId - The passenger's ID
	 * @throws SQLException
	 */
	public void deleteCarpool(int driverId, int passengerId) throws SQLException {
		
		// Command to delete selected review
		String query = "DELETE FROM reviews WHERE driverId = ? AND passengerId = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, driverId);
		pstmt.setInt(1, passengerId);
		int rowsAffected = pstmt.executeUpdate(); // Executes Changes
				
		if (rowsAffected > 0) {
			// Review Deleted
			System.out.println("Deleted review!"); 
		} else {
			// Review does not Exist
			System.out.println("Review Doesn't Exist!"); 
		}
		
	}

	/**
	 * Function edits a review from "reviews" table in database
	 * @param driverId - The host's ID
	 * @param passengerId - The passenger's ID
	 * @param rating - Passenger's rating for Driver
	 * @param comment - Passenger's Review for Driver
	 * @throws SQLException
	 */
	public void updateCarpool(int driverId, int passengerId, int rating, String comment) throws SQLException {
			
			// Command to updates selected review
			String query = "UPDATE reviews SET rating = ?, comment = ? "
				+ "WHERE driverId = ? AND passengerId = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, rating);
			pstmt.setString(2, comment);
			pstmt.setInt(3, driverId);
			pstmt.setInt(4, passengerId);
			int rowsAffected = pstmt.executeUpdate(); // Execute Changes		
			
			if (rowsAffected > 0) {
				// Review has been Updated
				System.out.println("Review has been Updated!");			
			} else {
				// Review does not Exist
				System.out.println("Review Doesn't Exist!"); 
			}
		}

	
	/**
	 * Function Populates the reviews array list
	 * @throws SQLException
	 */
	public void populateReviews() throws SQLException {
		// Clears current ArrayList
		reviews.clear();
				
		// Command selects every profile in database 
		String query = "SELECT * FROM profiles";
		Statement stmt = con.createStatement();
		ResultSet rslt = stmt.executeQuery(query);
				
		// Gets and stores Review
		while (rslt.next()) {
			int driverId = rslt.getInt("driverId");
			int passengerId = rslt.getInt("passengerId");
			int rating = rslt.getInt("rating");
			String comment = rslt.getString("comment");
		    
			// Creates & Adds Review to ArrayList
		    Review review = new Review(driverId, passengerId, rating, comment);
			    reviews.add(review);
		}
	}
	
	// Getter
	public List<Review> getReviewList() throws SQLException {
		// Updates reviews and returns reviews
		populateReviews();
		return reviews;
	}
	
}
