package Backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OTP_GeneratorTest {

    @Test
    void testGenerateOTP() {
        OTP_Generator otpGenerator = new OTP_Generator();

        // Generate an OTP
        int otp = otpGenerator.generateOTP();

        // Assertions to validate the OTP
        assertTrue(otp >= 100000 && otp <= 999999, "OTP should be a 6-digit number");
    }

    @Test
    void testGenerateOTP_Uniqueness() {
        OTP_Generator otpGenerator = new OTP_Generator();

        // Generate multiple OTPs
        int otp1 = otpGenerator.generateOTP();
        int otp2 = otpGenerator.generateOTP();

        // Ensure OTPs are likely unique (not guaranteed due to randomness)
        assertNotEquals(otp1, otp2, "Two consecutive OTPs should not usually be the same");
    }
}
