package Backend;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
    private final String path_to_UserDB = "Users.txt";
    private final String path_to_OTPDB = "OTPs.txt";
    private final String path_to_ArticleDB = "Articles.txt";

    // Load the user database from the file into UserList
    public void loadUserDB(UserList userL) {
        File userDBFile = new File(path_to_UserDB);
        if (userDBFile.exists()) { // Check if the file exists
            try (BufferedReader reader = new BufferedReader(new FileReader(userDBFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        break; // Stop reading if a blank line is encountered
                    }
                    String[] data = line.split("-"); // Split by "-"
                    if (data.length == 10) { // Now expecting 9 fields with middle name included
                        String username = data[0];
                        String password = data[1];
                        String firstName = data[2];
                        String middleName = data[3]; // Middle name added here
                        String lastName = data[4];
                        String preferredName = data[5];
                        String email = data[6];
                        String role = data[7]; // S, I, A (or combinations like SI)
                        int accResetOTP = Integer.parseInt(data[8]);
                        boolean setupDone = Boolean.parseBoolean(data[9]);

                        User user = null;

                        // Create the user object based on the role
                        if (role.equalsIgnoreCase("A")) {
                            user = new Admin(username, password, email, firstName, middleName, lastName, preferredName);
                        }
                        if (role.equalsIgnoreCase("S") && role.equalsIgnoreCase("I")) {
                            user = new Student(username, password, email, firstName, middleName, lastName, preferredName);
                            user.setInstructor(true);
                        } else {
                            if (role.equalsIgnoreCase("S")) {
                                user = new Student(username, password, email, firstName, middleName, lastName, preferredName);
                            }
                            if (role.equalsIgnoreCase("I")) {
                                user = new Instructor(username, password, email, firstName, middleName, lastName, preferredName);
                            }
                        }

                        // Assign the OTP
                        if (user != null) {
                            user.setAccOTP(accResetOTP);
                            if (setupDone) {
                                user.finishAccountSetup();
                            }
                        }
                        // Add the user to UserList

                        userL.addUser(user);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading user database: " + e.getMessage());
            }
        } else {
            // File doesn't exist; leave userL empty
            System.out.println("User database file does not exist. Starting with an empty UserList.");
        }
    }

    public void loadArticleDB(ArticleList articleL) {
        File userDBFile = new File(path_to_ArticleDB);
        if (userDBFile.exists()) { // Check if the file exists
            try (BufferedReader reader = new BufferedReader(new FileReader(userDBFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        break; // Stop reading if a blank line is encountered
                    }
                    String[] data = line.split("-"); // Split by "-"
                    if (data.length == 10) {
                        long UID = Long.parseLong(data[0]);
                        String level = data[1];
                        String security = data[2];
                        String author = data[3];
                        String title = data[4];
                        String abstractText = data[5];
                        String keywordString = data[6];
                        ArrayList<String> keywords = new ArrayList<>();
                        // Split the keywordString by commas and add each keyword to the ArrayList
                        String[] keywordArray = keywordString.split(","); // Assuming commas are used to separate keywords
                        for (String keyword : keywordArray) {
                            keywords.add(keyword.trim()); // trim() removes any leading/trailing spaces
                        }
                        String body = data[7];
                        String links = data[8];
                        String group = data[9];

                        Article article = new Article(UID, title, author, level, security, abstractText, keywords, body, links, group);
                        articleL.addArticle(article);
                    }
                }
            }catch (IOException e) {
                System.out.println("Error loading article database: " + e.getMessage());
            }
        } else {
            // File doesn't exist; leave userL empty
            System.out.println("Article database file does not exist. Starting with an empty ArticleList.");
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
                if (user.isStudent()) {
                    role += "S";
                    System.out.println("USer is student");
                }
                if (user.isInstructor()) {
                    role += "I";
                    System.out.println("USer is instructor");
                }
                if (user.isAdmin()) {
                    role += "A";
                    System.out.println("USer is admin");
                }

                // Format: username-password-firstname-middlename-lastname-preferredname-email-role-accResetOTP
                writer.write(user.getUserName() + "-" +
                        user.getPassword() + "-" +
                        user.getFirstName() + "-" +
                        user.getMiddleName() + "-" + // Now including middle name
                        user.getLastName() + "-" +
                        user.getPreferredName() + "-" +
                        user.getEmail() + "-" +
                        role + "-" +
                        user.getAccOTP() + "-" +
                        user.isAccountSetupDone());
                writer.newLine(); // Add a new line after each user
            }
        } catch (IOException e) {
            System.out.println("Error saving user database: " + e.getMessage());
        }
    }

    public void saveArticleDB(ArticleList articleL) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path_to_ArticleDB, false))) {
            for (Article article : articleL.getArticles()) {
                writer.write(article.getUID() + "-" +
                        article.getLevel() + "-" +
                        article.getSecurity() + "-" +
                        article.getAuthor() + "-" +
                        article.getTitle() + "-" +
                        article.getAbs() + "-" +
                        article.getKeywords() + "-" +
                        article.getBody() + "-" +
                        article.getLinks() + "-" +
                        article.getGroup());
                writer.newLine();
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

