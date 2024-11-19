package Backend;

/*******
 * <p> Encryption Class </p>
 *
 * <p> Description: This class provides methods to encrypt and decrypt the body of an article.
 * It uses a simple character shift encryption mechanism for demonstration purposes. </p>
 *
 * @version 1.00, 2024-11-19
 * @author Team - Th15
 *
 */

public class Encryption {

    // Encrypts the body of the given article
    public void encryptBody(Article article) {
        StringBuilder encryptedString = new StringBuilder();
        String input = article.getBody();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            // Shift the character by the specified number
            char encryptedChar = (char) (currentChar + 23);
            encryptedString.append(encryptedChar);
        }

        article.setBody(encryptedString.toString());
    }

    // Decrypts the body of the given article
    public String decryptBody(Article article) {
        StringBuilder decryptedString = new StringBuilder();
        String input = article.getBody();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            // Shift the character back by the specified number (subtract 23)
            char decryptedChar = (char) (currentChar - 23);
            decryptedString.append(decryptedChar);
        }

        return decryptedString.toString();
    }
}
