package Backend;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*******
 * <p> Update_DB Class </p>
 *
 * <p> Description: This class handles loading and saving user and OTP databases.
 * It reads from and writes to text files, managing user and OTP information for
 * the system. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class Update_DB {
    private String path_to_UserDB = "Users.txt";
    private String path_to_OTPDB = "OTPs.txt";

    // Load the user database from the file into UserList
    public void loadUserDB(UserList userL) {
        File userDBFile = new File(path_to_UserDB);
        if (userDBFile.exists()) { // Check if the file exists
            try (BufferedReader reader = new BufferedReader(new FileReader(userDBFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split("-"); // Split by "-"
                    if (data.length == 9) { // Now expecting 9 fields with middle name included
                        String username = data[0];
                        String password = data[1];
                        String firstName = data[2];
                        String middleName = data[3]; // Middle name added here
                        String lastName = data[4];
                        String preferredName = data[5];
                        String email = data[6];
                        String role = data[7]; // S, I, A (or combinations like SI)
                        int accResetOTP = Integer.parseInt(data[8]);

                        User user;

                        // Create the user object based on the role
                        if (role.equalsIgnoreCase("S")) {
                            user = new Student(username, password, email, firstName, middleName, lastName, preferredName);
                        }
                        else if (role.equalsIgnoreCase("I")) {
                            user = new Instructor(username, password, email, firstName, middleName, lastName, preferredName);
                        }
                        else if (role.equalsIgnoreCase("A")) {
                            user = new Admin(username, password, email, firstName, middleName, lastName, preferredName);
                        }
                        else {
                            user = new User(username, password, email, firstName, middleName, lastName, preferredName);
                        }

                        // Assign the OTP
                        user.setAccOTP(accResetOTP);

                        // Add the user to UserList
                        userL.addUser(user);
                    }
                }
            }
            catch (IOException e) {
                System.out.println("Error loading user database: " + e.getMessage());
            }
        }
        else {
            // File doesn't exist; leave userL empty
            System.out.println("User database file does not exist. Starting with an empty UserList.");
        }
    }

    // Load the OTP database from the file into OTPList
    public void loadOTPDB(OTPList otpList) {
        File otpDBFile = new File(path_to_OTPDB);
        if (otpDBFile.exists()) { // Check if the file exists
            try (BufferedReader reader = new BufferedReader(new FileReader(otpDBFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    int otp = Integer.parseInt(line.trim()); // Parse the OTP as an integer
                    otpList.addOTP(otp); // Add the OTP to the list
                }
            } catch (IOException e) {
                System.out.println("Error loading OTP database: " + e.getMessage());
            }
        } else {
            // File doesn't exist; leave OTP_LIST empty
            System.out.println("OTP database file does not exist. Starting with an empty OTPList.");
        }
    }

    // Save the user database to the file (overwrite if exists)
    public void saveUserDB(UserList userL) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path_to_UserDB, false))) { // Set append to false
            for (User user : userL.getUserList()) { // Accessing userL directly from UserList class
                // Determine the role(s)
                String role = "";
                if (user.isStudent()) role += "S";
                if (user.isInstructor()) role += "I";
                if (user.isAdmin()) role += "A";

                // Format: username-password-firstname-middlename-lastname-preferredname-email-role-accResetOTP
                writer.write(user.getUserName() + "-" +
                        user.getPassword() + "-" +
                        user.getFirstName() + "-" +
                        user.getMiddleName() + "-" + // Now including middle name
                        user.getLastName() + "-" +
                        user.getPreferredName() + "-" +
                        user.getEmail() + "-" +
                        role + "-" +
                        user.getAccOTP());
                writer.newLine(); // Add a new line after each user
            }
        } catch (IOException e) {
            System.out.println("Error saving user database: " + e.getMessage());
        }
    }

    // Save the OTP database to the file (overwrite if exists)
    public void saveOTPDB(OTPList otpList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path_to_OTPDB, false))) { // Set append to false
            for (Integer otp : otpList.getOTPList()) { // Accessing OTP_LIST directly from OTPList class
                writer.write(otp.toString());
                writer.newLine(); // Add a new line after each OTP
            }
        } catch (IOException e) {
            System.out.println("Error saving OTP database: " + e.getMessage());
        }
    }
}