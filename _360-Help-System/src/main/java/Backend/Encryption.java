package Backend;


public class Encryption {

    public void encryptBody(Article article)
    {
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
