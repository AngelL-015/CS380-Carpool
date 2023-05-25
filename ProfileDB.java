import java.sql.*;
/**
 * Controls login/signup of User
 */
public class ProfileDB {
	
	// Connection
	private static Connection con;
	
	// Constructor
	public ProfileDB(Connection con) {
		this.con = con;
	}
	
	// Log In Function
	public User logIn(String username, String password) throws SQLException { 
		
		// Command to get profile
		String query = "select * from profiles where username = ? and password = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rslt = pstmt.executeQuery();
            
        // Checks if User exist
        if (rslt.next()) {
        	int userId = rslt.getInt("passengerId");
            String firstName = rslt.getString("firstName");
            String lastName = rslt.getString("lastName");
            String email = rslt.getString("email");
            String phone = rslt.getString("phoneNumber");
            String address = rslt.getString("address");
            // Returns User // Unsure to save username & password to User object
            return new User(userId, firstName, lastName, email, phone, address);
        } else {
            // User does not exist
            System.out.println("Does Not Exist");
            return null;
        }
        
	}
	
	// Sign Up Function
	public User signUp(int userId, String firstName, String lastName, String email, 
		String phone, String username, String password, String address) throws SQLException { 
		
		// Command to get profile
		String query = "select * from profiles where username = ? and password = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rslt = pstmt.executeQuery();
		            
		// Checks If User Exist
		if (!rslt.next()) {
			// Insert new User to database
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
		    // User does not exist // Change for GUI
		    System.out.println("User already Exist");
		    return null;
		}	
		
	}

	// Gets name of Id
	public static String getIdName(int userId) throws SQLException {
		String query = "SELECT * FROM profiles WHERE passengerId = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, userId);
        ResultSet rslt = pstmt.executeQuery();
	
        if (rslt.next()) {
        	String firstName = rslt.getString("firstName");
            String lastName = rslt.getString("lastName");
            return firstName + " " + lastName;
        } else {
        	return null;
        }
        
	}
}
