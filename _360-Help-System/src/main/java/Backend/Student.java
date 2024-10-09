package Backend;

/*******
 * <p> Student Class </p>
 *
 * <p> Description: This class defines the student role in the system.
 * It extends the User class and handles functionalities specific to
 * a student's role in the help system. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class Student extends User {

    // Initializes a Student object by calling the default User constructor and setting student-specific privileges.
    public Student() {
        super();  // Calls the default constructor of User
        this.isStudent = true;  // Set Student-specific roles
    }

    // Initializes a Student with specific user details and assigns the Student role.
    public Student(String username, String password, String email, String firstName, String middleName, String lastName,String preferredName) {
        super(username, password, email, firstName, middleName, lastName,preferredName);  // Calls the parameterized constructor of User
        this.isStudent = true;  // Set Student-specific roles
    }
}