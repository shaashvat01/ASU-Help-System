package Backend;
import java.util.Random;

/*******
 * <p> OTP_Generator Class </p>
 *
 * <p> Description: This class is responsible for generating One-Time Passwords (OTPs).
 * The OTP is a 6-digit number, randomly generated for secure access. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

// This method generates and returns a random 6-digit OTP.
public class OTP_Generator
{
    Random rand = new Random();

    public int generateOTP()
    {
        return 100000 + rand.nextInt(900000);
    }

}
