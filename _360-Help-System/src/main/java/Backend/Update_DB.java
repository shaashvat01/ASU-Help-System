package Backend;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static com.example._360helpsystem.CreateAdminAccount.*;

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
    private final String path_to_UserDB = "Users-DB.txt";
    private final String path_to_OTPDB = "OTPs-DB.txt";
    private final String path_to_ArticleDB = "Articles-DB.txt";
    private final String path_to_GroupDB = "Groups-DB.txt";
    private final String path_to_BackupDB = "Backups-DB.txt";
    private final String path_to_genericMsgDB = "GenericMsgs-DB.txt";
    private final String path_to_searchHistory = "History-DB.txt";
    private final String path_to_requestsDB = "Requests-DB.txt";
    private final String path_to_futureArticleDB = "FutureArticles-DB.txt";
    private final String path_to_key = "Key-DB.txt";

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
        File articleDBFile = new File(path_to_ArticleDB);
        if (articleDBFile.exists()) { // Check if the file exists
            try (BufferedReader reader = new BufferedReader(new FileReader(articleDBFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        break; // Stop reading if a blank line is encountered
                    }
                    String[] data = line.split("-");
                    if (data.length == 11) {
                        long UID = Long.parseLong(data[0]);
                        String level = data[1];
                        String security = data[2];
                        String author = data[3];
                        String title = data[4];
                        String abstractText = data[5];
                        String keywords = data[6];
                        String body = data[7];
                        String links = data[8];
                        String group = data[9];
                        String iv = data[10];

                        Article article = new Article(UID, title, author, level, security, abstractText, keywords, body, links, group);
                        article.setIv(iv);
                        articleL.addArticle(article);
                        System.out.println("Article added to article database: " + article.getTitle() + " - " + article.getKeywords());

                    } else {
                        System.out.println("Data length mismatch. Expected 11, found: " + data.length);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading article database: " + e.getMessage());
            }
        } else {
            // File doesn't exist; leave articleL empty
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

    public void loadGrpDB(GroupList grpList) {
        File grpDBFile = new File(path_to_GroupDB);
        if (grpDBFile.exists()) { // Check if the file exists
            try (BufferedReader reader = new BufferedReader(new FileReader(grpDBFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Parse the group name
                    int nameEndIndex = line.indexOf(", [");
                    String name = line.substring(0, nameEndIndex);

                    // Parse the users list
                    int usersStartIndex = nameEndIndex + 2;
                    int usersEndIndex = line.indexOf("], [");
                    String usersPart = line.substring(usersStartIndex, usersEndIndex + 1).replaceAll("[\\[\\]]", "").trim();
                    ArrayList<String> users = new ArrayList<>();
                    if (!usersPart.isEmpty()) {
                        for (String user : usersPart.split(", ")) {
                            users.add(user.trim()); // Assuming `User` has a constructor that takes a username
                        }
                    }

                    // Parse the admins list
                    int adminsStartIndex = usersEndIndex + 4;
                    int adminsEndIndex = line.lastIndexOf("] -");
                    String adminsPart = line.substring(adminsStartIndex, adminsEndIndex + 1).replaceAll("[\\[\\]]", "").trim();
                    ArrayList<String> admins = new ArrayList<>();
                    if (!adminsPart.isEmpty()) {
                        admins.addAll(Arrays.asList(adminsPart.split(", ")));
                    }

                    // Parse the special flag
                    boolean isSpecial = Boolean.parseBoolean(line.substring(adminsEndIndex + 3).trim());

                    // Create a Group object and add it to grpList
                    Group group = new Group(name, isSpecial, users, admins);
                    grpList.addGroup(group);
                }
            } catch (IOException e) {
                System.out.println("Error loading Group database: " + e.getMessage());
            }
        } else {
            // File doesn't exist; initialize with a default group if needed
            System.out.println("Group database file does not exist. Starting with an empty GroupList.");
            grpList.addGroup(new Group("General", false));
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
            // Write every article in the list
            for (Article article : articleL) {
                System.out.println("Saving  " + article.getTitle() +" + "+ article.getKeywords());
                writer.write(article.getUID() + "-" +
                        article.getLevel() + "-" +
                        article.getSecurity() + "-" +
                        article.getAuthor() + "-" +
                        article.getTitle() + "-" +
                        article.getAbs() + "-" +
                        article.getKeywords() + "-" +
                        article.getBody() + "-" +
                        article.getLinks() + "-" +
                        article.getGroup() + "-" +
                        article.getIv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving article database: " + e.getMessage());
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

    public void saveGrpDB(GroupList grpList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path_to_GroupDB, false))) { // Set append to false
            for (Group grp : grpList) { // Accessing OTP_LIST directly from OTPList class
                writer.write(grp.getName());
                writer.write(String.join(", ", grp.getUsers().toString()));
                writer.write(String.join(", ", grp.getAdmins().toString()));
                writer.write("-" + grp.isSpecial());
                writer.newLine(); // Add a new line after each OTP
            }
        } catch (IOException e) {
            System.out.println("Error saving Group database: " + e.getMessage());
        }
    }

    public void writeBackup(String fileName, List<String> selectedGroups) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {// Set append to false
            for (Article article : ARTICLE_LIST) {
                for (String selectedGroup : selectedGroups) {
                    if(article.hasGroup(selectedGroup))
                    {
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
                        break;
                    }
                }

            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkDupBackup(String fileName) {
        File backupDBFile = new File(path_to_BackupDB);

        // First check if the file exists and contains the fileName
        if (backupDBFile.exists()) { // If BackupDB.txt exists
            try (BufferedReader reader = new BufferedReader(new FileReader(backupDBFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String backupName = line.trim();
                    if (backupName.equals(fileName)) {
                        return true;  // Duplicate found, return true
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading Backup database: " + e.getMessage());
            }
        }

        // Append the new file name to the BackupDB.txt, either if it doesn't exist or if no duplicate was found
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path_to_BackupDB, true))) { // Append mode
            writer.write(fileName);
            writer.newLine();  // Make sure to add a newline after the filename
        } catch (IOException ex) {
            throw new RuntimeException("Error writing to BackupDB.txt: " + ex.getMessage());
        }

        return false;  // No duplicate found, new filename added to the file
    }

    public List<String> getBackupList()
    {
        List<String> backupList = new ArrayList<>();

        File backupDBFile = new File(path_to_BackupDB);

        // First check if the file exists and contains the fileName
        if (backupDBFile.exists()) { // If BackupDB.txt exists
            try (BufferedReader reader = new BufferedReader(new FileReader(backupDBFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String backupName = line.trim();
                    backupList.add(backupName);
                }
                return backupList;
            } catch (IOException e) {
                System.out.println("Error loading Backup database: " + e.getMessage());
            }
        }
        return backupList;
    }

    public ArticleList readBackup(String fileName) {
        File backupDBFile = new File(fileName);
        if (backupDBFile.exists()) {
            ArticleList articleList = new ArticleList();
            try (BufferedReader reader = new BufferedReader(new FileReader(backupDBFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        break; // Stop reading if a blank line is encountered
                    }
                    String[] data = line.split("-");
                    if (data.length == 10) {
                        long UID = Long.parseLong(data[0]);
                        String level = data[1];
                        String security = data[2];
                        String author = data[3];
                        String title = data[4];
                        String abstractText = data[5];
                        String keywords = data[6];
                        String body = data[7];
                        String links = data[8];
                        String group = data[9];

                        Article article = new Article(UID, title, author, level, security, abstractText, keywords, body, links, group);
                        articleList.addArticle(article);
                    } else {
                        System.out.println("Data length mismatch. Expected 10, found: " + data.length);
                    }
                }
                return articleList;
            } catch (IOException e) {
                System.out.println("Error loading backup database: " + e.getMessage());
            }
        } else {

            System.out.println("Backup database file does not exist");
        }
        return null;
    }

    public void writeToMsgDB(String message) {
        try (FileWriter writer = new FileWriter(path_to_genericMsgDB, true)) {  // true enables append mode
            writer.write(message + System.lineSeparator());  // Write message with a newline at the end
        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace if an error occurs
        }
    }

    public void storeSearch(String search)
    {
        try (FileWriter writer = new FileWriter(path_to_searchHistory, true)) {  // true enables append mode
            writer.write(search + System.lineSeparator());  // Write message with a newline at the end
        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace if an error occurs
        }
    }

    public void clearSearchHistory() {
        try (FileWriter writer = new FileWriter(path_to_searchHistory)) {
        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace if an error occurs
        }
    }

    public void saveSearchHistory(String message)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(path_to_searchHistory));
             FileWriter writer = new FileWriter(path_to_futureArticleDB, true)) {  // true enables append mode
            writer.write("Help Message : "+message + System.lineSeparator());
            writer.write("User's search history - "+ System.lineSeparator());
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + System.lineSeparator());  // Append each line with a newline
            }
            writer.write("--------------------------------------------------------" + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace if an error occurs
        }
    }

    public void storeAccessRequest(User user,Article article) {
        try (FileWriter writer = new FileWriter(path_to_requestsDB, true)) {  // true enables append mode
            writer.write(user.username+"-"+article.getUID()+System.lineSeparator());  // Write message with a newline at the end
        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace if an error occurs
        }
    }

    public void loadKey()
    {
        File keyFile = new File(path_to_key);

        if (keyFile.exists()) { // Check if the key file exists
            try (BufferedReader reader = new BufferedReader(new FileReader(keyFile))) {
                String encodedKey = reader.readLine(); // Read the Base64 encoded key from the first line

                // Decode the Base64 encoded key string
                byte[] decodedKey = Base64.getDecoder().decode(encodedKey);

                // Rebuild the SecretKey from the decoded key bytes
                SECRET_KEY = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

                System.out.println("Secret key loaded successfully from " + path_to_key);

            } catch (IOException e) {
                System.out.println("Error loading secret key: " + e.getMessage());
            }
        } else {
            // If the key file doesn't exist, handle the case accordingly
            System.out.println("Secret key file does not exist. Generating new key file!");


            try (FileWriter writer = new FileWriter(path_to_key, true)) {  // true enables append mode
                SECRET_KEY = generateKey();
                writer.write(Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded())+System.lineSeparator());  // Write message with a newline at the end
            } catch (IOException e) {
                e.printStackTrace();  // Print stack trace if an error occurs
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Key size in bits
        return keyGenerator.generateKey();
    }

    public boolean isFileUnique(String name) {
        name = name + ".txt";
        System.out.println("Checking file name - " + name);

        if (name.equalsIgnoreCase(path_to_ArticleDB)) {
            System.out.println("File name matches path_to_ArticleDB.");
            return false;
        } else if (name.equalsIgnoreCase(path_to_futureArticleDB)) {
            System.out.println("File name matches path_to_futureArticleDB.");
            return false;
        } else if (name.equalsIgnoreCase(path_to_BackupDB)) {
            System.out.println("File name matches path_to_BackupDB.");
            return false;
        } else if (name.equalsIgnoreCase(path_to_UserDB)) {
            System.out.println("File name matches path_to_UserDB.");
            return false;
        } else if (name.equalsIgnoreCase(path_to_OTPDB)) {
            System.out.println("File name matches path_to_OTPDB.");
            return false;
        } else if (name.equalsIgnoreCase(path_to_GroupDB)) {
            System.out.println("File name matches path_to_GroupDB.");
            return false;
        } else if (name.equalsIgnoreCase(path_to_genericMsgDB)) {
            System.out.println("File name matches path_to_genericMsgDB.");
            return false;
        } else if (name.equalsIgnoreCase(path_to_searchHistory)) {
            System.out.println("File name matches path_to_searchHistory.");
            return false;
        } else if (name.equalsIgnoreCase(path_to_requestsDB)) {
            System.out.println("File name matches path_to_requestsDB.");
            return false;
        } else if (name.equalsIgnoreCase(path_to_key)) {
            System.out.println("File name matches path_to_key.");
            return false;
        } else {
            System.out.println("File name is unique.");
            return true;
        }
    }


}




