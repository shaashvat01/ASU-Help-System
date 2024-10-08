package Backend;

import java.util.LinkedList;

public class OTPList {
    private LinkedList<Integer> OTP_LIST;

    public OTPList() {
        OTP_LIST = new LinkedList<>();
    }

    // Add an OTP to the list
    public void addOTP(int otp) {
        OTP_LIST.add(otp);
    }

    // Remove an OTP from the list
    public boolean removeOTP(int otp) {
        if (OTP_LIST.contains(otp)) { // Check if OTP exists in the list
            OTP_LIST.remove(Integer.valueOf(otp)); // Remove the OTP
            return true;
        }
        else
        {
            return false;
        }
    }

    // Get the number of OTPs in the list
    public int getOTPCount() {
        return OTP_LIST.size(); // Return the size of OTP_LIST
    }

    // Find an OTP in the list
    public boolean findOTP(int otp) {
        for (Integer storedOtp : OTP_LIST) {
            if (storedOtp.equals(otp)) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<Integer> getOTPList() {
        return OTP_LIST; // Return the entire list
    }

    //debug function
    public String toString()
    {
        for(Integer otp : OTP_LIST)
        {
            System.out.println(otp);
        }
        return null;
    }
}