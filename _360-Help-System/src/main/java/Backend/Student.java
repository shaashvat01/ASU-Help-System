package Backend;

public class Student extends User {
    
    // Additional fields and methods specific to Student (if needed)
    
    public Student() {
        super();  // Calls the default constructor of User
        this.isStudent = true;  // Set Student-specific roles
    }
    
    public Student(String username, String password, String email, String firstName, String middleName, String lastName,String preferredName) {
        super(username, password, email, firstName, middleName, lastName,preferredName);  // Calls the parameterized constructor of User
        this.isStudent = true;  // Set Student-specific roles
    }
    

}