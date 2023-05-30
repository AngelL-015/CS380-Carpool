/**
 * Class to build User
 */
public class User {
	
	// Variables
	private int Id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	
	/**
	 * Constructor to build User
	 * @param Id - The User's ID
	 * @param firstName - The User's first name
	 * @param lastName - The User's last name
	 * @param email - The User's email
	 * @param phone - The User's phone number
	 * @param address  - The User's address
	 */
	public User(int Id, String firstName, String lastName, String email, String phone, String address) {
		this.Id = Id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	// Getters & Setters
	public int getID() {
		return Id;
	}

	public void setID(int Id) {
		this.Id = Id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	public String getAddress() {
		return address;
	}
	

	public void setAddress(String address) {
		this.address = address;
	}

}
