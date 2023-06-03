import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class manages the "profiles" table in the database
 * Class helps user sign-up & login to their Profile.
 */
public class ProfileDB {
	
	// Connection
	private static Connection con;
	private List<User> profiles;
	
	/**
	 * Constructor of Class. Establishes Connection.
	 * @param con
	 */
	public ProfileDB(Connection con) {
		this.con = con;
		profiles = new ArrayList<>();
	}
	
	/**
	 * Function for user to Login to an existing Profile
	 * @param username - User's username
	 * @param password - User's password
	 * @return User's Profile
	 * @throws SQLException
	 */
	public User logIn(String username, String password) throws SQLException { 
		
		// Command to get Profile
		String query = "select * from profiles where username = ? and password = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rslt = pstmt.executeQuery();
            
        // Checks if Profile exist
        if (rslt.next()) {
        	// Retrieves Profile
        	int userId = rslt.getInt("passengerId");
            String firstName = rslt.getString("firstName");
            String lastName = rslt.getString("lastName");
            String email = rslt.getString("email");
            String phone = rslt.getString("phoneNumber");
            String address = rslt.getString("address");
            return new User(userId, firstName, lastName, email, phone, address);
        } else {
            // Profile doesn't exist
            System.out.println("Profile does not Exist");
            return null;
        }
        
	}
	
	/**
	 * Function creates an Profile for User
	 * @param userId - The User's ID
	 * @param firstName - The User's first name
	 * @param lastName - The User's last name
	 * @param email - The User's email
	 * @param phone - The User's phone number
	 * @param username - The User's username
	 * @param password - The User's password
	 * @param address - The User's address
	 * @return User's created Profile
	 * @throws SQLException
	 */
	public User signUp(int userId, String firstName, String lastName, String email, 
		String phone, String username, String password, String address) throws SQLException { 
		
		// Command to get Profile
		String query = "select * from profiles where username = ? and password = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rslt = pstmt.executeQuery();
		            
		// Checks if Profile Exist
		if (!rslt.next()) {
			// Insert new Profile to database
			query = "insert into profiles"
				+ "(passengerId, firstName, lastName, email, phoneNumber, username, password, address)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			pstmt.setString(6, username);
			pstmt.setString(7, password);
			pstmt.setString(8, address);
			pstmt.executeUpdate(); 
			
			// Logs user in after signing up
			return logIn(username, password);
		} else {
		    // User does not exist
		    System.out.println("User already Exist");
		    return null;
		}	
		
	}
	
	// Function may need to be a Static Function
	// May not be needed or incorrect place
	/**
	 * Functions gets User's name
	 * @param userId - The User's ID
	 * @return String name
	 * @throws SQLException
	 */
	public static String getNameById(int userId) throws SQLException {
		
		// Command to select a profile
		String query = "SELECT * FROM profiles WHERE passengerId = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, userId);
        ResultSet rslt = pstmt.executeQuery();
	
        // Checks if Profile Exist
        if (rslt.next()) {
        	// Returns User's Name
        	String firstName = rslt.getString("firstName");
            String lastName = rslt.getString("lastName");
            return firstName + " " + lastName;
        } else {
        	// Returns nothing
        	return null;
        }
        
	}
	
	
	/**
	 * Function Populates the profile array list
	 * @throws SQLException
	 */
	public void populateProfiles() throws SQLException {
		// Clears current ArrayList
		profiles.clear();
				
		// Command selects every profile in database 
		String query = "SELECT * FROM profiles";
		Statement stmt = con.createStatement();
		ResultSet rslt = stmt.executeQuery(query);
				
		// Gets and stores User
		while (rslt.next()) {
			int passengerId = rslt.getInt("passengerId");
			String firstName = rslt.getString("firstName");
			String lastName = rslt.getString("lastName");
			String email = rslt.getString("email");
			String phoneNumber = rslt.getString("phoneNumber");
			String address = rslt.getString("address");
		    
			// Creates & Adds User to ArrayList
		    User user = new User (passengerId, firstName, lastName, email, phoneNumber, address);
			    profiles.add(user);
		}
	}
	
	// Getter
	public List<User> getProfileList() throws SQLException {
		// Updates profile and returns profile
		populateProfiles();
		return profiles;
	}
	
	
	
	
	
}
