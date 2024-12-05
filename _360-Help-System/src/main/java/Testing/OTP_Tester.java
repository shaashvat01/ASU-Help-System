package Testing;

import Backend.OTPList;
import Backend.OTP_Generator;

public class OTP_Tester {

    static int numPassed = 0;
    static int numFailed = 0;

    public static void main(String[] args) {
        System.out.println("____________________________________________________________________________");
        System.out.println("\nTesting OTP Functionality");

        // Create OTPList instance for testing
        OTPList otpList = new OTPList();
        OTP_Generator otpGenerator = new OTP_Generator();

        // Perform test cases
        performTestCaseFind(1, otpGenerator.generateOTP(), otpList, true);  // Test OTP existence (valid)
        performTestCaseFind(2, 12345, otpList, false);  // Test OTP existence (invalid)
        performTestCaseFind(3, otpGenerator.generateOTP(), otpList, true);  // Test OTP existence (valid)
        performTestCaseFind(4, 54321, otpList, false);  // Test OTP existence (invalid)
        performTestCaseRemove(5, otpGenerator.generateOTP(), otpList);     // Test OTP removal (valid)

        System.out.println("____________________________________________________________________________");
        System.out.println();
        System.out.println("Number of tests passed: " + numPassed);
        System.out.println("Number of tests failed: " + numFailed);
    }

    /**
     * Test case for validating OTP existence.
     */
    private static void performTestCaseFind(int testCase, int otp, OTPList otpList, boolean expectedValid) {
        System.out.println("____________________________________________________________________________\n\nTest case (Find): " + testCase);
        System.out.println("Generated OTP: " + otp);
        System.out.println("______________");

        // Add the OTP to the list if expected to be valid
        if (expectedValid) {
            otpList.addOTP(otp);
        }

        boolean isValid = otpList.findOTP(otp);

        if (isValid == expectedValid) {
            System.out.println("***Success*** The OTP <" + otp + "> validation result is correct.");
            numPassed++;
        } else {
            System.out.println("***Failure*** The OTP <" + otp + "> validation result is incorrect.");
            numFailed++;
        }

        System.out.println();
        displayEvaluation(otpList);
    }

    /**
     * Test case for removing an OTP.
     */
    /**
     * Test case for removing an OTP, showing the updated list before and after removal.
     */
    private static void performTestCaseRemove(int testCase, int otp, OTPList otpList) {
        System.out.println("____________________________________________________________________________\n\nTest case (Remove): " + testCase);
        System.out.println("Generated OTP: " + otp);
        System.out.println("______________");

        // Add the OTP to the list
        otpList.addOTP(otp);
        System.out.println("Updated OTP List after adding OTP: " + otpList.getOTPList());

        // Remove the OTP and validate
        boolean isRemoved = otpList.removeOTP(otp);
        System.out.println("Updated OTP List after removing OTP: " + otpList.getOTPList());

        boolean isStillPresent = otpList.findOTP(otp);

        if (isRemoved && !isStillPresent) {
            System.out.println("***Success*** The OTP <" + otp + "> was removed successfully.");
            numPassed++;
        } else {
            System.out.println("***Failure*** The OTP <" + otp + "> removal failed.");
            numFailed++;
        }

        System.out.println();
        displayEvaluation(otpList);
    }


    /**
     * Display the current state of the OTP list.
     */
    private static void displayEvaluation(OTPList otpList) {
        System.out.println("Current OTP List: " + otpList.getOTPList());
        System.out.println("Number of OTPs in the list: " + otpList.getOTPCount());
    }
}