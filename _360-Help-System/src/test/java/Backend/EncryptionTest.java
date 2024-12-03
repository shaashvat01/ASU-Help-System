package Backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EncryptionTest {

    @Test
    public void testEncryptBody() {
        Article article = new Article(1, "Test Title", "Author", "Beginner", "Public",
                "Test Abstract", "Test Keywords", "Test Body", "Test Links", "Group");
        Encryption encryption = new Encryption();
        encryption.encryptBody(article);

        assertNotEquals("Test Body", article.getBody());
    }

    @Test
    public void testDecryptBody() {
        Article article = new Article(1, "Test Title", "Author", "Beginner", "Public",
                "Test Abstract", "Test Keywords", "Test Body", "Test Links", "Group");
        Encryption encryption = new Encryption();
        encryption.encryptBody(article);
        String decryptedBody = encryption.decryptBody(article);

        assertEquals("Test Body", decryptedBody);
    }

    @Test
    public void testHandleEmptyBody() {
        Article article = new Article(1, "Empty Title", "Author", "Beginner", "Public",
                "Empty Abstract", "Empty Keywords", "", "Empty Links", "Group");
        Encryption encryption = new Encryption();
        encryption.encryptBody(article);

        assertEquals("", encryption.decryptBody(article));
    }
}