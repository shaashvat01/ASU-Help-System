package Backend;
import java.util.Random;

public class OTP_Generator
{
    Random rand = new Random();

    public int generateOTP()
    {
        return 100000 + rand.nextInt(900000);
    }

}
