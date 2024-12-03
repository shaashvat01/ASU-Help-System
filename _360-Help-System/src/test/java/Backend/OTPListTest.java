package Backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OTPListTest {

    @Test
    public void testAddOTP() {
        OTPList otpList = new OTPList();
        otpList.addOTP(12345);
        assertTrue(otpList.findOTP(12345));
    }

    @Test
    public void testRemoveOTP() {
        OTPList otpList = new OTPList();
        otpList.addOTP(54321);
        assertTrue(otpList.removeOTP(54321));
        assertFalse(otpList.findOTP(54321));
    }

    @Test
    public void testCheckOTPExistence() {
        OTPList otpList = new OTPList();
        otpList.addOTP(11111);
        otpList.addOTP(22222);
        assertTrue(otpList.findOTP(11111));
        assertFalse(otpList.findOTP(33333));
    }

    @Test
    public void testGetOTPCount() {
        OTPList otpList = new OTPList();
        otpList.addOTP(10101);
        otpList.addOTP(20202);
        assertEquals(2, otpList.getOTPCount());
    }

    @Test
    public void testRetrieveAllOTPs() {
        OTPList otpList = new OTPList();
        otpList.addOTP(10101);
        otpList.addOTP(20202);
        assertEquals(2, otpList.getOTPList().size());
        assertTrue(otpList.getOTPList().contains(10101));
        assertTrue(otpList.getOTPList().contains(20202));
    }
}