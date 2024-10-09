package Backend;

/*******
 * <p> Instructor Class </p>
 *
 * <p> Description: This class defines the instructor role in the system.
 * It extends the User class and handles functionalities specific to
 * an instructor's role in the help system. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

// Initializes an Instructor object by calling the default User constructor and setting instructor-specific privileges.
public class Instructor extends User {
    
    public Instructor() {
        super();  // Calls the default constructor of User
        this.isInstructor = true;  // Set Instructor-specific roles
    }

    // Initializes an Instructor with specific user details and assigns the Instructor role.
    public Instructor(String username, String password, String email, String firstName, String middleName, String lastName,String preferredName) {
        super(username, password, email, firstName, middleName, lastName,preferredName);  // Calls the parameterized constructor of User
        this.isInstructor = true;  // Set Instructor-specific roles
    }
}