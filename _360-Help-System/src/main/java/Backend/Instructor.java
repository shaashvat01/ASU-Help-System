package Backend;

public class Instructor extends User {
    
    // Additional fields and methods specific to Instructor (if needed)
    
    public Instructor() {
        super();  // Calls the default constructor of User
        this.isInstructor = true;  // Set Instructor-specific roles
    }
    
    public Instructor(String username, String password, String email, String firstName, String middleName, String lastName) {
        super(username, password, email, firstName, middleName, lastName);  // Calls the parameterized constructor of User
        this.isInstructor = true;  // Set Instructor-specific roles
    }
    

}