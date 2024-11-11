package Backend;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static com.example._360helpsystem.CreateAdminAccount.SECRET_KEY;


public class Encryption {

    public SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Key size in bits
        return keyGenerator.generateKey();
    }

    public void encryptBody(Article article) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
        byte[] iv = cipher.getIV();
        byte[] ciphertext = cipher.doFinal(article.getBody().getBytes());

        // Use Base64 encoding for both IV and ciphertext
        article.setIv(Base64.getEncoder().encodeToString(iv));
        article.setBody(Base64.getEncoder().encodeToString(ciphertext));
    }

    public String decryptArticle(Article article) throws Exception {
        byte[] encryptedBody = Base64.getDecoder().decode(article.getBody());
        byte[] iv = Base64.getDecoder().decode(article.getIv());

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY, spec);

        byte[] decryptedBody = cipher.doFinal(encryptedBody);
        return new String(decryptedBody);
    }
}
