package Testing;

import Backend.Article;
import Backend.Encryption;

public class Encryption_Tester {

    static int numPassed = 0;
    static int numFailed = 0;

    public static void main(String[] args) {
        System.out.println("____________________________________________________________________________");
        System.out.println("\nTesting Encryption Functionality");

        // Perform test cases
        performTestCaseEncrypt(1, "This is a test body for encryption.");
        performTestCaseDecrypt(2, "This is another test body for decryption.");

        System.out.println("____________________________________________________________________________");
        System.out.println();
        System.out.println("Number of tests passed: " + numPassed);
        System.out.println("Number of tests failed: " + numFailed);
    }

    /**
     * Test case for encrypting an article body.
     */
    private static void performTestCaseEncrypt(int testCase, String body) {
        System.out.println("____________________________________________________________________________\n\nTest case (Encrypt): " + testCase);
        System.out.println("Original Body: \"" + body + "\"");
        System.out.println("______________");

        Article article = new Article(1, "Test Title", "Author", "Beginner", "Public",
                "Test Abstract", "Test Keywords", body, "Test Links", "Group");
        Encryption encryption = new Encryption();

        // Encrypt the article body
        encryption.encryptBody(article);

        String encryptedBody = article.getBody();
        System.out.println("Encrypted Body: \"" + encryptedBody + "\"");

        // Validate that the body is encrypted
        if (!body.equals(encryptedBody)) {
            System.out.println("***Success*** The article body was successfully encrypted.");
            numPassed++;
        } else {
            System.out.println("***Failure*** The article body encryption failed.");
            numFailed++;
        }
    }

    /**
     * Test case for decrypting an article body.
     */
    private static void performTestCaseDecrypt(int testCase, String body) {
        System.out.println("____________________________________________________________________________\n\nTest case (Decrypt): " + testCase);

        Article article = new Article(1, "Test Title", "Author", "Beginner", "Public",
                "Test Abstract", "Test Keywords", body, "Test Links", "Group");
        Encryption encryption = new Encryption();

        // Encrypt the article body first
        encryption.encryptBody(article);
        String encryptedBody = article.getBody();
        System.out.println("Original (Encrypted) Body: \"" + encryptedBody + "\"");

        // Decrypt the article body
        String decryptedBody = encryption.decryptBody(article);
        System.out.println("Decrypted Body: \"" + decryptedBody + "\"");

        // Validate that the decrypted body matches the original input
        if (body.equals(decryptedBody)) {
            System.out.println("***Success*** The article body was successfully decrypted.");
            numPassed++;
        } else {
            System.out.println("***Failure*** The article body decryption failed.");
            numFailed++;
        }
    }
}