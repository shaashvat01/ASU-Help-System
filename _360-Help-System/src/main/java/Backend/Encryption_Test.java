package Backend;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;

public class Encryption_Test {

    public static void main(String[] args) throws Exception {
        String plaintext = "Hello, World!";

        // Generate a secret key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Key size in bits
        SecretKey secretKey = keyGenerator.generateKey();

        // Encrypt the data
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes());

        // Decrypt the data
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(128, iv));
        byte[] decryptedText = cipher.doFinal(ciphertext);

        // Print results
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext (Base64): " + Base64.getEncoder().encodeToString(ciphertext));
        System.out.println("Decrypted text: " + new String(decryptedText));
    }
}
