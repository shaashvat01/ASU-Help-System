package Backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the OTP_Generator class.
 *
 * This class validates the functionality of the OTP generation,
 * ensuring the OTPs are 6-digit numbers and exhibit reasonable uniqueness.
 */
class OTP_GeneratorTest {

    /**
     * Test case: Validate the OTP generation format.
     *
     * This test ensures that the generated OTP is a 6-digit number
     * within the range of 100000 to 999999.
     */
    @Test
    void testGenerateOTP() {
        OTP_Generator otpGenerator = new OTP_Generator();

        // Generate an OTP
        int otp = otpGenerator.generateOTP();

        // Assertion to validate the OTP range
        assertTrue(otp >= 100000 && otp <= 999999, "OTP should be a 6-digit number");
    }

    /**
     * Test case: Validate OTP uniqueness.
     *
     * This test ensures that two consecutively generated OTPs are not the same,
     * confirming reasonable randomness in generation.
     */
    @Test
    void testGenerateOTP_Uniqueness() {
        OTP_Generator otpGenerator = new OTP_Generator();

        // Generate two OTPs
        int otp1 = otpGenerator.generateOTP();
        int otp2 = otpGenerator.generateOTP();

        // Assertion to ensure uniqueness (not guaranteed but expected in most cases)
        assertNotEquals(otp1, otp2, "Two consecutive OTPs should not usually be the same");
    }
}
