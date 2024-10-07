package Backend;

public class User {
	
	// Private variables to store user information
	protected String username;
	protected String password;
	protected String email;
	protected String firstName;
	protected String middleName;
	protected String lastName;
	protected boolean isStudent;
	protected boolean isAdmin;
	protected boolean isInstructor;
	protected boolean isAccountSetupComplete;
	
	// Default constructor that initializes all fields with default values
	public User() {
		this.username = "";
		this.password = "";
		this.email = "";
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
		this.isStudent = false;
		this.isAdmin = false;
		this.isInstructor = false;
		this.isAccountSetupComplete = false;
	}
	
	// Parameterized constructor to initialize a user with specific values
	public User(String username, String password, String email, String firstName, String middleName, String lastName) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.isStudent = false;  // User role defaults to none (admin/student/instructor must be set later)
		this.isAdmin = false;
		this.isInstructor = false;
		this.isAccountSetupComplete = false;  // Account setup is incomplete by default
	}
	
	// Getter and setter methods for the username
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getUserName() {
		return this.username;
	}
	
	// Getter and setter methods for the password
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	// Getter and setter methods for the email
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	// Getter and setter methods for the first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	// Getter and setter methods for the middle name
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getMiddleName() {
		return this.middleName;
	}
	
	// Getter and setter methods for the last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	// Marks the account setup as completed
	public void finishAccountSetup() {
		this.isAccountSetupComplete = true;
	}
	
	// Returns whether the account setup has been completed
	public boolean isAccountSetupDone() {
		return this.isAccountSetupComplete;
	}
	
	// Getter method to check if the user is an admin
	public boolean isAdmin() {
		return this.isAdmin;
	}
	
	// Getter method to check if the user is a student
	public boolean isStudent() {
		return this.isStudent;
	}
	
	// Getter method to check if the user is an instructor
	public boolean isInstructor() {
		return this.isInstructor;
	}
	
	// Setter method to make the user an admin
	public void setAdmin() {
		this.isAdmin = true;
	}
	
	// Setter method to make the user an instructor
	public void setInstructor() {
		this.isInstructor = true;
	}
	
	// Setter method to make the user a student
	public void setStudent() {
		this.isStudent = true;
	}
}