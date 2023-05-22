import java.sql.*;

public class ProfileDB {
	
	// Variables, Getter, and Setters Remain other changes are made
	// Remember to delete!
	
	// Variables 
	//private String username;
	//private String password;
	private Connection con;
	
	// Constructor
	public ProfileDB(Connection con) {
		//this.username = username;
		//this.password = password;
		this.con = con;
	}
	
	// Log In Function
	public User logIn(String username, String password) throws SQLException { 
		
		// Command to get profile // Populate & execute query
		String query = "select * from profile where username = ? and password = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet user = pstmt.executeQuery();
            
        // Checks if User exist
        if (user.next()) {
        	int userId = user.getInt("passengerId");
            String firstName = user.getString("firstName");
            String lastName = user.getString("lastName");
            String email = user.getString("email");
            String phone = user.getString("phoneNumber");
            String address = user.getString("address");
            // Returns User // Unsure to save username & password to User object
            return new User(userId, firstName, lastName, email, phone, address);
        } else {
            // User does not exist // Change for GUI
            System.out.print("Does Not Exist");
            return null;
        }
        
	}
	
	// Sign Up Function
	public User signUp(int userId, String firstName, String lastName, String email, 
		String phone, String username, String password, String address) throws SQLException { 
		
		// Command to get profile // Populate & execute query
		String query = "select * from profile where username = ? and password = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet user = pstmt.executeQuery();
		            
		// Checks If User Exist
		if (!user.next()) {
			// Insert new User to database
			query = "insert into profile"
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
	/**
	// Getters & Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	**/
}
